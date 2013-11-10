package data;

public class Line {
	
	private String commitId;
	private String author;
	private int lineNumber;
	private String lineValue;
	private int timesChanged;
	private String codeType;
	
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
