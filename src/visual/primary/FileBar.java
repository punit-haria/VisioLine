package visual.primary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

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
	private int maxLineChanged;
	//file bar height
	private int absoluteHeight;
	

	public FileBar(Visualizer v, RepoFile file) {
		this.parent = v;
		this.numberOfLines = file.size();
		this.fileName = file.getFileName()+" "+file.getCommitNum();
		//construct LineStripes
		stripes = new ArrayList<LineStripe>();
		Iterator<Line> it = file.iterator();
		int currMax = 0;
		maxLineChanged = 1;
		while(it.hasNext()){
			Line line = it.next();
			LineStripe ls = new LineStripe(line);
			stripes.add(ls);
			currMax = line.getTimesChanged()+1;
			if(currMax > maxLineChanged){
				maxLineChanged = currMax;
			}
		}
		absoluteHeight = maxLineChanged;//initialize value
	}

	//display-width of FileBar
	public float getWidth() {
		return numberOfLines*Constants.getLineStripeWidth();
	}
	
	//file name
	public String getFileName(){
		return fileName;
	}
	
	//get maximum #changes
	public int getLocalMaxChanges(){
		return maxLineChanged;
	}
	
	//set file bar height
	public void setHeight(int h){
		absoluteHeight = h;
	}

	// draw bar
	public void display(float xx, float yy) {
		parent.noStroke();
		Iterator<LineStripe> it = stripes.iterator();
		float offset = 0;
		while(it.hasNext()){
			LineStripe stripe = it.next();
			stripe.display(xx + offset, yy);
			offset += Constants.getLineStripeWidth();
		}
		//print file name
		parent.fill(0);
		parent.text(fileName,xx,yy+Constants.lineStripeHeight+10);
	}
	
	private class LineStripe {
		
		//segments that make up this line
		private ArrayList<Segment> segs;
		
		private LineStripe(Line line){
			segs = new ArrayList<Segment>();
			ListIterator<String> authIt = line.getAuthors();
			ListIterator<String> commIt = line.getCommitIds();
			int pos = 1;
			while(authIt.hasPrevious()){
				String author = authIt.previous();
				String commitId = commIt.previous();
				Segment segment = new Segment(author,commitId,pos);
				segs.add(segment);
				pos++;
			}
		}	
		
		private void display(float xx, float yy){
			Iterator<Segment> iter = segs.iterator();
			while(iter.hasNext()){
				Segment sm = iter.next();
				sm.display(xx,yy);
			}
		}
		
		private class Segment {
			
			//hexadecimal valued color
			private int lineColor;
			//commit id
			private String commitId;
			//position in Line
			private int position;
			
			private Segment(String author, String commit, int pos){
				this.lineColor = parent.getAuthorColor(author);				
				this.position = pos;
			}
			
			private void display(float xx, float yy){
				parent.fill(lineColor);
				float height = Constants.lineStripeHeight * (1/(float)absoluteHeight);
				parent.rect(xx,yy+Constants.lineStripeHeight-(height*position),
						Constants.getLineStripeWidth(),height);
			}
			
		}
		
	}

}
