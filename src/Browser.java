import java.io.IOException;
import java.io.InputStream;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Properties;

/*This is the main class of this project. It initializes all of the instance variables (FileReader, WriteData,Content,History,Bookmarks,Toolbar) 
 * and puts them into JFrame's constructor. Upon initializion of new MainWindow() object, the browser is visible to the user. All the proccesses that 
 * are happening in this project take place in other classes. Browser is the main class.
*/


public class Browser {

	
	public static void main (String [] args) throws IOException{
		
		
		FileReader read = new FileReader();
		WriteData write = new WriteData();
		
		JEditorPane content = new Content();
		

		
		History history = new History();
		Bookmarks bookmarks = new Bookmarks();
		
		
		
		
		JToolBar toolbar = new Toolbar(content,history,bookmarks,read,write);

		
		
		JFrame browser = new MainWindow("CSC1022 Web Browser",toolbar,content, history,read,write);
		

		// Program stops when "X" is pressed
		
		browser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		browser.setVisible(true);
		
		
	}
}
