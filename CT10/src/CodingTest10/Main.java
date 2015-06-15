package CodingTest10;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Stack st = new Stack();
		String s = "noon";
		char[] sArr = s.toCharArray();
		
		for (char c:sArr){
			st.push(c);
		}
		
//		st.printStack();
//		char r;
//		r = (char)st.pop();
		while(!st.isEmpty()){
			System.out.print(st.pop());
		}
	}

}
