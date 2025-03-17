import java.lang.Math;

class assignment9 {
    public static int[] portion_of_array(int[] array, int left, int right) {
        int[] array1 = new int[right-left+1];
        for (int i = 0; i <= array1.length; i++) {
            array1[i] = array[i+left];
        }
    }
	public static int[] merge(int[] array1, int[] array2, int descending) {
		int[] array3 = new int[array1.length + array2.length];
		int i, j, k;
		while (k < array3.length) {
			if (Math.Pow(-1, descending)*array1[i] < Math.Pow(-1, descending)*array2[j]) { 
				array3[k] = array1[i];
				i++;
				k++;
			} else {
				array3[k] = array2[j];
				j++;
				k++;
			}
		}
		return array3;
	}
	public static void sort(int[] array, int descending) {
	    int middleBound = Math.floor(array.length/2);
	    int[] array1 = sort(portion_of_array(array, 0, middleBound-1), descending);
	    int[] array2 = sort(portion_of_array(array, middleBound, array.length), descending);
	    int[] array3 = new int[array.length];
	    array3 = merge(array1, array2, descending);
	}
	public static int[] createRandomArray(int arrayLength, int range) {
		int[] outputArray = new int[arrayLength];
		for (int i = 0; i < arrayLength; i++) {
			double random = Math.random()*(range+1);	
			outputArray[i] = (int) random;
		}
		return outputArray;
	}	
	public static void main(String[] args) {
	   int array[] = new int[1000];
	   int array2[] = new int[1000];
	   array = createRandomArray(1000, 100000);
	   for (int i = 0; i < 10; i++) {
	       System.out.println(array[i]);
	   }
	   array2 = sort(array, 0);
	   for (int i = 0; i < 10; i++) {
	       System.out.println(array2[i]);
	   }
	}
}

