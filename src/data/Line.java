package data;

public class Line {
	
	private String commitId;
	private String author;
	private int lineNumber;
	private String lineValue;
	private int timesChanged;
	
	public Line(String commitId, String author,
			int lineNumber, String lineValue){
		this.commitId = commitId;
		this.author = author;
		this.lineNumber = lineNumber;
		this.lineValue = lineValue;
		timesChanged = 0;
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
	
}
