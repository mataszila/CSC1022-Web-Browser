import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import javax.swing.JEditorPane;
import javax.swing.JFrame;

// This class is responsible for reading data.

public class FileReader {

	// Instance variables
	
	InputStream in;
	Scanner sc;
	
	// Method used to get the home page.

	
	public String getHomePage(){
		// The information is being read from config.properties file.
		// This method uses Properties class to retrieve keys and values.
		
		
		String filename = "config.properties";
		String homePage = null;
		Properties prop = new Properties();
		in = getClass().getResourceAsStream(filename);
		try {
			prop.load(in);
			homePage = prop.getProperty("homepage");			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return homePage;
	}
	
	
	// Method used to load history from the file "history.txt"
	
	public ArrayList<String> loadHistory(ArrayList<String> historyStack ) throws FileNotFoundException{
		sc = new Scanner(new File("history.txt"));
		while(sc.hasNextLine()){
			historyStack.add(sc.nextLine());
			
		}
		return historyStack;
		
	}
	
	// Method used to load bookmarks from the file "bookmarks.txt"
	
	public ArrayList<String> loadBookmarks(ArrayList<String> bookmarksStack) throws FileNotFoundException{
		sc = new Scanner(new File("bookmarks.txt"));
		while(sc.hasNextLine()){
			bookmarksStack.add(sc.nextLine());
		}
		return bookmarksStack;
		
	}
	
	
	

}