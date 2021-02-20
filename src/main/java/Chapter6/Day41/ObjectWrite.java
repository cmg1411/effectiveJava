package Chapter6.Day41;

import java.io.*;

public class ObjectWrite {
    private ObjectWrite() {
    }

    public static void newWriteObject(Serializable object, String path) {
        File file = new File(path);

        try (ObjectOutputStream oops = new ObjectOutputStream(new FileOutputStream(file))) {
            oops.writeObject(object);
        } catch (IOException e) {
            System.out.println("런타임에 에러 발생");
        }
    }
}
