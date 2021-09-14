import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * @author Tom Ben-Dor
 */
public class RegexProcessor {
    private final RelationsDatabase database;
    private final List<HearstPattern> patterns;

    /**
     * Constructor.
     *
     * @param database a database.
     * @param patterns patterns to search for in the text.
     */
    public RegexProcessor(RelationsDatabase database, List<HearstPattern> patterns) {
        this.database = database;
        this.patterns = patterns;
    }

    /**
     * @param files array of files.
     * @throws IOException if error occurs.
     */
    public void process(File[] files) throws IOException {
        for (File file : files) {
            process(file);
        }
    }

    /**
     * @param file a file.
     * @throws IOException if error occurs.
     */
    public void process(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            process(line);
        }
    }


    /**
     * @param line a text.
     * @throws IOException if error occurs.
     */
    public void process(String line) {
        for (HearstPattern pattern : patterns) {
            List<String> matches = pattern.matches(line);
            if (matches.isEmpty()) {
                continue;
            }
            for (String match : matches) {
                List<String> nounPhrases = HearstPattern.extractNounPhrases(match);
                String hypernym = nounPhrases.get(pattern.getHypernymIndex());
                nounPhrases.remove(pattern.getHypernymIndex());
                for (String hyponym : nounPhrases) {
                    database.insert(hypernym, hyponym);
                }
            }
        }
    }
}
