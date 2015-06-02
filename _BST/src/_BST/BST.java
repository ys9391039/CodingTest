package _BST;

public class BST {
	
	class Node{
		char data;
		Node left;
		Node right;
	}
	
	private Node root;
	
	public void insertBST(char x){
		root = insertNode(root, x);
	}
	
	private Node insertNode(Node p, char x){
		Node newNode = new Node();
		newNode.data = x;
		newNode.left = null;
		newNode.right = null;
		
		if (p == null){
			return newNode;
		}else if (p.data > x){
			p.left = insertNode(p.left, x);
			return p;
		}else if (p.data < x){
			p.right = insertNode(p.right, x);
			return p;
		}else {
			return p;
		}
	}
	
	public void searchBST(char x){
		Node p = searchNode(x);
		if (p != null)
			System.out.println("Search :"+p.data);
		else
			System.out.println("Search fail :"+x);
	}
	
	private Node searchNode(char x){
		Node p = root;
		while (p != null){
			if (p.data > x) p = p.left;
			else if (p.data < x) p = p.right;
			else break;
		}
		return p;
	}
	
	public void deleteBST(char x){
		deleteNode(root, x);
	}
	
	private void deleteNode(Node p, char x){
		Node parent = p;
		
		while (p != null && p.data != x){
			parent = p;
			if (p.data > x) p = p.left;
			else p = p.right;
		}
		
		if (p.left != null && p.right != null){
			Node maxLeftNode = selectMaxLeftNode(p.left);
			p.data = maxLeftNode.data;
			deleteNode(p.left, maxLeftNode.data);
		}else if (p.left != null){
			if (parent.left != null) parent.left = p.left;
			else parent.right = p.left;
		}else if (p.right != null){
			if (parent.left != null) parent.left = p.right;
			else parent.right = p.right;
		}else{
			if (parent.left != null) parent.left = null;
			else parent.right = null;
		}
	}
	
	private Node selectMaxLeftNode(Node p){
		while (p != null){
			if (p.right != null)
				p = p.right;
			else
				break;
		}
		return p;
	}
	
	public void printBST(){
		inOrder(root);
		System.out.println();
	}
	
	private void inOrder(Node p){
		if (p != null) {
			inOrder(p.left);
			System.out.print(p.data);
			inOrder(p.right);
		}
	}
	
	

}
