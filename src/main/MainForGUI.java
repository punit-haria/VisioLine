package main;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import visual.primary.Visualizer;

public class MainForGUI extends Frame {

	Panel panel;
	Button b1,b2;
	public MainForGUI()
	{

		// Set frame properties
		setTitle("MainForGUI"); // Set the title
		setSize(400,400); // Set size to the frame
		setLayout(new FlowLayout()); // Set the layout
		setVisible(true); // Make the frame visible
		setLocationRelativeTo(null);  // Center the frame

		// Create the panel
		panel=new Panel();

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
		add(panel);

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}
		});
	
	}



	public static void main(String args[])
	{
		new MainForGUI();
	}

}
