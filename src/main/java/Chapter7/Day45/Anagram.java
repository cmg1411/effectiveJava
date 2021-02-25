package Chapter7.Day45;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Anagram {

    public static void main(String[] args) throws FileNotFoundException {
        File dictionary = new File(args[0]);
        int minGroupSize = Integer.parseInt(args[1]);

        Map<String, Set<String>> groups = new HashMap<>();
        try (Scanner s = new Scanner(dictionary)) {
            while (s.hasNext()) {
                String word = s.next();
                groups.computeIfAbsent(alphabetize(word), unused -> new TreeSet<>()).add(word);
            }
        }

        /**
         * computeIfAbsent
         * 키 값이 있으면 매핑된 값 반환
         * 없으면 함수 객체를 키에 적용하여 값을 계산하고, 키값에 매핑한 다음 계산된 값 반환
         */

        for (Set<String> group : groups.values()) {
            if (group.size() == minGroupSize)
                System.out.println(group.size() + ": " + group);
        }
    }

    private static String alphabetize(String word) {
        char a[] = word.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }
}
