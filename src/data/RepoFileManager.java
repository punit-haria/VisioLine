package data;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.errors.AmbiguousObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.filter.AndTreeFilter;
import org.eclipse.jgit.treewalk.filter.PathFilter;
import org.eclipse.jgit.treewalk.filter.TreeFilter;

public class RepoFileManager {

	//so far the real code only get 300 commitids so i use fake method to get full list of commitIds
	//but the return type and type of the parameter will same
	public static Iterable<RevCommit> getCommitIds(String dir) throws IOException, NoHeadException, GitAPIException {
		File gitWorkDir = new File(dir);
	    Git git = Git.open(gitWorkDir);
	    Iterable<RevCommit> commits = git.log().all().call();
	    
	  
		return commits;
	}

	/*
	 * Gets all commits that a file was altered in
	 */
	public static HashSet<String> getAlteringCommitIDs(FileRepository repo, String filePath)
			throws RevisionSyntaxException, MissingObjectException,
			IncorrectObjectTypeException, AmbiguousObjectException, IOException {
		HashSet<String> commits = new HashSet<String>();

		RevWalk revWalk = new RevWalk(repo);
		RevCommit root = revWalk.parseCommit(repo.resolve("HEAD"));
		revWalk.markStart(root);
		revWalk.setTreeFilter(AndTreeFilter.create(PathFilter.create(filePath),
				TreeFilter.ANY_DIFF));

		// prints out relevant commits (changed file commits)
		for (RevCommit revCommit : revWalk) {
			commits.add(revCommit.toObjectId().getName());
		}
		return (commits);
	}
	
}
