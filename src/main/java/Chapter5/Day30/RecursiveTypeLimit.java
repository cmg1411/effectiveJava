package Chapter5.Day30;

import java.util.*;

public class RecursiveTypeLimit<E> {
    /**
     * 재귀적 타입한정.
     * <E extends Comparable<E>> 는 모든 타입 E는 자신과 비교할 수 있다. 라는 말과 같다.
     */
    public static <E extends Comparable<E>> E max(Collection<E> c) {
        if (c.isEmpty()) {
            throw new IllegalArgumentException("컬렉션이 비었습니다.");
        }
        E result = null;
        for (E e : c) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(RecursiveTypeLimit.max(List.of("dai", "a" , "c")));
//        System.out.println(RecursiveTypeLimit.max(List.of("dai", 1 , "c"))); // 컴파일에러 발생
    }
}
