class Main {

    final static protected int minArgsLength = 0; // Satisfies requirements.
    final static private     int maxArgsLength = 0;

    static void help() { // Insert help function here.
        System.out.println("Usage: java -jar assignment11.jar" );
    }

    public static void main(String[] args) {
        if (args.length > maxArgsLength) {
            help();
        } else {
            Game game = new Game();
            game.start();
        }
    }
}
