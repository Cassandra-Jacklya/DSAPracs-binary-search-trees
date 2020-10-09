import java.util.*;
import java.io.Serializable;

/**
** Author: Cassandra Jacklya
** Purpsose: create a binary tree and its methods
** Last modified on: 8th September 2020
**/

public class DSABinarySearchTree implements Serializable {
	
	private static final long serialversionUID = 3000L;
	private class DSATreeNode implements Serializable {
		private String mKey;
		private Object mValue;
		private DSATreeNode mLeftChild;
		private DSATreeNode mRightChild;
		
		public DSATreeNode(String inKey, Object inVal) {
			if (inKey == null) {
				throw new IllegalArgumentException("Key cannot be null");
			}
			else {
				mKey = inKey;
				mValue = inVal;
				mRightChild = null;
				mLeftChild = null;
			}
		}
		
		// ----------------- public methods ----------------------------------------
		public String getKey() {
			return mKey;
		}
		
		public Object getValue() {
			return mValue;
		}
		
		public DSATreeNode getLeft() {
			return mLeftChild;
		}
		
		public void setLeft(DSATreeNode newLeft) {
			mLeftChild = newLeft;
		}
		
		public DSATreeNode getRight() {
			return mRightChild;
		}
		
		public void setRight(DSATreeNode newRight) {
			mRightChild = newRight;
		}
	}
	
	// ------- start of the DSABinarySearchTree methods--------------------
	private DSATreeNode mRoot;
	
	public DSABinarySearchTree() {
		mRoot = null;
	}
	
	//-------------------------wrapper methods-----------------------------
	public Object find(String key) {
		Object value = null;
		try {
			value = findRec(key,mRoot);
		}
		catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
		return value;
	}
	
