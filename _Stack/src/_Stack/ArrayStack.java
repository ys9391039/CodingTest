package _Stack;

public class ArrayStack implements Stack{

	private int top;
	private int stackSize;
	private char[] itemArray;
	
	public ArrayStack(int stackSize){
		top = -1;
		this.stackSize = stackSize;
		itemArray = new char[this.stackSize];
	}
	
	public boolean isEmpty(){
		return (top < 0);
	}
	
	public void push(char item){
		if (top == this.stackSize)
			System.out.println("Stack is full");
		else
			itemArray[++top] = item;
	}
	
	public Object pop(){
		if (isEmpty()){
			System.out.println("Stack is empty");
			return 0;
		}
		return itemArray[top--];
	}
	
	public void delete(){
		if (isEmpty())
			System.out.println("Stack is empty");
		else
			top--;
	}
	
	public Object peek(){
		if (isEmpty()){
			System.out.println("Stack is empty");
			return 0;
		}
		return itemArray[top];
	}
	
	public void printStack(){
		if (isEmpty())
			System.out.println("Stack is empty");
		else{
//			for(char x : itemArray){
//				System.out.print(x);
//			}
			for(int i=0; i<=top; i++){
				System.out.print(itemArray[i]);
			}
		}
		
	}
}
