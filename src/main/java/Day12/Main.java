package Day12;

import java.util.HashMap;
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
     *
     * 포멧을 문서화할 것인가 아닌가를 결정해야 한다.
     * 전화번호나 행렬같은 값 객체들은 문서화하는것이 좋다.
     *
     * 문서화가 되면, 표준이 생기고 ㅁ여확하고, 사람이 읽을 수 있다.
     * 따라서 그 값 그대로 입출력에 사용하거나 데이터 객체로 저장할 수 있따.
     *
     * 명시한 포맷에 맞는 문자열과 객체를 상호 전환할 수 있는 정적 팩터리나 생성자를 함께 제공한다.
     *
     * 하지만 문서화는 개발자가 포멧에 얽매이게 한다.
     * toString 으로 반환되는 값을 따로 파싱하거나 하는 추가 비용이 발생할 수 있다.
     *
     * 문서화를 하든 안하든, 주석을 통해 오버라이딩한 toString() 이 어떻게 왜 이렇게 재구현하는지 설명해야한다.
     * 그리고 포멧 명시여부와 관계없이, toString 이 반환하는 값에 포함된 정보를 가져올 수 있는 API 를 제공하여야 한다.
     *
     * 그렇지 않으면 전화번호 클래스에서 첫 자리, 둘째 자리, 셋째 자리 값을 구하기 위해 파싱을 해야한다.
     *
     * 정적 유틸리티 클래스는 객체생성을 하지 않으므로, toString 을 제공하지 않아도 된다.
     * Enum 은 완벽한 toString 을 기본적으로 제공하니 재정의 하지 않아도 된다.
     *
     * 하지만 하위 클래스들이 공통으로 사용하는 인스턴스가 있는 추상 클래스는 재정의 해줘야 한다.
     * 하위 클래스들이 추상 클래스의 toString 을 상속하여 쓴다. (super)
     *
     * Lombok 이나 AutoValue 같은 툴들은 완벽히 구현해주지는 않지만, 없는거보다는 낫다.
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

