package CodingTest14;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] times = {"09:03:22 11:24:33", "10:43:22 13:34:22", "11:45:22 16:42:35"};
		
		String time = "11:04:23";
		
//		System.out.println(times.length);
		
		int count = 0;
		for (String workTime : times){
//			System.out.println(workTime);
			String[] workTimeArr = workTime.split(" ");
			String startTime = workTimeArr[0];
			String endTime = workTimeArr[1];
//			System.out.println("startTime>"+startTime);
//			System.out.println("endTime>"+endTime);
			
			String[] startHourArr = startTime.split(":");
			String[] endHourArr = endTime.split(":");
			
			int startHour = Integer.parseInt(startHourArr[0]);
			int endHour = Integer.parseInt(endHourArr[0]);
			System.out.println("startHour>>"+startHour);
			System.out.println("endHour>>"+endHour);
			
			String[] checkTimeArr = time.split(":");
			int checkTime = Integer.parseInt(checkTimeArr[0]);
			System.out.println("checkTime>>"+checkTime);
			
			if (startHour < checkTime && endHour >= checkTime){
				count++;
			}
			
		}
		System.out.println("count:"+count);
		
		
	}

}
