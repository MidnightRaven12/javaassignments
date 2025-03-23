import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class assignment13 {
    // We can get an O(n log(n)) way of finding the number of strings. In particular, one way that we can do so is just by sorting the words, and then using a linear scan to print all of the words out. (this is a classic trick in LeetCode adjacent problems. Yay! :) )

    // We first get the strings from the array (assignment7, except with Strings.)
    public static String[] readFileToArray(String fileName) { // Assume that the delimiter is a \n.
		ArrayList<String> list = new ArrayList<String>(); 
		try {
			File file = new File(fileName);
			Scanner Reader = new Scanner(file);
			while (Reader.hasNextLine()) {
				list.add(Reader.nextLine());
			}
			int size = list.size();
			String[] array = new String[size];
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
    // Then, we get the words from the paragraphs from the newlines. 
    public static String[] words(String[] paragraphs) {
        ArrayList<String> words = new ArrayList<String>();
        for (String paragraph : paragraphs) {
           	String[] tmp =  paragraph.split(" "); 
           	for (String word : tmp) {				 
				if (word.equals("\n")) {
					continue;
				} else {
					words.add(word.toLowerCase().replaceAll("[\\p{P}\\n]", "")); // Replaces all punctuation
				}
           }
        }
		System.out.println(words.size());
		
        return words.toArray(new String[0]);
    }

    // Then, we use the merge sort algorithm from Assignment 10 in order to sort the array.
    public static void merge(String[] array, int left, int middle, int right, int descending) {
		//Initializing the Left and Right arrays:
		int leftArraySize = middle-left+1;
		int rightArraySize =  right-middle;
		String[] Left = (String[]) new String[middle-left+1];
		String[] Right = (String[]) new String[right-middle];
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
			if (Math.pow(-1,descending)*Left[leftIndex].compareTo(Right[rightIndex]) >= 0) { // Again, the -1 trick allows us to implement descending in the same line of code. 
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
            offset++;
		}
	}
	// The actual Merge Sort. 
	public static String[] mergeSort(String[] array, int left, int right, int descending) {
		if (left < right) {
			int middleBound = (left + right)/2;
			// Gives two already sorted algorithms; take note that merge only gives a sorted array, when the two subarrays are themselves sorted. 
			mergeSort(array, left, middleBound, descending);
			mergeSort(array, middleBound+1, right, descending);
			merge(array, left, middleBound, right, descending);
		}
		return array;
	}

	public static String[] toLowerCase(String[] arrayStrings) {
        String[] resultingArray = new String[arrayStrings.length];
        for (int i = 0; i < arrayStrings.length; i++) { 
            resultingArray[i] = arrayStrings[i].toLowerCase();
        }
        return resultingArray;
    }
    public static void wordCount(String[] stringArray) {
        System.out.println("The number of words is: "+stringArray.length);
		
        // THIS ARRAY MUST BE SORTED IN ASCENDING ORDER TO WORK.
        String[] resultingArray = mergeSort(toLowerCase(stringArray), 0, stringArray.length - 1, 0); 
        String tmp = resultingArray[0];
        int count = 0;
        for (int i = 0; i < resultingArray.length; i++) {
            if (!tmp.equals(resultingArray[i])) {
				if (!tmp.isEmpty()) {
					System.out.printf("The word count for %s is %d \n", tmp, count);
				}
                tmp = resultingArray[i];
                count = 1;
                if (i == resultingArray.length - 1)  {
                    System.out.printf("The word count for %s is %d \n", tmp, count);
                } 
            } else {
                count++;
            }
        }
    }

	// With VS Code, you can use custom snippets (as well as vim.) Hence, we have put this as a way of writing a template.

    final static int minArgsLength = 1;
    final static int maxArgsLength = 1;


    static void help() { // Insert help function here.
        System.out.println("Usage: <fileName> " );
		// System.out.println("In particular, the input for, \"")
    }
    public static void main(String[] args) {
		if (args.length < minArgsLength || args.length > maxArgsLength) {
			help();
		}  else {
			wordCount(words(readFileToArray(args[0]))); // Actually, you even get the new lines! 
		}
    }
}
