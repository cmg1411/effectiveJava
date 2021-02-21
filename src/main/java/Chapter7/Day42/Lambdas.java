package Chapter7.Day42;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Lambdas {

    public static void main(String[] args) {
        List<String> words1 = Arrays.asList("가나","가나다","가");
        List<String> words2 = Arrays.asList("가나","가나다","가");
        List<String> words3 = Arrays.asList("가나","가나다","가");
        List<String> words4 = Arrays.asList("가나","가나다","가");

        Collections.sort(words1, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });

        System.out.println(words1);

        Collections.sort(words2,  (x, y) -> Integer.compare(x.length(), y.length()));
        System.out.println(words2);

        Collections.sort(words3, Comparator.comparingInt(String::length));
        System.out.println(words3);

        words4.sort(Comparator.comparingInt(String::length));
        System.out.println(words4);
    }
}
