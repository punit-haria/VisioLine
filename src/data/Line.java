package data;

import java.util.Date;

public class Line {
	
	private String commitId;
	private String author;
	private Date date;
	private int lineNumber;
	private String lineValue;
	
	public Line(String commitId, String author, Date date,
			int lineNumber, String lineValue){
		this.commitId = commitId;
		this.author = author;
		this.date = date;
		this.lineNumber = lineNumber;
		this.lineValue = lineValue;		
	}
	
	public String getCommitId() {
		return commitId;
	}

	public String getAuthor() {
		return author;
	}

	public Date getDate() {
		return date;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public String getLineValue() {
		return lineValue;
	}
	
}
