package Chapter4.Day18;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        InstrumentedHashSet<String> s = new InstrumentedHashSet<>();
        s.addAll(List.of("hi", "hello"));

        System.out.println(s.getAddCount());
    }
}
