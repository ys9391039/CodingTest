package Sort;

public class InsertionSort {

	public int[] sort(int[] a){
		for(int i=1;i<a.length;i++){
			int tmp = a[i];
			int j = i;
			while(j>0 && tmp<a[j-1]){
				a[j]=a[j-1];
				j--;
			}
			a[j] = tmp;
		}
		return a;
	}
}
