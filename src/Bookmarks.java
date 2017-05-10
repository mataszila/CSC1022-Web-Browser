import java.awt.List;
import java.util.ArrayList;

//Bookmarks class is responsible for getting and setting bookmarks, which are stored here and in a text file.


public class Bookmarks {

	public int getBookmarksIndex() {
		return bookmarksIndex;
	}

	public void setBookmarksIndex(int bookmarksIndex) {
		this.bookmarksIndex = bookmarksIndex;
	}

	int bookmarksIndex = 0;
	
	public ArrayList<String> getBookmarks() {
		return bookmarks;
	}

	public void setBookmarks(ArrayList<String> bookmarks) {
		this.bookmarks = bookmarks;
	}

	private ArrayList<String> bookmarks = new ArrayList<String>();
	
	
	
	
}
