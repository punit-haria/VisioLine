package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Line implements Serializable {
	
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
	public Iterator<String> authorIterator() {
		return authors.iterator();
	}

	/*
	 * Ordered by newest first
	 */
	public Iterator<String> commitIterator() {
		return commits.iterator();
	}
}