	public void insert(String key, Object value) {
		try {
			mRoot = insertRec(key,value,mRoot);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void delete(String key) {
		mRoot = deleteRec(key,mRoot);
	}
	
	public void display(int choice) {
		switch(choice) {
			case 1:
				inOrder(mRoot);
				break;
			case 2:
				preOrder(mRoot);
				break;
			case 3:
				postOrder(mRoot);
				break;
		}
	}
	
	public String toString(int option) {
		return toStringRec(mRoot,option);
	}
	
	public String min() {
		return minRec(mRoot);
	}
	
	public String max() {
		return maxRec(mRoot);
	}
	
	public int height() {
		return heightRec(mRoot);
	}
	
	public double balanced() {
        return balance(mRoot);
    }
	
	//------------------------private methods--------------------------------------
	private void preOrder(DSATreeNode node) {	//NLR -- node, left then right
		if (node != null) {
			System.out.print("Key: " + node.getKey() + " Value: " + node.getValue() + "\n");
			preOrder(node.getLeft());
			preOrder(node.getRight());
		}
	}
	
	private void postOrder(DSATreeNode node) {	//LRN -- left, right then node
		if (node != null) {
			postOrder(node.getLeft());
			postOrder(node.getRight());
			System.out.print("Key: " + node.getKey() + " Value: " + node.getValue() + "\n");
		}
	}
		
	private void inOrder(DSATreeNode node) {	//LNR -- left, node then right
		if (node != null) {
			inOrder(node.getLeft());
			System.out.print("Key: " + node.getKey() + " Value: " + node.getValue() + "\n");
			inOrder(node.getRight());
		}
	}
	
	private DSATreeNode deleteRec(String key, DSATreeNode cur) {
		if (cur == null) {
			throw new IllegalArgumentException("Key does not exist");	//checks if head is pointing to null
		}
		else {	
			if (key.equals(cur.getKey())) {	//if key is equal, value to removed is found
				cur = deleteNode(key,cur);
			}
			else if (key.compareTo(cur.getKey()) < 0) {	//smaller will recurse to left
				cur.setLeft(deleteRec(key,cur.getLeft()));
			}
			else {
				cur.setRight(deleteRec(key,cur.getRight()));	//larger will recurse to the right
			}
		}
		return cur;
	}
	
	private DSATreeNode deleteNode(String key, DSATreeNode delNode) {
		DSATreeNode updateNode = null;
		if ((delNode.getLeft() == null) && (delNode.getRight() == null)) {	//no children
			updateNode = null;
		}
		else if ((delNode.getLeft() != null) && (delNode.getRight() == null)) {	//only have the left child
			updateNode = delNode.getLeft();
		}
		else if ((delNode.getLeft() == null) && (delNode.getRight() != null)) {	//only have right child
			updateNode = delNode.getRight();
		}
		else {
			updateNode = promoteSuccessor(delNode.getRight());	//have two children
			if (updateNode != delNode.getRight()) {
				updateNode.setRight(delNode.getRight());
			}
			updateNode.setLeft(delNode.getLeft());
		}
		return updateNode;
	}
	
	private DSATreeNode promoteSuccessor(DSATreeNode cur) {	//finds the replacement from the left most of the right subtree
		DSATreeNode successor = cur;
		if (cur.getLeft() != null) {
			successor = promoteSuccessor(cur.getLeft());
			if (successor == cur.getLeft()) {
				cur.setLeft(successor.getRight());
			}
		}
		return successor;
	}
			
	private DSATreeNode insertRec(String key, Object data, DSATreeNode cur) {	
		DSATreeNode updateNode = cur;
		if (cur == null) {	//if tree is empty, insert the key + value
			updateNode = new DSATreeNode(key, data);
		}
		else {
			if (key.equals(cur.getKey())) {	//if the key already exists, abort the operation
				throw new IllegalArgumentException("Not able to be added as key already exists.");
			}
			else if (key.compareTo(cur.getKey()) < 0) {	//if key to be inserted is smaller than current node, recurse to left
				cur.setLeft(insertRec(key,data,cur.getLeft()));
			}
			else {
				cur.setRight(insertRec(key,data,cur.getRight()));	//else recurse to the right
			}
		}
		return updateNode;
	}
			
	private Object findRec(String key, DSATreeNode currNode) {
		Object value = null;
		if (currNode == null) {	//checks if BST is empty
			throw new NoSuchElementException("Key " + key + " not found");
		}
		else if (key.equals(currNode.getKey())) {	//base case - found target
			value = currNode.getValue();
		}
		else if (key.compareTo(currNode.getKey()) < 0) {	//if key to be found is smaller, recurse to left
			value = findRec(key,currNode.getLeft());
		}
		else {
			value = findRec(key,currNode.getRight());	//else recurse to the right
		}
		return value;
	}

	private String minRec(DSATreeNode curNode) {
		String minKey;
		if (curNode.getLeft() != null) {	//keep recursing to the left if node is not a leaf node
			minKey = minRec(curNode.getLeft());
		}
		else {
			minKey = curNode.getKey();	//otherwise, the minimum key is found
		}
		return minKey;
	}
	
	private String maxRec(DSATreeNode curNode) {
		String maxKey;
		if (curNode.getRight() != null) {	//keep recursing to the right if the node is not a leaf node
			maxKey = maxRec(curNode.getRight());
		}
		else {
			maxKey = curNode.getKey();	//otherwise, the maximum key is found
		}
		return maxKey;
	}
	
	private int heightRec(DSATreeNode curNode) {
		int height, leftHeight, rightHeight;
		if (curNode == null) {
			height = -1;	//no height if tree is empty
		}
		else {
			leftHeight = heightRec(curNode.getLeft());
			rightHeight = heightRec(curNode.getRight());
			if (leftHeight > rightHeight) {	//checks for the maximum height between the left and right subtree of the current node
				height = leftHeight + 1;	//plus one as the head has a height difference of 0 from the top of the BST
			}
			else {
				height = rightHeight + 1;
			}
		}
		return height;
	}
	
	private String toStringRec(DSATreeNode node, int opt) {
		String finalString = "";
		if (node == null) {
			finalString = "";
		}
		else {
			if (opt == 1) {
				finalString += toStringRec(node.getLeft(),1);
				finalString += node.getKey() + "," + node.getValue() + "\n";
				finalString += toStringRec(node.getRight(),1);
			}
			else if (opt == 2) {
				finalString += node.getKey() + "," + node.getValue() + "\n";
				finalString += toStringRec(node.getLeft(),2);
				finalString += toStringRec(node.getRight(),2);
			}
			else if (opt == 3) {
				finalString += toStringRec(node.getLeft(),3);
				finalString += toStringRec(node.getRight(),3);
				finalString += node.getKey() + "," + node.getValue() + "\n";
			}
			else {
				throw new IllegalArgumentException("Invalid option");
			}
		}
		return finalString;
	}
	
	 private double balance(DSATreeNode node) {
        int balance;
        double percentage;

        if (node == null) { 	//tree is balance if it is empty
            balance = 0;
        }
        else {
            balance = Math.abs(heightRec(node.getLeft()) - heightRec(node.getRight()));	//calculates the difference of the height of both left and right subtrees
        }
        percentage = (int)((1.0-((double)(balance/(double)height())))*100);
        return percentage;
    }
}
		
	
		