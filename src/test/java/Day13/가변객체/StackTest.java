package Day13.가변객체;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StackTest {
    Stack s = null;
    Stack clone = null;

    @Before
    public void before() {
        s = new Stack();
        s.push(1);
        s.push(2);

        clone = s.clone();
    }

    @Test
    public void 다른_객체인가() {
        assertNotEquals(s, clone);
    }

    @Test
    public void 참조_필드는_값는_같지만_객체는_다른가() {
        Object[] stackElement = s.getElements();
        Object[] cloneElement = clone.getElements();

        // 들어있는 값이 같은가
        assertArrayEquals(stackElement, cloneElement);
        // 두 배열이 다른 객체인가
        assertNotEquals(stackElement.hashCode(), cloneElement.hashCode());
    }
}