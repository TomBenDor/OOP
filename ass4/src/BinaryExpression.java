import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Tom Ben-Dor
 */
public abstract class BinaryExpression extends BaseExpression {
    private final Expression firstExpression;
    private final Expression secondExpression;


    /**
     * Constructor.
     *
     * @param firstExpression  an operand.
     * @param secondExpression an operand.
     */
    public BinaryExpression(Expression firstExpression, Expression secondExpression) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }

    /**
     * Applying the operator on te operands.
     *
     * @param a operand.
     * @param b operand.
     * @return the applied value.
     */
    public abstract boolean apply(boolean a, boolean b);

    /**
     * @return the first operand.
     */
    public Expression getFirstExpression() {
        return firstExpression;
    }

    /**
     * @return the second operand.
     */
    public Expression getSecondExpression() {
        return secondExpression;
    }

    @Override
    public List<String> getVariables() {
        Set<String> variables = new HashSet<>();

        variables.addAll(firstExpression.getVariables());
        variables.addAll(secondExpression.getVariables());

        return new ArrayList<>(variables);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        try {
            return this.getClass().getConstructor(Expression.class, Expression.class)
                    .newInstance(firstExpression.assign(var, expression), secondExpression.assign(var, expression));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return apply(firstExpression.evaluate(assignment), secondExpression.evaluate(assignment));
    }

    @Override
    public Expression simplify() {
        try {
            return new Val(evaluate());
        } catch (Exception e) {
            return this;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!this.getClass().isInstance(obj)) {
            return false;
        }
        return (firstExpression.equals(((BinaryExpression) obj).firstExpression)
                && secondExpression.equals(((BinaryExpression) obj).secondExpression))
                || (secondExpression.equals(((BinaryExpression) obj).firstExpression)
                && firstExpression.equals(((BinaryExpression) obj).secondExpression));
    }

    @Override
    public int hashCode() {
        int result = firstExpression.hashCode();
        result = 31 * result + secondExpression.hashCode();
        return result;
    }
}
