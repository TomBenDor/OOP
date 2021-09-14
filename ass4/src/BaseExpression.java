import java.util.HashMap;

/**
 * @author Tom Ben-Dor
 */
public abstract class BaseExpression implements Expression {
    @Override
    public Boolean evaluate() throws Exception {
        return evaluate(new HashMap<>());
    }
}
