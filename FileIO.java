import java.util.*;
import java.io.*;

/**
** Author: Cassandra Jacklya 
** Purpose: to handle the saving and loading of serialized objects and csv files
** Last modified on: 8th September 2020
**/

//obtained from my own previous code from PDI unit with a few modifications
//... to fit the practical needs

public class FileIO {
	DSABinarySearchTree csvTree = new DSABinarySearchTree();
	
	/**************************************************************************
	** Submodule name: readFile
	** Import: fileName (String)
	** Export: csvTree (DSABinarySearchTree)
	** Purpose: reads the csv file and returns the key + value as a binary tree
	**************************************************************************/
	public DSABinarySearchTree readFile(String fileName) {
	    String word = "";
	    FileInputStream fileStream = null;
	    InputStreamReader reader;
	    BufferedReader buffer;
	    String line;
	    try {
	        fileStream = new FileInputStream(fileName);
    		reader = new InputStreamReader(fileStream);
			buffer = new BufferedReader(reader);
			
			//reads the first line in the file
			line = buffer.readLine();
			while (line != null) {	
			    
				//if it is a non-empty line, file continues to be read
				if (line.length() == 0) {
		    	   line = buffer.readLine();	//just to make sure that "\n" lines are not considered as EOF
				}
				else {
					word = processLine(line);	
				}
				//continues to read the next non-empty line in the file
		        line = buffer.readLine();
			}
			fileStream.close();
			System.out.println("\n" + "File has been read and saved to tree");
		}
	    catch(IOException e) {
			if (fileStream != null) {
				try {
					fileStream.close();
				}
				catch(IOException e2) { 		     	 				
					System.out.println("Error: File not found or file data is invalid");
				}
			}
	    }
	    return csvTree;
	}    	
	
	
	/**********************************************************************
	** Submodule name: writeFile
	** Import: fileName (String), writeArray (String)
	** Export: none
	** Purpose: writes the key + value of the binary tree into a csv file
	**********************************************************************/
	public void writeFile(String fileName, String writeArray) {
	    String word = "";
	    String[] change = writeArray.split("\n");	//uses the "\n" as a delimiter to separate the different nodes in the tree
	    FileOutputStream fileStream = null;
	    PrintWriter printLine; 
	    try {
			fileStream = new FileOutputStream(fileName);
			printLine = new PrintWriter(fileStream);
			for (int count = 0; count < change.length; count++) {
				word = toFileString(change[count]);	//converts the string to a csv file string format
				printLine.println(word);	//enters the data into the file
			}
			printLine.close();
			System.out.println("\n" + "Data has been written to file");
	    }
	    catch(IOException e) {
			if (fileStream != null) {
				try {
					fileStream.close();
				} 
				catch (IOException e2) {  }
			}
			System.out.println("Error in writing into file. Sorry!");
	    } 
	}
	
	/******************************************************************************************
	** Submodule name: processLine
	** Import: line (String)
	** Export: word (String)
	** Purpose: converts csv string file format to readable format to be inserted into the tree
	*******************************************************************************************/
	public String processLine(String line) {
	    String word = "";
	    String[] splitLine;
	    splitLine = line.split(",");	//delimiter "," is used to separate key and data
	    for (int count = 1; count < splitLine.length; count++) {
	        word = word + splitLine[count] + " ";
	    }
		csvTree.insert(splitLine[0], word);		//splitLine[0] represents the key (student ID)
		return word;
	}

	/***************************************************************************
	** Submodule name: toFileString
	** Import: line (String)
	** Export: newLine (String)
	** Purpose: converts the string of the key + value of BST to csv file format
	****************************************************************************/
	private String toFileString(String line) {
	    String[] splitLine;
	    String newLine = "";
		System.out.println(line);
	    splitLine = line.split(",");	//makes "," as a delimiter as the output from BST consists of a comma
	    for (int count = 0; count < splitLine.length; count++) {
		    if (count == (splitLine.length-1)) {
				newLine = newLine + splitLine[count];	//checks if it is the last string in the line
		    }
		    else {
				newLine = newLine + splitLine[count] + ",";		//else a comma is added
		    }
		}
		return newLine;
	}
	
	/*************************************************************
	** Submodule name: save 
	** Import: objSave (DSABinarySearchTree), filename (String)
	** Export: none
	** Purpose: saves the object and serializes it into a file
	**************************************************************/
	public void save(DSABinarySearchTree objSave, String filename) {
		FileOutputStream fileStream;
		ObjectOutputStream objStream;
		try {
			fileStream = new FileOutputStream(filename);
			objStream = new ObjectOutputStream(fileStream);
			objStream.writeObject(objSave);
			objStream.close();
			fileStream.close();
			System.out.println("Object has been serialized");
		}
		catch (IOException e2) {
			System.out.println("Error in saving object to file");
		}
	}
	
	/*******************************************************************************
	** Submodule name: load
	** Import: filename (String)
	** Export: inObj (DSABinarySearchTree)
	** Purpose: loads the srialized file and outputs the key + value to the terminal
	********************************************************************************/
	public DSABinarySearchTree load(String filename) {
		FileInputStream fileStream;
		ObjectInputStream objStream;
		DSABinarySearchTree inObj = null;
		
		try {
			fileStream = new FileInputStream(filename);
			objStream = new ObjectInputStream(fileStream);
			inObj = (DSABinarySearchTree)objStream.readObject();
			objStream.close();
			fileStream.close();
			System.out.println("Object has been deserialized");
		}
		catch (ClassNotFoundException e) {
			System.out.println("Class does not exist");
		}
		catch (IOException e2) {
			System.out.println("Error in loading object from file");
		}
		return inObj;
	}
	
	/************************************************************************
	** Submodule name: displayList
	** Import: filename (String)
	** Export: none
	** Purpose: displays the serialized data in all different traversal orders
	**************************************************************************/
	public void displayList(String filename) {
		try {
			DSABinarySearchTree iTree = new DSABinarySearchTree();
			iTree = load(filename);
			System.out.println("In order display: ");
			iTree.display(1);
			System.out.println(" ");
			System.out.println("Pre order display: ");
			iTree.display(2);
			System.out.println(" ");
			System.out.println("Post order display: ");
			iTree.display(3);
		}
		catch (NullPointerException e) {
			System.out.println("Non-existent data in file");
		}
	}
}
