package Sort;

public class ShellSort {

	public int[] sort(int[] a){
		int interval = a.length/2;
		while(interval >= 1){
			for(int i=0; i<interval; i++){
				intervalSort(a, i, a.length-1, interval);
				interval /= 2;
			}
		}
		return a;
	}
	
	private void intervalSort(int[] a, int begin, int end, int interval){
		int item, j;
		
		for(int i=begin+interval; i<=end; i=i+interval){
			item = a[i];
			for(j=i-interval;j>=begin && item<a[j]; j=j-interval)
				a[j+interval] = a[j];
			a[j+interval] = item;
		}
	}
}
