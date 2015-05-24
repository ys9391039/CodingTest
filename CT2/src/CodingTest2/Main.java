package CodingTest2;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			String ori = "i want to go home. if you wanted home, Please tell me!";
			System.out.println(ori);
			
			char[] arrOri = ori.toCharArray();
			
			boolean isFirst = true;
			for (int i=0;i<arrOri.length;i++) {
				//System.out.print(arrOri[i]);
				//System.out.print(arrOri[i]+":");
				//System.out.println((byte)arrOri[i]);
				byte ascii = (byte)arrOri[i];
				if (isFirst && ascii >= 97 && ascii <= 122){
					//System.out.print((char)(ascii-32));
					arrOri[i] = (char)(ascii-32);
					isFirst = false;
				}else if (!isFirst && ascii >= 65 && ascii <= 90) {
					//System.out.print((char)(ascii+32));
					arrOri[i] = (char)(ascii+32);
				}
				if (ascii == 46){
					isFirst = true;
				}
			}
			//System.out.println(new String(arrOri));
			System.out.println(arrOri);
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
