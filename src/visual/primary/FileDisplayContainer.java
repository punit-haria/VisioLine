package visual.primary;

import java.util.ArrayList;
import java.util.Iterator;
import data.RepoFile;


public class FileDisplayContainer {
	
	//parent PApplet that we render onto
	private Visualizer parent;
	//collection of LineStripes
	private ArrayList<FileBar> files;
	
	public FileDisplayContainer(Visualizer v){
		this.parent = v;
		files = new ArrayList<FileBar>();
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
			//fbar.display(xx + offset, yy);
			//offset += Constants.getFileBarSpacing() + fbar.getWidth();
			fbar.display(xx, yy + offset);
			offset += Constants.getLineStripeHeight()+10;
		}
	}

}
