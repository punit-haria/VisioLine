package data;

import java.util.ArrayList;
import java.util.Iterator;

public class File implements Iterable<Line> {
	
	private String fileName;
	private String commitId;
	
	//change to whatever data structure is most suitable
	private ArrayList<Line> lines;
	
	public File(String name, String commitId){
		lines = new ArrayList<Line>();
		this.fileName = name;
		this.commitId = commitId;
	}	

	public void add(Line line){
		lines.add(line);
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
