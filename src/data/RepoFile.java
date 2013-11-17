package data;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.jgit.api.BlameCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.blame.BlameResult;
import org.eclipse.jgit.diff.Edit;
import org.eclipse.jgit.diff.EditList;
import org.eclipse.jgit.diff.HistogramDiff;
import org.eclipse.jgit.diff.RawText;
import org.eclipse.jgit.diff.RawTextComparator;
import org.eclipse.jgit.errors.AmbiguousObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.filter.AndTreeFilter;
import org.eclipse.jgit.treewalk.filter.PathFilter;
import org.eclipse.jgit.treewalk.filter.TreeFilter;

public class RepoFile implements Iterable<Line>, Serializable {

	/**
	 * serialization ID
	 */
	private static final long serialVersionUID = 5528537749976601214L;

	private String fileName;
	private String commitId;
	private int commitNum;
	// change to whatever data structure is most suitable
	private ArrayList<Line> lines;

	/*
	 * @param name is a the files relative path
	 * 
	 * @param commitId must be a commit within this file's history otherwise
	 * this function will result in an error.
	 * 
	 * Setup the code-base to be used for analysis
	 */
	public RepoFile(FileRepository repo, String name, String commitId)
			throws GitAPIException, RevisionSyntaxException,
			AmbiguousObjectException, IncorrectObjectTypeException, IOException {
		this.fileName = name;
		this.commitId = commitId;
		commitNum = 0;
		// create list of line objects
		this.lines = populateLineInfo(repo);
	}

	/*
	 * Populates lines Array with the information retrieved from the code-base
	 */
	private ArrayList<Line> populateLineInfo(FileRepository repo)
			throws RevisionSyntaxException, MissingObjectException,
			IncorrectObjectTypeException, AmbiguousObjectException,
			IOException, GitAPIException {

		// obtain blame information
		BlameCommand blamer = new BlameCommand(repo);
		ObjectId commitID = repo.resolve(commitId);
		blamer.setStartCommit(commitID);
		blamer.setFilePath(fileName);
		BlameResult blame = blamer.call();
		RawText result = blame.getResultContents();

		// getting number of changes information
		RevWalk revWalk = new RevWalk(repo);
		RevCommit root = revWalk.parseCommit(commitID);
		revWalk.markStart(root);
		revWalk.setTreeFilter(AndTreeFilter.create(PathFilter.create(fileName),
				TreeFilter.ANY_DIFF));

		// variable for storing changes information
		ArrayList<ArrayList<String>> lineChanges = new ArrayList<ArrayList<String>>(
				result.size());
		ArrayList<ArrayList<String>> lineCommits = new ArrayList<ArrayList<String>>(
				result.size());
		HashMap<Integer, String> changesLog = new HashMap<Integer, String>();

		// initialize
		for (int i = 0; i < result.size(); ++i) {
			RevCommit currentCommit = blame.getSourceCommit(i);
			PersonIdent currentAuthor = blame.getSourceAuthor(i);

			ArrayList<String> changes = new ArrayList<String>();
			ArrayList<String> commits = new ArrayList<String>();

			if (pre_check_line(currentCommit, currentAuthor,
					result.getString(i))) {
				changes.add(currentAuthor.getName());
				commits.add(currentCommit.getName());
			}

			lineChanges.add(changes);
			lineCommits.add(commits);
		}

		for (RevCommit currentCommit : revWalk) {
			commitNum++;
			RawText text = getText(blamer, currentCommit);
			// old new
			EditList diffList = getEditList(text, result);

			for (Edit edits : diffList) {
				int start = edits.getBeginB();
				int end = edits.getEndB();
				int startOld = edits.getBeginA();
				int endOld = edits.getEndA();

				PersonIdent oldAuthor = blame.getSourceAuthor(startOld);
				RevCommit oldCommit = blame.getSourceCommit(startOld);

				if (pre_check_line(oldCommit, oldAuthor,
						text.getString(startOld))) {

					String changesStr = "";
					for (int i = startOld; i < endOld; ++i) {
						changesStr = changesStr + text.getString(i) + "\n";
					}

					if (changesLog.containsKey(start)) {
						if (!changesLog.get(start).equals(changesStr)) {
							changesLog.put(start, changesStr);
							for (int i = start; i < end; ++i) {
								// check last element and add if not the same commit
								if (!lineCommits.get(i)
										.get(lineCommits.get(i).size() - 1)
										.equals(oldCommit.getName())) {
									lineChanges.get(i).add(oldAuthor.getName());
									lineCommits.get(i).add(oldCommit.getName());
								}
							}
						}
					} else {
						changesLog.put(start, changesStr);
						for (int i = start; i < end; ++i) {
							// check last element and add if not the same commit
							if (!lineCommits.get(i)
									.get(lineCommits.get(i).size() - 1)
									.equals(oldCommit.getName())) {
								lineChanges.get(i).add(oldAuthor.getName());
								lineCommits.get(i).add(oldCommit.getName());
							}
						}
					}
				}
			}
		}

		// populate list
		ArrayList<Line> lines = new ArrayList<Line>(result.size());

		for (int i = 0; i < result.size(); ++i) {
			String currentStr = result.getString(i);
			int type = Line.FUNCTION_TYPE;
			// determine linetype
			if (currentStr.matches("^import")) {
				type = Line.IMPORT_TYPE;
			}
			// @TODO check use regex for this
			else if (currentStr.matches("^\\s*$")) {
				type = Line.WHITESPACE_TYPE;
			} else if (currentStr.matches("^\\s*//")
					|| currentStr.matches("^\\s*\\*")
					|| currentStr.matches("^\\s*/\\*")) {
				type = Line.COMMENT_TYPE;
			}

			// if can make a line make it
			lines.add(new Line(lineCommits.get(i), lineChanges.get(i), i,
					result.getString(i), type));
			// TODO add correct line type\
		}
		return lines;
	}

