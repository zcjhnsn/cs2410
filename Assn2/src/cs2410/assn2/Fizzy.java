package cs2410.assn2;

public class Fizzy {
    /**
     * Counter for basic program function
     */
    private static int counter;

    /**
     * This is the default constructor
     */
    public Fizzy() {
        counter = 1;
    }

    /**
     * This is the main start method
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Fizzy fizzy = new Fizzy();
        while(counter <= 100) {
            if (isFizz(counter) && isBuzz(counter)) {
                System.out.println("FizzBuzz");
            } else if (isFizz(counter)) {
                System.out.println("Fizz");
            } else if (isBuzz(counter)) {
                System.out.println("Buzz");
            } else {
                System.out.println(counter);
            }
            counter++;
        }
    }

    /**
     * Checks if value is a multiple of 3
     *
     * @param val value of counter
     * @return boolean
     */
    private static boolean isFizz (int val) {
        return val % 3 == 0;
    }

    /**
     * Checks if value is a multiple of 5
     *
     * @param val
     * @return boolean
     */
    private static boolean isBuzz(int val) {
        return val % 5 == 0;
    }
}
