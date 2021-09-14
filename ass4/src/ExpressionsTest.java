import java.util.HashMap;
import java.util.Map;

/**
 * @author Tom Ben-Dor
 */
public class ExpressionsTest {
    /**
     * Testing the expressions.
     *
     * @param args command line arguments.
     * @throws Exception if error accrued.
     */
    public static void main(String[] args) throws Exception {
        Var x = new Var("x");
        Var y = new Var("y");
        Var z = new Var("z");

        Expression expression = new And(x, new Or(y, new And(z, Val.TRUE)));

        System.out.println(expression);

        Map<String, Boolean> assignment = new HashMap<>();
        assignment.put("x", true);
        assignment.put("y", false);
        assignment.put("z", true);
        System.out.println(expression.evaluate(assignment));

        System.out.println(expression.nandify());

        System.out.println(expression.norify());

        System.out.println(expression.simplify());
    }
}
