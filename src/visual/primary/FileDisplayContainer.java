package visual.primary;

import java.util.ArrayList;
import java.util.Iterator;

import data.RepoFile;

import processing.core.PApplet;

public class FileDisplayContainer {
	
	//parent PApplet that we render onto
	private PApplet parent;
	//collection of LineStripes
	private ArrayList<FileBar> files;
	
	public FileDisplayContainer(PApplet p){
		this.parent = p;
	}
	
	public void addFile(RepoFile repofile){
		files.add(new FileBar(parent, repofile));		
	}

	// draw FileBars 
	public	void display(float xx, float yy) {
		Iterator<FileBar> it = files.iterator();
		float offset = 0;
		while(it.hasNext()){
			FileBar fbar = it.next();
			fbar.display(xx + offset, yy);
			offset += Constants.getFileBarSpacing();
		}
	}

}
