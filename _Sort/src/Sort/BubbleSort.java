package Sort;

public class BubbleSort {

	public int[] sort(int[] dataArray){
		for(int i=dataArray.length-1; i>0; i--){
			for(int j=0; j<i; j++){
				if(dataArray[j]>dataArray[j+1]){
					int tmp = dataArray[j];
					dataArray[j] = dataArray[j+1];
					dataArray[j+1] = tmp;
				}
			}
			System.out.print(">");
			for(int data:dataArray)
				System.out.print(data);
			System.out.println();
		}
		return dataArray;
	}
}
