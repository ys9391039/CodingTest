package _Queue;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LinkedQueue lq = new LinkedQueue();
		lq.enQueue('a');
		lq.enQueue('a');
		lq.enQueue('a');
		lq.enQueue('a');
		lq.enQueue('a');
		lq.printQueue();
		lq.peek();
		lq.printQueue();
		char item = lq.deQueue();
		System.out.println("item:"+item);
		lq.printQueue();
			
	}

}
