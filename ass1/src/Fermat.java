/**
 * @author Tom Ben-Dor
 */
public class Fermat {
    public static final int NUMBER_OF_ARGUMENTS = 2;

    /**
     * The program gets 2 numbers from the user: n & range.
     * It prints all natural numbers a, b, & c when a <= b < range, c < range and:
     * a^n + b^n = c^n
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        // Validating number of arguments.
        if (args.length != NUMBER_OF_ARGUMENTS) {
            System.out.println("Invalid input");
            return;
        }

        // Initializing variables.
        int n = Integer.parseInt(args[0]), range = Integer.parseInt(args[1]);

        // Validating arguments.
        if ((n <= 0) || (range <= 0)) {
            System.out.println("Invalid input");
            return;
        }

        // If n > 2 there are no such a, b & c.
        if (n > 2) {
            System.out.println("no");
            return;
        }

        boolean found = false;
        for (int a = 1; a < range; a++) {
            for (int b = a; b < range; b++) {
                double c = Math.pow(a, n) + Math.pow(b, n);
                if (n == 2) {
                    c = Math.sqrt(c);
                }
                if ((c % 1 == 0) && (c < range)) {
                    // Printing the numbers.
                    System.out.println(a + "," + b + "," + (int) c);
                    found = true;
                }
            }
        }

        // Printing no if not found.
        if (!found) {
            System.out.println("no");
        }
    }
}
