package package2;

public class tour2 {
    // Satisfies Requirements
    protected int dummy_variable = 0;
    private int dummy_variable2 = 0;
    public static void tourTwo() {
        System.out.println("Here is the second package!");
    }
    public tour2() {
        this.dummy_variable = 14;
        this.dummy_variable2 = 34;
    }
    public tour2(int dummy_variable, int dummy_variable2) {
        this.dummy_variable = dummy_variable;
        this.dummy_variable2 = dummy_variable2;
    }
    public void overloading() {
        System.out.println("Overloading!");
    }
    public void overloading(int iterations) {
        for (int i = 0; i < iterations; i++) {
            System.out.println("This is the " + (i+1) + "th iteration.");           
        }
    }
}
