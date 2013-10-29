package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.jgit.api.BlameCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.blame.BlameResult;
import org.eclipse.jgit.diff.RawText;
import org.eclipse.jgit.errors.AmbiguousObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.filter.AndTreeFilter;
import org.eclipse.jgit.treewalk.filter.PathFilter;
import org.eclipse.jgit.treewalk.filter.TreeFilter;

public class RepoFile implements Iterable<Line> {

	private String fileName;
	private String commitId;
	private HashSet<String> previousCommitIds;

	// change to whatever data structure is most suitable
	private ArrayList<Line> lines;

	/*
	 * @param name is a the files relative path
	 * @param commitId must be a commit within this file's history otherwise
	 * this function will result in an error.
	 */
	public RepoFile(FileRepository repo, String name, String commitId)
			throws GitAPIException, RevisionSyntaxException,
			AmbiguousObjectException, IncorrectObjectTypeException, IOException {
		lines = new ArrayList<Line>();
		this.fileName = name;
		this.commitId = commitId;

		previousCommitIds = getPreviousCommitIDs(repo);
		// create list of line objects
		lines = populateLineInfo(repo);
	}

	/*
	 * Populates line object.
	 */
	private ArrayList<Line> populateLineInfo(FileRepository repo)
			throws RevisionSyntaxException, MissingObjectException,
			IncorrectObjectTypeException, AmbiguousObjectException,
			IOException, GitAPIException {
		// obtain blame information
		BlameCommand blamer = new BlameCommand(repo);
		blamer.setStartCommit(repo.resolve(commitId));
		blamer.setFilePath(fileName);

		BlameResult blame = blamer.call();

		RawText result = blame.getResultContents();
		int numberOfLines = result.size();
		ArrayList<Line> lines = new ArrayList<Line>(numberOfLines);

		for (int i = 0; i < numberOfLines; ++i) {
			RevCommit currentCommit = blame.getSourceCommit(i);
			PersonIdent currentAuthor = blame.getSourceAuthor(i);
			lines.add(new Line(currentCommit.getName(),
					currentAuthor.getName(), i, result.getString(i)));
		}

		return lines;
	}

	/*
	 * Gets all previous commits that file was altered in
	 */
	private HashSet<String> getPreviousCommitIDs(FileRepository repo)
			throws RevisionSyntaxException, MissingObjectException,
			IncorrectObjectTypeException, AmbiguousObjectException, IOException {
		HashSet<String> commits = new HashSet<String>();

		RevWalk revWalk = new RevWalk(repo);
		RevCommit root = revWalk.parseCommit(repo.resolve(commitId));
		revWalk.markStart(root);
		revWalk.setTreeFilter(AndTreeFilter.create(PathFilter.create(fileName),
				TreeFilter.ANY_DIFF));

		// prints out relevant commits (changed file commits)
		for (RevCommit revCommit : revWalk) {
			commits.add(revCommit.toObjectId().getName());
		}
		return (commits);
	}

	public boolean prevCommitIdExists(String commitId) {
		return previousCommitIds.contains(commitId);
	}

	public int size() {
		return lines.size();
	}

	@Override
	public Iterator<Line> iterator() {
		return lines.iterator();
	}

	public String getFileName() {
		return fileName;
	}

	public String getCommitId() {
		return commitId;
	}

}
