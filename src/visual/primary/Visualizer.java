package visual.primary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

import processing.core.*;
import visual.primary.Constants;
import visual.primary.Constants.Colour;

@SuppressWarnings("serial")
public class Visualizer extends PApplet {
	
	private int[][] test;
	
	//set of project authors
	private HashMap<String,Integer> authorColorScheme;
	
	public Visualizer(ArrayList<String> authorList){
		super();		
		Constants.init(this);		
		mapAuthorsToColors(authorList);
	}
	
	//for testing only
	public Visualizer(){
		super();
		Constants.init(this);
		int numColors = 0;
		for(Colour c : Colour.values()){
			numColors += 1;
		}
		System.out.println(numColors);
	}
	
	@Override
	public void setup() {
		//inital setup
		background(Constants.white);	
		
		//resizability
		if(frame != null){
			frame.setResizable(true);
		}
		
		//test code
		TestData td = new TestData();
		test = td.getData();		
	}
		
	@Override
	public void draw() {		
		//test drawing
		noStroke();
		for(int i = 0; i < test.length; i++){
			int value = test[i][0];
			float _width = Constants.getLineStripeWidth(); 
			fill(value*25,(10-value)*12,0);	
			rect((i*_width) + 50, 50,
				_width,Constants.getLineStripeHeight());
		}		
	}
	
	private void mapAuthorsToColors(ArrayList<String> authorList) {
		//create map of authors to colours
		if(authorList == null){
			System.exit(ERROR);
		}
		//get number of authors
		int numAuthors = authorList.size();	
		//get number of available colours
		int numColors = 0;
		for(@SuppressWarnings("unused") Colour c : Colour.values()){
			numColors += 1;
		}
		
		//map author to colours
		ArrayList<Integer> requiredColors = new ArrayList<Integer>();
		if(numAuthors > numColors){
			int perColor = (int)Math.ceil((double)numAuthors/numColors);
			for(Colour c : Colour.values()){
				int originalColor = c.get();
				for(int i = 0; i < perColor; i++){
					int offset = (int)(Colour.offset()*((double)i/perColor));
					int currentColor = originalColor;
					for(int j = 0; j < 6; j += 2){
						currentColor += ((int)Math.pow(0x10,j))*offset;
					}
					requiredColors.add(currentColor);
				}
			}
		}
		else if(numAuthors <= numColors){
			for(Colour c : Colour.values()){
				requiredColors.add(c.get());
			}
		}

		//assign colour to each author
		if(requiredColors.size() < numAuthors) System.exit(ERROR);
		Iterator<String> authIter = authorList.iterator();
		Iterator<Integer> colorIter = requiredColors.iterator();
		while(authIter.hasNext()){
			String author = authIter.next();
			Integer colr = colorIter.next();
			authorColorScheme.put(author,colr);
		}
	}
	
}
