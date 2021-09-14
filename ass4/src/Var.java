import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tom Ben-Dor
 */
public class Var implements Expression {
    private final String name;

    /**
     * Constructor.
     *
     * @param name the variable's name.
     */
    public Var(String name) {
        this.name = name;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        if (assignment.containsKey(name)) {
            return assignment.get(name);
        }
        throw new Exception(String.format("'%s' variable was not set in assignment.", name));
    }

    @Override
    public Boolean evaluate() throws Exception {
        return evaluate(new HashMap<>());
    }

    @Override
    public List<String> getVariables() {
        List<String> list = new ArrayList<>();
        list.add(name);
        return list;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (var.equals(name)) {
            return expression;
        }
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
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Var)) {
            return false;
        }
        return name.equals(((Var) obj).name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
