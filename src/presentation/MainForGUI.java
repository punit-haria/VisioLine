package presentation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.revwalk.RevCommit;

import visual.primary.Visualizer;
import data.RepoFile;
import data.RepoFileManager;

@SuppressWarnings("serial")
public class MainForGUI extends Frame {

	private static String gitPath;
	Panel panel;
	Button b1,b2;
	 
	private ArrayList<RepoFile> repfiles;
	private ArrayList<String> authorList;
	private ArrayList<String> commitsList;
	public MainForGUI()
	{

		// Set frame properties
		setTitle("VisioLine"); // Set the title
		setSize(400,400); // Set size to the frame
		//layout
		this.setLayout(new BorderLayout());
		setVisible(true); // Make the frame visible
		setLocationRelativeTo(null);  // Center the frame

		// Create the panel
		panel=new Panel();

		// Set panel background
		panel.setBackground(Color.gray); 	 	

		// Create buttons
		b1=new Button(); // Create a button with default constructor
		b1.addActionListener(new ActionListener() {
	       	@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
	       		loadData();
				
			}
	    });
		b1.setLabel("input data"); // Set the text for button

		b2=new Button("play"); // Create a button with sample text
		b2.addActionListener(new ActionListener() {
	       	@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
	       		play();
				
			}

		 
	    });
		b2.setBackground(Color.lightGray); // Set the background to the button

		// Add the buttons to the panel
		panel.add(b1);
		panel.add(b2);

		// Add the panel to the frame
		this.add(panel,BorderLayout.NORTH);

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}
		});

		//Test visualization
	
	}



	protected void play() {
		// TODO Auto-generated method stub
		FileInputStream fileIn;
		try {
			JOptionPane.showMessageDialog(null, "load the object from filename.ser");
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Select target directory");
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int returnVal = chooser.showOpenDialog(new Frame());
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File repo_out_File = chooser.getSelectedFile();
    		fileIn = new FileInputStream(repo_out_File);
			ObjectInputStream file_in = new ObjectInputStream(fileIn);
	        repfiles = (ArrayList<RepoFile>) file_in.readObject();
	        Collections.sort(repfiles, RepoFile.Comparators.COMMITS);
	        authorList = (ArrayList<String>) file_in.readObject();
	        commitsList=(ArrayList<String>) file_in.readObject();
	        file_in.close();
	        fileIn.close();
			}
			/* chooser = new JFileChooser();
			chooser.setDialogTitle("Select target directory");
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			 returnVal = chooser.showOpenDialog(new Frame());
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File author_out_File = chooser.getSelectedFile();
		fileIn = new FileInputStream(author_out_File);
			 
			ObjectInputStream author_in = new ObjectInputStream(fileIn);
	        authorList = (ArrayList<String>) author_in.readObject();
	        author_in.close();
	        fileIn.close();
			}*/
			
	    	Visualizer vis = new Visualizer(authorList,repfiles,commitsList);
			this.add(vis,BorderLayout.CENTER);
		    vis.init();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
	}



	protected void loadData() {
		// TODO Auto-generated method stub
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
		 commitsList=new ArrayList<String>();
			ArrayList<String> authors = new ArrayList<String>();
		for(RevCommit commit:commits){
			String commitId=commit.getName();
			//System.out.println(commitId);
			String s=commit.getCommitterIdent().getName();
			commitsList.add(commitId);
			if(!authors.contains(s)){
			authors.add(s);
			//System.out.println(s);
			} 
		}
			//visualizer
			ArrayList<RepoFile> fileList = new ArrayList<RepoFile>();
			
			for(String s:files){				
				System.out.println(s);
				RepoFile rf = new RepoFile(rfm.getRepo(), s, "HEAD");
				if (rf.getErrorScore() < 10)
					fileList.add(rf);
			}
			
			JOptionPane.showMessageDialog(null, "save the object in filename.ser");
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int retrival=chooser.showSaveDialog(null);
			if(retrival == JFileChooser.APPROVE_OPTION){
				File repoSaveFile = chooser.getSelectedFile();
				 FileOutputStream fileOut =new FileOutputStream(repoSaveFile);
		         ObjectOutputStream out = new ObjectOutputStream(fileOut);
		         out.writeObject(fileList);
		         out.writeObject(authors);
		         out.writeObject(commitsList);
		         out.close();
		         fileOut.close();
			}
		/*	chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			 retrival=chooser.showSaveDialog(null); 
			if(retrival == JFileChooser.APPROVE_OPTION){
				File authSaveFile = chooser.getSelectedFile();
				 FileOutputStream authorOut =new FileOutputStream(authSaveFile );
				         ObjectOutputStream a_out = new ObjectOutputStream(authorOut);
				        a_out.writeObject(authors);
				         a_out.close();
				         authorOut.close();
				        
			}
		        */
		     
					
		} catch (RevisionSyntaxException | GitAPIException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}



	public static void main(String args[])
	{
		new MainForGUI();
		
		
	}

}
