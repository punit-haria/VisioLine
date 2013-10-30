package main;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFileChooser;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.revwalk.RevCommit;

import data.Line;
import data.RepoFile;
import data.RepoFileManager;

import visual.primary.Visualizer;

public class MainForGUI extends Frame {
	
	private static String gitPath;
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
			 JFileChooser chooser = new JFileChooser();
			    chooser.setDialogTitle("Select target directory");
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    int returnVal = chooser.showOpenDialog(new Frame());
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    File myFile = chooser.getSelectedFile();
			    
			    gitPath=myFile.getAbsolutePath();
			    }
			    try {
			    	
					RepoFileManager rfm=new RepoFileManager(gitPath);
					 
					 LinkedList<String> files=rfm.getRelevantPathofFilesByExetension("java");
					  
					 Iterable<RevCommit> commits=rfm.getCommitList();
					 
					 int i=0;
	 
					 for(RevCommit commit:commits){
						 for(String s:files){
							System.out.println(i++);
							//if( rfm.getAlteringCommitIDs(rfm.getRepo(),s ).contains(commit.getName())){
							/* RepoFile rf=new RepoFile(rfm.getRepo(), s, commit.getName());
							 Iterator<Line> lines= rf.iterator();
								while(lines.hasNext()){
									Line line=lines.next();
									System.out.println(line.getLineNumber()+line.getAuthor()+line.getCommitId()+line.getLineValue());
						 */
						//}
							//}
						 }
						
						 
					 }
				} catch (RevisionSyntaxException | GitAPIException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	 
	    new MainForGUI();
	    }

}
