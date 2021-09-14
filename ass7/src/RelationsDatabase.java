import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author Tom Ben-Dor
 */
public class RelationsDatabase {
    private final Map<String, Map<String, Integer>> db = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    /**
     * Inserting a new relation.
     *
     * @param hypernym a hypernym.
     * @param hyponym  a hyponym.
     */
    public void insert(String hypernym, String hyponym) {
        if (!db.containsKey(hypernym)) {
            db.put(hypernym, new TreeMap<>(String.CASE_INSENSITIVE_ORDER));
        }
        Map<String, Integer> count = db.get(hypernym);
        count.put(hyponym, count.getOrDefault(hyponym, 0) + 1);
    }

    /**
     * Writing to file.
     *
     * @param output file to write into.
     * @throws IOException if error occurs.
     */
    public void writeToFile(File output) throws IOException {
        output.createNewFile();
        FileWriter writer = new FileWriter(output);

        writer.write(db.entrySet().stream()
                .filter(e -> e.getValue().size() >= 3)
                .map(e -> e.getKey() + ": " + e.getValue().entrySet().stream()
                        .sorted((e1, e2) -> e2.getValue() - e1.getValue())
                        .map(e0 -> String.format("%s (%d)", e0.getKey(), e0.getValue()))
                        .collect(Collectors.joining(", ")))
                .collect(Collectors.joining("\n")));
        writer.close();
    }

    /**
     * Printing all relations that included the lemma.
     *
     * @param lemma a lemma.
     */
    public void search(String lemma) {
        String out = db.entrySet()
                .stream()
                .filter(e -> e.getValue().containsKey(lemma))
                .sorted((e1, e2) -> e2.getValue().get(lemma) - e1.getValue().get(lemma))
                .map(e -> String.format("%s: (%d)", e.getKey(), e.getValue().get(lemma)))
                .collect(Collectors.joining("\n"));
        if (!out.isEmpty()) {
            System.out.println(out);
        } else {
            System.out.println("The lemma doesn't appear in the corpus.");
        }
    }
}
