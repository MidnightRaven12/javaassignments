import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// A little bit, overkill? Maybe? Nah. 

public class chanassignment4 {
    // In particular, this is where we see the importance of the "static" modifier. If you were to just try to use this as just void ReverseString, you would need to create an object of class App, and you would cause huge problems. 

    public static String ReverseString(String string) {
        char[] characters = string.toCharArray(); // This turns it into a regular C Array, making it ten times easier to work with.
        String reversed = "";
        for (int i = 0; i < characters.length; i++) {
            // The distinction is needed in order to loop over to the next string. 
            reversed += characters[characters.length-1-i];
        }
        return reversed;
    }
    // In particular, this is where we see the importance of the "static" modifier. If you were to just try to use this as just void ReverseString, you would need to create an object of class App, and you would cause huge problems. 

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
    // Read a file, and then writes the output. We used the ReverseString (but you can do this by copying another file as well.)
    public static void ReadFiletoWrite(String fileName1, String fileName2) {
        Boolean append = false;
        try {
            File file = new File(fileName1);
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                WriteFile(ReverseString(data) + "\n", fileName2, append);
                append = true;
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }
    
    public static void main(String[] args) throws Exception {
        if (args.length != 1 && args.length != 4) {
            System.out.println("Usage: java chanassignment4.java <-h> <-f filename>  <-o:output>");
        } else {
            if (args.length == 1 && args[0].equals("-h")) {
                System.out.println("Usage: java chanassignment4.java <-h> <-f filename>  <-o:output>");
            } else if (args.length == 4 && args[0].equals("-f") && args[2].equals("-o")) {
                createFile(args[3]);
                ReadFiletoWrite(args[1], args[3]);
            }
        }
    }
}
