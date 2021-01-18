package Day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    /**
     * toString 메서드는 오버라딩 하지 않으면
     * 객체@해시코드값(16진수) 가 나온다.
     *
     * 이 정보 또한 정보지만, 훨씬 더 유익한 정보를 알고싶다!
     *
     * 모든 하위 클래스에서 toString 을 재정의 해야한다.
     *
     * toString 은
     * println, 문자열 연결 +, assert, 디버거가 객체를 출력할때
     * 자동으로 불린다.
     *
     * 우리가 직접 쓰지 않더라도 어디선가는 쓰이기 때문에 재정의 하는 것이 좋다.
     *
     * toString 은 모든 정보를 제공하는 것이 좋다.
     * 정보가 방대하다면, 전체 정보를 유추할 수 있는 유의미한 요약 데이터를 제공한다.
     */
    public static void main(String[] args) {
        // toString 재정의 안함
        System.out.println(new Car("sonata", 5).toString());

        // toString 재정의
        System.out.println(new PhoneNumber("010", "1111", "1111"));

        Map<Integer, PhoneNumber> m = new HashMap<>();
        m.put(1, new PhoneNumber("010", "2222", "2222"));
        m.put(2, new PhoneNumber("010", "3333", "3333"));
        m.put(3, new PhoneNumber("010", "4569", "2804"));

        for (Integer key : m.keySet()) {
            System.out.println(m.get(key));
        }
    }
}

