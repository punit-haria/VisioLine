package visual;

import java.util.ArrayList;
import java.util.Iterator;
import data.RepoFile;

/**
 * Manager for FileBars. (list of FileBars)
 */
public class FileDisplayContainer {
	
	//parent PApplet that we render onto
	private Visualizer parent;
	//collection of LineStripes
	private ArrayList<FileBar> files;
	
	public FileDisplayContainer(ArrayList<RepoFile> repfiles, Visualizer v){
		this.parent = v;
		files = new ArrayList<FileBar>();
		Iterator<RepoFile> iter = repfiles.iterator();
		int max_height = 1;
		while(iter.hasNext()){
			RepoFile file = iter.next();
			FileBar fb = new FileBar(parent, file);
			int curr_max = fb.getLocalMaxChanges();
			if(curr_max > max_height) max_height = curr_max;
			files.add(fb);		
		}
		Iterator<FileBar> it = files.iterator();
		while(it.hasNext()){
			FileBar fb = it.next();
			fb.setHeight(max_height);
		}
	}
	
	//Gets the display length of each FileBar
	public float getLength(){
		Iterator<FileBar> iter = files.iterator();
		float length = 0f;
		while(iter.hasNext()){
			FileBar fb = iter.next();
			length += fb.getWidth() + Constants.getFileBarSpacing();
		}
		return length;
	}

	//code for drawing this object onto screen
	public	void display(float xx, float yy) {
		Iterator<FileBar> it = files.iterator();
		float offset = 0;
		while(it.hasNext()){
			FileBar fbar = it.next();
			fbar.display(xx + offset, yy);
			offset += Constants.getFileBarSpacing() + fbar.getWidth();
		}
	}

}
