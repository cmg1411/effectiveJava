package Day9;

public class TryCatchProblem implements AutoCloseable {

    public void run() {
        System.out.println("Method run");
        throw new firstException();
    }

    @Override
    public void close() throws Exception {
        System.out.println("close() Method");
        throw new secondException();
    }
}
