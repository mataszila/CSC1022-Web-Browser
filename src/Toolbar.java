import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

//Toolbar class is used to initialize the key part of the program - the toolbar.

public class Toolbar extends JToolBar{
	
	// instance variables

	static String url;
	
	static JTextField addressBar;
	
	private JButton refreshButton;
	private JButton goButton;
	private JButton homeButton;
	private JButton backButton;
	private JButton forwardButton;
	private JButton historyButton;
	private JButton bookmarksButton;
	private JButton	addToBookmarksButton;
	
	public Toolbar(JEditorPane content, History history, Bookmarks bookmarks,FileReader read,WriteData write){
		
		// JToolBar settings
		
		setBackground(Color.WHITE);
		setLayout(new FlowLayout());
		setFloatable(false);
		
		// initializing Swing objects
		
		addressBar = new JTextField(125);
		refreshButton = new JButton("Refresh");
		goButton  = new JButton("Go");
		homeButton = new JButton("Home");
		backButton = new JButton("Back");
		forwardButton = new JButton("Forward");
		historyButton = new JButton("History");
		bookmarksButton = new JButton("Bookmarks");
		addToBookmarksButton = new JButton("Add to Bookmarks");
		
		//Adding buttons to the JToolbar
		
		add(refreshButton);
		add(homeButton);
		add(addressBar);
		add(goButton);
		add(backButton);
		add(forwardButton);
		add(historyButton);
		add(bookmarksButton);
		add(addToBookmarksButton);
		
		
		// Address Bar action listener. Responds when "Enter" is clicked within the address bar
		
		
		
		addressBar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				url = addressBar.getText();
				try {
					// Get JEditorPane to display selected URL
					content.setPage(url);
					// Set address bar text to be that of current page's URL
					addressBar.setText(url);
					// Add url to the Back Stack
					history.getBackStack().push(url);
					// Write url to the history file
					write.writeHistory(url);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(content, "Incorrect URL. Try again!");
				}
			}
		});
		
		// "Go" Button action listener.

		
		goButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// Set text in address bar to be URL
				url = addressBar.getText();
				try {
					// Get JEditorPane to display selected URL
					content.setPage(url);
					// Set address bar text to be that of current page's URL
					addressBar.setText(url);
					// Add url to the Back Stack
					history.getBackStack().push(url);
					// Write url to the history file
					write.writeHistory(url);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(content, "Incorrect URL. Try again!");
				}
				
			}
		});
		
		// "Home" Button action listener
		
		homeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// Get home page URL from config.properties file
				url = addressBar.getText();
				String homepage = read.getHomePage();
				try {
					// Get JEditorPane to display selected URL
					content.setPage(homepage);
					// Set address bar text to be that of current page's URL
					// If url isn't the same as homepage, Write url to the history file	
					if(homepage != url){
					write.writeHistory(homepage);
					}
					url = homepage;
					addressBar.setText(url);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(content, "Incorrect URL. Please check the configuration file and  try again!");
				}
			}
		});
		
		// "History" button action listener. Creates a new HistoryWindow object
		
		historyButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println(history.getBackStack());
				try {
					HistoryWindow showHistory = new HistoryWindow(history,content,write,read);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		// "Back" button action listener. Goes back one page
		
		backButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				url = (String) history.getBackStack().peek();

				try {
				
					// Get JEditor to display the last url in the back stack
					content.setPage((String) url);
					// Push the url to the Forward Stack
					history.getForwardStack().push(url);
					// Pop the Back Stack
					history.getBackStack().pop();
					
					if(history.getBackStack().isEmpty()){
						JOptionPane.showMessageDialog(null, "You can't go back.");
					}
					
					
					// Set address bar text to be that of current page's URL
					addressBar.setText(url);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		
		// "Forward" button action listener. Goes forward by one page
		
		forwardButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				url = (String) history.getForwardStack().peek();
				
				try {
					// Get JEditorPane to display last item in the Forward Stack
					if(url!=null){
					content.setPage( url);
					}
					//Push url to the Back Stack
					history.getBackStack().push(url);
					//Pop url from the Forward Stack
					history.getForwardStack().pop();
					
					if(history.getForwardStack().isEmpty()){
						JOptionPane.showMessageDialog(null, "You can't go forward.");
					}
					// Set address bar text to be that of current page's URL 
					addressBar.setText(url);
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		// "Refresh" button action listener. Refreshes the page
		
		refreshButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					content.setPage(url);
					addressBar.setText(url);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		// "Bookmarks" button action listener. Creates a new BookmarksWindow object.
		
		bookmarksButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println(history.getBackStack());
				try {
					BookmarksWindow showBookmarks = new BookmarksWindow(bookmarks,content,write,read);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		// "Add to bookmarks" action listener. Adds the current page to bookmarks.
		
		addToBookmarksButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				bookmarks.getBookmarks().add(addressBar.getText());
				try {
					write.writeBookmarks(addressBar.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Bookmark added!");
			}
		});
		
		
	}

	
	// get and set methods
	
	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}


	public static JTextField getAddressBar() {
		return addressBar;
	}

	public void setAddressBar(JTextField addressBar) {
		this.addressBar = addressBar;
	}

	public JButton getRefreshButton() {
		return refreshButton;
	}

	public void setRefreshButton(JButton refreshButton) {
		this.refreshButton = refreshButton;
	}

	public JButton getGoButton() {
		return goButton;
	}

	public void setGoButton(JButton goButton) {
		this.goButton = goButton;
	}

	public JButton getHomeButton() {
		return homeButton;
	}

	public void setHomeButton(JButton homeButton) {
		this.homeButton = homeButton;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
	}

	public JButton getForwardButton() {
		return forwardButton;
	}

	public void setForwardButton(JButton forwardButton) {
		this.forwardButton = forwardButton;
	}

	public JButton getHistoryButton() {
		return historyButton;
	}

	public void setHistoryButton(JButton historyButton) {
		this.historyButton = historyButton;
	}

	public JButton getBookmarksButton() {
		return bookmarksButton;
	}

	public void setBookmarksButton(JButton bookmarksButton) {
		this.bookmarksButton = bookmarksButton;
	}

	public static void setAddressBarUrl(String url){
		addressBar.setText(url);
	}
	
	
	
}
