package CodingTest11;

public class SelectionSort {

	public int[] sort(int[] a){
		int m;
		for(int i=0;i<a.length-1;i++){
			m=i;
			for(int j=i+1;j<a.length;j++){
				if(a[m]>a[j])
					m=j;
			}
			int tmp = a[i];
			a[i] = a[m];
			a[m] = tmp;
		}
		return a;
	}
}
