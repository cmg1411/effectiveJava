package Chapter3.Day14;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * BigDecimal 은 compareTo 와  equals 메서드가
 * 일관되게 설계되지 못했다.
 */
public class Bigdecimal {
    public static void main(String[] args) {
        BigDecimal d1 = new BigDecimal("1.0");
        BigDecimal d2 = new BigDecimal("1.00");

        Set<BigDecimal> s1 = new HashSet<>();
        Collections.addAll(s1, d1, d2);
        Set<BigDecimal> s2 = new TreeSet<>();
        Collections.addAll(s2, d1, d2);

        
        // BigDecimal 의 equals 와 compareTo 가 다르다는 증
        // TODO HashSet 은 equals 로 요소를 비교하기 때문에, BigDecimal 중복이 제거되지 않음
        System.out.println("HashSet");
        s1.stream().forEach(System.out::println);
        // TODO TreeSet 은 compareTo 로 요소를 비교하기 때문에, BigDecimal 중복이 제거
        System.out.println("TreeSet");
        s2.stream().forEach(System.out::println);
    }
}
