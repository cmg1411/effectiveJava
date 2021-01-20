package Day14;

import java.util.Comparator;

public class ComparatorEx implements Comparable<ComparatorEx> {
    private int firstPhone;
    private int secondPhone;
    private int thirdPhone;

    public ComparatorEx(int firstPhone, int secondPhone, int thirdPhone) {
        this.firstPhone = firstPhone;
        this.secondPhone = secondPhone;
        this.thirdPhone = thirdPhone;
    }

    // 함수형 인터페이스 상수 구현
    private static final Comparator<ComparatorEx> COMPARATOR =
        Comparator.comparingInt((ComparatorEx cp) -> cp.firstPhone) // 타입 추론이 완벽하지 않아서 cp 의 자료형 알려줘야
        .thenComparing(cp -> cp.secondPhone)
        .thenComparing(cp -> cp.thirdPhone);

    @Override
    public int compareTo(ComparatorEx o) {
        return COMPARATOR.compare(this, o);
    }
}
