import package1.tour1;
import package2.tour2;
import package3.tour3;
import sleep.sleep;
class Main {

    final static private int minNoLength = 0; // Satisfies requirements.
    final static private int maxNoLength = 1;

    static void tour() {
        tour1.tourOne();
        sleep.sleepTime(1000);
        tour2.tourTwo();
        sleep.sleepTime(1000);
        tour3.tourThree();
        sleep.sleepTime(1000);
        System.out.println("Alright. Now time for the actual game.");
        Game game = new Game();
        game.start();
    }
    public void overloading() {
        System.out.println("Overloading!");
    }
    public void overloading(int iterations) {
        for (int i = 0; i < iterations; i++) {
            System.out.println("This is the " + (i+1) + "th iteration.");           
        }
    }
    static void Main() { // Insert help function here, which is written to satisfy requirements. 
        System.out.println("Usage: java -jar assignment11.jar" );
    }

    public static void main(String[] args) {
        if (args.length > maxNoLength || args.length < minNoLength) {
            Main Obj = new Main();
        } else if (args.length == 0) {
            Game game = new Game();
            game.start();
        } else if (args.length == 1 && args[0].equals("--packages")) {
            tour();
        }
    }
}
