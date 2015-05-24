package CodingTest4;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		int wordCount = 5;
		
		String wordList[] = {"Hello", "Coffee", "Effect", "Office", "Korea"};
		int count = 0;
		//String useWord = "";
		LinkedStack stack = new LinkedStack();
		Scanner scn = new Scanner(System.in);
		while(count<=wordCount){
			System.out.println("word?");
			String word = scn.next();
			//System.out.println("word:"+word);
			//String word = "Check";
			
			
			byte lastChar = (byte)word.toCharArray()[word.length()-1];
			if (lastChar >= 97 && lastChar <= 122) lastChar = (byte) (lastChar - 32);
			
			//첫 단어가 아닌 경우 이전 단어의 끝말을 이었는지 체크
			if (!stack.isEmpty()){
				String lastWord = (String)stack.peek();
				byte lastWordChar = (byte)lastWord.toCharArray()[lastWord.length()-1];
				if (lastWordChar >= 97 && lastWordChar <= 122) lastWordChar = (byte) (lastWordChar - 32);
				
				byte firstWordChar = (byte)word.toCharArray()[0];
				if (firstWordChar >= 97 && firstWordChar <= 122) firstWordChar = (byte) (firstWordChar - 32);
				
				if (lastWordChar != firstWordChar){
					System.out.println("Wrong answer, plz retry");
					continue;
				}
			}
			
//			System.out.println(lastChar);
			String answerWord = "";
			
			boolean used = false;
			for (String myWord: wordList){
				byte firstChar = (byte)myWord.toCharArray()[0];
				if (firstChar >= 97 && firstChar <= 122) firstChar = (byte) (firstChar - 32);
				if (lastChar == firstChar){
					// 사용한 단어인지 체크
					//String checkWord[] =  useWord.split(",");
					while(!stack.isEmpty()){
						if ((String)stack.pop() == myWord){
							used = true;
						}
					}
					
					if (!used){
						stack.push(myWord);
						answerWord = myWord;
						break;
					}
				}
			}
			
			if (answerWord == ""){
				System.out.println("You win");
				break;
			}
			else
				System.out.println("Answer:"+answerWord);
			
			count++;
		}
		scn.close();
	}
	
	public static class LinkedStack implements Stack{
		
		private class StackNode{
			Object data;
			StackNode link;
		}
		
		private StackNode top;
				
		public boolean isEmpty(){
			return (top == null);
		}
		
		public void push(Object item){
			StackNode newNode = new StackNode();
			newNode.data = item;
			newNode.link = top;
			top = newNode;
		}
		
		public Object pop(){
			if (isEmpty())
				return null;
			
			Object item = top.data;
			top = top.link;
			return item;
		}
		
		public Object peek(){
			if (isEmpty())
				return null;
			
			return top.data;
		}
		
		public void delete(){
			if (!isEmpty())
				top = top.link;
		}
		
	}
	
	interface Stack{
		public boolean isEmpty();
		public void push(Object item);
		public Object pop();
		public Object peek();
		public void delete();
	}
}
