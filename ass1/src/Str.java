import java.util.Arrays;

/**
 * @author Tom Ben-Dor
 */
public class Str {
    public static final int NUMBER_OF_ARGUMENTS = 2;

    /**
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        // Validating number of arguments.
        if (args.length != NUMBER_OF_ARGUMENTS) {
            System.out.println("Invalid input");
            return;
        }

        // Initializing variables.
        String query = args[0], sequence = args[1];

        // Splitting the sequence.
        String[] words = sequence.split("_");

        // Printing all words that start with query.
        Arrays.stream(words)
                .filter(word -> word.startsWith(query))
                .forEach(System.out::println);

        // Printing all words that contains query but do no start with query.
        Arrays.stream(words)
                .filter(word -> word.contains(query))
                .filter(word -> !word.startsWith(query))
                .forEach(System.out::println);
    }
}
