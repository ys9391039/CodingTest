package CodingTest15;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String url = "http://mysite.siteaddr.com/site1?key1=value1&key2=value2&key3=value3";
//		int tmp = url.indexOf("?");
//		System.out.println(tmp);
		if (url.indexOf("?") > 0){
			String queryString = url.substring(url.indexOf("?")+1);
//			System.out.println(queryString);
			String[] splitArr = queryString.split("&");
//			System.out.println(splitArr.length);
			for(String item : splitArr){
//				System.out.println(item);
				String[] itemArr = item.split("=");
				System.out.print("key:"+itemArr[0]);
				System.out.println("value:"+itemArr[1]);
			}
			
		}
	}

}
