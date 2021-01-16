package Day9;


import java.io.*;

/**
 * InputStream, OutputStream, jdbc connection 등
 * 자바에는 close() 롤 호출하여 자원을 닫아줘야하는 것이 많다.
 *
 * 명시적으로 닫지 않으면 문제가 생길 수 있다.
 */
public class TryFinally {
    private static final int BUFFER_SIZE = 10;

    public static void main(String[] args) throws Exception {
        tryFinallyBad();
    }

    /**
     * try - catch 가 중첩되면 너무 지저분하고 복잡하다 !
     * */
    static void firstLineOfFile(String src, String dst) throws IOException {
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);
            try {
                byte[] buf = new byte[BUFFER_SIZE];
                int n;
                while ((n = in.read(buf)) >= 0) {
                    out.write(buf, 0, n);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }

    /**
     * run(), close() 둘 다 예외를 던지게 해놨다.
     * 하지만 실행해보면 알 수 있듯이,
     *
     * 첫번쨰 예외가 두번째 예외에 의해 잡아 먹힌다.
     * 첫번째 예외는 추적할 수 없으며, 디버깅시 매우 불편하다.
     *
     * try-with-resource 방식을 쓰면 위 두가지 문제점을 모두 잡을 수 있다.
     */
    static void tryFinallyBad() throws Exception {
        TryCatchProblem tryCatchProblem = null;

        try {
            tryCatchProblem = new TryCatchProblem();
            tryCatchProblem.run();
        } finally {
            tryCatchProblem.close();
        }
    }
}
