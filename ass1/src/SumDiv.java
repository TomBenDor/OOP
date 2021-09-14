/**
 * @author Tom Ben-Dor
 */
public class SumDiv {
    public static final int NUMBER_OF_ARGUMENTS = 3;

    /**
     * The program gets 3 numbers from the user: a, b & c.
     * It prints the numbers that are between 1 - a and divisible by b or c, and their sum.
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
        int a = Integer.parseInt(args[0]), b = Integer.parseInt(args[1]), c = Integer.parseInt(args[2]);

        // Validating arguments.
        if ((a <= 0) || (b <= 0) || (c <= 0)) {
            System.out.println("Invalid input");
            return;
        }

        // Summing all numbers between 1 - a that are divisible by b or c.
        int sum = 0;
        for (int i = 1; i <= a; i++) {
            if ((i % b == 0) || (i % c == 0)) {
                System.out.println(i);
                sum += i;
            }
        }

        // Printing the sum.
        System.out.println(sum);
    }
}
