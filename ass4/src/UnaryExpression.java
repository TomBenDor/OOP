import java.util.List;
import java.util.Map;

/**
 * @author Tom Ben-Dor
 */
public abstract class UnaryExpression extends BaseExpression {
    private final Expression expression;

    /**
     * Constructor.
     *
     * @param expression the operand.
     */
    public UnaryExpression(Expression expression) {
        this.expression = expression;
    }

    /**
     * Applying the operator.
     *
     * @param a a value.
     * @return the result.
     */
    public abstract boolean apply(boolean a);

    /**
     * @return the operand.
     */
    public Expression getExpression() {
        return expression;
    }

    @Override
    public List<String> getVariables() {
        return expression.getVariables();
    }

    @Override
    public Expression assign(String var, Expression exp) {
        try {
            return this.getClass().getConstructor(Expression.class).newInstance(expression.assign(var, exp));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return apply(expression.evaluate(assignment));
    }

    @Override
    public boolean equals(Object obj) {
        if (!getClass().isInstance(obj)) {
            return false;
        }
        return ((UnaryExpression) obj).expression.equals(expression);
    }

    @Override
    public int hashCode() {
        return expression.hashCode();
    }
}
