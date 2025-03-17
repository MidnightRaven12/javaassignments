import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class assignment9 {
    // A few generic functions 
	public static <T> void printArray(T[] array) {
        for (T element: array) {
			System.out.println(element);
		}
	}

	// Dealing with files: 
	public static void createFile(String fileName) {
		try {
			File file = new File(fileName);
			if (file.createNewFile()) {
				System.out.println("File created: " + file.getName());
			} else {
				System.out.println("File exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	public static void writeToFile(String fileName, int[] array) {
		try {
			FileWriter file = new FileWriter(fileName);
			for (int i = 0; i < array.length; i++) {
				file.write(array[i] + "\n");
			}
			file.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	public static int[] readFileToArray(String fileName) { // Assume that the delimiter is a \n.
		ArrayList<Integer> list = new ArrayList<>(); 
		try {
			File file = new File(fileName);
			Scanner Reader = new Scanner(file);
			while (Reader.hasNextLine()) {
				list.add(Integer.parseInt(Reader.nextLine()));
			}
			int size = list.size();
			int[] array = new int[size];
			for (int i = 0; i < size; i++) {
				array[i] = list.get(i);
			}
			Reader.close();
			return array;
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			return null;
		}
	}	

	// Creating a Random Array
	public static int[] createRandomArray(int arrayLength, int range) {
		int[] outputArray = new int[arrayLength];
		for (int i = 0; i < arrayLength; i++) {
			outputArray[i] = (int) Math.floor(Math.random()*(range+1)); // Type casting in order to get an integer. 
		}
		return outputArray;
	}

	
		
	public static void merge(int[] array, int left, int middle, int right, int descending) {
		//Initializing the Left and Right arrays:
		int leftArraySize = middle-left+1;
		int rightArraySize =  right-middle;
		int[] Left = new int[middle-left+1];
		int[] Right = new int[right-middle];
		for (int i = 0; i < leftArraySize; i++) {
			Left[i] = array[left+i];
		}
		for (int j = 0; j < rightArraySize; j++) {
			Right[j] = array[middle+1+j];
		}
		int leftIndex = 0; 
		int rightIndex = 0;
		int origin = left; // Makes the code more readable
		
		int offset = 0;
		while (leftIndex < leftArraySize && rightIndex < rightArraySize) {
			if (Math.pow(-1,descending)*Left[leftIndex] >= Math.pow(-1,descending)*Right[rightIndex]) { // Again, the -1 trick allows us to implement descending in the same line of code. 
				array[origin + offset] = Right[rightIndex];
				rightIndex++;
			} else {
				array[origin + offset] = Left[leftIndex];
				leftIndex++;
			}
			offset++;
		}
		/*
		In particular, we must make the distinction between left array sizes and right array sizes.
		This is because whenever we have exhausted each index of one size (say, rightIndex), there is no possible way to check for that,except for having two separate while loops. So, whenever say some Left[leftIndex] is bigger than Right[rightIndex], the rightIndex is raised by 1, thereby throwing an error. That's the reason why the while loop is there. 
        */
		while (leftIndex < leftArraySize) {
			array[origin + offset] = Left[leftIndex];
			leftIndex++;
			offset++;
		}
		while (rightIndex < rightArraySize) {
			array[origin + offset] = Right[rightIndex];
			rightIndex++;
		}
	}
	public static void mergeSort(int[] array, int left, int right, int descending) {
		if (left < right) {
			int middleBound = (left + right)/2;
			mergeSort(array, left, middleBound, descending);
			mergeSort(array, middleBound+1, right, descending);
			merge(array, left, middleBound, right, descending);
		}
	}
	
	public static int[] bubbleSort(int[] array, int descending) {
		int length = array.length; // So that we don't have to refer to array.length all of the time.
		int[] outputArray = new int[length];
		for (int i = 0; i < length - 1; i++) {
			outputArray[i] = array[i];
		}
		int tmp;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length - 1; j++) {
				if (Math.pow(-1, descending)*outputArray[j+1] <= Math.pow(-1, descending)*outputArray[j]) { // The -1 trick is to not reuse code.
					tmp = outputArray[j];
					outputArray[j] = outputArray[j+1];
					outputArray[j+1] = tmp;
				}
			}
		}
		return outputArray;
	}
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("Usage: java -jar <length> <range> <descending, 0 or 1>");
		} else {
			int length = Integer.parseInt(args[0]);
			int range = Integer.parseInt(args[1]); 
			int descending = Integer.parseInt(args[2]);
			int[] array = createRandomArray(length, range);
            long startBubbleSortTime = System.nanoTime();
			bubbleSort(array, descending);
			long endBubbleSortTime = System.nanoTime();
			long elapsedBubbleSortTime = endBubbleSortTime - startBubbleSortTime;
			System.out.println("The time it takes for Bubble Sort to Occur is: " + elapsedBubbleSortTime + " ns");
			long startMergeSortTime = System.nanoTime();
			mergeSort(array, 0, length-1, descending);
			long endMergeSortTime = System.nanoTime();
			long elapsedMergeSortTime = endMergeSortTime - startMergeSortTime;
			System.out.println("The time it takes for Merge Sort to Occur is: " + elapsedMergeSortTime + " ns");
		}
	}
}

