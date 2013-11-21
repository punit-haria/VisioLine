package data;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeMap;

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
	private boolean errorDectected;
	// change to whatever data structure is most suitable
	private ArrayList<Line> lines;
	private int errorScore;

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
		errorScore = 0;
		errorDectected = false;
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

		// variable for storing changes information
		ArrayList<TreeMap<Date, String>> lineChanges = new ArrayList<TreeMap<Date, String>>(
				result.size());
		ArrayList<TreeMap<Date, String>> lineCommits = new ArrayList<TreeMap<Date, String>>(
				result.size());
		ArrayList<HashSet<String>> changesLog = new ArrayList<HashSet<String>>();

		// initialize
		for (int i = 0; i < result.size(); ++i) {
			RevCommit currentCommit = blame.getSourceCommit(i);
			PersonIdent currentAuthor = blame.getSourceCommitter(i);

			TreeMap<Date, String> changes = new TreeMap<Date, String>();
			TreeMap<Date, String> commits = new TreeMap<Date, String>();
			HashSet<String> addedCommits = new HashSet<String>();

			if (pre_check_line(currentCommit, currentAuthor,
					result.getString(i))) {
				Date date = currentAuthor.getWhen();
				changes.put(date, currentAuthor.getName());
				commits.put(date, currentCommit.getName());
				addedCommits.add(currentCommit.getName());
			} else {
				System.out.println("missing line info: " + i + " for: "
						+ commitID.getName());
				errorDectected = true;
				errorScore++;
			}
			lineChanges.add(changes);
			lineCommits.add(commits);
			changesLog.add(addedCommits);
		}

		// getting number of changes information
		RevWalk revWalk = new RevWalk(repo);
		RevCommit root = revWalk.parseCommit(commitID);
		revWalk.markStart(root);
		revWalk.setTreeFilter(AndTreeFilter.create(PathFilter.create(fileName),
				TreeFilter.ANY_DIFF));

		for (RevCommit currentCommit : revWalk) {
			commitNum++;

			// text for older elements
			blamer.setStartCommit(currentCommit);
			BlameResult blameOld = blamer.call();

			if (blameOld == null) {
				errorScore += 5;
				errorDectected = true;
				System.out.println("Cannot blame file: " + fileName
						+ " for commit: " + currentCommit.getName());
				continue;
			}
			RawText text = blameOld.getResultContents();

			// old new
			EditList diffList = getEditList(text, result);

			for (Edit edits : diffList) {
				final int start = edits.getBeginB();
				final int end = edits.getEndB();
				final int startOld = edits.getBeginA();
				final int endOld = edits.getEndA();

				int indexOld = startOld;
				for (int i = start; i < end; ++i) {
					if (endOld <= indexOld) {
						break;
					}
					RevCommit oldCommit = blameOld.getSourceCommit(indexOld);
					if (oldCommit == null) {
						errorScore++;
						System.out.println("missing line info: " + i + " for: "
								+ currentCommit.getName());
						errorDectected = true;
						break;
					}
					PersonIdent oldAuthor = blameOld.getSourceCommitter(indexOld);
					Date date = oldAuthor.getWhen();
					
					// check last element and add if not the same
					// commit
					if (!changesLog.get(i).contains(oldCommit.getName())) {
						changesLog.get(i).add(oldCommit.getName());
						lineChanges.get(i).put(date, oldAuthor.getName());
						lineCommits.get(i).put(date, oldCommit.getName());
					}
					indexOld++;
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
			lines.add(new Line(new ArrayList<String>(lineCommits.get(i).values()),
					new ArrayList<String>(lineChanges.get(i).values()), i, result
							.getString(i), type));
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
	 * Check line for correctness to prevent errors
	 */
	private boolean pre_check_line(RevCommit currentCommit,
			PersonIdent currentAuthor, String string) {
		if (currentCommit == null || currentAuthor == null || string == null) {
			// System.out.println("missing line");
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
	 * return if error was detected when processing file
	 */
	public boolean getErrorStatus() {
		return errorDectected;
	}

	/*
	 * return integer representing the amount or errors detected when processing file
	 */
	public int getErrorScore() {
		return errorScore;
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
