package Chapter5.Day30;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ImmutableCustomMap<K, V> {
    private static final HashMap SINGLETON_MAP = new HashMap<>(); // 싱글턴 객체
    private final Map map = new HashMap<>();

    private ImmutableCustomMap() {
    }

    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> getImmutableMap() {
        return  SINGLETON_MAP;
    }

    public static void main(String[] args) {
        Map<String, Integer> map1 = ImmutableCustomMap.getImmutableMap();
        Map<Integer, String> map2 = ImmutableCustomMap.getImmutableMap();
    }
}
