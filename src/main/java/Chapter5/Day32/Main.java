package Chapter5.Day32;

import java.util.Collections;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        /**
         * 가변인수 배열을 String[] 이 아닌 Object[] 로 만든다.
         *
         * 왜냐하면, pickTwo 는 T 를 썼기 때문에, 어떤 타입의 객체로든 넘길 수 있어야 하는데,
         * Object[] 가 모든 타입을 포함하는 가장 구체적인 타입이기 때문이다.
         *
         * 따라서 Object[] 를 반환한다.
         */
        Integer[] s = ToArray.pickTwo(1, 2, 3);
    }
}
