import java.util.*;
import java.io.*;
/**
** Author: Cassandra Jacklya
** Purpose: Main where all the functions of the DSABinarySearchTree are tested"
** Last modified on: 8th September 2020
**/

public class TestBinaryTree {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int choice, num;
		
		DSABinarySearchTree tree = new DSABinarySearchTree();
		System.out.println("Binary Tree Example");
		
		System.out.println(" ");
		System.out.println("--------------------------------------------------");
		
		try {
			System.out.print("Testing insert function: ");
			tree.insert("c", 2);
			tree.insert("e", 4);
			tree.insert("i", 8);
			tree.insert("g", 6);
			tree.insert("h", 7);
			tree.insert("d", 3);
			tree.insert("j", 9);
			tree.insert("k", 10);
			tree.insert("f", 5);
			tree.insert("b", 1);
			tree.insert("a", 0);
			tree.insert("l", 11);
			tree.insert("o", 15);
			tree.insert("n", 13);
			tree.insert("m", 12);
			tree.insert("p", 20);
			
			//before deletion
			/***************************************************************
			*                           2                                  *
			*                          / \                                 *
			*                         1   4                                *
			*                        /   / \                               *
			*						0	3   8                              * 
			*							   / \                             *
			*							  6   9                            *
			*							 / \   \                           *
			*							5   7  10                          *
			*						             \                         *
			*                                    11                        *
            *                                      \                       *
			*									   15                      *
			*									   / \                     *
			*									  13 20                    *
			*									  /                        *
			*									 12                        *
			****************************************************************/
			System.out.println("PASSED!");
		}
		catch (IllegalArgumentException e) {
			System.out.println("FAIL");
		}
		
		System.out.println(" ");
		System.out.println("--------------------------------------------------");
		System.out.print("Testing delete function: ");
		try {
			tree.delete("a");
			tree.delete("o");
			
			//after deletion
			/***************************************************************
			*                           2                                  *
			*                          / \                                 *
			*                         1   4                                *
			*                            / \                               *
			*						 	3   8                              * 
			*							   / \                             *
			*							  6   9                            *
			*							 / \   \                           *
			*							5   7  10                          *
			*						             \                         *
			*                                    11                        *
            *                                      \                       *
			*									   20                      *
			*									   /                       *
			*									  13                       *
			*									  /                        *
			*									 12                        *
			****************************************************************/
			
			System.out.println("PASSED!");
		}
		catch (IllegalArgumentException e2) {
			System.out.println("FAIL");
		}
		
		System.out.println("--------------------------------------------------");
		System.out.println("Testing find function");
		System.out.println("Should be found: " + tree.find("f")+ "\n");
		System.out.print("Cannot be found: " + tree.find("o") + " " );
		
		System.out.println(" ");
		System.out.println("--------------------------------------------------");
		System.out.println("Testing min and max functions");
		System.out.println("Max:");
		System.out.println("Key = " + tree.max());
		System.out.println("\n" + "Min:");
		System.out.println("Key = " + tree.min());
		
		System.out.println(" ");
		System.out.println("--------------------------------------------------");
		System.out.println("Testing height function");
		System.out.println("Height of tree: " + tree.height());
		
		System.out.println(" ");
		System.out.println("--------------------------------------------------");
		System.out.println("Check if tree is balanced");
		System.out.println("Balanced percentage: " + tree.balanced());
		
		String filename;
		FileIO iFile = new FileIO();
		boolean valid = true;
		DSABinarySearchTree array = new DSABinarySearchTree();
		
		try {
			do {
				//displays the menu to the user via terminal
				System.out.println(" ");
				System.out.println("Main menu. Choose a number");
				System.out.println("1. Read a CSV file");
				System.out.println("2. Read a serialized file");
				System.out.println("3. Display the tree");
				System.out.println("4. Write into CSV file");
				System.out.println("5. Write into serialized file");
				System.out.println("6. Exit");
				choice = sc.nextInt();
				switch (choice) {
					case 1: 
						System.out.print("Enter the filename to read: ");
						sc.nextLine();
						filename = sc.nextLine();
						array = iFile.readFile(filename+".csv");
						break;
			
					case 2:
						System.out.print("Enter the serialized file to read: ");
						sc.nextLine();
						filename = sc.nextLine();
						iFile.displayList(filename+".txt");
						break;
			
					case 3: 
						num = displayRec(tree);
						break;
			
					case 4:
						System.out.print("Enter a filename to save to: ");
						sc.nextLine();
						filename = sc.nextLine();
						num = displayRec();
						iFile.writeFile(filename+".csv",array.toString(num));
						break;
			
					case 5:
						System.out.print("Enter a filename to serialize the object: ");
						sc.nextLine();
						filename = sc.nextLine();
						iFile.save(tree,filename+".txt");
						break;
					
					case 6:
						System.out.println("Goodbye!");
						valid = false;
						break; 
						
					default:
						valid = false;
						throw new IllegalArgumentException("Wrong option!");
				}
			} while (valid == true);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		catch (NullPointerException e2) {
			System.out.println("Empty array. Cannot write to file");
		}
		catch (InputMismatchException e3) {
			System.out.println("Choice must be an integer");
		}
	}
	
	//overload methods
	private static int displayRec(DSABinarySearchTree iTree) {	//prints out the data
		Scanner sc = new Scanner(System.in);
		int show;
		System.out.println("1. inOrder");
		System.out.println("2. preOrder");
		System.out.println("3. postOrder");
		show = sc.nextInt();
		iTree.display(show);
		return show;
	}
	
	private static int displayRec() {	//does not print out the data 
		Scanner sc = new Scanner(System.in);
		int show;
		System.out.println("1. inOrder");
		System.out.println("2. preOrder");
		System.out.println("3. postOrder");
		show = sc.nextInt();
		return show;
	}
}