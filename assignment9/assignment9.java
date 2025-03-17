import java.lang.Math

class assignment9 {
	public static int[] merge(int[] array, int left, int right, int descending) {
		int[] array3 = new int[right-left+1];
		int i, j, k;
		while (k < array3.length) {
			if (Math.Pow(-1, descending)*array1[i] < Math.Pow(-1, descending)*array2[j]) { // The -1 trick allows us to increase functionality without any extra effort. 
				int array3[k] = array1[i];
				i++;
				k++;
			} else {
				int array3[k] = array2[j];
				j++;
				k++;
			}
		}
		return array3;
	}
	public static void sort
	public static void main(String[] args)
}
