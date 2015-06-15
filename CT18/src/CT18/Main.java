package CT18;


public class Main {

	public static void main(String[] args){
		
		int org = 3123;
		
		int asc = ascSibling(org);
		int desc = descSibling(org);
		
		System.out.println("Asc:"+asc);
		System.out.println("Desc:"+desc);

	}
	
	public static int ascSibling(int org){
		String sOrg = String.valueOf(org);
		char[] arrOrg = sOrg.toCharArray();
		int[] arrNum = {0,0,0,0,0,0,0,0,0,0};
		
		for (char c:arrOrg){
			byte index = (byte)((byte)c - 48);
			arrNum[index]++;
		}
		
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<10;i++){
			if(arrNum[i] != 0){
				char c = (char)(i+48);
				for(int j=0;j<arrNum[i];j++)
					sb.append(c);
			}
		}
		String sDst = sb.toString();
		int dst = Integer.valueOf(sDst);
		return dst;
	}
	
	public static int descSibling(int org){
		String sOrg = String.valueOf(org);
		char[] arrOrg = sOrg.toCharArray();
		int[] arrNum = {0,0,0,0,0,0,0,0,0,0};
		
		for (char c:arrOrg){
			byte index = (byte)((byte)c - 48);
			arrNum[index]++;
		}
		
		StringBuffer sb = new StringBuffer();
		for(int i=9;i>=0;i--){
			if(arrNum[i] != 0){
				char c = (char)(i+48);
				for(int j=0;j<arrNum[i];j++)
					sb.append(c);
			}
		}
		String sDst = sb.toString();
		int dst = Integer.valueOf(sDst);
		return dst;
	}
}
