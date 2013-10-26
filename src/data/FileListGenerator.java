package data;

import java.io.File;
import java.util.LinkedList;

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
		        	 files.add(fileEntry.getPath());
		        }
		    }
		 
	}
	public LinkedList<String> getFullPathofFiles(){
		return files;
	}
	public LinkedList<String> getRelevantPathofFiles(){
		LinkedList<String> shotPathFiles=new LinkedList<String>();
		int start=dir.length()+1;
		
		for(String s:files){
			shotPathFiles.add(s.substring(start));
			
		}
		return shotPathFiles;
		
	}
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
