package Chapter6.Day41;

import java.io.*;

public class SerializableTest {

    static class MeetCoder implements Serializable {
        private final String name = "MeetCoder";
        private final int Number;

        public MeetCoder(int number) {
            Number = number;
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String path = "test.txt";

        ObjectWrite.newWriteObject(new MeetCoder(10), path);
    }
}
