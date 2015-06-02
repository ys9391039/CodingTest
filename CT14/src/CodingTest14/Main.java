package CodingTest14;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] times = {"09:03:22 11:24:33", "10:43:22 13:34:22", "11:45:22 16:42:35"};
		String time = "11:04:23";
		int count = 0;
//		System.out.println(times.length);
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date startDate = null;
		Date endDate = null;
		Date targetDate = null;
		
		for(String t: times){
			String[] tArr = t.split(" ");
			String startTime = tArr[0];
			String endTime = tArr[1];
			
			try{
				startDate = sdf.parse(startTime);
				endDate = sdf.parse(endTime);
				targetDate = sdf.parse(time);
				
				if(startDate.compareTo(targetDate)<=0 && endDate.compareTo(targetDate)>=0)
					count++;
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		
		
		
/*		Calendar startCL = Calendar.getInstance();
		Calendar endCL = Calendar.getInstance();
		Calendar targetCL = Calendar.getInstance();
		for(String t : times){
			String[] tArr = t.split(" ");
			String startTime = tArr[0];
			String endTime = tArr[1];
			
			startCL = setCalendar(startCL, startTime);
			endCL = setCalendar(endCL, endTime);
			targetCL = setCalendar(targetCL, time);
			
			if (startCL == null || endCL == null || targetCL == null){
				System.out.println("Time problem");
				break;
			}
			
			System.out.println(startCL.compareTo(targetCL));
			System.out.println(endCL.compareTo(targetCL));
			
			if (startCL.compareTo(targetCL) <= 0 && endCL.compareTo(targetCL) >= 0)
				count++;
		}*/
		
		System.out.println("count:"+count);
		
	}
	
	public static Calendar setCalendar(Calendar cl, String t){
		try{
			String[] tArr = t.split(":");
			cl.set(Calendar.YEAR, 2000);
			cl.set(Calendar.MONTH, 1);
			cl.set(Calendar.DAY_OF_YEAR, 1);
			cl.set(Calendar.HOUR, Integer.parseInt(tArr[0]));
			cl.set(Calendar.MINUTE, Integer.parseInt(tArr[1]));
			cl.set(Calendar.SECOND, Integer.parseInt(tArr[2]));
		}catch(Exception e){
			//System.out.println(e.getMessage());
			return null;
		}
		return cl;
	}

}
