package Sort;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int a[] = {9,8,7,5,4,2,1,1,3,3,5,6};
		//int a[] = {69,10,30,2,16,8,31,22};
		
		for(int i=0;i<a.length;i++)
			System.out.print(a[i]+" ");
		System.out.println();
		
//		SelectionSort sort = new SelectionSort();
//		BubbleSort sort = new BubbleSort();
		QuickSort sort = new QuickSort();
//		InsertionSort sort = new InsertionSort();
//		ShellSort sort = new ShellSort();
//		MergeSort sort = new MergeSort();
		int[] b = sort.sort(a);

		for(int i=0;i<b.length;i++)
			System.out.print(b[i]+" ");
		System.out.println();
		
	}

}
