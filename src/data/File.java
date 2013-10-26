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
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.revwalk.RevCommit;

public class File implements Iterable<Line> {
	
	private String fileName;
	private String commitId;
	
	//change to whatever data structure is most suitable
	private ArrayList<Line> lines;
	
	/*
	 * @param name is a the file's relative path
	 * @param commitId must be a commit within this file's history
	 * otherwise this function will result in an error.
	 */
	public File(FileRepository repo, String name, String commitId) throws GitAPIException, RevisionSyntaxException, AmbiguousObjectException, IncorrectObjectTypeException, IOException{
		lines = new ArrayList<Line>();
		this.fileName = name;
		this.commitId = commitId;
		
		//obtain blame information
		BlameCommand blamer = new BlameCommand(repo);
		blamer.setStartCommit(repo.resolve(commitId));
		blamer.setFilePath(name);
		lines = populateLineInfo(blamer.call());
	}	
	
	private ArrayList<Line> populateLineInfo(BlameResult blame)
	{
		RawText result = blame.getResultContents();
		int numberOfLines = result.size();
		ArrayList<Line> lines = new ArrayList<Line>(numberOfLines);
		
		for(int i = 0; i < numberOfLines; ++i)
		{
			RevCommit currentCommit = blame.getSourceCommit(i);
			lines.add(new Line(currentCommit.getName(), currentCommit.getName(), i,
					result.getString(i)));
		}
		
		return lines;
	}
	
	public int size(){
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
