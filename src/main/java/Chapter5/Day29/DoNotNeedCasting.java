package Chapter5.Day29;

import java.util.List;

public class DoNotNeedCasting {
    public static void main(String[] args) {
        GenericStack<String> stack = new GenericStack<>();

        for (String s : List.of("hi", "hello", "bye")) {
            stack.push(s);
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop().toUpperCase());
        }
    }
}
