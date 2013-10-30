package main;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.util.ArrayList;

import data.RepoFile;

import visual.primary.Visualizer;

@SuppressWarnings("serial")
public class MainApplet extends Applet {
	
	Panel panel;
	Button b1,b2;

	@Override
	public void init(){
		//layout
		this.setLayout(new BorderLayout());
		
		// Create the panel
		panel = new Panel();
		panel.setLayout(new FlowLayout()); // Set the layout
		panel.setVisible(true); // Make the frame visible		

		// Set panel background
		panel.setBackground(Color.gray); 	 	

		// Create buttons
		b1=new Button(); // Create a button with default constructor
		b1.setLabel("I am button 1"); // Set the text for button

		b2=new Button("Button 2"); // Create a button with sample text
		b2.setBackground(Color.lightGray); // Set the background to the button

		// Add the buttons to the panel
		panel.add(b1);
		panel.add(b2);

		// Add the panel to the frame
		this.add(panel,BorderLayout.NORTH);

		//Test visualization
		//Visualizer vis = nastyTestingMethod();
		Visualizer vis = new Visualizer();
		this.add(vis,BorderLayout.CENTER);
		vis.init();
		
	}
	
	@SuppressWarnings("unused")
	private Visualizer nastyTestingMethod(){
		ArrayList<String> authorList = new ArrayList<String>();
		authorList.add("auth1");
		authorList.add("auth2");
		authorList.add("auth3");
		authorList.add("fourthAuthor");
		authorList.add("fifthAuthor");
		authorList.add("six");
		
		ArrayList<RepoFile> repFiles = new ArrayList<RepoFile>();
		
		Visualizer v = new Visualizer(authorList,repFiles);		
		return v;
	}

}
