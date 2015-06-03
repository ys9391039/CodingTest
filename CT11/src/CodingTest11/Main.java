package CodingTest11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		int[] a = {9,8,7,5,4,2,1,1,3,3,5,6};
		String string = "wO3efj26Wij6fw8I34jf";
		char[] cArr = string.toCharArray();
		ArrayList<Integer> al = new ArrayList<Integer>();
		int sum = 0;
		for(char c:cArr){
			if(c>=65 && c<=90 || c>=97 && c<=122)
				al.add(Integer.valueOf(c));
			else if (c>=48 && c<=57){
//				System.out.println(Character.getNumericValue(c));
				sum+=Character.getNumericValue(c);
			}
		}
//		System.out.println(al);
//		System.out.println((byte)'0');

		// 합
		System.out.println("Sum:"+sum);
		
		// 대소문자 구분하고 정렬한다면...
		QuickSort s = new QuickSort();
		Integer[] a = new Integer[al.size()];
		a = al.toArray(a);
		Integer[] b = s.sort(a);
		for (Integer i:b){
			System.out.print((char)(byte)(int)i );
		}
		
		// 대소문자 구분없이 정렬한다면...
		TreeMap<Character, List<Character>> map = new TreeMap(Collections.reverseOrder());
		
		List<Character> i = new ArrayList();
		i.add('A');
		map.put('A', i);
		List<Character> i2 = new ArrayList();
		i2.add('C');
		i2.add('c');
		map.put('C', i2);
		List<Character> i3 = new ArrayList();
		i3.add('B');
		i3.add('B');
		map.put('B', i3);
				
		System.out.println(map);

		
	}

}


