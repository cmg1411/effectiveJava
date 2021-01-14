package Day8;

/**
 * Finalizer 은 구체적으로 다음과 같은 이유로 자바 9부터 deprecated 된 메소드이다.
 *
 * 1. Finalizer 와 Cleaner는 언제 실행될지 모른다. 따라서 타이밍이 중요한 작업은 이를 사용하면 안된다.
 *
 * 2. 특히 Finalizer 이 실행되는 스레드는 우선순위가 낮아 자원 반납을 지연시킬 수 도 있다.
 * (Cleaner 은 별도의 스레드를 사용하기에 낫다.)
 * 스레드 내부에 어떤 작업이 있고, 그 일이 너무 커서 처리를 못하고 대기하고 있다면, GC 가 실행되지 않을 것이고 계속 쌓이게 된다.
 * 따라서 OutOfMemoryException 이 발생할 수 도 있다.
 *
 * 3. 아예 실행되지 않을 수 도 있다.
 *
 * 4. 성능적으로도 try-with-resource 를 사용하는 것보다 5~10배는 느리다.
 *
 * 5. Finalizer 보안 이슈도 있다.
 */

/**
 * Finalizer 은 어디에 쓸까.
 * try-with-resource 방식으로 close() 하는 것은 클라이언트의 몫이다.
 * 하지만 이를 안했다고 가정하고 확실히 자원을 반납하기 위해 안전망이 필요할 떄 사용한다.
 */
public class Clean implements AutoCloseable {

    private boolean closed;

    // cleaner 는 Java9 부터 나왔다 ..
//    private static final Cleaner CLEANER = Cleaner.create();
//    private final Cleaner.Cleanable cleanable;
//    private final Clean clean;

//    public Clean() {
//        this.clean = new Clean();
//        this.cleanable = CLEANER.register(this, clean);
//    }

    private static class ResourceCleaner implements Runnable {

        @Override
        public void run() {
            System.out.println("clean 을 하는 작업을 여기 정의");
        }
    }

    @Override
    public void close() throws Exception {
        if (this.closed) {
            throw new IllegalStateException();
        }
        closed = true;
        // cleanble.clean();
    }

    public void hello() {
        System.out.println("hello");
    }
}
