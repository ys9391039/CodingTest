package _Stack;

public class ArrayStack2 {

	private int top;
	private int stackSize;
	private Object[] dataArray;
	
	public ArrayStack2(int size){
		top = -1;
		stackSize = size;
		dataArray = new Object[stackSize];
	}
	
	public boolean isEmpty(){
		return (top < 0);
	}
	
	public boolean isFull(){
		return (top >= stackSize);
	}
	
	public void push(Object data){
		if (!isFull()){
			dataArray[++top] = data;
		}
	}
	
	public Object pop(){
		Object data = null;
		if(!isEmpty()){
			data = dataArray[top--];
		}
		return data;
	}
	
	public Object peek(){
		if(!isEmpty())
			return dataArray[top];
		else
			return null;
	}
	
	public void delete(){
		if(!isEmpty())
			top--;
	}
}
