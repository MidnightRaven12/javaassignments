import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class assignment12 {

    public static List<String> ls(String filterName) { 
        List<String> fileList = new ArrayList<>();
        File directory = new File(filterName);
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                fileList.add(filterName+"/"+file.getName());
            }
        }
        return fileList;
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

    public static boolean found(int element, int[] array) {
        for (int integer : array) {
            if (integer == element) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }

    public static int verifyCharacters(char[] charArray, int[] asciiNumbers) {
        int[] asciiArray = new int[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            char tmp = charArray[i];
            int ascii = (int) tmp;
            asciiArray[i] = ascii;
        }
        for (int i = 0; i < charArray.length; i++) {
            
            if (found(asciiArray[i], asciiNumbers)) {
                return 1;
            }
            else {
                continue;
            }
        }
        return 0;
    }
    public static int verifyPassword(String passwd, String filterDirectory, int minPasswdStrength, int minPasswdLength, int maxPasswdLength) { // Again, with the theme of putting integer inputs instead of booleans. 
        int passwdStrength = 0;
        if (passwd.length() < minPasswdLength) {
            return 2; 
        } 
        else if (passwd.length() > maxPasswdLength) {
            return 3;
        }
        else {
            char[] charArray = passwd.toCharArray();
            for (int i = 0; i < ls(filterDirectory).size(); i++) {
                passwdStrength += verifyCharacters(charArray, readFileToArray(ls(filterDirectory).get(i)));
            }
            if (passwdStrength < minPasswdStrength) {
                return 1;
            } else {
                return 0;
            }
        }
    }
    static void help() { // Insert help function here.
        System.out.println("Usage: java -jar assignment12.jar <password> <Filter Directory> <Minimum Password Strength> <Minimum Password Length> <Max Password Length>");
        System.out.println("In particular, the Filter Directory must contain the ASCII values of the password that you are trying to decode.");
        System.out.println();
        System.out.println("Example: java -jar assignment12.jar Pa$$word123 demo 3 8 16");
        System.out.println("Will use the directory named \"demo\" for the filters that will be applied");
    }

    // With VS Code, you can use custom snippets (as well as vim.) Hence, we have put this as a way of writing a template.

    final static int minArgsLength = 5;
    final static int maxArgsLength = 5;

    public static void main(String[] args) {
        
        if (args.length < minArgsLength || args.length > maxArgsLength) {
            help();
        } else {
            String passwd = args[0];
            String filterDirectory = args[1];
            int minPasswdStrength = Integer.parseInt(args[2]); 
            int minPasswdLength = Integer.parseInt(args[3]);
            int maxPasswdLength = Integer.parseInt(args[4]);
            int result = verifyPassword(passwd, filterDirectory, minPasswdStrength, minPasswdLength, maxPasswdLength);
            switch (result) {
                case 0: 
                    System.out.println("Your password is good!");
                    break;
                case 1:
                    System.out.println("Your password is not strong enough");
                    break;
                case 2: 
                    System.out.println("Your password is too short.");
                    break;
                case 3:
                    System.out.println("Your password is too long.");
                    break;
                default:
                    System.out.println("Unknown Error");
                    break;
            }
        }
    }
}
