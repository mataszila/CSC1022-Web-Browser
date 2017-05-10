import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

// BookmarksWindow class is responsible of what happens when the user clicks "Bookmarks".

public class BookmarksWindow extends JFrame{
	
	
	// Instance variables
	
	private JList links;
	private DefaultListModel<String> model;
	
	
	private JButton goButton;
	private JButton deleteButton;
	
	// For BookmarksWindow to work, it needs to import several components.
	
	public BookmarksWindow(Bookmarks bookmarks, JEditorPane content,WriteData write,FileReader read) throws FileNotFoundException{
		
		
		//The name of JFrame is set.
		super("Bookmarks");
	
		// JFrame configurations
		
		setVisible(true);
		setLayout(new FlowLayout());
		setResizable(false);
		setSize(360,480);
		// JList and DefaultListModel are being initialized
		
		model = new DefaultListModel();
		links = new JList(model);
		
		/*The next 16 lines are used to set up the bookmarks*/
		
		// If bookmarks were loaded at least once before, clear the bookmarks (this is done to avoid duplicates)
		
		if(bookmarks.getBookmarksIndex()>0){
			bookmarks.getBookmarks().clear();
		}
		
		// Set bookmarks to be the ones that are in the "bookmarks.txt" file
		
		bookmarks.setBookmarks(read.loadBookmarks(bookmarks.getBookmarks()));
		
		// Add elements from the file to the DefaultListModel
		
		for(int i=0;i<bookmarks.getBookmarks().size();i++){
			model.addElement(bookmarks.getBookmarks().get(i));
		}
		// Increase BookmarksIndex by 1
		bookmarks.setBookmarksIndex(bookmarks.getBookmarksIndex()+1);
		
		
		goButton = new JButton("Go");
		deleteButton = new JButton("Delete");
		
		
		// JList settings
		
		links.setVisible(true);
		links.setVisibleRowCount(16);
		links.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		links.setBackground(Color.WHITE);
		setBackground(Color.GRAY);
		
		// Initializing buttons
		
		// Addding JList to JScrollPane, so that the JList could be scrolled.
		
		final JScrollPane scrollPane = new JScrollPane(links);
		
		links.setPreferredSize(new Dimension(320,600));
		scrollPane.setSize(new Dimension(360,640));
		
		
		getContentPane().add(BorderLayout.CENTER, scrollPane);
		
		// Adding buttons to the JFrame
		
		add(goButton);
		add(deleteButton);
		
		// "Go" button listener. Goes to the URL of the bookmark
		
		goButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				String url = links.getSelectedValue().toString();
				try {
					content.setPage(url);
					write.writeBookmarks(url);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		});
		
		
		// "Delete" button listener. Deletes a single bookmark from the list.
		
		deleteButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {

				int index = links.getSelectedIndex();
				if(index != -1){
					//Removes item from the DefaultListModel
					
					model.remove(index);
					try {
						//Removes item from the file
						write.removeBookmarks(index, bookmarks.getBookmarks());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//Removes item from the actual ArrayList of bookmarks
					bookmarks.getBookmarks().remove(index);
				}
			}
			
			
		});
		
		
	}
}
