package _Queue;

interface Queue {
	boolean isEmpty();
	void enQueue(char item);
	char deQueue();
	void delete();
	char peek();
}

class Node{
	char data;
	Node link;
}

class LinkedQueue implements Queue{
	private Node front;
	private Node near;
	
	public LinkedQueue(){
		front = null;
		near = null;
	}
	
	public boolean isEmpty(){
		return (front == null);
	}
	
	public void enQueue(char x){
		Node newNode = new Node();
		newNode.data = x;
		newNode.link = null;
		
		if(isEmpty()){
			front = newNode;
			near = newNode;
		}else{
			near.link = newNode;
			near = newNode;
		}
	}
	
	public char deQueue(){
		char item = 0;
		if(!isEmpty()){
			item = front.data;
			front = front.link;
			if (front == null)
				near = null;
		}
		return item;
	}
	
	public void delete(){
		if(!isEmpty()){
			front = front.link;
			if (front == null)
				near = null;
		}
	}
	
	public char peek(){
		char item = 0;
		if(!isEmpty()){
			item = front.data;
		}
		return item;
	}
	
	public void printQueue(){
		if(!isEmpty()){
			Node tmp = front;
			while(tmp != null){
				System.out.print(tmp.data);
				tmp = tmp.link;
			}
			System.out.println();
		}
	}
	
}
