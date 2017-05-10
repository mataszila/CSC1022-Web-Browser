import java.awt.Color;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

//This class is responsible for managing the main JEditorPane, which displays the content

public class Content extends JEditorPane {

	public Content(){
	
		// Set background color
		setBackground(Color.GRAY);
		// Set content type to "text/html"
		setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
		
		
	
		
	}

	
	
	
	
}
