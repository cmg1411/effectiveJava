package Chapter8.Day59;

import java.time.Instant;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadLocalRandomEx {

    private static final int BOUND = 2 * (Integer.MAX_VALUE / 3);
    private static final int REPEAT_NUM = 100000000;

    public static void main(String[] args) {
        Random random = new Random();
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

        for (int j = 0; j < 10; j++) {
            System.out.println("-----------" + (j+1) + "회차---------------");
            Instant now = Instant.now();

            for (int i = 0; i < REPEAT_NUM; i++) {
                makeRandomNumber(random);
            }
            System.out.println("일반 랜덤 1억번 시간 : " + (Instant.now().toEpochMilli() - now.toEpochMilli()));

            now = Instant.now();

            for (int i = 0; i < REPEAT_NUM; i++) {
                makeRandomNumber(threadLocalRandom);
            }
            System.out.println("ThreadLocalRandom 1억번 시간 : " + (Instant.now().toEpochMilli() - now.toEpochMilli()));
        }
    }

    private static int makeRandomNumber(Random random) {
        return random.nextInt(BOUND);
    }
}
