package Day9;

import java.io.*;

public class TryWithResource {
    private static final int BUFFER_SIZE = 10;

    public static void main(String[] args) throws Exception {
        tryFinallyBad();
    }

    /**
     * 코드량이 매우 줄었다 !
     * */
    static void firstLineOfFile(String src, String dst) throws IOException {
        try (InputStream in = new FileInputStream(src)) {
            try (OutputStream out = new FileOutputStream(dst)) {
                byte[] buf = new byte[BUFFER_SIZE];
                int n;
                while ((n = in.read(buf)) >= 0) {
                    out.write(buf, 0, n);
                }
            }
        }
    }

    /**
     * 위와 같은 코드.
     * try-with-resource 를 쓰면,
     * close() 를 해야하는 객체를 한 try 문 안에 ; 로 연결하여 쓸 수 있다 !
     */
    static void firstLineOfFile2(String src, String dst) throws IOException {
        try (InputStream in = new FileInputStream(src) ; OutputStream out = new FileOutputStream(dst)) {
            byte[] buf = new byte[BUFFER_SIZE];
            int n;
            while ((n = in.read(buf)) >= 0) {
                out.write(buf, 0, n);
            }
        }
    }

    /**
     * 코드량이 줄음과 동시에 두 예외가 모두 잡힌다 !
     *
     * 처음 잡힌 firstException 이 먼저 잡힌다 ! 이부분도 좋다 !
     *
     * 뒤에 잡힌 secondException 은 suppressed 로 보여진다 !
     */
    static void tryFinallyBad() throws Exception {

        try (TryCatchProblem tryCatchProblem = new TryCatchProblem()) {
            tryCatchProblem.run();
        }
    }
}
