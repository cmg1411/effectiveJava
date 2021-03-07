package Chapter7.Day47;


import java.util.List;
import java.util.stream.Stream;

public class IterableSequence {

    public static void main(String[] args) {
        for (Integer integer : List.of(1, 2, 3)) {
            System.out.println(integer);
        }
    }
}
