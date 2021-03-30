package Chapter11.Day81;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchEx {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        try {
            long result = time(executorService, 3, () -> System.out.println("hello"));
            System.out.println("총 걸린 시간 : " + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
}

    public static long time(Executor executor, int concurrency, Runnable action) throws InterruptedException {
        CountDownLatch ready = new CountDownLatch(concurrency);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(concurrency);
        for (int i = 0; i < concurrency; i++) {
            executor.execute(() -> {
                ready.countDown(); // 타이머에게 준비가 됐음을 알린다.
                try {
                    // 모든 작업자 스레드가 준비될 때까지 기다린다.
                    start.await();
                    action.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    // 타이머에게 작업을 마쳤음을 알린다.
                    done.countDown();
                }
            });
        }
        ready.await(); // 모든 작업자가 준비될 때까지 기다린다.
        long startNanos = System.nanoTime();
        start.countDown(); // 작업자들을 깨운다.
        done.await(); // 모든 작업자가 일을 끝마치기를 기다린다.
        return System.nanoTime() - startNanos;
    }
}