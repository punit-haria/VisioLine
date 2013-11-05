package visual.primary;

import java.util.ArrayList;
import java.util.Iterator;
import data.RepoFile;


public class FileDisplayContainer {
	
	//parent PApplet that we render onto
	private Visualizer parent;
	//collection of LineStripes
	private ArrayList<FileBar> files;
	//length of display container
	private float length;
	
	public FileDisplayContainer(ArrayList<RepoFile> repfiles, Visualizer v){
		this.parent = v;
		files = new ArrayList<FileBar>();
		Iterator<RepoFile> iter = repfiles.iterator();
		length = 0f;
		while(iter.hasNext()){
			RepoFile file = iter.next();
			FileBar fb = new FileBar(parent, file);
			length += fb.getWidth() + Constants.fileBarSpacing;
			files.add(fb);			
		}
	}
	
	public float getLength(){
		return length;
	}

	// draw FileBars 
	public	void display(float xx, float yy) {
		Iterator<FileBar> it = files.iterator();
		float offset = 0;
		while(it.hasNext()){
			FileBar fbar = it.next();
			fbar.display(xx + offset, yy);
			offset += Constants.fileBarSpacing + fbar.getWidth();
			//fbar.display(xx, yy + offset);
			//offset += Constants.lineStripeHeight+25;
		}
	}

}
