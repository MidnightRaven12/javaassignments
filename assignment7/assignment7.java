import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class assignment7 {
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
	public static int[] createRandomArray(int arrayLength, int range) {
		int[] outputArray = new int[arrayLength];
		for (int i = 0; i < arrayLength; i++) {
			double random = Math.random()*(range+1);	
			outputArray[i] = (int) random;
		}
		return outputArray;
	}	
	public static int[] readFileToArray(String fileName) { // Assume that the delimiter is a \n.
		ArrayList<Integer> list = new ArrayList<Integer>(); 
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
	public static int[] bubbleSort(int[] array, int descending) {
		int length = array.length; // So that we don't have to refer to array.length all of the time.
		int[] outputArray = new int[length];
		for (int i = 0; i < length; i++) {
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
		for (int i = 0; i < length; i++) {
			System.out.println(outputArray[i]);
		}
		return outputArray;
	}
	public static void helpFunction() { // If you're pedantic enough...you can put all of this in a separate .txt or .md file in order to read out. 
		System.out.println("Please use in the following way:");
		System.out.println("java -jar assignment7.jar <arrayLength> <range> -o <Output> <descending, 0 or 1>");
		System.out.println("Alternatively, you can use it this way:");
		System.out.println("java -jar assignment7.jar <readFile> -o <Output> <descending, 0 or 1>");
		System.out.println("For example, 'java -jar assignment7.jar array.txt -o output.txt 0' sorts the numbers in array.txt into output.txt in ascending order.");
	}
	public static void main(String[] args){
		if (args.length <= 3 || args.length >= 6) {
			helpFunction();
		} else {
			if (args.length == 4 && args[1].equals("-o")) {
				int[] array = readFileToArray(args[0]);
				int[] output = bubbleSort(array, Integer.parseInt(args[3]));
				writeToFile(args[2], output);
			} else if (args.length == 5 && args[2].equals("-o")) {
				int arrayLength, range;
				arrayLength = Integer.parseInt(args[0]);
				range = Integer.parseInt(args[1]);
				int[] array = createRandomArray(arrayLength, range);
				int[] output = bubbleSort(array, Integer.parseInt(args[4]));
				writeToFile(args[3], output);
			} else {
				helpFunction();
			}
			
		}	
	}

}

