package Chapter4.Day17;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        BigDecimal d1 = BigDecimal.valueOf(12);
        BigDecimal d2 = BigDecimal.valueOf(12);

        System.out.println(System.identityHashCode(d1));
        System.out.println(System.identityHashCode(d2));

        System.out.println(d1 == d2);
    }
}
