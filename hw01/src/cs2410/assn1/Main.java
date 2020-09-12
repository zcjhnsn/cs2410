package cs2410.assn1;

/**
 * This is a simple program that outputs my first and last name.
 *
 * @author Zac Johnson
 * @version 1.0
 */

public class Main {

    /**
     * My first name
     */
    public String fName;

    /**
     * My last name
     */
    public String lName;

    /**
     * This is the default constructor
     */
    public Main() {
        fName = "Zac";
        lName = "Johnson";
    }

    /**
     * This is the main start method
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.printName(main.fName, main.lName);
    }

    /**
     * Print the first and last name of the developer and a small message.
     *
     * @param fName first name parameter
     * @param lName last name parameter
     */
    private static void printName(String fName, String lName) {
        System.out.println(fName + " " + lName + " is excited to take this class.");
    }
}
