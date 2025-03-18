import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

// This is an example class so that people don't use Strings as the example class. 

class Car implements Comparable<Car>{
    private String model;
    private int year;
    private String owner; 
    
    public Car(String model, int year, String owner) { // Constructing Car
        this.model = model;
        this.year = year;
        this.owner = owner;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getOwner() {
        return owner;
    }

    // VSCode throws an error if you don't include a compareTo function, for some reason. 
    // It turns out that you have to use the exact function and then override it. 
    @Override
    public int compareTo(Car car) {
        String str1= this.owner;
        String str2 = car.owner;
        return str1.compareTo(str2); // Also, note that compareTo compares the ASCII Values, basically saying that 0 goes before A, which goes before a. 
    }
    @Override
    public String toString() {
        return "Model: " + this.model + " Year: " + this.year + " Owner: " + this.owner;
    }
}

class assignment10 {
	
    
    // File things stay the same as before; nothing new.
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
	public static <T> void writeToFile(String fileName, T[] array) {
		try {
			FileWriter file = new FileWriter(fileName);
			for (int i = 0; i < array.length; i++) {
				file.write(array[i].toString() + "\n"); // Note that toString() is a method for type T!
			}
			file.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	// Initialization of sorting arrays:

	
	public static Car[] createRandomArray(int arrayLength, int stringLength) { 
		Car[] outputArray = new Car[arrayLength];
        LocalDate today = LocalDate.now();
        final int carInventionYear = 1886; // 1886 was the invention of the first combustion car.  
        // For now, we are just going to have random years, models, and owners
		for (int i = 0; i < arrayLength; i++) {
            String model = generateRandomString(stringLength);
            int year = (int) (Math.random()*(today.getYear() - carInventionYear)  + carInventionYear); 
            String owner = generateRandomString(stringLength);
            Car car = new Car(model, year, owner);
            outputArray[i] = car;
        }
		return outputArray;
	}	
	public static String generateRandomString(int length) {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String returnedString = "";

		for (int i = 0; i < length; i++) {
			int randomIndex = (int) (Math.random()*(characters.length()));
			returnedString += characters.charAt(randomIndex);
		}
		return returnedString;
	}
	
	/*
		__________     ___.  ___.   .__             _________              __   
		\______   \__ _\_ |__\_ |__ |  |   ____    /   _____/ ____________/  |_ 
		 |    |  _/  |  \ __ \| __ \|  | _/ __ \   \_____  \ /  _ \_  __ \   __\
		 |    |   \  |  / \_\ \ \_\ \  |_\  ___/   /        (  <_> )  | \/|  |  
		 |______  /____/|___  /___  /____/\___  > /_______  /\____/|__|   |__|  
		        \/          \/    \/          \/          \/                    
	 */

	public static <T extends Comparable<T>> T[] bubbleSort(T[] array, int descending) {
		int length = array.length; // So that we don't have to refer to array.length all of the time.
		T tmp;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length - 1; j++) {
				if (Math.pow(-1, descending)*array[j+1].compareTo(array[j]) <= 0) { // The -1 trick is to not reuse code.
					tmp = array[j];
					array[j] = array[j+1];
					array[j+1] = tmp;
				}
			}
		}
		for (int i = 0; i < length; i++) {
			System.out.println(array[i].toString());
		}
		return array;
	}

	/*
		  /     \   ___________  ____   ____     __________________/  |_ 
		 /  \ /  \_/ __ \_  __ \/ ___\_/ __ \   /  ___/  _ \_  __ \   __\
		/    Y    \  ___/|  | \/ /_/  >  ___/   \___ (  <_> )  | \/|  |  
		\____|__  /\___  >__|  \___  / \___  > /____  >____/|__|   |__|  
		        \/     \/     /_____/      \/       \/                   
	 */

	public static <T extends Comparable<T>> void merge(T[] array, int left, int middle, int right, int descending) {
		//Initializing the Left and Right arrays:
		int leftArraySize = middle-left+1;
		int rightArraySize =  right-middle;
		T[] Left = (T[]) new Comparable[middle-left+1];
		T[] Right = (T[]) new Comparable[right-middle];
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
		}
	}
	// The actual Merge Sort. 
	public static <T extends Comparable<T>> T[] mergeSort(T[] array, int left, int right, int descending) {
		if (left < right) {
			int middleBound = (left + right)/2;

			// Gives two already sorted algorithms; take note that merge only gives a sorted array, when the two subarrays are themselves sorted. 
			mergeSort(array, left, middleBound, descending);
			mergeSort(array, middleBound+1, right, descending);

			merge(array, left, middleBound, right, descending);
		}
		return array;
	}
	public static void helpFunction() { // If you're pedantic enough...you can put all of this in a separate .txt or .md file in order to read out. 
		System.out.println("Usage: java -jar assignment10.jar <lengthOfArray> <lengthOfRandomStrings> <descending, 0 or 1> <unsorted output> <bubble sorted file> <merge sorted file> ");
	}
	public static void main(String[] args){
		if (args.length != 6) { 
			helpFunction();
		} else {
				int arrayLength = Integer.parseInt(args[0]);
				int stringLength = Integer.parseInt(args[1]);
				int descending = Integer.parseInt(args[2]);
				String unsortedFileName = args[3];
				String bubbleSortedFileName = args[4];
				String mergeSortedFileName = args[5];
				createFile(unsortedFileName);
				createFile(bubbleSortedFileName);
				createFile(mergeSortedFileName);
				Car[] array = createRandomArray(arrayLength, stringLength);
				writeToFile(unsortedFileName, array); // Hey, did you know that if you write in camelCase, you can have the variables (say one is named camelCaseVariable), you can type "cCV", and it will load it in VSCode as a variable name that you can type out! 
				
        		long startBubbleSortTime = System.nanoTime();
				Car[] bubbleArray = bubbleSort(array, descending);
				long endBubbleSortTime = System.nanoTime();
				long elapsedBubbleSortTime = endBubbleSortTime - startBubbleSortTime;
				System.out.println("The time it takes for Bubble Sort to Occur is: " + elapsedBubbleSortTime + " ns");
				writeToFile(bubbleSortedFileName, bubbleArray);
				long startMergeSortTime = System.nanoTime();
				Car[] mergeSortOutput = (Car[]) mergeSort(array, 0, arrayLength - 1, descending);
				long endMergeSortTime = System.nanoTime();
				long elapsedMergeSortTime = endMergeSortTime - startMergeSortTime;
				System.out.println("The time it takes for Merge Sort to Occur is: " + elapsedMergeSortTime + " ns");
				writeToFile(mergeSortedFileName, mergeSortOutput);
		}	
	}

}