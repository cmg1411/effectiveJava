package Day8;

/**
 * AutoClosable 인터페이스를 구현하면
 * close() 를 오버라이딩하여 사용할 수 있다.
 */
public class SampleResource implements AutoCloseable {

    @Override
    public void close() throws Exception {
        System.out.println("자원 반납");
    }

    public void hello() {
        System.out.println("hi");
    }
}
