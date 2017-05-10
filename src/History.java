import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;

// History class is used to store  history, using ArrayList, and to store the so called temporary history, which is stored in back and forward stacks. 
// They are used for user to navigate Back and Forward. HistoryIndex is used in the HistoryWindow class.
 
public class History {

	// Instance variables
	
	int historyIndex = 0;
	public Stack backStack = new Stack();
	public Stack forwardStack = new Stack();
	public ArrayList<String> historyStack = new ArrayList<String>();

	// get and set methods for the variables above.
	
	public int getHistoryIndex() {
		return historyIndex;
	}

	public void setHistoryIndex(int historyIndex) {
		this.historyIndex = historyIndex;
	}
	
	
	
	public Stack getBackStack() {
		return backStack;
	}

	public void setBackStack(Stack backStack) {
		this.backStack = backStack;
	}
	


	public Stack getForwardStack() {
		return forwardStack;
	}

	public void setForwardStack(Stack forwardStack) {
		this.forwardStack = forwardStack;
	}
	
	
	public ArrayList<String> getHistoryStack() {
		return historyStack;
	}

	public void setHistoryStack(ArrayList<String> historyStack) {
		this.historyStack = historyStack;
	}


	
	
	
	
	
}
