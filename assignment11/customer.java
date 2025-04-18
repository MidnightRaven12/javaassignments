import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Customer implements Events {
    protected int purchasing;
    protected double patience;
    final private int applesWanted;
    final private int bananasWanted;
    final private int carrotsWanted;
    final private int datesWanted;
    
    public Customer(int purchasing, double patience, int applesWanted, int bananasWanted, int carrotsWanted, int datesWanted) {
        this.purchasing =  purchasing;
        this.patience =  patience;
        this.applesWanted =  applesWanted;
        this.bananasWanted =  bananasWanted;
        this.carrotsWanted =  carrotsWanted;
        this.datesWanted =  datesWanted;
    }
    public int buy(Game game) {
        if (game.apples >= applesWanted && game.bananas >= bananasWanted && game.carrots >= carrotsWanted && game.dates >= datesWanted) {
            game.money += purchasing;
            game.apples -=  applesWanted;
            game.bananas -=  bananasWanted;
            game.carrots -=  carrotsWanted;
            game.dates -=  datesWanted;
            System.out.println("Pleasure doing business!");
            return 0; // Successful purchase.
        } else {
            System.out.println("Sorry, you cannot sell this. You must turn them away.");
            System.out.println();
            return 2; // Not sold
        }
    }
    public Customer() { // Generic customer, for debugging. 
        this.purchasing = 30;
        this.patience =  50;
        this.applesWanted =  1;
        this.bananasWanted =  1;
        this.carrotsWanted =  1;
        this.datesWanted =  1;
    }
    public static Customer generateRandomCustomer() { // Add some more things later, dealing with game progression. 
        Random random = new Random();
        int purchasing = random.nextInt(100) + 1; // Something

        double patience = random.nextDouble() * 100;

        // Randomizing how many of each item the customer wants
        int applesWanted = random.nextInt(10);  // Random number of apples wanted (0-9)
        int bananasWanted = random.nextInt(10); // Random number of bananas wanted (0-9)
        int carrotsWanted = random.nextInt(10); // Random number of carrots wanted (0-9)
        int datesWanted = random.nextInt(10);   // Random number of dates wanted (0-9)

        // Create and return a new customer with random values
        return new Customer(purchasing, patience, applesWanted, bananasWanted, carrotsWanted, datesWanted);
    }
    public void asking() {
        System.out.println("Hello, I would like " + this.applesWanted + " apples, " + this.bananasWanted + " bananas, " + this.carrotsWanted + " carrots, and " + this.datesWanted + " dates for "  + this.purchasing + " please");
    }
    
    // Satisfies the method overriding requirement. (although, it could be argued that method overriding is confusing.)
    public void asking(boolean nice) {
        if (nice) {
            System.out.println("Hello, I would like " + this.applesWanted + " apples, " + this.bananasWanted + " bananas, " + this.carrotsWanted + " carrots, and " + this.datesWanted + " dates for "  + this.purchasing + " please");
        } else {
            System.out.println("Hello, I would like " + this.applesWanted + " apples, " + this.bananasWanted + " bananas, " + this.carrotsWanted + " carrots, and " + this.datesWanted + " dates for "  + this.purchasing + ".");
        }
    }
    public int negotiate(Game game) {
        Scanner scanner = game.scanner;
        if (!(game.apples >= applesWanted && game.bananas >= bananasWanted && game.carrots >= carrotsWanted && game.dates >= datesWanted)) {
            System.out.println("Sorry, you cannot sell this. You must turn them away.");
            System.out.println();
            return 2; // Not sold
        }
        while (true) {
            System.out.println("What is your asking price?");
            System.out.print("Input your asking price: ");
            int price = scanner.nextInt();
            if (price <= this.purchasing) {
                this.purchasing = price;
                System.out.println("Thanks!");
                buy(game);
            } else {
                while (this.patience >= 0) {
                    Random random = new Random();
                    double patienceLoss = 100*random.nextDouble();
                    double amount = (price - this.purchasing)*random.nextDouble();
                    this.patience -= patienceLoss;
                    if (this.patience < 0) {
                        System.out.println("No deal. I'm done dealing with you.");
                        while (true) {
                            System.out.println("Make your decision:");
                            System.out.println("(1) Accept");
                            System.out.println("(2) Dismiss");
                            try {
                                int choice = scanner.nextInt();
                                if (choice < 1 || choice > 2) {
                                    System.out.println("Invalid input. Please choose again.");
                                } else if (choice == 1){
                                    return buy(game);
                                } else {
                                    return dismiss();
                                }
                            }
                            catch (InputMismatchException e) {
                                // Handle invalid input (not an integer)
                                System.out.println("Invalid input! Please enter a valid integer.");
                                scanner.nextLine();  // Clear the invalid input from the scanner buffer
                            }
                        }
                    } else {
                        this.purchasing += amount;
                        while (true) {
                            System.out.println("Okay, I'm willing to give you " + this.purchasing + ". Make your decision");
                            System.out.println("(1) Accept");
                            System.out.println("(2) Negotiate");
                            try { 
                                int choice = scanner.nextInt();
                                if (choice < 1 || choice > 2) {
                                    System.out.println("Invalid input. Please choose again.");
                                } else if (choice == 1){
                                    return buy(game);
                                } else {
                                    break;
                                }
                            } catch (InputMismatchException e) {
                                // Handle invalid input (not an integer)
                                System.out.println("Invalid input! Please enter a valid integer.");
                                scanner.nextLine();  // Clear the invalid input from the scanner buffer
                            }
                        }
                    }
                }
                
            }
        }
    }
    private int dismiss() {
        System.out.println("Dismissing the customer:");
        return 3; // Code for dismiss.
    }
    @Override
    public void overloading() {
        System.out.println("Overloading!");
    }
    @Override
    public void overloading(int iterations) {
        for (int i = 0; i < iterations; i++) {
            System.out.println("This is the " + (i+1) + "th iteration.");           
        }
    }
    @Override
    public int screen(Game game) {
        Scanner scanner = game.scanner;
        System.out.println("Hello, I would like " + this.applesWanted + " apples, " + this.bananasWanted + " bananas, " + this.carrotsWanted + " carrots, and " + this.datesWanted + " dates for "  + this.purchasing + " please");
        while (true) {
            System.out.println("Choose your decision: ");
            System.out.println("(1) Negotiate");
            System.out.println("(2) Buy");
            System.out.println("(3) Dismiss");
            System.out.println("(4) See Stats");
            System.out.println("(5) Back");
            try {
                int decision = scanner.nextInt();  
                if (decision < 1 || decision > 5) {
                    System.out.println("You have chosen an invalid input.");
                    System.out.println();  
                } else {
                    if (decision == 1) {
                        return this.negotiate(game);
                    } else if (decision == 2) {
                        return this.buy(game);
                    } else if (decision == 3) {
                        return this.dismiss();
                    } else if (decision == 4) {
                        System.out.println(game.toString());
                    } else if (decision == 5) {
                        return 5; // Says, that the customer is still here. 
                    } else {
                        System.out.println("Invalid decision.");
                    }
                }
            } catch (InputMismatchException e) {
                // Handle invalid input (not an integer)
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.nextLine();  // Clear the invalid input from the scanner buffer
            }
        }
    }
}
