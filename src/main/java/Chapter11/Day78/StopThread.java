package Chapter11.Day78;

import java.util.concurrent.TimeUnit;

public class StopThread {

    private static boolean stopRequested;
//    private static volatile boolean stopRequested; // volatile 을 사용하면 정상동작한다.

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
           int i = 0;
           while (!stopRequested) {
               i++;
           }
        });

        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }
}
