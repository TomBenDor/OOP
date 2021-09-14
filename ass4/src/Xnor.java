/**
 * @author Tom Ben-Dor
 */
public class Xnor extends BinaryExpression {
    /**
     * Constructor.
     *
     * @param firstExpression  an operand.
     * @param secondExpression an operand.
     */
    public Xnor(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    public boolean apply(boolean a, boolean b) {
        return a == b;
    }

    @Override
    public Expression nandify() {
        Expression firstExpressionNandified = getFirstExpression().nandify();
        Expression secondExpressionNandified = getSecondExpression().nandify();

        return new Nand(
                new Nand(
                        new Nand(
                                firstExpressionNandified,
                                firstExpressionNandified
                        ),
                        new Nand(
                                secondExpressionNandified,
                                secondExpressionNandified
                        )
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
                        new Nor(
                                firstExpressionNandified,
                                secondExpressionNandified
                        )
                ),
                new Nor(
                        secondExpressionNandified,
                        new Nor(
                                firstExpressionNandified,
                                secondExpressionNandified
                        )
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

        if (firstExpressionSimplified.equals(secondExpressionSimplified)) {
            return Val.TRUE;
        }

        return new Xnor(firstExpressionSimplified, secondExpressionSimplified);
    }

    @Override
    public String toString() {
        return "(" + getFirstExpression() + " # " + getSecondExpression() + ")";
    }
}
