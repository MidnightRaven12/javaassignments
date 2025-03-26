import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;



public class Shop implements Events {
    final private double applePrice = 1;
    final private double bananaPrice = 1;
    final private double carrotPrice = 1;
    final private double datePrice = 1;
    private int[] stock = new int[4]; // This implementation allows us to reference the different attributes via number, rather than through the fruits.. 
    private int[] order = new int[4];
    protected int dummy_variable = 0;
    // Here to satisfy requirements.
    public void overloading() {
        System.out.println("Overloading!");
    }
    public void overloading(int iterations) {
        for (int i = 0; i < iterations; i++) {
            System.out.println("This is the " + (i+1) + "th iteration.");           
        }
    }
    public static Shop generateRandomShop() {
        Random random = new Random();
        int appleStock = random.nextInt(100) + 1;  // Random value between 1 and 100
        int bananaStock = random.nextInt(100) + 1;
        int carrotStock = random.nextInt(100) + 1;
        int dateStock = random.nextInt(100) + 1;
        return new Shop(appleStock, bananaStock, carrotStock, dateStock);
    }
    public Shop(int appleStock, int bananaStock, int carrotStock, int dateStock) {
        this.stock[0] = appleStock;
        this.stock[1] = bananaStock;
        this.stock[2] = carrotStock;
        this.stock[3] = dateStock;
        this.order[0] = this.order[1] = this.order[2] = this.order[3] = 0;
    }
    // Default, but mostly just to satisfy requirements. 
    public Shop() {
        this.stock[0] = 10;
        this.stock[1] = 10;
        this.stock[2] = 10;
        this.stock[3] = 10;
        this.order[0] = 0;
        this.order[1] = 0;
        this.order[2] = 0;
        this.order[3] = 0;
    }
    public void updateStock(int apples, int bananas, int carrots, int dates) {
        this.stock[0] -= apples;
        this.stock[1] -= bananas;
        this.stock[2] -= carrots;
        this.stock[3] -= dates;
    }
    // In case. 
    public String indexToFruits(int choice) {
        if (choice == 0) {
            return "Apples";
        } else if (choice == 1) {
            return "Bananas";
        } else if (choice == 2) {
            return "Carrots";
        } else if (choice == 3) {
            return "Dates"; 
        } else {
            return "";
        }
    }
    public double calculateTotalPrice() {
        // Calculate the price for each item and sum it up
        double total = (this.order[0] * this.applePrice) + (this.order[1] * this.bananaPrice) + (this.order[2] * this.carrotPrice) + (this.order[3] * this.datePrice);
        return total;
    }
    public void seeStockAndOrder() {
        System.out.println("Apple Stock: " + this.stock[0]);
        System.out.println("Banana Stock: " + this.stock[1]);
        System.out.println("Carrot Stock: " + this.stock[2]);
        System.out.println("Date Stock: " + this.stock[3]);
        System.out.println("Apple Order: " + this.order[0]);
        System.out.println("Banana Order: " + this.order[1]);
        System.out.println("Carrot Order: " + this.order[2]);
        System.out.println("Date Order: " + this.order[3]);
    }
    public int checkout(Game game) {
        Scanner scanner = game.scanner;
        while (true) {
            System.out.println("To confirm, we have the following order:");
            System.out.println("Apple Order: " + this.order[0]);
            System.out.println("Banana Order: " + this.order[1]);
            System.out.println("Carrot Order: " + this.order[2]);
            System.out.println("Date Order: " + this.order[3]);
            System.out.printf("All of which will cost: %.2f dollars.", this.calculateTotalPrice()); 
            System.out.println();
            System.out.println("Decide what to do here:");
            System.out.println("(1) Buy");
            System.out.println("(2) Cancel Order");
            System.out.println("(3) Back");
            while (true) {
                try {
                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            game.money -= this.calculateTotalPrice();
                            game.apples += this.order[0];
                            game.bananas += this.order[1];
                            game.carrots += this.order[2];
                            game.dates += this.order[3];
                            this.stock[0] -= this.order[0];
                            this.stock[1] -= this.order[1];
                            this.stock[2] -= this.order[2];
                            this.stock[3] -= this.order[3];
                            this.order[0] = 0;
                            this.order[1] = 0;
                            this.order[2] = 0;
                            this.order[3] = 0;
                            return 0; // Success
                        case 2:
                            this.order[0] = 0;
                            this.order[1] = 0;
                            this.order[2] = 0;
                            this.order[3] = 0;
                            return 2; // Cancellation
                        case 3:
                            return 1; // Back
                    }
                } catch (InputMismatchException e) {

                }
            }
        }
        
    }
    @Override
    public int screen(Game game) {
        Scanner scanner = game.scanner;
        while (true) {
            System.out.println("What would you like to buy?");
            System.out.println("We have the following items for sale:");
            System.out.printf("(1) Apples ($%.2f each)\n", this.applePrice);
            System.out.printf("(2) Bananas ($%.2f each)\n",  this.bananaPrice);
            System.out.printf("(3) Carrots ($%.2f each)\n", this.carrotPrice);
            System.out.printf("(4) Dates ($%.2f each)\n", this.datePrice);
            System.out.println("(5) See Order/Stock");
            System.out.println("(6) See Inventory");
            System.out.println("(7) Checkout");
            System.out.println("(8) Exit");
            System.out.println("Please select an action by entering its number (1-8).");
            System.out.print("Input: ");
            int choice = scanner.nextInt() - 1;
            if (choice == 7) {
                System.out.println("Thank you for visiting the shop. Goodbye!");
                return 1; // Exits
            }
            String item = "";
            if (choice == 0) {
                item = "apples";
            } else if (choice == 1) {
                item = "bananas";
            } else if (choice == 2) {
                item = "carrots";
            } else if (choice == 3) {
                item = "dates";
            } else if (choice == 4) {
                this.seeStockAndOrder();
            } else if (choice == 5) {
                System.out.println(game.toString());  
            } else if (choice == 6) {
                this.checkout(game);
            } else {
                System.out.println("Invalid choice, please try again.");
                continue; // Ask the user to select a valid option
            }
            if (!item.equals("")) {
                System.out.println("How many " + item + " would you like to buy? You can in fact, choose negative numbers in order to reduce the amount that you buy!");
                System.out.print("Input: ");
                while (true) {
                    try {
                        int quantity = scanner.nextInt();
                        if (quantity == 0) {
                            break;
                        }   else if ((-1)*quantity > this.order[choice]) {
                            System.out.println("Sorry, you cannot take away the amount that you have already ordered. Please, try again.");
                        } else {
                            this.order[choice] += quantity;
                            break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input! Please enter a valid integer.");
                        scanner.nextLine();
                    }
                }
            }
        }
    }
}

