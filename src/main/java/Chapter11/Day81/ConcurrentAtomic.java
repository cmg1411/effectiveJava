package Chapter11.Day81;

import com.google.common.cache.Cache;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static java.util.Objects.*;

public class ConcurrentAtomic {

    private static final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

    private static String intern(String s) {
        String result = map.get(s);
        if (isNull(result)) {
            result = map.putIfAbsent(s, s);
            if (isNull(result)) {
                result = s;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        intern("hi");
        intern("hi");

        for (String s : map.keySet()) {
            System.out.println(map.get(s));
        }
    }
}
