package demo; /**
 *
 */

/**≠≠≠
 * @author Chad
 *
 */
public class StringCompare {

    public StringCompare() {

        System.out.println("String Literals");
        String a = "Hello";
        String b = "Hello";
        String c = new String("Hello");

        System.out.println("\nString Equality");
        compareTo(a, b);
        equals(a, b);
        doubleEquals(a, b);

        System.out.println("\nString Equality with new");
        compareTo(b, c);
        equals(b, c);
        doubleEquals(b, c);

        System.out.println("\nString Equality with interning");
        c = c.intern();

        compareTo(b, c);
        equals(b, c);
        doubleEquals(b, c);
    }

    private void compareTo(String x, String y) {
        System.out.println("compareTo: " + x.compareTo(y));
    }

    private void equals(String x, String y) {
        System.out.println("equals: " + x.equals(y));
    }

    private void doubleEquals(String x, String y) {
        System.out.println("==: " + (x == y));
    }

    public static void main(String[] args) {
        new StringCompare();
    }

}