import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

// HistoryWindow class is responsible for what happens when user clicks "History", the layout of the class is similar to that of BookmarksWindow class

public class HistoryWindow extends JFrame {
	
	
	//instance variables
	
	private JList links;
	
	private JButton clearHistoryButton;
	private JButton goButton;
	
	private JLabel historyClearedLabel;
	
	private DefaultListModel model;
	
	// For BookmarksWindow to work, it needs to import several components.
	
	public HistoryWindow(History history, JEditorPane content,WriteData write,FileReader read) throws FileNotFoundException{
		
		//JFrame properties
		
		super("History");
		setVisible(true);
		setLayout(new FlowLayout());
		setResizable(false);
		setSize(360,480);
			
		// If history loaded at least once before, clear the history (this is done to avoid duplicates)
		 
		model = new DefaultListModel();
		links  = new JList(model);
		
		if(history.getHistoryIndex() > 0){
			history.getHistoryStack().clear();
		}
		
		// Set history to be the one that is in the "history.txt" file

		
		history.setHistoryStack(read.loadHistory(history.getHistoryStack()));
		
		// Add elements from the file to the DefaultListModel
		
		for(int i=0;i<history.getHistoryStack().size();i++){
			model.addElement(history.getHistoryStack().get(i));
		}
		
		// Increase HistoryIndex by 1
		history.setHistoryIndex(history.getHistoryIndex()+1);
	
		// Initializing buttons
		
		goButton = new JButton("Go");
		clearHistoryButton = new JButton("Clear History");
		historyClearedLabel = new JLabel("History cleared!");

		// JList settings
		
		links.setVisible(true);
		links.setVisibleRowCount(16);
		links.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		links.setBackground(Color.WHITE);
		setBackground(Color.GRAY);
	
		// Adding JList to the ScrollPane, so that JList could be scrolled
		
		final JScrollPane scrollPane = new JScrollPane(links);
		links.setPreferredSize(new Dimension(320,600));
		scrollPane.setSize(new Dimension(360,640));
		
		// Adding JScrollPane to JFrame
		
		getContentPane().add(BorderLayout.CENTER, scrollPane);
		
		// Adding buttons to the JFrame
		
		add(goButton);
		add(clearHistoryButton);

		// "Go" button listener. Goes to the link selected in the JList.
		
		goButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				String url = links.getSelectedValue().toString();
				try {
					content.setPage(url);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		 
		// "Clear History" button listener. Deletes all history. 
		
		clearHistoryButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				model.removeAllElements();
				history.getHistoryStack().clear();
				add(historyClearedLabel);
				try {
					write.clearHistory();
					JOptionPane.showMessageDialog(content, "History Cleared");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
	}
	

}
