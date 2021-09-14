/**
 * @author Tom Ben-Dor
 */
public class And extends BinaryExpression {
    /**
     * Constructor.
     *
     * @param firstExpression  an operand.
     * @param secondExpression an operand.
     */
    public And(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    public boolean apply(boolean a, boolean b) {
        return a && b;
    }

    @Override
    public Expression nandify() {
        Expression firstExpressionNandified = getFirstExpression().nandify();
        Expression secondExpressionNandified = getSecondExpression().nandify();

        return new Nand(
                new Nand(
                        firstExpressionNandified,
                        secondExpressionNandified
                ),
                new Nand(
                        firstExpressionNandified,
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
                        firstExpressionNandified
                ),
                new Nor(
                        secondExpressionNandified,
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

        if (firstExpressionSimplified.equals(Val.TRUE)) {
            return secondExpressionSimplified;
        }
        if (secondExpressionSimplified.equals(Val.TRUE)) {
            return firstExpressionSimplified;
        }
        if ((firstExpressionSimplified.equals(Val.FALSE)) || (secondExpressionSimplified.equals(Val.FALSE))) {
            return Val.FALSE;
        }
        if (firstExpressionSimplified.equals(secondExpressionSimplified)) {
            return firstExpressionSimplified;
        }
        return new And(firstExpressionSimplified, secondExpressionSimplified);
    }

    @Override
    public String toString() {
        return "(" + getFirstExpression() + " & " + getSecondExpression() + ")";
    }
}
