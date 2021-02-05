package Chapter9.Day61;

import java.util.Comparator;

public class OrderCompare {

    public static void main(String[] args) {
        Comparator<Integer> comparator = (x, y) -> (x < y) ? -1 : (x == y ? 0 : 1);

        System.out.println(comparator.compare(new Integer(3), new Integer(3)));
        System.out.println(comparator.compare(Integer.valueOf(111111), Integer.valueOf(111111)));

        Comparator<Integer> tComparator = Comparator.naturalOrder();
        System.out.println(tComparator.compare(new Integer(3), new Integer(3)));
    }
}
