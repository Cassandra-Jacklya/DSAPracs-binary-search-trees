# DSAPracs-binary-search-trees
# Introduction
Specifically created to implement a binary search tree for testing

Motivation:  
This project has been created for the main purpose of my lab practical requirements (Data Structures and Algorithm)

## Code Style
Programmed this project using Java 

### Things required to run this program
Use any command line interface such as command prompt, or if you will be using Linux, just open the terminal :-  

$ javac *.java  
* this will be necessary as the files do not have its own class files yet  

$ java TestBinaryTree  
* runs the test harness program


#### Features of the program AND how to use it
There will be a main menu for the user to choose from its options

__Options are listed down below:__
1. Reading a CSV file
2. Reading a serialized file
3. Display the tree
4. Writing into a CSV file
5. Writing into a serialized file
6. Exit

### How to enter filenames:
Enter the name WITHOUT the file extension
e.g. If the csv file name is "Hello.csv" (WITH THE FILE EXTENSION) then you will only need to enter "Hello" whenever prompted  

Existing files given:
RandomNames7000.csv (provides all the key + value for the tree)  

## Explanations:  
Option 1:-
As the name suggests, this will just read the file (assuming the file is of .csv file extension) and import the values to create a tree

Option 2:-
By reading a serialized file, the entire tree will be displayed to the terminal

Option 3 :-
This will display the tree in the program to the terminal

Option 4 :-
This option allows the tree to be converted to a CSV file format

Option 5 :-
Allows the entire BST to be serialized into a file 

Option 6:
This will immediately exit the program

##### Extra features
Instead of displaying in inOrder tree traversal, I gave an option to either display in inOrder, preOrder or postOrder tree traversal.
