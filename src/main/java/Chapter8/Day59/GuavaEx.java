package Chapter8.Day59;

import com.google.common.collect.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


public class GuavaEx {



    public static void main(String[] args) {
        // JDK
        Map<Integer, String> map1 = new HashMap<>();
        // GUAVA
        Map<Integer, String> map2 = Maps.newHashMap();
        Map<Integer, String> map3 = Maps.newHashMapWithExpectedSize(3);
        map3.put(1, "aa");
        map3.put(1, "b");
        map3.put(1, "aa");
        map3.put(1, "b");

        map1.put(1, "aa");
        map1.put(1, "b");
        System.out.println(map1.get(1));

        Multimap<Integer, String> multimap = HashMultimap.create();
        multimap.put(1, "A");
        multimap.put(1, "B");
        System.out.println(multimap.get(1));

        BiMap<Integer, Integer> biMap = HashBiMap.create();
        biMap.put(1, 2);
        //biMap.put(3, 2);
    }
}
