import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class App{
    // Basically, takes a string and then separates it into inputs. 
    public static int[] Separator(String input, String Delimiter) {
        char[] inputArray = input.toCharArray();
        int[] final = new int[];
        int result = 0; 
        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i] instanceof int) {
                result = 10*result + inputArray[i];
            } else {

            }
        }
    }
    // Creates a file. standard stuff. 
    public static void createFile(String name) {
        try {
            File file = new File(name);
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred with the create file function.");
            e.printStackTrace();
        }
    }

    // Writes to a file. Additionally, note the Boolean Value of Append, which will reset the file each time it is written.
    public static void WriteFile(String string, String fileName, Boolean append) {
        try {
            FileWriter file = new FileWriter(fileName, append);
            file.write(string);
            file.close();
        } catch (IOException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }  
    public static int[][] ReadFile(String fileName1, String delimiter) {
        int[][] matrix;
        String separator = delimiter;
        try {
            File file = new File(fileName1);
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();

            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }
    public static void main(String[] args) throws Exception {
        if (args.length != 1 && args.length != 4) {
            System.out.println("Usage: java chanassignment5.java <-h> <file1> <file2> <-d: delimiter> <-n> <-o:output> ");
        } else {

            if (args.length == 1 && args[0].equals("-h")) {
                System.out.println("Usage: java chanassignment5.java <-h> <file1> <file2> <-d: delimiter> <-n> <-o:output>");
            } else if (args.length == 2 && args[0].equals("-n")) {
                createFile("matrix1.txt");
                createFile("matrix2.txt")

            }
            else if (args.length == 4 && args[0].equals("-f") && args[2].equals("-o")) {
                createFile(args[3]);
                ReadFiletoWrite(args[1], args[3]);
            }
        }
    }
}