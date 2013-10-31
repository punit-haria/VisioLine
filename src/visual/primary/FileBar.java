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
	private int numberOfLines;
	//file name
	private String fileName;
	//max number of times a line has been changed
	private int maxLineChanged = 1;

	public FileBar(Visualizer v, RepoFile file) {
		this.parent = v;
		this.numberOfLines = file.size();
		this.fileName = file.getFileName();
		//construct LineStripes
		stripes = new ArrayList<LineStripe>();
		Iterator<Line> it = file.iterator();
		int currMax;
		while(it.hasNext()){
			Line line = it.next();
			LineStripe ls = new LineStripe(line);
			stripes.add(ls);
			currMax = line.getTimesChanged()+1;
			if(currMax > maxLineChanged){
				maxLineChanged = currMax;
			}
		}
		
	}

	//display-width of FileBar
	public float getWidth() {
		return numberOfLines*Constants.getLineStripeWidth();
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
		//print file name
		parent.text(fileName,xx,yy+Constants.getLineStripeHeight()+10);
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
			float height = Constants.getLineStripeHeight() * ((float)(line.getTimesChanged()+1)/(float)maxLineChanged);
			parent.rect(xx,yy+(Constants.getLineStripeHeight()-height),Constants.getLineStripeWidth(),
					height);
		}
		
	}

}
