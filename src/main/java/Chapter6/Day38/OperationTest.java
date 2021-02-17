package Chapter6.Day38;

import java.nio.file.LinkOption;
import java.util.EnumSet;
import java.util.Set;

public class OperationTest {
    public static void main(String[] args) {
        double x = 1.5;
        double y = 3.0;

        System.out.println("--지구--");
        test(BasicOperation.class, x, y);
        System.out.println("--화성--");
        test(MarsOperation.class, x, y);

        Set<MarsOperation> s = EnumSet.of(MarsOperation.MARS_PLUS, MarsOperation.MARS_MINUS);

        LinkOption
    }

    private static <T extends Enum<T> & Operation> void test (Class<T> opEnumType, double x, double y) {
        for (Operation op : opEnumType.getEnumConstants()) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
    }
}
