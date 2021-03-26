package Chapter11.Day78;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 모르겠읍니다.
public class Exce {

    static int A = 0;
    static int B = 0;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(() -> {
            int r2 = A;
            System.out.println(r2);
            B = 1;
        });

        executor.submit(() -> {
            int r1 = B;
            System.out.println(r1);
            A = 2;
        });



        executor.shutdown();
    }
}
