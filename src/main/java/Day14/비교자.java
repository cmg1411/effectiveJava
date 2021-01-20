package Day14;

import java.util.Comparator;

public class 비교자 {
    static final Comparator<Object> hashCodeOrder = new Comparator<Object>() {
        @Override
        public int compare(Object o1, Object o2) {
            return Integer.compare(o1.hashCode(), o2.hashCode());
        }
    };

    // 키 추출자를 받아 키의 자연적인 순서를 이용하여 정렬하는 비교자 생성
    static final Comparator<Object> hashCodeOrder2 = Comparator.comparingInt(w -> w.hashCode());
}
