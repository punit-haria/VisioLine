package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

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

public class RepoFile implements Iterable<Line> {

	private String fileName;
	private String commitId;

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
		this.fileName = name;
		this.commitId = commitId;
		// create list of line objects
		this.lines = populateLineInfo(repo);
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
