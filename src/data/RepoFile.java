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
	 * Populates lines Array.
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
			lineChanges.get(i).add(currentAuthor.getName());
			lineCommits.get(i).add(currentCommit.getName());
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
				String currentAuthor = blame.getSourceAuthor(startOld)
						.getName();
				String currentCommitID = blame.getSourceCommit(startOld)
						.getName();

				String changesStr = "";
				for (int i = startOld; i < endOld; ++i) {
					changesStr = changesStr + text.getString(i) + "\n";
				}

				if (changesLog.containsKey(start)) {
					if (!changesLog.get(start).equals(changesStr)) {
						changesLog.put(start, changesStr);
						for (int i = start; i < end; ++i) {
							lineChanges.get(i).add(currentAuthor);
							lineCommits.get(i).add(currentCommitID);
						}
					}
				} else {
					changesLog.put(start, changesStr);
					for (int i = start; i < end; ++i) {
						lineChanges.get(i).add(currentAuthor);
						lineCommits.get(i).add(currentCommitID);
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

	@Override
	public Iterator<Line> iterator() {
		return lines.iterator();
	}

	/*
	 * returns file name of files
	 */
	public String getFileName() {
		return fileName;
	}

	public int getCommitNum() {
		return commitNum;
	}

	/*
	 * return commitId given
	 */
	public String getCommitId() {
		return commitId;
	}

	public static class Comparators {

		public static Comparator<RepoFile> COMMITS = new Comparator<RepoFile>() {
			@Override
			public int compare(RepoFile f1, RepoFile f2) {
				return f1.commitNum - f2.commitNum;
			}
		};

	}
}
