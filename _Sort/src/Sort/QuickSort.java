package Sort;

public class QuickSort {

	public int[] sort(int[] a){
		quickSort(a, 0, a.length-1);
		return a;
	}
	
	private void quickSort(int[] a, int begin, int end){
		if (begin < end){
			int p = partition(a, begin, end);
			quickSort(a, begin, p-1);
			quickSort(a, p+1, end);
		}
	}
	
	private int partition(int[] a, int begin, int end){
		int L = begin;
		int R = end;
		int pivot = (begin+end)/2;
//		System.out.print("=");
//		for(int data:a)
//			System.out.print(data);
//		System.out.println();
		while(L<R){
			while(a[L] < a[pivot] && L<R) L++;
			while(a[pivot] <= a[R] && L<R) R--;
			if(L<R){
				int tmp = a[L];
				a[L] = a[R];
				a[R] = tmp;
				
//				System.out.print("<");
//				for(int data:a)
//					System.out.print(data);
//				System.out.println();					
				if (L==pivot){
					return R;
				}
			}
		}
		int tmp = a[pivot];
		a[pivot] = a[R];
		a[R] = tmp;
//		System.out.print(">");
//		for(int data:a)
//			System.out.print(data);
//		System.out.println();		
		
		return R;
	}
}
