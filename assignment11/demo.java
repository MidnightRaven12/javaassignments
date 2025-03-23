import java.util.Scanner;
import java.util.Random;

class demo {

    // With VS Code, you can use custom snippets (as well as vim.) Hence, we have put this as a way of writing a template.



    final static int minArgsLength = 0;
    final static int maxArgsLength = 0;

    public static void confirmation(Scanner scanner) { // Insert help function here.
        
        while (true) {
            System.out.println("Are you sure?");
            int input = scanner.nextInt();
            if (input == 1) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Generate a random secret number between 1 and 100
        Random random = new Random();
        int secretNumber = random.nextInt(100) + 1;  // Random number between 1 and 100

        // Initialize variables
        int userGuess = 0;
        int attempts = 0;

        System.out.println("Welcome to the 'Guess the Secret Number' game!");
        System.out.println("I have selected a secret number between 1 and 100. Try to guess it!");

        // Loop until the user guesses correctly
        while (userGuess != secretNumber) {
            System.out.print("Enter your guess: ");
            userGuess = scanner.nextInt();  // Read the user's guess
            attempts++;  // Increment the number of attempts
            confirmation(scanner);
            // Provide feedback
            if (userGuess < secretNumber) {
                System.out.println("Too low! Try again.");
            } else if (userGuess > secretNumber) {
                System.out.println("Too high! Try again.");
            } else {
                System.out.println("Congratulations! You guessed the secret number.");
                System.out.println("It took you " + attempts + " attempts.");
            }
        }

        // Close the scanner to prevent resource leaks
        scanner.close();
    }
}
