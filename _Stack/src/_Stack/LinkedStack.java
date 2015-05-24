package _Stack;

public class LinkedStack implements Stack{

	public class StackNode{
		Object data;
		StackNode link;
	}
	
	private StackNode top;
	
	public boolean isEmpty(){
		return (top == null);
	}
	
	public void push(char item){
		StackNode newNode = new StackNode();
		newNode.data = item;
		newNode.link = top;
		
		top = newNode;
	}
	
	public Object pop(){
		if (top == null)
			return 0;
		
		Object item = top.data;
		top = top.link;
		
		return item;
	}
	
	public void delete(){
		if (top != null){
			top = top.link;
		}
	}
	
	public Object peek(){
		if (top == null)
			return 0;
		
		return top.data;
	}
	
	public void printStack(){
		StackNode p = top;
		while (p != null){
			System.out.print(p.data);
			p = p.link;
		}
	}
}
