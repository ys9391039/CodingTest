package CodingTest3;

public class BST {
	
	private Node root;
	
	public void insertBST(char x){
		root = insertKey(root, x);
	}
	
	private Node insertKey(Node p, char x){
		if (p == null){
			Node newNode = new Node();
			newNode.data = x;
			newNode.left = null;
			newNode.right = null;
			p = newNode;
		}
		else if(p.data > x)
			p.left = insertKey(p.left, x);
		else if (p.data < x)
			p.right = insertKey(p.right, x);
		
		return p;
	}
	
	public void searchBST(char x){
		Node p = searchKey(x);
		if (p != null)
			System.out.println("search:"+p.data);
		else
			System.out.println("No data");
	}
	
	private Node searchKey(char x){
		Node p = root;
		while(p != null){
			if (p.data == x) break;
			else if (p.data > x) p = p.left;
			else p = p.right;
		}
		return p;
	}
	
	public void deleteBST(char x){
		deleteKey(root, x);
	}
	
	private void deleteKey(Node p, char x){
		Node parent = p;
		
		while(p != null && p.data != x){
			parent = p;
			if (p.data > x) p = p.left;
			else p = p.right;
		}
		if (p == null) return;
		
		if (p.left != null && p.right != null){
			Node candidate = selectLeftMax(p.left);
			p.data = candidate.data;
			deleteKey(p.left, candidate.data);
		}else if (p.left != null){
			if (parent.left == p) parent.left = p.left;
			else parent.right = p.left;
		}else if (p.right != null){
			if (parent.left == p) parent.left = p.right;
			else parent.right = p.right;
		}else{
			if (p == root) root = null;
			else if (parent.left == p) parent.left = null;
			else parent.right = null;
		}
	}
	
	private Node selectLeftMax(Node p){
		while (p != null){
			if (p.right == null)
				break;
			else
				p = p.right;
		}
		return p;
	}
	
	public void printBST(){
		inOrder(root);
	}
	
	private void inOrder(Node p){
		if (p != null){
			inOrder(p.left);
			System.out.print(p.data);
			inOrder(p.right);
		}
	}
	
//		public void mergeBST(Node a, Node b){
//			merge(a, b);
//		}
	//	
//		private Node merge (Node head1, Node head2)
//		{
//			if (head1 == null) return head2;
//			if (head2 == null) return head1;
//			
//			if (head1.data > head2.data)
//			{
//				Node temp = head2.right;
//				head2.right = null;
//				head1.left = merge (head1.left, head2);
//				head1 = merge (head1, temp);
//				return head1;
//			}
//			else if (head1.data < head2.data)
//			{
//				Node temp = head2.left;
//				head2.left = null;
//				head1.right = merge (head1.right, head2);
//				head1 = merge (head1, temp);
//				return head1;
//			}
//			else
//			{
//				head1.left = merge (head1.left, head2.left);
//				head1.right = merge (head1.right, head2.right);
//				
//				return head1;
//			}
//		}
}
