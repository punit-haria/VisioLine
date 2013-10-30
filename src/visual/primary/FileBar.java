package visual.primary;

import java.util.ArrayList;
import java.util.Iterator;

import data.RepoFile;
import data.Line;

public class FileBar {
	
	//parent PApplet that we render onto
	private Visualizer parent;
	//collection of LineStripes
	private ArrayList<LineStripe> stripes;
	//number of files
	private int numberOfFiles;
	//file name
	private String fileName;

	public FileBar(Visualizer v, RepoFile file) {
		this.parent = v;
		this.numberOfFiles = file.size();
		this.fileName = file.getFileName();
		//construct LineStripes
		stripes = new ArrayList<LineStripe>();
		Iterator<Line> it = file.iterator();
		while(it.hasNext()){
			Line line = it.next();
			LineStripe ls = new LineStripe(line);
			stripes.add(ls);
		}
		
	}

	//display-width of FileBar
	public float getWidth() {
		return numberOfFiles*Constants.getLineStripeWidth();
	}
	
	//file name
	public String getFileName(){
		return fileName;
	}

	// draw bar
	public void display(float xx, float yy) {
		Iterator<LineStripe> it = stripes.iterator();
		float offset = 0;
		while(it.hasNext()){
			LineStripe stripe = it.next();
			stripe.display(xx + offset, yy);
			offset += Constants.getLineStripeWidth();
		}
	}
	
	private class LineStripe {
		
		//reference to corresponding Line object
		private Line line; 
		//hexadecimal valued color
		private int lineColor;
		
		private LineStripe(Line l){
			this.line = l;
			this.lineColor = parent.getAuthorColor(line.getAuthor());
		}	
		
		private void display(float xx, float yy){
			parent.noStroke();
			parent.fill(lineColor);
			parent.rect(xx,yy,Constants.getLineStripeWidth(),
					Constants.getLineStripeHeight());
		}
		
	}

}
