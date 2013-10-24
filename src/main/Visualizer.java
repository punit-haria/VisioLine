package main;

import processing.core.*;

@SuppressWarnings("serial")
public class Visualizer extends PApplet {
//	An array of stripes
	Stripe[] stripes = new Stripe[50];
	int[][] test;

	public void setup() {
		size(500,500);
		TestData td = new TestData();
		test = td.getData();		
	}

	public void draw() {
		background(100);
		// Move and display all "stripes"
		for (int i = 0; i < stripes.length; i++) {
			stripes[i].move();
			stripes[i].display();
		}
	}
	
	private void drawStripe(){
		fill(255,100);
		noStroke();
		//rect(x,0,w,parent.height);
	}
	
}
