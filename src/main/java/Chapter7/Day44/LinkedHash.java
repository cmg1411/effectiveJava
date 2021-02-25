package Chapter7.Day44;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHash<K, V> extends LinkedHashMap<K, V> {

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > 100;
    }
}
