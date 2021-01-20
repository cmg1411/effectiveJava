package Day8;

import java.io.BufferedReader;

public class FinalizerExample {

    /**
     * Gc 가 실행될 떄 실행된다.
     * 하지만 GC 의 대상이 된다고 무조건 GC 가 이루어지는 것이 아니기에,
     * 언제 Finalizer 가 실행될지 알 수 없다.
     */
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalize");
    }

    public void hello() {
        System.out.println("hi");
    }
}
