package Chapter11.Day78;

import java.util.concurrent.ExecutorService;

public class ReadWrite {
    int A = 0;
    int B = 0;

    Thread thread1 = new Thread(() ->{
        int r2 = A;
        B = 1;
    });

    Thread thread2 = new Thread(() ->{
        int r1 = B;
        A = 2;
    });

    public static void main(String[] args) {
        ReadWrite rw = new ReadWrite();
        rw.thread1.start();
        rw.thread2.start();
    }
}
