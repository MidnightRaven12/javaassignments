package sleep;

// I got annoyed that there were several times in which I had to make this exact method. So I'm doing it right here. 

public class sleep {
    // Satisfies Requirements
    protected int dummy_variable = 14;
    private int dummy_variable2 = 34;
    public static void sleepTime(int time) {
        try {
            Thread.sleep(time);          
        } catch (InterruptedException e) {
            System.out.println("The sleep was interrupted.");
        }
    }
    // Satisfies Requirements.
    public sleep() {
        this.dummy_variable = 1;
        this.dummy_variable2 = 4;
    }
    public sleep(int dummy_variable, int dummy_variable2) {
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
