package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;

public class Line implements Serializable {
	
	
	/**
	 * serialization ID
	 */
	private static final long serialVersionUID = 3022542689668488155L;
	
	//variable for determining type
	static int IMPORT_TYPE = 1;
	static int WHITESPACE_TYPE = 2;
	static int FUNCTION_TYPE = 3;
	static int COMMENT_TYPE = 4;
	
	private int lineNumber;
	private String lineValue;
	private int codeType;
	private ArrayList<String> authors;
	private ArrayList<String> commits;

	/*
	 * Represents a single line in a file with information about who generated
	 * it or if has been changed over time
	 */
	public Line(ArrayList<String> commits, ArrayList<String> authors, int lineNumber,
			String lineValue, int type) {
		this.authors = authors;
		this.commits = commits;
		this.lineNumber = lineNumber;
		this.lineValue = lineValue;
		this.codeType = type;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public String getLineValue() {
		return lineValue;
	}

	public int getCodeType() {
		return codeType;
	}
	
	public int getTimesChanged() {
		return authors.size();
	}

	/*
	 * Ordered by newest first
	 */
	public ListIterator<String> getAuthors() {
		return authors.listIterator(authors.size());
	}

	/*
	 * Ordered by newest first
	 */
	public ListIterator<String> getCommitIds() {
		return commits.listIterator(commits.size());
	}
}
