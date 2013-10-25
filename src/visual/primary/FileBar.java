package visual.primary;

import java.util.ArrayList;
import java.util.Iterator;

import data.File;
import data.Line;
import processing.core.PApplet;

public class FileBar {
	
	//parent PApplet that we render onto
	private PApplet parent;
	//width of FileBar
	private float _width;   
	//collection of LineStripes
	private ArrayList<LineStripe> stripes;

	public FileBar(PApplet p, File file) {
		this.parent = p;
		this._width = file.size()*Constants.lineStripeWidth;
		
		//construct LineStripes
		stripes = new ArrayList<LineStripe>();
		Iterator<Line> it = file.iterator();
		while(it.hasNext()){
			Line line = it.next();
			LineStripe ls = new LineStripe(line);
			stripes.add(ls);
		}
		
	}

	public float getWidth() {
		return _width;
	}

	// draw bar
	void display(float xx, float yy) {
		Iterator<LineStripe> it = stripes.iterator();
		float offset = 0;
		while(it.hasNext()){
			LineStripe stripe = it.next();
			stripe.display(xx + offset, yy);
			offset += Constants.lineStripeWidth;
		}
	}
	
	private class LineStripe {
		
		//reference to corresponding Line object
		private Line line;
		
		private LineStripe(Line line){
			this.line = line;
		}	
		
		private void display(float xx, float yy){
			parent.fill(255,100);
			parent.noStroke();
			parent.rect(xx,yy,Constants.lineStripeWidth,
					Constants.lineStripeHeight);
		}
		
	}

}
