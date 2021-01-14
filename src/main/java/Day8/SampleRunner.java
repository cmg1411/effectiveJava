package Day8;

public class SampleRunner {

    /**
     * GC 가 메모리를 해제해주긴 하지만, 언제 호출될지 모르기에,
     * 사용이 끝나고 Finalizer 을 명시적으로 호출할 수 가 있다. 하지만 이는 좋지 않다.
     *
     * Finalizer 조차 언제 호출될지 모르기 때문이다.
     *
     * 따라서 자원 반납에서는 try-with-resource 를 사용하거나
     */
    public static void main(String[] args) throws InterruptedException {
        SampleRunner runner = new SampleRunner();
        runner.run();

        // GC 는 언제 실행될지 모르니까 System.gc() 에 의존하면 안
        System.gc();
//        Thread.sleep(1000l); // 이거 해도 Finalizer 호출 안됬음
    }

    private void run() {
        FinalizerExample finalizerExample = new FinalizerExample();
        finalizerExample.hello();
    }
}
