package CodingTest6;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String base = "한글 test";
		try {
			byte[] utf8s = base.getBytes("UTF-8");
			System.out.println("UTF-8 len:"+utf8s.length);
			byte[] euckrs = base.getBytes("EUC-KR");
			System.out.println("EUC-KR len:"+euckrs.length);			
		}catch(Exception e){
			
		}
	}

}
