package Chapter8.Day59;

import java.util.Random;

public class RandomEx {

    public static void main(String[] args) {
        Random random = new Random();
        int random1 = random.nextInt();


        int n = 2 * (Integer.MAX_VALUE / 3);
        int low1 = 0;
        int low2 = 0;

        for (int i = 0; i < 100000000; i++) {
            if (random.nextInt() % n < (n/2))
                low1++;
        }

        for (int i = 0; i < 100000000; i++) {
            if (random.nextInt(n) < (n/2))
                low2++;
        }

        System.out.println("nextInt() 1억번 중 절반 이하 : " + low1);
        System.out.println("nextInt(int bound) 1억번 중 절반 이하 : " + low2);
    }
}
