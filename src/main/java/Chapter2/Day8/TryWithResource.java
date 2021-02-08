package Chapter2.Day8;

public class TryWithResource {
    public static void main(String[] args) throws Exception {

        /**
         * 기존의 AutoClosable 을 구현하여
         * close() 를 통해 자원을 반납.
         */
        SampleResource sampleResource = null;
        try {
            sampleResource = new SampleResource();
            sampleResource.hello();
        } finally {
            sampleResource.close();
        }

        /**
         * AutoClosable 인터페이스와
         * try-with-resource 를 이용하여 암묵적인 close() 호출
         * 가장 이상적인 반납법
         */
        try(SampleResource sampleResource1 = new SampleResource()) {
            sampleResource1.hello();
        }
    }
}
