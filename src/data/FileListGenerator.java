package data;

import java.io.File;
import java.util.LinkedList;
//everytime try to get a list of files in the folder MUST create a new filelistgenerator,
//dont worry we only use it in project once which is at the beginning
public class FileListGenerator {
	final File folder; 
	LinkedList<String> files;
	String dir;
	public FileListGenerator(String dir){
		this.dir=dir;
		this.folder=new File(dir);
		files=new LinkedList<String>();
	}
	public File getFolder(){
	return folder;
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
		    			if(s.contains("\\")) System.out.println("still");
		        	 files.add(s);
		        }
		    }
		 
	}
	public LinkedList<String> getFullPathofFiles(){//useless method for test purpose
		return files;
	}
	public LinkedList<String> getRelevantPathofFiles(){//all jgit method need relevant path
		LinkedList<String> shotPathFiles=new LinkedList<String>();
		int start=dir.length()+1;
		
		for(String s:files){
			shotPathFiles.add(s.substring(start));
			
		}
		return shotPathFiles;
		
	}
	//if you only deal with java file use this parameter is "java"
	public LinkedList<String> getRelevantPathofFilesByExetension(String fileType){
		LinkedList<String> shotPathFiles=new LinkedList<String>();
		int start=dir.length()+1;
		 
		for(String s:files){
			if(s.substring(s.lastIndexOf('.')+1).equalsIgnoreCase(fileType)){
					
			shotPathFiles.add(s.substring(start));
			}
			
		}
		return shotPathFiles;
		
	}


}
