package Chapter7.Day46;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class Collectors {

    public static void main(String[] args) {
        List<String> words = new ArrayList<>();

        Map<String, Long> freq = words
            .stream().collect(groupingBy(String::toLowerCase, counting()));
    }
}
