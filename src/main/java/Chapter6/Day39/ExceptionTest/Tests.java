package Chapter6.Day39.ExceptionTest;

import java.util.ArrayList;
import java.util.List;

public class Tests {

    @ExceptionTest(ArithmeticException.class)
    public static void success() {
        int i = 0;
        i = i / i;
    }

    @ExceptionTest(ArithmeticException.class)
    public static void fail() {
        int[] a = new int[0];
        int i = a[1];
    }

    @ExceptionTest(ArithmeticException.class)
    public static void fail2() {}

    @ExceptionTest(IndexOutOfBoundsException.class)
    @ExceptionTest(NullPointerException.class)
    public static void doublyBad() {
        List<String> list = new ArrayList<>();
        list.add(5, null);
    }
}
