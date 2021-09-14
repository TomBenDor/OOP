/**
 * @author Tom Ben-Dor
 */
public class Nor extends BinaryExpression {
    /**
     * Constructor.
     *
     * @param firstExpression  an operand.
     * @param secondExpression an operand.
     */
    public Nor(Expression firstExpression, Expression secondExpression) {
        super(firstExpression, secondExpression);
    }

    @Override
    public boolean apply(boolean a, boolean b) {
        return !(a || b);
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
                        new Nand(
                                firstExpressionNandified,
                                firstExpressionNandified
                        ),
                        new Nand(
                                secondExpressionNandified,
                                secondExpressionNandified
                        )
                )
        );
    }

    @Override
    public Expression norify() {
        return new Nor(getFirstExpression().norify(), getSecondExpression().norify());
    }

    @Override
    public Expression simplify() {
        Expression result = super.simplify();

        if (result.equals(Val.TRUE) || result.equals(Val.FALSE)) {
            return result;
        }

        Expression firstExpressionSimplified = getFirstExpression().simplify();
        Expression secondExpressionSimplified = getSecondExpression().simplify();

        if ((firstExpressionSimplified.equals(Val.TRUE)) || (secondExpressionSimplified.equals(Val.TRUE))) {
            return Val.FALSE;
        }
        if (firstExpressionSimplified.equals(Val.FALSE)) {
            return new Not(secondExpressionSimplified).simplify();
        }
        if (secondExpressionSimplified.equals(Val.FALSE)) {
            return new Not(firstExpressionSimplified).simplify();
        }
        if (firstExpressionSimplified.equals(secondExpressionSimplified)) {
            return new Not(firstExpressionSimplified).simplify();
        }

        return new Nor(firstExpressionSimplified, secondExpressionSimplified);
    }

    @Override
    public String toString() {
        return "(" + getFirstExpression() + " V " + getSecondExpression() + ")";
    }

}
