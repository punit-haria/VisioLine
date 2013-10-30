package data;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;

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
	private String gitDir;
	final File gitWorkDir;
	private Git git;
	private FileRepository repo;
	private Iterable<RevCommit> commits;
	private LinkedList<String> files;
	public RepoFileManager(String Dir) throws IOException, GitAPIException{
		this.gitDir=Dir;
		this.gitWorkDir = new File(this.gitDir);
		this.repo=new FileRepository((this.gitDir+"/.git"));
		this.git = Git.open(gitWorkDir);
		commits = git.log().all().call();
		this.files=new LinkedList<String>();
		 computeFilesList(gitWorkDir);//to get list of all files in the folder
	}
	public  Iterable<RevCommit> getCommitList(){
		 return commits;
	 }
	 public File get_folder(){
		 return this.gitWorkDir;
	 }
	 public void computeFilesList(File folder){			 
		    for (final File fileEntry : folder.listFiles()) {
		        if (fileEntry.isDirectory()) {
		        	
		           computeFilesList(fileEntry);
		          
		        } else {
		        	 
		        	String s=fileEntry.getPath();
		        	if(s.contains("\\")){
		   			 
		    			s=s.replace("\\", "/");
		    			} 
		        	 files.add(s);
		        }
		    }
		 
	}
	 public LinkedList<String> getFullPathofFiles(){//useless method for test purpose
			return files;
		}
		public LinkedList<String> getRelevantPathofFiles(){//all jgit method need relevant path
			LinkedList<String> shotPathFiles=new LinkedList<String>();
			int start=this.gitDir.length()+1;
			
			for(String s:files){
				shotPathFiles.add(s.substring(start));
				
			}
			return shotPathFiles;
			
		}
		//if you only deal with java file use this parameter is "java"
		public LinkedList<String> getRelevantPathofFilesByExetension(String fileType){
			LinkedList<String> shotPathFiles=new LinkedList<String>();
			int start=this.gitDir.length()+1;
			 
			for(String s:files){
				if(s.substring(s.lastIndexOf('.')+1).equalsIgnoreCase(fileType)){
						
				shotPathFiles.add(s.substring(start));
				}
				
			}
			return shotPathFiles;
			
		}
		public FileRepository getRepo(){
			return this.repo;
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
