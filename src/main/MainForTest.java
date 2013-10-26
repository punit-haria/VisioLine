package main;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;

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
String realdir="C:/Users/hai/cs410/owncloud/android";
String fakedir="c:/Users/hai/git/VisioLine/data/listofcommits.txt";// change to your path of listofcommits.txt
LinkedList<String> commitIds=CommitIdListGenerator.getCommitIds(fakedir);
for(String s:commitIds)
{
	System.out.println(s);
}
}

}
