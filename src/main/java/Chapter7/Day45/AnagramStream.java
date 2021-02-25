package Chapter7.Day45;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * 스트림과 도우미메서드의 적절한 조화.
 */
public class AnagramStream {

    public static void main(String[] args) throws IOException {
        Path dic = Paths.get(args[0]);
        int minGroupSize = Integer.parseInt(args[1]);

        try (Stream<String> words = Files.lines(dic)) {
            words.collect(Collectors.groupingBy(word -> alphabetize(word)))
                .values().stream()
                .filter(group -> group.size() > minGroupSize)
                .forEach(g -> System.out.println(g.size() + ": " + g));
        }
    }

    private static String alphabetize(String word) {
        char a[] = word.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }
}
