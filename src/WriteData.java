import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;

// WriteData class is used to write/change data in files.

public class WriteData {
	
		// instance variables
	
		BufferedWriter writer;
	

		public WriteData() throws IOException{
		}
			
		
		// writeHistory methods write one url to a file at a time
		
		public void writeHistory(String historyLink) throws IOException{
				writer = new BufferedWriter(new FileWriter("history.txt",true));
				writer.write(historyLink);
				writer.newLine();
				writer.flush();
				writer.close();

		}
		
		// clearHistory method deletes everything from the history file
		
		public void clearHistory() throws IOException{
			writer = new BufferedWriter(new FileWriter("history.txt",false));
		}
		
		// writeBookmarks method writes one url to the file at a time
		
		public void writeBookmarks(String bookmarkLinks) throws IOException{
			writer = new BufferedWriter(new FileWriter("bookmarks.txt",true));
			writer.write(bookmarkLinks);
			writer.newLine();
			writer.flush();
			writer.close();
			
		}
		
		// clearBookmarks method deletes everything from the bookmarks file
		
		public void clearBookmarks() throws IOException{
			writer = new BufferedWriter(new FileWriter("bookmarks.txt",false));
		}
		
		// removeBookmarks methods deletes one selected url at the time
		
		public void removeBookmarks(int index, ArrayList<String> bookmarks) throws IOException{
			writer = new BufferedWriter(new FileWriter("bookmarks.txt",false));
				for(int i=0;i<bookmarks.size();i++){
					if(i!=index){
						writer.write(bookmarks.get(i));
						writer.newLine();
				}
			}
				writer.flush();
				writer.close();
				
		}
		


	
}
