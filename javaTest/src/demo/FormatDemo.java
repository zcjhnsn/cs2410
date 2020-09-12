package demo;

/**
 * Add a description of the class here
 *
 * @author Chad
 * @version XXX
 */
public class FormatDemo {
    private String string1 = "First";
    private String string2 = "Second";
    private int int1 = 123456;
    private double double1 = 12345.6789;

    public FormatDemo() {
        System.out.printf("%15s %15s %15d %15f\n", string1, string2, int1, double1);
        System.out.printf("%-15s %15s %-15d %15f\n", string1, string2, int1, double1);
        System.out.printf("%-15s %15s %-15d %15.2f\n", string1, string2, int1, double1);
        System.out.printf("%-15s %-15s %-15d %15f\n", string1, string2, int1, double1);

        String output = "";
        output += String.format("%15s %15s %15d %15f\n", string1, string2, int1, double1);
        output += String.format("%-15s %15s %-15d %15f\n", string1, string2, int1, double1);
        output += String.format("%-15s %15s %-15d %15.2f\n", string1, string2, int1, double1);
        output += String.format("%-15s %-15s %-15d %15f\n", string1, string2, int1, double1);
        System.out.println(output);

    }

    public static void main(String args[]) {
        new FormatDemo();
    }
}