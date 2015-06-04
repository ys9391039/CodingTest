package CodingTest10;

public class Stack {

	class Node {
		Object data;
		Node link;
	}
	
	private Node top;
	
	public Stack(){
		this.top = null;
	}
	
	public boolean isEmpty(){
		return (top == null);
	}
	
	public void push(Object x){
		Node newNode = new Node();
		newNode.data = x;
		newNode.link = top;
		top = newNode;
	}
	
	public Object pop(){
		Object item = null; 
		if (top != null){
			item = top.data;
			top = top.link;
		}
		return item;
	}
	
	public Object peek(){
		Object item = null;
		if (top != null){
			item = top.data;
		}
		return item;
	}
	
	public void delete(){
		if (top != null)
			top = top.link;
	}
	
	public void printStack(){
		Node p = top;
		while(p != null){
			System.out.println(p.data);
			p = p.link;
		}
	}
	
}
