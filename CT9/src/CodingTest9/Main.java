package CodingTest9;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Stack stack = new Stack();
		stack.push("AAA");
		stack.push(111);
		stack.push("BBB");
		stack.push("CCC");
		stack.printStack();
		stack.pop();
		stack.printStack();
		
	}

}
