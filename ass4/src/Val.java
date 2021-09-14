import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tom Ben-Dor
 */
public class Val implements Expression {
    public static final Val TRUE = new Val(true);
    public static final Val FALSE = new Val(false);
    private final boolean value;

    /**
     * Constructor.
     *
     * @param value the boolean value.
     */
    public Val(boolean value) {
        this.value = value;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return value;
    }

    @Override
    public Boolean evaluate() throws Exception {
        return evaluate(new HashMap<>());
    }

    @Override
    public List<String> getVariables() {
        return new ArrayList<>();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return this;
    }

    @Override
    public Expression nandify() {
        return this;
    }

    @Override
    public Expression norify() {
        return this;
    }

    @Override
    public Expression simplify() {
        return this;
    }

    @Override
    public String toString() {
        return value ? "T" : "F";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Val)) {
            return false;
        }

        return ((Val) obj).value == value;
    }

    @Override
    public int hashCode() {
        return (value ? 1 : 0);
    }
}
