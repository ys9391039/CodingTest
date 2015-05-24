package Sort;

public class MergeSort {

	private int[] sorted = new int [30];
	
	public int[] sort(int[] a){
		mergeSort(a, 0, a.length-1);
		return a;
	}
	
	private void mergeSort(int[] a, int begin, int end){
		if(begin < end){
			int middle = (begin + end)/2;
			mergeSort(a, begin, middle);
			mergeSort(a, middle+1, end);
			merge(a, begin, middle, end);
		}
	}
	
	private void merge(int[] a, int m, int middle, int n){
		int i = m;
		int j = middle + 1;
		int k = m;
		int t;
		while(i<=middle && j<=n){
			if(a[i] <= a[j]) sorted[k] = a[i++];
			else sorted[k] = a[j++];
			k++;			
		}
		if(i>middle){
			for(t=j; t<=n; t++, k++)
				sorted[k] = a[t];
		}else{
			for(t=i;t<=middle; t++, k++)
				sorted[k] = a[t];
		}
		for (t=m;t<=n; t++)
			a[t] = sorted[t];
	}
}
