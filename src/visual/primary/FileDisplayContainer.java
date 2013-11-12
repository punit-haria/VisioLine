package visual.primary;

import java.util.ArrayList;
import java.util.Iterator;
import data.RepoFile;


public class FileDisplayContainer {
	
	//parent PApplet that we render onto
	private Visualizer parent;
	//collection of LineStripes
	private ArrayList<FileBar> files;
	
	public FileDisplayContainer(ArrayList<RepoFile> repfiles, Visualizer v){
		this.parent = v;
		files = new ArrayList<FileBar>();
		Iterator<RepoFile> iter = repfiles.iterator();
		while(iter.hasNext()){
			RepoFile file = iter.next();
			FileBar fb = new FileBar(parent, file);
			files.add(fb);			
		}
	}
	
	public float getLength(){
		Iterator<FileBar> iter = files.iterator();
		float length = 0f;
		while(iter.hasNext()){
			FileBar fb = iter.next();
			length += fb.getWidth() + Constants.getFileBarSpacing();
		}
		return length;
	}

	// draw FileBars 
	public	void display(float xx, float yy) {
		Iterator<FileBar> it = files.iterator();
		float offset = 0;
		while(it.hasNext()){
			FileBar fbar = it.next();
			fbar.display(xx + offset, yy);
			offset += Constants.getFileBarSpacing() + fbar.getWidth();
			//fbar.display(xx, yy + offset);
			//offset += Constants.lineStripeHeight+25;
		}
	}

}
