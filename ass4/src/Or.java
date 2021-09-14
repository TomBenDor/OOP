/**
 * @author Tom Ben-Dor
 */
public class Or extends BinaryExpression {
    /**
     * Constructor.
     *
     * @param firstExpression  an operand.
     * @param secondExpression an operand.
     */
    public Or(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    public boolean apply(boolean a, boolean b) {
        return a || b;
    }

    @Override
    public Expression nandify() {
        Expression firstExpressionNandified = getFirstExpression().nandify();
        Expression secondExpressionNandified = getSecondExpression().nandify();

        return new Nand(
                new Nand(
                        firstExpressionNandified,
                        firstExpressionNandified
                ),
                new Nand(
                        secondExpressionNandified,
                        secondExpressionNandified
                )
        );
    }

    @Override
    public Expression norify() {
        Expression firstExpressionNandified = getFirstExpression().norify();
        Expression secondExpressionNandified = getSecondExpression().norify();

        return new Nor(
                new Nor(
                        firstExpressionNandified,
                        secondExpressionNandified
                ),
                new Nor(
                        firstExpressionNandified,
                        secondExpressionNandified
                )
        );
    }

    @Override
    public Expression simplify() {
        Expression result = super.simplify();

        if (result.equals(Val.TRUE) || result.equals(Val.FALSE)) {
            return result;
        }

        Expression firstExpressionSimplified = getFirstExpression().simplify();
        Expression secondExpressionSimplified = getSecondExpression().simplify();

        if (firstExpressionSimplified.equals(Val.FALSE)) {
            return secondExpressionSimplified;
        }
        if (secondExpressionSimplified.equals(Val.FALSE)) {
            return firstExpressionSimplified;
        }
        if ((firstExpressionSimplified.equals(Val.TRUE)) || (secondExpressionSimplified.equals(Val.TRUE))) {
            return Val.TRUE;
        }
        if (firstExpressionSimplified.equals(secondExpressionSimplified)) {
            return firstExpressionSimplified;
        }
        return new Or(firstExpressionSimplified, secondExpressionSimplified);
    }

    @Override
    public String toString() {
        return "(" + getFirstExpression() + " | " + getSecondExpression() + ")";
    }
}
