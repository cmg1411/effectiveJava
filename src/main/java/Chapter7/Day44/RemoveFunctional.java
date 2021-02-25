package Chapter7.Day44;

import java.util.Map;

@FunctionalInterface
public interface RemoveFunctional<K, V> {
    boolean remove(Map<K, V> map, Map.Entry<K, V> eldest);
}
