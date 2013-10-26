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
import org.eclipse.jgit.revwalk.RevCommit;

import data.CommitIdListGenerator;
import data.FileListGenerator;

public class MainForTest {

	public static void main(String[] args) throws IOException, NoHeadException, GitAPIException {
		// TODO Auto-generated method stub
System.out.println("helloworld");

FileListGenerator filesList=new FileListGenerator("C:/Users/hai/cs410/owncloud/android");//change to the .git on your local path
File filesfolder=filesList.getFolder();

filesList.computeFilesList(filesfolder);
LinkedList<String> files=filesList.getRelevantPathofFilesByExetension("java");
for(String s:files){
	System.out.println(s);
}
 
Iterable<RevCommit> commitIds=CommitIdListGenerator.getCommitIds("C:/Users/hai/cs410/owncloud/android");
List<RevCommit> reverseCommits=new ArrayList<RevCommit>();
int i=0;
for(RevCommit commit:commitIds)
{
	i++;
	System.out.println(i+"  "+commit.getName()+" "+commit.getCommitterIdent().getWhen());
	reverseCommits.add(commit);
}
 Collections.reverse(reverseCommits);
 for(RevCommit commit:reverseCommits)
 {
 	 i--;
 	System.out.println(i+"  "+commit.getName()+" "+commit.getCommitterIdent().getWhen());
 }
 }

}
