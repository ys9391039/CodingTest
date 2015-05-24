package CodingTest3;

public class BinarySearchTree {

	private TreeNode root;// = new TreeNode();
	
	public void insertBST(char x){
//		if (root == null)
//			System.out.println("root is null");
//		else
//			System.out.println("root:"+root.data);
		root = insertKey(root, x);
	}
	
	private TreeNode insertKey(TreeNode root, char x){
		TreeNode p = root;
		TreeNode newNode = new TreeNode();
		newNode.data = x;
		newNode.left = null;
		newNode.right = null;
		
		if (p == null){
			//System.out.println("root is null");
			return newNode;
		}else if (p.data > x){
			p.left = insertKey(p.left, x);
			return p;
		}else if (p.data < x){
			p.right = insertKey(p.right, x);
			return p;
		}else
			return p;  
	}
	
	public void printBST(){
		inOrder(root);
	}
	
	private void inOrder(TreeNode p){
		if (p != null){
			inOrder(p.left);
			System.out.print(p.data);
			inOrder(p.right);
		}
	}
	
	public void searchBST(char x){
		TreeNode p = searchKey(root, x);
		if (p != null)
			System.out.print("Search "+p.data);
		else
			System.out.print("No");
			
	}
	
	private TreeNode searchKey(TreeNode root, char x){
		TreeNode p = root;

		while (p != null){
			if (p.data > x) p=p.left;
			else if (p.data < x) p=p.right;
			else return p;
		}
		return p;
	}
	
	public void deleteBST(char x){
		deleteKey(root, x);
	}
	
	public void deleteKey(TreeNode root, char x){
		//TreeNode p = (TreeNode) root.clone();
		TreeNode p = root;
		TreeNode parent = root;
		System.out.println("p.data:"+p.data);
		while (p.data != x){
			if (p != null){
				parent = p;
				if (p.data > x) p=p.left;
				else p=p.right;
			}
		}
		
		if (p.left != null && p.right != null){ // 양쪽 자식이 있는 노드
			System.out.println("p.left:"+p.left.data);
			TreeNode max = leftMaxKey(p.left);
			System.out.println("max:"+max.data);
			p.data = max.data;
			deleteKey(p.left, max.data);			

		}else if (p.left != null){ // 왼쪽 자식이 있는 노드
			if (parent.left == p)
				parent.left = p.left;
			else
				parent.right = p.left;
		}else if (p.right != null){ // 오른쪽 자식이 있는 노드
			if (parent.left == p)
				parent.left = p.right;
			else
				parent.right = p.right;
		}else{ // 자식이 없는 노드
			if (p == root)
				p = null;
			else if (parent.left == p)
				parent.left = null;
			else
				parent.right = null;
		}
	}
	
	public TreeNode leftMaxKey(TreeNode p){
		while(p != null){
			if (p.right != null)
				p=p.right;
			else
				break;
		}
		return p;
	}
}
