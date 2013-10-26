package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.revwalk.RevCommit;

public class CommitIdListGenerator {
	//so far the real code only get 300 commitids so i use fake method to get full list of commitIds
	//but the return type and type of the parameter will same
	public static Iterable<RevCommit> getCommitIds(String dir) throws IOException, NoHeadException, GitAPIException {
		File gitWorkDir = new File(dir);
	    Git git = Git.open(gitWorkDir);
	    Iterable<RevCommit> commits = git.log().all().call();
	  
		return commits;
	}

}
