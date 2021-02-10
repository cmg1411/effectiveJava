package Chapter5.Day29;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {
    @DisplayName("Object 스택은 문제가 있다!")
    @Test
    public void StackTest() {
        Stack stack = new Stack();

        stack.push(1);
        stack.push("1");

        assertEquals(stack.pop().getClass(), String.class);
        assertEquals(stack.pop().getClass(), String.class); // Integer !!
    }
}