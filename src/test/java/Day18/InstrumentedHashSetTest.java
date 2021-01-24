package Day18;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class InstrumentedHashSetTest {
    private InstrumentedHashSet<String> s;

    @BeforeEach
    public void 초기화() {
        s = new InstrumentedHashSet<>();
    }

    @Test
    public void 오류_테스트() {
        s.addAll(List.of("hi", "hello","goodBye"));
        assertThat(s.getAddCount()).isEqualTo(3);
    }
    /**
     * addAll() 가 add() 를 사용하여 구현하였기 떄문에
     * 예상과 달리  6이 나온다.
     */
}