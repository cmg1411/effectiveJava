package Chapter2.Day7;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class CacheLeak {

    /**
     * 강한 참조 (Strong Reference)
     * – Integer prime = 1;   와 같은 가장 일반적인 참조 유형이다.
     * prime 변수 는 값이 1 인 Integer 객체에 대한 강한 참조 를가진다.
     * 이 객체를 가리키는 강한 참조가 있는 객체는 GC 대상이 되지않는다.
     *
     * 부드러운 참조 (Soft Reference)
     * – SoftReference<Integer> soft = new SoftReference<Integer>(prime);   와 같이 SoftReference Class 를 이용하여 생성이 가능하다.
     * 만약 prime == null 상태가 되어 더이상 원본(최초 생성 시점에 이용 대상이 되었던 Strong Reference) 은 없고 대상을 참조하는 객체가 SoftReference만 존재할 경우 GC대상으로 들어가도록 JVM은 동작한다.
     * 다만 WeakReference 와의 차이점은 메모리가 부족하지 않으면 굳이 GC 하지 않는 점이다.  때문에 조금은 엄격하지 않은 Cache Library 들에서 널리 사용되는 것으로 알려져있다.
     *
     * 약한 참조 (Weak Reference)
     * – WeakReference<Integer> soft = new WeakReference<Integer>(prime);   와 같이 WeakReference Class를 이용하여 생성이 가능하다.
     * prime == null 되면 (해당 객체를 가리키는 참조가 WeakReference 뿐일 경우) GC 대상이 된다.  앞서 이야기 한 내용과 같이 SoftReference와 차이점은 메모리가 부족하지 않더라도 GC 대상이 된다는 것이다.
     * 다음 GC가 발생하는 시점에 무조건 없어진다.
     */

    /**
     * 캐시는 보통 Map 으로 구현한다.
     *
     * 캐시는 임시 데이터인 만큼, 쓸모가 없어지면 삭제되어야 한다.
     *
     * 하지만 HashMap() 으로 구현하게 되면, Map 의 캐시값이 사용되지 않더라도 Map 이라는 컬렉션이 강하게 참조하고 있기 때문에, 삭제되지 않아 메모리를 차지한다.
     *
     * WeakHashMap() 은 캐시의 키값을 강한 참조 위에 WeakReference 로 감싸는 형태이기 때문에, 해당 캐시가 사용하지 않게 되면 GC의 대상이 되어 메모리가 해제된다.
     */

    /**
     * 콜백이 뭔지 잘 모르지만 콜백 또한 캐시와 같이 WeakHashMap() 을 사용해야 한다고 한다.
     */

    public static void main(String[] args) {
        Map<Integer, String> map1 = new WeakHashMap<>();
        Map<Integer, String> map2 = new HashMap<>();

        Integer key1 = 1000;
        Integer key2 = 2000;
        Integer key3 = 1000;
        Integer key4 = 2000;

        map1.put(key1, "test a");
        map1.put(key2, "test b");
        map2.put(key3, "test a");
        map2.put(key4, "test b");

        key1 = null; // 약한 참조
        key3 = null; // 강한 참조

        System.gc();  //강제 Garbage Collection

        map1.entrySet().stream().forEach(el -> System.out.println(el));
        System.out.println();
        map2.entrySet().stream().forEach(el -> System.out.println(el));
    }
}
