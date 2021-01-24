package Day18;

import java.util.*;

/**
 * 컴포지션 : 새 클래스의 구성요소로 기존의 클래스가 쓰임
 *
 * 전달(forwarding) : 새 클래스의 인스턴스 메서드들은 기존 클래스의 대응하는 메서드를 호출해 그 결과를 반환한다.
 *
 * 래퍼 클래스 : InstrumentSet. 다른 인스턴스를 감싸고 있다. (데코레이션 패턴)
 *
 * 전달 클래스 : ForwardingSet
 */
public class InstrumentedSet<E> extends ForwardingSet<E> {

    private int addCount = 0;

    public InstrumentedSet(Set<E> s) {
        super(s);
    }

    @Override public boolean add(E e) {
        addCount++;
        return super.add(e);
    }
    @Override public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }
    public int getAddCount() {
        return addCount;
    }

    public static void main(String[] args) {
        InstrumentedSet<String> s = new InstrumentedSet<>(new HashSet<>());
        s.addAll(List.of("틱", "탁탁", "펑"));
        System.out.println(s.getAddCount());
    }
}
