package Chapter2.Day6;

public class AutoBoxing {
    public static void main(String[] args) {
        long before = System.currentTimeMillis();

        Long sum = 0l;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        System.out.println("autoboxing : " + (System.currentTimeMillis() - before));


        /**
         * 오토박싱 또한 불필요한 객체를 생성하는 예이다.
         * 개발자의 실수로 오토박싱이 일어나면,
         * 위와 아래의 차이 같이 10배의 속도가 차이나게 된다.
         */

        before = System.currentTimeMillis();

        long sum2 = 0;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum2 += i;
        }
        System.out.println("non-autoboxing : " + (System.currentTimeMillis() - before));
    }
}
