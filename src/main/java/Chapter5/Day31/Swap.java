package Chapter5.Day31;

import java.util.List;

public class Swap {

    public static <E> void swap1(List<E> list, int i, int j) {
        list.set(i, list.set(i, list.get(i)));
    }

    public static void swap2(List<?> list, int i, int j) {
        swap2Helper(list, i, j);
    }

    private static <E> void swap2Helper(List<E> list, int i, int j) {
        list.set(i, list.set(i, list.get(i)));
    }
}
