
//There's going to be a lot of comments here. 

class Animal {
    public int age;
    public String gender;
    public Animal(int animalAge, String animalGender) {
        age = animalAge;

    } 
    public void isMammal() { // Notice that this variable is public. Which means that I can access it from any other class.
        System.out.println("I may or may not be a mammal.");
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
};
class Fish extends Animal {
    private final double sizeInFeet; // Think of these as temporary/hidden variables, that you might get from a database. Again, the final part is because sizeInFeet will (presumably), not change.
    public Fish(int animalAge, String animalGender, double size) {
        super(animalAge, animalGender);
        sizeInFeet = size;
    } 
    public void size() {
        System.out.println(sizeInFeet);
    }
    // Notice that with the following setup, we cannot even access anything.
    private void canEat() {
        System.out.println("This is a private method canEat() from class fish");
    } 
    // But we can here...
    public void publicCanEat() {
        canEat();
    } 
};
class Zebra extends Animal {
    public Zebra(int animalAge, String animalGender) { 
        super(animalAge, animalGender); //This super() is a way to construct the class of Animal.
    }
    public Boolean is_wild;
    public void run() {
        System.out.println("Hello World!");
    }
    public static void staticRun() { // Notice that I can call this, without defining an object, though you will have to define Zebra.staticRun()
        System.out.println("Hello, static World!");
    }
}


public class chanassignment6 {
    public static void mainStaticRun() { // Notice that I can call this, without even defining an object of App
        System.out.println("Hello, static, Main, World!");
    }
    public static void main(String[] args) throws Exception {
        Fish Nemo = new Fish(14, "male", 5.5);
        Nemo.Call();
        Nemo.publicCanEat();
        Animal Fido = new Animal(13, "male");
        Fido.publicanimals();
        Zebra Marty = new Zebra(12, "male");
        Marty.run();
        Zebra.staticRun();
        mainStaticRun();
    }
}
