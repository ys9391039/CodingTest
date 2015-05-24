package CodingTest5;

import java.math.BigInteger;

public class Main{
	public static final BigInteger NINE = BigInteger.valueOf(9);
	
	public static void main(String[] args) {
		//for (int i = 1; ; i++)
		//int i = 6;
	        System.out.println(repeatingFraction(1, 998001));
	}

	private static String repeatingFraction(long num, long den) {
	    StringBuilder sb = new StringBuilder();
	    sb.append(num / den);
	    sb.append('.');
	    num %= den;
	    for (int i = 3, lim = (int) Math.sqrt(num); i <= lim; i++) {
	        while (num % i == 0 && den % i == 0) {
	            num /= i;
	            den /= i;
	        }
	    }
	
	    while (num > 0) {
	        while (den % 2 == 0 && num % 2 == 0) {
	            num /= 2;
	            den /= 2;
	        }
	        while (den % 5 == 0 && num % 5 == 0) {
	            num /= 5;
	            den /= 5;
	        }
	        // simplify.
	        BigInteger nine = NINE;
	        BigInteger denBI = BigInteger.valueOf(den);
	        long lim = den;
	        while (lim % 2 == 0) lim /= 2;
	        while (lim % 5 == 0) lim /= 5;
	        for (int j = 1; j <= lim; j++, nine = nine.multiply(BigInteger.TEN).add(NINE)) {
	            if (nine.mod(denBI).equals(BigInteger.ZERO)) {
	                BigInteger repeat = BigInteger.valueOf(num).multiply(nine).divide(denBI);
	                sb.append('(').append(String.format("%0" + j + "d", repeat)).append(')');
	                return sb.toString();
	            }
	        }
	        num *= 10;
	        sb.append(num / den);
	        num %= den;
	    }
	    return sb.toString();
	}
}
