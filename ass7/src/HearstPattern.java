import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tom Ben-Dor
 */
public class HearstPattern {
    public static final List<HearstPattern> PATTERNS =
            List.of(new HearstPattern("such NP as NP( , NP)*( (, )?(and|or) NP)?", 0),
                    new HearstPattern("NP (, )?including NP( (, )?NP)*( (, )?(and|or) NP)?", 0),
                    new HearstPattern("NP (, )?especially NP( (, )?NP)*( (, )?(and|or) NP)?", 0),
                    new HearstPattern("NP (, )?such as NP( (, )?NP)*( (, )?(and|or) NP)?", 0),
                    new HearstPattern("NP (, )?which is ((an example|a kind|a class) of )?NP", 1));
    private static final String NOUN_PHRASE_REGEX = "<np>([^<]*)</np>";
    private final Pattern pattern;
    private final int hypernymIndex;

    /**
     * Constructor.
     *
     * @param regex         the pattern.
     * @param hypernymIndex the index of the hypernym.
     */
    public HearstPattern(String regex, int hypernymIndex) {
        this.pattern = Pattern.compile(regex.replace("NP", NOUN_PHRASE_REGEX));
        this.hypernymIndex = hypernymIndex;
    }

    /**
     * @param text a sentence.
     * @return list of all NPs in the sentence.
     */
    public static List<String> extractNounPhrases(String text) {
        Matcher matcher = Pattern.compile(NOUN_PHRASE_REGEX).matcher(text);
        List<String> phrases = new ArrayList<>();
        while (matcher.find()) {
            phrases.add(matcher.group(1));
        }
        return phrases;
    }

    /**
     * @return hypernymIndex.
     */
    public int getHypernymIndex() {
        return hypernymIndex;
    }

    /**
     * @param line a line.
     * @return list of all sentences that match the regex.
     */
    public List<String> matches(String line) {
        Matcher matcher = pattern.matcher(line);

        List<String> results = new ArrayList<>();
        while (matcher.find()) {
            results.add(matcher.group());
        }
        return results;
    }
}
