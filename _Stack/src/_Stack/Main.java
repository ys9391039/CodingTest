package _Stack;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		ArrayStack stack = new ArrayStack(5);
		LinkedStack2 stack = new LinkedStack2();
		
		String Word = "hello";
		char[] charArray = Word.toCharArray();
		
		for (char x : charArray){
			//System.out.println(x);
			stack.push(x);
		}
		stack.printStack();
		System.out.println();
		
//		stack.push('n');
//		stack.push('o');
//		stack.push('o');
//		stack.push('n');
//		
		char x = (char)stack.pop();
		System.out.print(x);
		System.out.println();

		x = (char)stack.peek();
		System.out.print(x);
		System.out.println();
		
		stack.printStack();
		System.out.println();
		
		stack.delete();
		stack.delete();
		stack.delete();
		stack.delete();
		stack.delete();
		stack.delete();
		stack.delete();
		stack.delete();
		stack.delete();
		stack.delete();
		stack.delete();
		stack.delete();
		stack.delete();
		stack.delete();
		stack.push('n');
		stack.push('o');
		stack.push('o');
		stack.push('n');		
		stack.printStack();
		System.out.println();
		
	}

}
