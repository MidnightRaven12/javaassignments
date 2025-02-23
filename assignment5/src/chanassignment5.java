import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class chanassignment5 {
    public static int[][] RandomMatrix(int length, int range) { // Note that this will only get square matrices.
        int array[][] = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                array[i][j] = (int)(Math.random() * (range + 1));
            }
        }
        return array;
    }
    public static int[][] Matrix(String fileName1, String regex_delimiter) {
        int rows = 0;
        ArrayList<String> matrix = new ArrayList<String>();
        try {
            // This is to first initiate the matrices.
            File file1 = new File(fileName1);
            Scanner fileReader1 = new Scanner(file1);
            while (fileReader1.hasNextLine()) {
                String data = fileReader1.nextLine();
                matrix.add(data);
                rows += 1;
            }
            fileReader1.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        String[] temp_array = matrix.get(0).split(regex_delimiter);
        int columns = temp_array.length;
        int[][] matrix2 = new int[rows][columns];
        int i = 0;
        for (String row : matrix) {
            String[] numbers = row.split(regex_delimiter);
            int j = 0;
            for (String number : numbers) {
                matrix2[i][j] = Integer.parseInt(number);
                j++;
            }
            i++;
        }
        return matrix2;
    }
    public static void WriteFile(int[][] matrix, String fileName, String delimiter) {
        String[] array = new String[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            String write = ""; 
            for (int j = 0; j < matrix[i].length; j++) {
                write += Integer.toString(matrix[i][j]) + delimiter;
            }
            array[i] = write;
        }
        try {
            FileWriter file = new FileWriter(fileName);
            for (int i = 0; i < matrix.length; i++) {
                file.write(array[i] + "\n");
            }
            file.close();
        } catch (IOException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }
    public static void Something(String array, String delimiter) {
        String[] row = array.split(delimiter);
        for (String number : row) {
            System.out.println(Integer.parseInt(number));
        }
    }
    public static int[][] Multiplication(int[][] matrix1, int[][] matrix2) {
        int[][] matrix3 = new int[matrix1.length][matrix2[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                int result = 0;
                for (int k = 0; k < matrix2.length; k++) {
                    result += matrix1[i][k] * matrix2[k][j];
                }
                matrix3[i][j] = result;
            }
        }
        return matrix3;
    }

    public static void main(String[] args) {
        if (args.length == 5 && args[0].equals("-f") && args[3].equals("-o")) {
            int[][] matrix1 = Matrix(args[1], "\s");
            int[][] matrix2 = Matrix(args[2], "\s");
            int[][] matrix3 = Multiplication(matrix1, matrix2);
            WriteFile(matrix3, args[4], " ");        
            System.out.println("Successfully multiplied two matrices!");
        } 
        else if (args.length == 3 && args[0].equals("-f")) {
            int[][] matrix1 = Matrix(args[1], "\s");
            int[][] matrix2 = Matrix(args[2], "\s");
            int[][] matrix3 = Multiplication(matrix1, matrix2);
            WriteFile(matrix3, "matrix3.txt", " ");
            System.out.println("Successfully multiplied two matrices!");
        } else if (args.length == 4 && args[0].equals("-n") && args[2].equals("-r")) {
            int[][] matrix1 = RandomMatrix(Integer.parseInt(args[1]), Integer.parseInt(args[3]));
            int[][] matrix2 = RandomMatrix(Integer.parseInt(args[1]), Integer.parseInt(args[3]));
            WriteFile(matrix1, "matrix1.txt", " ");
            WriteFile(matrix2, "matrix2.txt", " ");
            int[][] matrix3 = Multiplication(matrix1, matrix2);
            WriteFile(matrix3, "matrix3.txt", " ");
            System.out.println("Successfully multiplied two matrices!");
        } else if (args.length == 6 && args[0].equals("-n") && args[2].equals("-r") && args[4].equals("-o")) {
            int[][] matrix1 = RandomMatrix(Integer.parseInt(args[1]), Integer.parseInt(args[3]));
            int[][] matrix2 = RandomMatrix(Integer.parseInt(args[1]), Integer.parseInt(args[3]));
            WriteFile(matrix1, "matrix1.txt", " ");
            WriteFile(matrix2, "matrix2.txt", " ");
            int[][] matrix3 = Multiplication(matrix1, matrix2);
            WriteFile(matrix3, args[5], " ");
            System.out.println("Successfully multiplied two matrices!");
        } else {
            System.out.println("Usage:");
            System.out.println("java chanassignment5.java <-f> <input1> <input2> <-o> <output>");
            System.out.println("This is one use, where you use the -f flag to specify your files. and -o to specify the output. Without the -o flag, it will output to matrix3.txt.");
            System.out.println("java chanassignment5.java <-n> <number> <-r> <range> <-o> <output>");
            System.out.println("This is another use, where you use the -n flag to specify the size of the matrix, and -r is the range of positive integers and -o to specify the output. Without the -o flag, it will output to matrix3.txt.");
        }
    }
}