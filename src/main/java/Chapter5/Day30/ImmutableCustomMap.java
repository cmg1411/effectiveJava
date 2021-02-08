package Chapter5.Day30;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ImmutableCustomMap<K, V> {
    private static final ImmutableCustomMap<Object, Object> SINGLETON_MAP = new ImmutableCustomMap<>(); // 싱글턴 객체
    private final Map map = new HashMap<>();

    private ImmutableCustomMap() {
    }

    @SuppressWarnings("unchecked")
    public static <K, V> ImmutableCustomMap<K, V> getImmutableMap() {
        return  (ImmutableCustomMap<K, V>) SINGLETON_MAP;
    }

    public static void main(String[] args) {
        ImmutableCustomMap<String, Integer> map1 = ImmutableCustomMap.getImmutableMap();
        ImmutableCustomMap<Integer, String> map2 = ImmutableCustomMap.getImmutableMap();
    }
}
