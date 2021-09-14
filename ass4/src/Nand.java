/**
 * @author Tom Ben-Dor
 */
public class Nand extends BinaryExpression {
    /**
     * Constructor.
     *
     * @param firstExpression  an operand.
     * @param secondExpression an operand.
     */
    public Nand(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    public boolean apply(boolean a, boolean b) {
        return !(a && b);
    }

    @Override
    public Expression nandify() {
        return new Nand(getFirstExpression().nandify(), getSecondExpression().nandify());
    }

    @Override
    public Expression norify() {
        Expression firstExpressionNandified = getFirstExpression().norify();
        Expression secondExpressionNandified = getSecondExpression().norify();

        return new Nor(
                new Nor(
                        new Nor(
                                firstExpressionNandified,
                                firstExpressionNandified
                        ),
                        new Nor(
                                secondExpressionNandified,
                                secondExpressionNandified
                        )
                ),
                new Nor(
                        new Nor(
                                firstExpressionNandified,
                                firstExpressionNandified
                        ),
                        new Nor(
                                secondExpressionNandified,
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

        if ((firstExpressionSimplified.equals(Val.FALSE)) || (secondExpressionSimplified.equals(Val.FALSE))) {
            return Val.TRUE;
        }
        if (firstExpressionSimplified.equals(Val.TRUE)) {
            return new Not(secondExpressionSimplified).simplify();
        }
        if (secondExpressionSimplified.equals(Val.TRUE)) {
            return new Not(firstExpressionSimplified).simplify();
        }
        if (firstExpressionSimplified.equals(secondExpressionSimplified)) {
            return new Not(firstExpressionSimplified).simplify();
        }

        return new Nand(firstExpressionSimplified, secondExpressionSimplified);
    }

    @Override
    public String toString() {
        return "(" + getFirstExpression() + " A " + getSecondExpression() + ")";
    }

}
