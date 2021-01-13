package Day6;

public class Application {
    public static void main(String[] args) {

        // String 은 불변객체지만, new 로 생성하면 매번 새로운 객체가 나온다.
        // 따라서 불필요한 객체가 생성된다.
        String str = new String("hello");
        String str2 = new String("hello");

        // false
        System.out.println(str == str2);


        /**
         *     public static Integer valueOf(int i) {
         *         if (i >= IntegerCache.low && i <= IntegerCache.high)
         *             return IntegerCache.cache[i + (-IntegerCache.low)];
         *         return new Integer(i);
         *     }
         *
         *     Integer 의 valueOf() 는 IntegerCache() 를 사용한다.
         *     Integer integer = 1; 을 하면 자동으로 valueOf 로 가져온다.
         *
         *     IntegerCache() 의 구현부를 보면 -128 ~ 127 의 숫자는 캐시하여 사용한다.
         */
        Integer one = 1;
        Integer one2 = 1;

        System.out.println(one == one2);

        long beforeTime = System.currentTimeMillis();
        System.out.println(beforeTime);
        Integer[] intArr = new Integer[30000000];
        // Caching
        for (int i = 0; i < 20000000; i++) {
            intArr[i] = 1;
        }
        System.out.println("캐싱 소요시간(1) : " + (System.currentTimeMillis() - beforeTime));

        beforeTime = System.currentTimeMillis();
        for (int i = 0; i < 20000000; i++) {
            intArr[i] = 127;
        }
        System.out.println("캐싱 소요시간(127) : " + (System.currentTimeMillis() - beforeTime));

        beforeTime = System.currentTimeMillis();
        for (int i = 0; i < 20000000; i++) {
            intArr[i] = 128;
        }
        System.out.println("캐싱안한 소요시간(128) : " + (System.currentTimeMillis() - beforeTime));
    }
}
