package Chapter7.Day43;

import java.util.HashMap;
import java.util.Map;

public class MethodReference {

    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        map.merge(1, 10, (a, b) -> a+b);
        map.merge(1, 10, Integer::sum);
        System.out.println(map);
    }
}