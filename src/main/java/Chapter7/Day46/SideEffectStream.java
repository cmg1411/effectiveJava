package Chapter7.Day46;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SideEffectStream {

    /**
     * 단어별 수를 세어 Map 빈도표를 만드는 기능.
     */
    public static void main(String[] args) {
        Map<String, Long> freq1 = new HashMap<>();

        /**
         * 외부 객체인 freq 를 직접 수정하므로 스트림이 절대 아니다 !
         */
        try (Stream<String> words = new Scanner(System.in).tokens()) {
            words.forEach(word -> {
                freq1.merge(word.toLowerCase(), 1L, Long::sum);
            });
        }

        /**
         * 위를 잘 리펙토링한 스트림이다.
         * Collectors 를 잘 이용하라.
         */
        Map<String, Long> freq2 = new HashMap<>();

        try (Stream<String> words = new Scanner(System.in).tokens()) {
            freq2 = words
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));
        }
    }
}
