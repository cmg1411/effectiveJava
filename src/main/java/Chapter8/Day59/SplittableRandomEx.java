package Chapter8.Day59;

import java.util.OptionalDouble;
import java.util.SplittableRandom;
import java.util.concurrent.ThreadLocalRandom;

public class SplittableRandomEx {

    private static final Long REPEAT_NUM = 100000L;

    public static void main(String[] args) {
        timeWithThreadLocalRandomWithParallel();
        timeWithSplittableRandomWithParallel();
    }

    public static void timeWithThreadLocalRandomWithParallel() {
        long l = System.currentTimeMillis();
        OptionalDouble max = ThreadLocalRandom.current()
            .doubles()
            .parallel()
            .limit(REPEAT_NUM)
            .max();
        System.out.println("최댓값 : " + max.getAsDouble() + " / 걸린시간 : "  + (System.currentTimeMillis() - l));
    }

    public static void timeWithSplittableRandomWithParallel() {
        SplittableRandom splittableRandom = new SplittableRandom();
        long l = System.currentTimeMillis();
        OptionalDouble max = splittableRandom
            .doubles()
            .parallel()
            .limit(REPEAT_NUM)
            .max();
        System.out.println("최댓값 : " + max.getAsDouble() +  " / 걸린시간 : "  + (System.currentTimeMillis() - l));
    }
}
