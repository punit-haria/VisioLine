package main;

import processing.core.*;

@SuppressWarnings("serial")
public class Visualizer extends PApplet {

	int[][] test;
	private static int xx = 50;
	private static int yy = 50;
	private static int _width = 10;
	private static int _height = 100; 
	
	public void setup() {
		size(500,500);
		TestData td = new TestData();
		test = td.getData();		
	}

	public void draw() {
		background(100);
		noStroke();
		for(int i = 0; i < test.length; i++){
			drawStripe(i*3 + xx, test[i][0]);			
		}		
	}
	
	private void drawStripe(int xcoord, int value){
		fill(value*25,(10-value)*12,0);		
		rect(xcoord,yy,_width,_height);
	}
	
}
