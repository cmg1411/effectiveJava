package Chapter2.Day6;

import java.util.*;

public class Keyset {
    public static void main(String[] args) {
        Object key = new Object();
        Object value = new Object();

        Map<Object, Object> test = new HashMap<Object, Object>();

        test.put(key, value);

        Set<Object> keys = test.keySet();
        Set<Object> keys2 = test.keySet();

        System.out.println(keys.size());
        System.out.println(keys2.size());

        /**
         * keyset() 은 map 에 대한 key 들을 반환한다.
         * 하지만 여러번 메소드를 호출한다고 새로운 객체를 반환하는 것이 아닌,
         * 하나의 객체 참조 (싱글턴같이) 를 반환한다.
         *
         * 따라서 아래와 같이 한 객체만 remove 해줘도 keyset() 으로 만든 모든 객체가 영향을 받는다.
         */
        test.remove(key);

        System.out.println(keys.size());
        System.out.println(keys2.size());

        /**
         * 따라서 keyset() 로 생성하는 객체도 여러개는 불필요하며, 하나만 생성해야 한다.
         *
         * 또한 위험을 방지하기 위해
         * 어댑터 패턴, 객체 포장, 방어적 복사 를 이용할 수 있다.
         * 방어적 복사 : https://johngrib.github.io/wiki/defensive-copy.md/
         */
    }
}
