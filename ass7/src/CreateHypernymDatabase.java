import java.io.File;
import java.io.IOException;

/**
 * @author Tom Ben-Dor
 */
public class CreateHypernymDatabase {
    /**
     * Creating a database and writing to file.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        File dir = new File(args[0]);
        File output = new File(args[1]);

        File[] files = dir.listFiles();

        RelationsDatabase database = new RelationsDatabase();
        RegexProcessor processor = new RegexProcessor(database, HearstPattern.PATTERNS);
        try {
            processor.process(files);
            database.writeToFile(output);
        } catch (IOException e) {
            System.out.println("Invalid arguments - failed to create database");
        }
    }
}
