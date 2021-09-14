/**
 * @author Tom Ben-Dor
 */
public class Not extends UnaryExpression {
    /**
     * Constructor.
     *
     * @param expression an operand.
     */
    public Not(Expression expression) {
        super(expression);
    }

    @Override
    public boolean apply(boolean a) {
        return !a;
    }

    @Override
    public Expression nandify() {
        Expression expressionNandified = getExpression().nandify();

        return new Nand(
                expressionNandified,
                expressionNandified
        );
    }

    @Override
    public Expression norify() {
        Expression expressionNorified = getExpression().norify();

        return new Nor(
                expressionNorified,
                expressionNorified
        );
    }

    @Override
    public Expression simplify() {
        Expression expressionSimplified = getExpression().simplify();
        if (expressionSimplified.equals(Val.FALSE)) {
            return Val.TRUE;
        }
        if (expressionSimplified.equals(Val.TRUE)) {
            return Val.FALSE;
        }

        return new Not(expressionSimplified);
    }

    @Override
    public String toString() {
        return "~(" + getExpression() + ")";
    }
}
