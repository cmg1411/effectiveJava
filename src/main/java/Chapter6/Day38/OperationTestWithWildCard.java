package Chapter6.Day38;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

public class OperationTestWithWildCard {
    public static void main(String[] args) {
        double x = 1.5;
        double y = 3.0;

        System.out.println("--지구--");
        test(EnumSet.allOf(BasicOperation.class), x, y);
        System.out.println("--화성--");
        test(EnumSet.allOf(MarsOperation.class), x, y);
        System.out.println("--지구의 더하기와 화성의 더하기--");
        test(Set.of(BasicOperation.PLUS, MarsOperation.MARS_PLUS), x, y);
    }

    private static void test (Collection<? extends Operation> opSet, double x, double y) {
        for (Operation op : opSet) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
    }
}
