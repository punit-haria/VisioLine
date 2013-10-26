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
	public static LinkedList<String> getCommitIds(String dir) throws IOException, NoHeadException, GitAPIException{
		LinkedList<String> commits=new LinkedList<String>();
		try
		  {
		  FileInputStream in = new FileInputStream(dir);
		  BufferedReader br = new BufferedReader(new InputStreamReader(in));
		  String strLine;
		 
		  while((strLine = br.readLine())!= null)
		  {
			String[] tokens=  strLine.split(" ");
			String commitId=tokens[0];
			commits.add(commitId);
	      }
		 
		  }catch(Exception e){
		   System.out.println(e);
		  }
		
		/*this is the real codes but doent work only get 300 commit i will fix it in next month*/
/*		File gitWorkDir = new File(dir);
	    Git git = Git.open(gitWorkDir);
	    Iterable<RevCommit> log = git.log().call();// get log but doesnt work only get partial commitIds
		   int i=0;
	  while(log.iterator().hasNext()){
			  RevCommit rcid=log.iterator().next();
		 if(rcid!=null){
			 System.out.println(rcid.getName());
			 i++;
		 }
		 
	  }*/
	    return commits;
	}

}
