package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Line implements Serializable {
	
	private int lineNumber;
	private String lineValue;
	private int timesChanged;
	private String codeType;
	private ArrayList<String> authors;
	private ArrayList<String> commits;

	/*
	 * Represents a single line in a file with information about who generated
	 * it or if has been changed over time
	 */
	public Line(ArrayList<String> commits, ArrayList<String> authors, int lineNumber,
			String lineValue, int changes, String codeType) {
		this.authors = authors;
		this.commits = commits;
		this.lineNumber = lineNumber;
		this.lineValue = lineValue;
		timesChanged = changes;
		this.codeType = codeType;
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

	public String getCodeType() {
		return codeType;
	}

	public Iterator<String> authorIterator() {
		return authors.iterator();
	}

	public Iterator<String> commitIterator() {
		return commits.iterator();
	}
}
