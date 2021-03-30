package Chapter11.Day81;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PutIfAb {

    private static Map<String, String> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        map.put("hi", "hello");

//        String result = map.putIfAbsent("hi", "hello");
//        String result = map.putIfAbsent("good", "bye");
        String result = map.computeIfAbsent("Auth", str -> str + "(by mingeor)");
        System.out.println(result);
        System.out.println(map.get("Auth"));
    }
}
