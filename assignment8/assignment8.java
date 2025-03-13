public class assignment8 {
    public static int fibonacci(int n) {
        int a = 0;
        int b = 1; // Basically, F_1
        int i = n;
        while (i > 1) {
            int c = a + b;
            a = b;
            b = c;
            i--;
        }
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return b;
        }
    }
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java -jar assignment8.jar <n>");
            System.out.println("Example: java -jar assignment8.jar 5 should output 5.");
        } else {
            System.out.println(fibonacci(Integer.parseInt(args[0])));
        }
    }
}