	/*
	 * Helper method to list the differences in a file between two points in
	 * time
	 */
	private EditList getEditList(RawText textOld, RawText textNew)
			throws GitAPIException, IOException {
		EditList diffList = new EditList();
		diffList.addAll(new HistogramDiff().diff(RawTextComparator.DEFAULT,
				textOld, textNew));
		return diffList;
	}

	/*
	 * Returns the raw text of a file from a specific commit
	 */
	private RawText getText(BlameCommand blamer, RevCommit commit)
			throws GitAPIException {
		// text for older elements
		blamer.setFilePath(fileName);
		blamer.setStartCommit(commit);
		BlameResult result = blamer.call();
		return result.getResultContents();
	}

	/*
	 * Check line for correctness to prevent errors
	 */
	private boolean pre_check_line(RevCommit currentCommit,
			PersonIdent currentAuthor, String string) {
		if (currentCommit == null || currentAuthor == null || string == null) {
			return false;
		} else
			return true;
	}

	/*
	 * returns number of lines in file
	 */
	public int size() {
		return lines.size();
	}

	/*
	 * creates an iterable Line Object
	 */
	public Iterator<Line> iterator() {
		return lines.iterator();
	}

	/*
	 * return file name of files
	 */
	public String getFileName() {
		return fileName;
	}

	/*
	 * return number of commits
	 */
	public int getCommitNum() {
		return commitNum;
	}

	/*
	 * return commitId given
	 */
	public String getCommitId() {
		return commitId;
	}

	/*
	 * Helper method that compares two RepoFiles
	 */
	public static class Comparators {

		public static Comparator<RepoFile> COMMITS = new Comparator<RepoFile>() {
			@Override
			public int compare(RepoFile f1, RepoFile f2) {
				return f1.commitNum - f2.commitNum;
			}
		};

	}
}
