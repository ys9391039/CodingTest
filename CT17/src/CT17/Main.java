package CT17;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int org = 64;
		int tmp = org;
		int n = 2;
		String result = "";
		
		while(tmp > 0){
			int mod = tmp%n;
			if(mod>9)
				result=(char)((byte)mod+65-10)  +result;
			else
				result=mod+result;
			tmp = tmp/n;
		}
		
		System.out.println(result);
	}

}
