package Chapter5.Day30;

import java.util.HashSet;
import java.util.Set;

public class UnionClass {
    /**
     * union 메서드는 인수 타입과 반환 타입 3개가 모두 같아야 한다.
     * 이를 한정적 와일드카드 타입으로 더 유연하게 할 수 있다.
     */
    public static <E> Set<E> union(Set<E> s1, Set<E> s2) {
        Set<E> result = new HashSet<>(s1);
        result.addAll(s2);
        return result;
    }

    public static void main(String[] args) {
        Set<String> guys = Set.of("토마스", "타이");
        Set<String> ladies = Set.of("라이언", "레피", "레미");
        Set<String> tomasTeam = union(guys, ladies);
        System.out.println(tomasTeam);
    }
}
