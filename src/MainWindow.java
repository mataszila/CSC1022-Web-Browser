import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;


import javax.swing.*;

/* MainWindow class is responsible for creating a JFrame, and storing all of the contents in it.
*/


public class MainWindow extends JFrame{

	// importing all the necessary components for the MainWindow class
	
	public MainWindow(String name, JToolBar toolbar, JEditorPane content, History history,FileReader read,WriteData write){
		
		//set Browser to full screen
				
		setSize(1920,1080);
		
		setTitle("CSC1022 Web Browser by Matas Zilaitis");
				
		
		/* The program tries to read the homepage from a file, and the address bar text is being set to that of homepage URL	
		 * The program writes the URL to the history file.
		 * If there are any problems, an exception is being thrown.
		 */		
		try {
			content.setPage(read.getHomePage());
			Toolbar.setAddressBarUrl(read.getHomePage());
			write.writeHistory(read.getHomePage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(content, "Incorrect URL! Check configuration file and try again!");
		}
		
		// Content in the page itself cannot be edited
		
		content.setEditable(false);

		
		// One of the most important methods in the whole program, makes hyperlinks clickable 
		
		content.addHyperlinkListener(new HyperlinkListener(){
			public void hyperlinkUpdate(HyperlinkEvent event){
				if(event.getEventType()==HyperlinkEvent.EventType.ACTIVATED){
					
					//What happens on click
					
					try {
						
						
						content.setPage(event.getURL().toString());
						Toolbar.setAddressBarUrl(event.getURL().toString());
						
											
						//Add url to ArrayList, so they can be modified in program.
						history.getHistoryStack().add(event.getURL().toString());
						
						// Add url to the history file, so they can be seen by user upon request.
						write.writeHistory(event.getURL().toString());
						// Add url to the Back Stack, so it can be popped when user wants to go back.
						history.getBackStack().push(event.getURL().toString());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		
		setBackground(Color.BLACK);
		
		
		// JEditorPane is put into JScrollPane, which makes the content scrollable.
		
		final JScrollPane scrollPane=  new JScrollPane(content);
		
		
		//BorderLayout setups the page, so that toolbar is at the top and the content is at the bottom
		
		getContentPane().add(BorderLayout.NORTH, toolbar);
		getContentPane().add(BorderLayout.CENTER, scrollPane);
		
		
		
	
	}

	


	
}
