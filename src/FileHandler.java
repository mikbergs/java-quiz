import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
*	FileHandler.java
*	Author:	Mikael Bergström
*	Version: 0.1 2016-08-23
*/
public class FileHandler {
	/*
	 * Loads the file with the questions.
	 * @ questionArray - Arrayist <string>
	 * @ return questionArray
	 */
	public ArrayList<String> loadFile() throws IOException{
	ArrayList<String> questionArray = new ArrayList<>();
	BufferedReader bReader;
	try {
		bReader = new BufferedReader(new FileReader("questions.txt"));
		String line = bReader.readLine();
		while (bReader.readLine() != null){
			line = bReader.readLine();
			questionArray.add(line); //The question
			line = bReader.readLine(); 
			questionArray.add(line); 				//Alternative 1
			line = bReader.readLine();
			questionArray.add(line);				//Alternative x
			line = bReader.readLine();
			questionArray.add(line);				//Alternative 2
			line = bReader.readLine();
			questionArray.add(line);				//adds the correct answer. (1/x/2)
		}	
		bReader.close();
		return questionArray;
	} catch (FileNotFoundException e) {
			e.printStackTrace();
			return questionArray;
		}
		
	}
}
