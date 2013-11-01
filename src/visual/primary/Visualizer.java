package visual.primary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import data.RepoFile;

import processing.core.*;
import visual.primary.Constants;
import visual.primary.Constants.Colour;

@SuppressWarnings("serial")
public class Visualizer extends PApplet {
	
	//set of project authors
	private HashMap<String,Integer> authorColorScheme = null;
	//set of fileBars
	private FileDisplayContainer displayFiles = null;
	
	public Visualizer(ArrayList<String> authorList, 
			ArrayList<RepoFile> repfiles){
		super();		
		Constants.init(this);		
		mapAuthorsToColors(authorList);
		
		//add repoFiles to displayContainer
		displayFiles = new FileDisplayContainer(this);
		Iterator<RepoFile> iter = repfiles.iterator();
		while(iter.hasNext()){
			RepoFile file = iter.next();
			displayFiles.addFile(file);
		}
	}
	
	@Override
	public void setup() {
		//inital setup
		background(Constants.white);	
		
		//resizability
		if(frame != null){
			frame.setResizable(true);
		}	
	}
		
	@Override
	public void draw() {	
		//test drawing
		noStroke();
		displayFiles.display(10,50);					
	}
	
	//get corresponding color for author
	public int getAuthorColor(String author){
		return authorColorScheme.get(author);
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
				requiredColors.add(c.get());
			}
			for(Colour c : Colour.values()){
				int originalColor = c.get();
				for(int i = perColor; i > 1; i--){
					int offset = (int)(Colour.offset()*((double)i/perColor));
					int currentColor = originalColor;
					for(int j = 4; j >= 0; j -= 2){
						int temp = Math.max(((int)Math.pow(0x10,j))*offset,0x0);
						currentColor -= temp;
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
		authorColorScheme = new HashMap<String,Integer>();
		Iterator<String> authIter = authorList.iterator();
		Iterator<Integer> colorIter = requiredColors.iterator();
		while(authIter.hasNext()){
			String author = authIter.next();
			Integer colr = colorIter.next();
			authorColorScheme.put(author,colr);
		}
	}
	
}
