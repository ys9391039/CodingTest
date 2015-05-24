package _Stack;

public class LinkedStack2 {

	public class Stack{
		Object data;
		Stack link;
	}
	
	private Stack top;
	
	public boolean isEmpty(){
		return (top == null);
	}

	public void push(Object data){
		Stack newStack = new Stack();
		newStack.data = data;
		newStack.link = top;
		top = newStack;
	}
	
	public Object pop(){
		Object data = null;
		if(!isEmpty()){
			data = top.data;
			top = top.link;
		}
		return data;
	}
	
	public Object peek(){
		Object data = null;
		if (!isEmpty())
			data = top.data;
		
		return data;
	}
	
	public void delete(){
		if (!isEmpty())
			top = top.link;
	}
	
	public void printStack(){
		Stack p = top;
		while (p != null){
			System.out.print(p.data);
			p = p.link;
		}
	}
}
