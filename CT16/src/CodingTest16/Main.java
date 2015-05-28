package CodingTest16;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File file = new File("D:\\MyWorks\\WorkSpace\\Git\\CT16\\src\\CodingTest16\\file_20150526.log");
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

        HashMap<String,Integer> map = new HashMap<String,Integer>();
        //ValueComparator bvc =  new ValueComparator(map);
        MVC<String, Integer> mvc =  new MVC<String, Integer>(map);
        TreeMap<String,Integer> sorted_map = new TreeMap<String,Integer>(mvc);

        map.put("A",1);
        map.put("B",1);
        map.put("C",2);
        map.put("D",1);
        map.put("A",2);
        map.put("D",1);

        System.out.println("unsorted map: "+map);

        sorted_map.putAll(map);

        System.out.println("results: "+sorted_map);
        
//		Map<String, Integer> map = new HashMap<String, Integer>();
//		map.put("404", 1);
//		map.put("400", 3);
//		map.put("400", 2);
//		map.put("500", 2);
//		map.put("403", 9);
//		  map.put("하춘하",99);
//		  map.put("고창석",70);
//		  map.put("임재범",89);
//		  map.put("민경옥",79);
//		  map.put("오창민",99);
//		TreeMap<String, Integer> tm = new TreeMap<String, Integer>(map);
//		Iterator<String> it = tm.keySet().iterator();
//		Iterator<String> it = tm.descendingKeySet().iterator();
//		
//		while(it.hasNext()){
//			String key = it.next();
//			int value = tm.get(key);
//			System.out.println(key+":"+value);
//		}
		
		//  TreeMap tm = new TreeMap(map);
		  
		  //Iterator iteratorKey = tm.keySet( ).iterator( );   //키값 오름차순 정렬(기본)
		                //Iterator iteratorKey = tm.descendingKeySet().iterator(); //키값 내림차순 정렬
//		  while(iteratorKey.hasNext()){
//		   String key = (String)iteratorKey.next();
//		   System.out.println(key+","+tm.get(key));
//		  }
		  
//		try{
//			fis = new FileInputStream(file);
//			isr = new InputStreamReader(fis, "UTF-8");
//			br = new BufferedReader(isr);
//			String tmp;
//			while((tmp = br.readLine()) != null){
//				System.out.println(tmp);
//			}
//			
//		}catch(Exception e){
//			System.out.println(e.getMessage());
//			
//		}finally{
//			try{
//				fis.close();
//				isr.close();
//				br.close();
//			}catch(Exception e){
//				System.out.println(e.getMessage());
//			}
//		}
	}
	
	public class HttpResult implements Comparable{
		private String code;
		private Integer count;
		
		public HttpResult(String code, int count){
			this.code = code;
			this.count = count;
		}
		
		public int compareTo(Object o){
			return count.compareTo(((HttpResult)o).count);
		}
	}
	
		
	public static class ValueComparator implements Comparator<String> {
		
		Map<String, Double> base;
		public ValueComparator(Map<String, Double> base) {
			this.base = base;
		}
		
		// Note: this comparator imposes orderings that are inconsistent with equals.    
		public int compare(String a, String b) {
			if (base.get(a) >= base.get(b)) {
				return -1;
			} else {
				return 1;
			} // returning 0 would merge keys
		}
	}
}


final class MVC<K,V> implements Comparator<K> {

    private Map<K,V> map;

    public MVC(Map<K,V> map) {
        this.map = map;
    }

    public int compare(K o1, K o2) {
    	if ((Integer)map.get(o1) >= (Integer)map.get(o2))
    		return -1;
    	else
    		return 1;
    }
}