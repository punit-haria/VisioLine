package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Line implements Serializable {
	
	private String commitId;
	private String author;
	private int lineNumber;
	private String lineValue;
	private int timesChanged;
	private String codeType;
	
	//replace "author" and "commitId" with the following:
	private ArrayList<String> authors; //list of authors in order of last commit
	private ArrayList<String> commitIds;//corresponding commit ids
	
	public Iterator<String> getAuthors() {
		return authors.iterator();
	}

	public Iterator<String> getCommitIds() {
		return commitIds.iterator();
	}	
	
	public Line(String commitId, String author,
			int lineNumber, String lineValue, int changes, String codeType){
		this.commitId = commitId;
		this.author = author;
		this.lineNumber = lineNumber;
		this.lineValue = lineValue;
		timesChanged = changes;
		this.codeType = codeType;
	}
	
	public String getCommitId() {
		return commitId;
	}

	public String getAuthor() {
		return author;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public String getLineValue() {
		return lineValue;
	}
	
	public int getTimesChanged() {
		return timesChanged;
	}
	
	public String getCodeType()
	{
		return codeType;
	}
	
}
