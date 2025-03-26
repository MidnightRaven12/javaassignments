import java.util.InputMismatchException;
import java.util.Scanner;

interface dummyInterface {
    public void dummyInterfaceMethod();
}

abstract class Human {
    // Fields/Attributes
    protected String name;
    protected int age;

    Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    Human() {
        this.name = "John Doe";
        this.age = 40;
    }
    abstract public void speak(); 

    void introduce() {
        System.out.println("Hello, my name is " + this.name + " and I am " + this.age + " years old.");
    }

    abstract public void work();

    abstract public void dummyMethod();
    static void greet() {
        System.out.println("Hello World! I am a human");
    }
    // Here to satisfy requirements.
    public void overloading() {
        System.out.println("Overloading!");
    }
    public void overloading(int iterations) {
        for (int i = 0; i < iterations; i++) {
            System.out.println("This is the " + (i+1) + "th iteration.");           
        }
    }
}

class Staff extends Human implements dummyInterface {
    
    protected int dummy_variable;
    private int dummy_variable2;
    String typeOfZoo;
    Staff(String name, int age, String typeOfZoo) {
        super(name, age);
        this.typeOfZoo = typeOfZoo;
    }
    Staff() {
        super("Jane Doe", 40);
        this.typeOfZoo = "San Diego";
    }
    public void dummyInterfaceMethod() {
        System.out.println("Hi! I'm a dummy interface method!");
    }
    public void dummyMethod() {
        System.out.println("Hi! I'm a dummy method.");
    }
    public void introduce() {
        System.out.println("Hello, my name is " + this.name + " and I am " + this.age + " years old. I also work at " + this.typeOfZoo + ".");
    }
    public void work() {
        System.out.println("I work at a zoo!");
    }
    public void speak() {
        System.out.println("Hi guys! I work at a zoo!");
    }
    // Here to satisfy requirements.
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
}

// Copy pasted from Assignment 6 to meet requirements. 
abstract class Animal {
    public int age;
    public String gender;
    public boolean mammal;
    public Animal(int animalAge, String animalGender, boolean mammal) { // Notice that we can't create an object here...
        this.age = animalAge;
        this.gender = animalGender;
        this.mammal = mammal;
    } 
    public Animal() { 
        this.age = 2;
        this.gender = "Female";
        this.mammal = false;
    } 
    public void isMammal() { // Notice that this variable is public. Which means that I can access it from any other class.
        if (this.mammal) {
            System.out.println("I am a mammal.");
        } else {
            System.out.println("I am not a mammal");
        }
    }
    protected void Call() { // Notice that this is protected, and I can only access this through itself, and some other subclasses.
        System.out.println("I am an animal, and I'm being called.");
    }
    private void animals() { // Notice that this is private, and I can only access this from this class.
        System.out.println("I am only an animal.");
    }
    public void publicanimals() {
        animals(); // But I can access a private method from here!
    }
    static public void introduce() {
        System.out.println("Hello! I'm an animal!");
    }
    // Here to satisfy requirements.
    public void overloading() {
        System.out.println("Overloading!");
    }
    public void overloading(int iterations) {
        for (int i = 0; i < iterations; i++) {
            System.out.println("This is the " + (i+1) + "th iteration.");           
        }
    }
};
class Fish extends Animal implements dummyInterface{
    private final double sizeInFeet; // Think of these as temporary/hidden variables, that you might get from a database. Again, the final part is because sizeInFeet will (presumably), not change.
    protected boolean living;
    public Fish(int animalAge, String animalGender, double size) {
        super(animalAge, animalGender, false);
        this.sizeInFeet = size;
        this.living = true;
    } 
    public Fish() {
        super();
        this.sizeInFeet = 3;
        this.living = true;
    } 
    public void size() {
        System.out.println(this.sizeInFeet);
    }
    // Notice that with the following setup, we cannot even access anything.
    private void canEat() {
        System.out.println("This is a private method canEat() from class fish");
    } 
    // But we can here...
    public void publicCanEat() {
        canEat();
    } 
    @Override 
    public void Call() {
        System.out.println("I am a fish!");
    }
    // Here to satisfy requirements.
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
    public void dummyInterfaceMethod() {
        System.out.println("Hi! I'm a dummy interface method!");
    }
};
class Zebra extends Animal implements dummyInterface{
    protected final boolean laysEggs = false;
    private boolean is_wild;
    public Zebra(int animalAge, String animalGender, boolean is_wild) { 
        super(animalAge, animalGender, true); //This super() is a way to construct the class of Animal.
        this.is_wild = is_wild;
    }
    public Zebra() {
        super();
        this.is_wild = false;
    } 
    public void run() {
        if (this.is_wild) {
            System.out.println("Hello World!");
        } else {
            System.out.println("I'll stay in one place.");
        }
    }
    public static void staticRun() { // Notice that I can call this, without defining an object, though you will have to define Zebra.staticRun()
        System.out.println("Hello, static World!");
    }
    // Here to satisfy requirements.
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
    public void dummyInterfaceMethod() {
        System.out.println("Hi! I'm a dummy interface method!");
    }
}
class Giraffe extends Animal implements dummyInterface {
    protected final boolean laysEggs = false;
    private boolean is_wild;
    public Giraffe(int animalAge, String animalGender, boolean is_wild) { 
        super(animalAge, animalGender, true); //This super() is a way to construct the class of Animal.
        this.is_wild = is_wild;
    }
    public Giraffe() {
        super();
        this.is_wild = false;
    } 
    @Override
    public void Call() {
        System.out.println("I am a giraffe!");
    }
    // Here to satisfy requirements.
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
    public void dummyInterfaceMethod() {
        System.out.println("Hi! I'm a dummy interface method!");
    }
}
class Hippo extends Animal implements dummyInterface {
    protected final boolean laysEggs = false;
    private boolean is_wild;
    public Hippo(int animalAge, String animalGender, boolean is_wild) { 
        super(animalAge, animalGender, true); //This super() is a way to construct the class of Animal.
        this.is_wild = is_wild;
    }
    public Hippo() {
        super();
        this.is_wild = false;
    } 
    @Override
    public void Call() {
        System.out.println("I am a hippo!");
    }
    // Here to satisfy requirements.
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
    public void dummyInterfaceMethod() {
        System.out.println("Hi! I'm a dummy interface method!");
    }
}


