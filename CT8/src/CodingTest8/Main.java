package CodingTest8;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BST bst = new BST();
		bst.insertBST('G');
		bst.insertBST('D');
		bst.insertBST('I');
		bst.insertBST('B');
		bst.insertBST('E');
		bst.insertBST('A');
		bst.insertBST('C');
		bst.insertBST('F');
		bst.insertBST('H');
		bst.insertBST('M');
		bst.insertBST('J');
		bst.insertBST('K');
		bst.insertBST('L');
		bst.insertBST('M');
		bst.insertBST('N');
		bst.insertBST('Q');
		//bst.searchBST('C');
		bst.searchBST('B');
		bst.printBST();
		bst.deleteBST('G');
		bst.printBST();
	}

}
