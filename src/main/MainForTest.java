package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import processing.opengl.LineStroker;
import data.CommitIdListGenerator;
import data.FileListGenerator;
import data.Line;
import data.RepoFile;


public class MainForTest {

	public static void main(String[] args) throws IOException, NoHeadException, GitAPIException {
		// TODO Auto-generated method stub
		System.out.println("helloworld");

		FileListGenerator filesList=new FileListGenerator("C:/Users/hai/cs410/owncloud/android");//change to the .git on your local path
		File filesfolder=filesList.getFolder();

		filesList.computeFilesList(filesfolder);
		LinkedList<String> files=filesList.getRelevantPathofFilesByExetension("java");
		int i=0;
		int k=0;
		for(String s:files){
			if(s.contains("\\")){
				i=1;
			}else i=0;
			System.out.println(s+i);
			k++;
		}

		Iterable<RevCommit> commitIds=CommitIdListGenerator.getCommitIds("C:/Users/hai/cs410/owncloud/android");
		List<RevCommit> reverseCommits=new ArrayList<RevCommit>();

		for(RevCommit commit:commitIds)
		{

			//System.out.println(i+"  "+commit.getName()+" "+commit.getCommitterIdent().getWhen());
			reverseCommits.add(commit);
		}
		int j=0;
		Collections.reverse(reverseCommits);
		for(RevCommit commit:reverseCommits)
		{
			j++;
			//System.out.println(i+"  "+commit.getName()+" "+commit.getCommitterIdent().getWhen());
		}
		System.out.println("the number of iterations is"+(k*j));
		File gitWorkDir = new File("C:/Users/hai/cs410/owncloud/android/.git");

		FileRepository repo  =  new FileRepository(gitWorkDir);
		System.out.println(repo.getBranch());
		int num_repo_files=0;
		for(String s:files){
			for(RevCommit commit:reverseCommits)
			{
				//	System.out.println(s);
				RepoFile repoFile=new RepoFile(repo,s,"f79e2c0430b9b732d299cbb516c6884715d10e11");
				Iterator<Line> lines= repoFile.iterator();
				while(lines.hasNext()){
					Line line=lines.next();
					System.out.println(line.getLineNumber()+line.getAuthor()+line.getCommitId()+line.getLineValue());
				}
				// System.out.println("the size of repofile is"+repoFile.size());
				//num_repo_files++;
				// System.out.println("this is working "+repoFile.getFileName());
				// System.out.println("the number of iterations is  "+num_repo_files);

			}

		}

	}

}
