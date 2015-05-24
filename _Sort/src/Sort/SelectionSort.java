package Sort;

public class SelectionSort {

	public int[] sort(int[] dataArray){
		int minIndex;
		for(int i=0;i<dataArray.length -1;i++){
			minIndex = i;
			for(int j=i+1;j<dataArray.length;j++){
				if (dataArray[minIndex]>dataArray[j]){
					minIndex = j;
				}
			}
			int tmp = dataArray[minIndex];
			dataArray[minIndex] = dataArray[i];
			dataArray[i] = tmp;
			
			System.out.print(">");
			for(int data:dataArray)
				System.out.print(data);
			System.out.println();
		}
		return dataArray;
	}
}
