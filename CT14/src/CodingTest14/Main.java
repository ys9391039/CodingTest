package CodingTest14;

import java.util.Calendar;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] times = {"09:03:22 11:24:33", "10:43:22 13:34:22", "11:45:22 16:42:35"};
		String time = "11:04:23";
		int count = 0;
//		System.out.println(times.length);
		for(String t : times){
//			System.out.println(t);
			String[] tArr = t.split(" ");
			String startTime = tArr[0];
			String endTime = tArr[1];
			
//			String[] startTimeArr = startTime.split(":");
//			String[] endTimeArr = endTime.split(":");
//			String[] timeArr = time.split(":");
			
			Calendar startCL = Calendar.getInstance();
//			startCL.set(Calendar.HOUR, Integer.parseInt(startTimeArr[0]));
//			startCL.set(Calendar.MINUTE, Integer.parseInt(startTimeArr[1]));
//			startCL.set(Calendar.SECOND, Integer.parseInt(startTimeArr[2]));
			startCL = setCalendar(startCL, startTime);
					
			Calendar endCL = Calendar.getInstance();
//			endCL.set(Calendar.HOUR, Integer.parseInt(endTimeArr[0]));
//			endCL.set(Calendar.MINUTE, Integer.parseInt(endTimeArr[1]));
//			endCL.set(Calendar.SECOND, Integer.parseInt(endTimeArr[2]));
			endCL = setCalendar(endCL, endTime);
			
			Calendar targetCL = Calendar.getInstance();
//			targetCL.set(Calendar.HOUR, Integer.parseInt(timeArr[0]));
//			targetCL.set(Calendar.MINUTE, Integer.parseInt(timeArr[1]));
//			targetCL.set(Calendar.SECOND, Integer.parseInt(timeArr[2]));
			targetCL = setCalendar(targetCL, time);
			
			System.out.println(startCL.compareTo(targetCL));
			System.out.println(endCL.compareTo(targetCL));
			
			if (startCL.compareTo(targetCL) <= 0 && endCL.compareTo(targetCL) >= 0)
				count++;
		}
		
		System.out.println("count:"+count);
		
	}
	
	public static Calendar setCalendar(Calendar cl, String t){
		String[] tArr = t.split(":");
		cl.set(Calendar.HOUR, Integer.parseInt(tArr[0]));
		cl.set(Calendar.MINUTE, Integer.parseInt(tArr[1]));
		cl.set(Calendar.SECOND, Integer.parseInt(tArr[2]));
		
		return cl;
	}

}
