package CodingTest;

import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Insert number?");
			//int inputNumber = scanner.nextInt();
			BigInteger inputNumber = scanner.nextBigInteger();
			
			System.out.println("inputNumber:"+inputNumber);
			scanner.close();
			
			String result = "No";
			//System.out.println(inputNumber.and(inputNumber.subtract(BigInteger.valueOf(1))));
			
			if (inputNumber.compareTo(BigInteger.ZERO)>0 
					&& inputNumber.and(inputNumber.subtract(BigInteger.valueOf(1))).compareTo(BigInteger.ZERO)==0)
			{
				result = "OK!!";
			}

			System.out.println(result);
				
			
/*			// 0이 될때까지 우측 쉬프트
			int shiftCount = 0;
			BigInteger tmp = inputNumber;
			
			while(tmp.compareTo(BigInteger.valueOf(1))>0) {
				if (!tmp.equals(BigInteger.ZERO)){
					//System.out.println("tmp:"+tmp);
					tmp = tmp.shiftRight(1);
					shiftCount++;
				}
			}
			System.out.println("shiftCount:"+shiftCount);
			
			BigInteger targetNumber = BigInteger.ZERO;
			if (shiftCount > 0)
				targetNumber = BigInteger.valueOf(1).shiftLeft(shiftCount);
			System.out.println("targetNumber:"+targetNumber);
			
			if (targetNumber.compareTo(inputNumber)==0)
				System.out.println("OK");
			else
				System.out.println("No");*/
			
		} catch(InputMismatchException e) {
			//System.out.println(e);
			System.out.println("숫자만 넣으세요.");
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
