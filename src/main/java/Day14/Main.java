package Day14;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        int[] arr = {4, 2, 3 ,1};
        Arrays.sort(arr);
        for (int i : arr)
            System.out.println(i);

        /**
         * comparable 을 구현한 String 이용하여 treeset 이 정렬해줌.
         */
        Set<String> s = new TreeSet<>();
        s.add("z");
        s.add("e");
        s.add("u");
        s.add("y");
        Collections.addAll(s, "z", "d", "f", "a", "a");
        System.out.println(s);

        ComparatorEx ce1 = new ComparatorEx(100,4569,2804);
        ComparatorEx ce2 = new ComparatorEx(010,1234,5678);
        System.out.println(ce2.compareTo(ce1));
        System.out.println(ce1.compareTo(ce2));

    }
}
