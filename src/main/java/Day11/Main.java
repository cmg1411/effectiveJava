package Day11;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<PhoneNumber, String> m = new HashMap<>();
        m.put(new PhoneNumber(010,4569,2804), "Tomas");

        /**
         * hashMap 의 get 은 hashCode() 를 이용하여 해시버킷에서 값을 찾아온다.
         * 하지만 두 객 체는 물리적으로 다른 객체이고, 해시코드값이 다르기 때문에 null 이 반환된다.
         *
         * 따라서 hashCode() 를 오버라이딩 해주어야한다.
         */
        System.out.println(m.get(new PhoneNumber(010,4569,2804)));
    }
}
