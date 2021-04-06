package Chapter12.Day89;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationTester {

    public byte[] serialize(Object instance) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (bos; ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(instance);
        } catch (Exception e) {
            // ... 구현 생략
        }
        return bos.toByteArray();
    }

    public Object deserialize(byte[] serializedData) {
        ByteArrayInputStream bis = new ByteArrayInputStream(serializedData);
        try (bis; ObjectInputStream ois = new ObjectInputStream(bis)) {
            return ois.readObject();
        } catch (Exception e) {
            // ... 구현 생략
        }
        return null;
    }

    public static void main(String[] args) {
        MySingleton instance = MySingleton.getINSTANCE();
        SerializationTester serializationTester = new SerializationTester();
        byte[] serializedData = serializationTester.serialize(instance);
        MySingleton result = (MySingleton)serializationTester.deserialize(serializedData);
        System.out.println(instance);
        System.out.println(result);
        System.out.println("instance == result : " + (instance == result));
        System.out.println("instance.equals(result) : " + (instance.equals(result)));
    }
}
