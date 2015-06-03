package CodingTest11;

public class QuickSort {

	public Integer[] sort(Integer[] a){
		quickSort(a, 0, a.length-1);
		return a;
	}
	
	public void quickSort(Integer[] a, int begin, int end){
		if(begin < end){
			int p = partion(a, begin, end);
			quickSort(a, begin, p-1);
			quickSort(a, p+1, end);
		}
	}
	
	private int partion(Integer[] a, int begin, int end){
		int L = begin;
		int R = end;
		int P = (begin+end)/2;
		
		while(L<R){
			while(L<R && a[L] < a[P]) L++;
			while(L<R && a[P] <= a[R]) R--;
			
			if(L<R){
				int tmp = a[L];
				a[L] = a[R];
				a[R] = tmp;
				
				if(L==P)
					return R;
			}
		}
		int tmp = a[R];
		a[R] = a[P];
		a[P] = tmp;
		
		return R;
	}
	
}
