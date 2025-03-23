import java.util.Scanner;
import java.util.Random; 
import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;

interface secret {
    int secret = 1434;
}

public class Game implements secret {
    public int money;
    public double suspicion; 
    public int apples;
    public int bananas;
    public int carrots;
    public int dates;
    public int health;
    public boolean armed;
    public Scanner scanner;
    public int fame;
    final protected int winningMoney; 
    public int day;
    public List<customer> customerList; // To make it easier to reference.
    public Game() {
        this.money = 0;
        this.apples = 10;
        this.bananas = 10;
        this.carrots = 10;
        this.dates = 10; 
        this.suspicion = 10;
        this.winningMoney = 10000;
        this.day = 1;
        this.armed = false;
        this.fame = 10;
        this.customerList = new  ArrayList<>();
        this.scanner = new Scanner(System.in); // Initializes the single scanner to use for the rest of the game.
    }
    public Game(int money,int apples, int bananas, int carrots, int dates, double suspicion, int winningMoney, Scanner scanner, int day, boolean armed, int fame) {
        this.money = money;
        this.apples = apples;
        this.bananas = bananas;
        this.carrots = carrots;
        this.dates = dates; 
        this.suspicion = suspicion;
        this.winningMoney = winningMoney;
        this.day = day;
        this.scanner = scanner;
        this.fame = fame;
        this.armed = armed;
    }
    @Override
    public String toString() {
        return "Money: " + money + "\n" + "Suspicion: " + suspicion + "\n" + "Apples: " + apples + "\n" + "Bananas: " + bananas + "\n" + "Carrots: " + carrots + "\n" + "Dates: " + dates + "\n";
    }
    public void getGameState() {
        this.toString();
    }
    public void start() {
        System.out.println("Welcome to running a suspicious fruit stand. You are an outlaw fruit seller, selling apples, bananas, carrots, and dates, all outlawed by your land. Your goal is to grow your empire.");
        // Thread.sleep(5000); 
        System.out.println("Here's what you start with: " + this.toString());
        // Thread.sleep(5000); 
        System.out.println("Get ready.");
        for (int i = 0; i < 10; i++) {
            day();
        }
        end(0);
    }
    public void end(int gameEndingCode) {
        this.scanner.close();
    }
    // Yeah, turns out you need an exception. 
    private void sleep(int time) {
        try {
            Thread.sleep(time);          
        } catch (InterruptedException e) {
            System.out.println("The sleep was interrupted.");
        }
    }
    public int InteractWithCustomer() {
        while (true) {
            if (this.customerList.isEmpty()) {
                while (true)
                    try {
                        String input = this.scanner.nextLine();
                        System.out.println("You have no customers. Type anything to go back.");
                        if (input != "") {  
                            return 0; // This is a trick which allows you to break out of any nested while loop.
                        } 
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a valid integer.");
                        this.scanner.nextLine();                 
                    }
            }
            System.out.println("You have: " + this.customerList.size() + " customers. Which one would you like to interact with? \nAnswer with a number from 1 to " + this.customerList.size() + " to access them.\nType in " + (this.customerList.size() + 1) + " in order to go back.");

            try {
                // Attempt to read an integer input
                int index = this.scanner.nextInt();
                if (index < 1 || index > this.customerList.size() + 1) {
                    System.out.println("Invalid.");
                } else if (index == this.customerList.size() + 1) {
                    return 0;
                } else {
                    customer customer = this.customerList.get(index - 1);
                    int output = customer.screen(this);
                    if (output == 5) {
                        continue;
                    } else {
                        customerList.remove(index-1);
                    }
                }
            } catch (InputMismatchException e) {
                // Catch and handle the case where the user doesn't enter an integer
                System.out.println("Invalid input! Please enter a valid integer.");
                this.scanner.nextLine();  // Clear the invalid input from the scanner buffer
            }            
        }        
    }

    public void Buying() {
        System.out.println("Buying...");
    }

    public void secret() {
        
    }
    public void loop(List<customer> customers) {
        while (true) {
            
            System.out.println("Choose an action:");
            System.out.println("(1) Interact with Customer");
            System.out.println("(2) Buying");
            System.out.println("(3) New Day");
            System.out.println("(4) See Stats");
            
            int choice = this.scanner.nextInt();
            
            if (choice == 1) {
                this.InteractWithCustomer();
            } else if (choice == 2) {
                this.Buying();
            } else if (choice == 3) {
                System.out.println("Moving on to the next day: ");
                this.day++;
                break;
            } else if (choice == 4)  {
                System.out.println(this.toString());
            } else if (choice == secret) {
                this.secret();
            } else {
                System.out.println("Invalid choice. Please enter a number between 1-4.");
            }
        }
    }

    public void day() { // this will implement the new day logic.
        for (int i = 0; i < this.customerList.size(); i++) {
            customerList.remove(i);
        }
        this.sleep(1000); 
        System.out.println("Day " + this.day);
        this.sleep(1000); 
        int noCustomers = (int) Math.floor(Math.log(this.fame)/Math.log(10) + 2);
        for (int i = 0; i < noCustomers; i++) {
            this.customerList.add(customer.generateRandomCustomer());
        }
        loop(this.customerList);
    }
}


// public class shop implements Events {

// }

// This must be public. 
   
