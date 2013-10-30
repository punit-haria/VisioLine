package visual.primary;

import java.util.ArrayList;
import java.util.Iterator;

import processing.core.PApplet;

public class FileDisplayContainer {
	
	//parent PApplet that we render onto
	private PApplet parent;
	//collection of LineStripes
	private ArrayList<FileBar> files;
	
	public FileDisplayContainer(PApplet p){
		this.parent = p;
	}

	// draw bar
	public	void display(float xx, float yy) {
		Iterator<FileBar> it = files.iterator();
		float offset = 0;
		while(it.hasNext()){
			FileBar fbar = it.next();
			fbar.display(xx + offset, yy);
			offset += Constants.lineStripeWidth;
		}
	}

}
