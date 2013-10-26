package main;

import java.io.File;
import java.util.LinkedList;

import data.FileListGenerator;

public class MainForTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
System.out.println("helloworld");

FileListGenerator filesList=new FileListGenerator("C:/Users/hai/cs410/owncloud/android");
File filesfolder=filesList.getFolder();

filesList.computeFilesList(filesfolder);
LinkedList<String> files=filesList.getRelevantPathofFilesByExetension("java");
for(String s:files){
	System.out.println(s);
}

	}

}
