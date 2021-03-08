package Chapter8.Day52;

import java.util.*;

public class BadOverloading {

    public static String classify(Set<?> s) {
        return "집합";
    }

    public static String classify(List<?> s) {
        return "리스트";
    }

    public static String classify(Collection<?> s) {
        return "그 외";
    }

    public static void main(String[] args) {
        Collection<?>[] collections = {
            new ArrayList<Double>(),
            new HashSet<Integer>(),
            new HashMap<String, String>().values()
        };

        for (Collection<?> collection : collections) {
            System.out.println(classify(collection));
        }
    }
}
