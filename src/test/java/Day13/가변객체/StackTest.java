package Day13.가변객체;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StackTest {
    Stack s = null;
    Stack clone = null;

    @BeforeEach
    public void before() {
        s = new Stack();
        s.push(1);
        s.push(2);

        clone = s.clone();
    }

    @Test
    public void 다른_객체인가() {
        assertThat(s)
            .isNotEqualTo(clone);
    }

    @Test
    public void 참조_필드는_값는_같지만_객체는_다른가() {
        Object[] stackElement = s.getElements();
        Object[] cloneElement = clone.getElements();

        // 들어있는 값이 같은가
        assertThat(stackElement).isEqualTo(cloneElement);
        // 두 배열이 다른 객체인가
        assertThat(stackElement.hashCode())
            .isNotEqualTo(cloneElement.hashCode());
    }
}