// Basically a simple User Interface for each class.

public class Secret {
    Staff staffMember = new Staff("John", 45, "San Diego");
    Fish fish = new Fish(2, "Male", 3);
    Zebra zebra = new Zebra(2, "Male", false);
    Hippo hippo = new Hippo(2, "Male", true);
    Giraffe giraffe = new Giraffe(2, "Male", true);
    Scanner scanner;
    protected int dummy;
    public Secret(Scanner scanner) {
        this.scanner = scanner;
    }
    public Secret() {
        System.out.println("This is a secret! Shh...");
    }
    private void sleep(int time) {
        try {
            Thread.sleep(time);          
        } catch (InterruptedException e) {
            System.out.println("The sleep was interrupted.");
        }
    }
    // Here to satisfy requirements.
    public void overloading() {
        System.out.println("Overloading!");
    }
    public void overloading(int iterations) {
        for (int i = 0; i < iterations; i++) {
            System.out.println("This is the " + (i+1) + "th iteration.");           
        }
    }
    
    public void screen(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Choose a class!");
                System.out.println("(1) Human");
                System.out.println("(2) Staff");
                System.out.println("(3) Animal");
                System.out.println("(4) Fish");
                System.out.println("(5) Zebra");
                System.out.println("(6) Hippo");
                System.out.println("(7) Giraffe");
                System.out.println("(8) Quit");
                System.out.print("Input: ");
                int method_choosing = this.scanner.nextInt();
                if (method_choosing == 1) {
                    System.out.println("You cannot interact with this class at all, since it is abstract. However, ");
                    this.sleep(2500);
                    System.out.println("There is still a method, namely greet() whose output is put here:");
                    this.sleep(2500);
                    System.out.println();
                    System.out.println("Output:");
                    Human.greet();                
                    this.sleep(2500);   
                } else if (method_choosing == 2) {
                    System.out.println("You have chosen the class of Staff.");
                    this.sleep(2000);
                    System.out.println("He would like to introduce you.");
                    this.sleep(1500);
                    staffMember.introduce();
                    this.sleep(1500);
                } else if (method_choosing == 3) {
                    System.out.println("Animal is a class that you cannot interact with. However, what you can still do is to see a static method with it!");
                    this.sleep(2500);
                    Animal.introduce();
                    this.sleep(3500);
                } else if (method_choosing == 4) {
                    System.out.println("You have chosen the Fish class!");
                    this.sleep(1500);
                    System.out.println("They say hi!");
                    this.sleep(1000);
                    System.out.println("And here it is, saying hi!");
                    this.sleep(1500);
                    this.fish.Call();
                    this.sleep(2000);
                    System.out.println("To talk more about methods, we can invoke the public publicCanEat() method which calls a private method.");
                    this.sleep(4000);
                    this.fish.publicCanEat();
                    this.sleep(4000); 
                } else if (method_choosing == 5) {
                    System.out.println("You have chosen the Zebras class!");
                    this.sleep(1500);
                    System.out.println("They say hi!");
                    this.sleep(1000);
                    System.out.println("And here it is, saying hi!");
                    this.sleep(1500);
                    this.zebra.Call();
                    this.sleep(2000);
                    System.out.println("Notice that this can be accessed since it is part of the animal class! Pretty cool, right?");
                    this.sleep(2500);
                } else if (method_choosing == 6) {
                    System.out.println("Hey.");
                    this.sleep(500);
                    this.hippo.Call();
                    this.sleep(2000);
                    System.out.println("Peace out.");
                } else if (method_choosing == 7) {
                    System.out.println("Hey.");
                    this.sleep(500);
                    this.giraffe.Call();
                    this.sleep(2000);
                    System.out.println("Peace out.");
                } else if (method_choosing == 8) {
                    System.out.println("Thanks for visiting the zoo!");
                    break;
                } else {
                    System.out.println("Please enter a valid integer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
                this.scanner.nextLine();  // Clear the invalid input from the scanner buffer
            }
        }   
    }
}
