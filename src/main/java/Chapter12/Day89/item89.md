# 인스턴스 수를 통제해야 한다면 readResolve 보다는 열겨 타입을 사용하라.

## writeObject, readObject
 - ObjectInputStream, ObjectOutputStream 의 메서드.
 - 기본 자바 직렬화 과정에서 객체를 쓰고 읽는 역할.
 - 기본 자바 직렬화 과정 이외에 별도의 처리가 필요하다면, Serailizable 을 구현한 클래스에 선언하여 정의하면 된다.
 - 선언해두면 자동으로 호출되는 메서드이다.
    - private 메서드여야함(재정의 금) : 아니면 자동호출되지 않음 
    - defaultWriteObject, defaultReadObject 메서드를 제일 먼저 호출한 뒤 처리할 처리를 해야함.
        - 이 두 메서드는 transient, static 을 빼고 기본 직렬화를 해준다.
        - 이후 적절히 transient 필드에 대한 초기화를 하거나 해준다.
        

## 그런데 readObject 로 읽어온 객체는 싱글톤이 아니다.
 - 싱글톤 클래스에 Serializable 을 추가하면, 싱글톤이 아니게 된다.
 ```java
public final class MySingleton implements Serializable { // Serializable
    private static final MySingleton INSTANCE = new MySingleton();

    private MySingleton() {
    }

    public static MySingleton getINSTANCE() {
        return INSTANCE;
    }
}
```

```java
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
```

## readResolve 를 선언해주면 싱글톤을 얻을 수 있다.
 - 역직렬화 이후 새로 생성된 객체를 인수로 readResolve 메서드가 자동으로 실행된다.
 - 이 메서드가 반환한 객체 참조가 새로 생성된 객체를 대신해 반환된다.
 - 새로 생성된 객체는 참조를 유지하지 않으므로 GC 대상이 되어 사라진다.
 - 이렇게 싱글톤을 성립시킬 수 있다.
 
## readResolve 를 인스턴스 통제 목적으로 사용한다면, 객체 참조 타입 인스턴스 필드는 모두 transient 로 선언해야한다.
 - readResolve 메서드가 실행되기 이전, 역직렬화된 객체의 참조를 공격할 여지가 남는다.
    - 싱글턴의 transient 가 아닌 필드들은, readResolve 메서드가 실행되기 이전에 역직렬화된다.
    - transient 필드가 있음으로서 readResolve 와 역직렬화 사이의 시간을 이용한 공격이 되겠다.
    - 원래 싱글턴 객체로 바꿔서 반환하기 이전에, 역직렬화된 참조를 가져올 수 있다는 것이다.
    - 그렇게 훔쳐온 객체를 사용하게 되면 싱글톤이 깨진다는 것이다.
 - 모든 필드를 transient 로 선언해야 하지만, 더 나은 방법을 아래에 소개한다. 
    
## 싱글톤으로 사용해야하는 클래스를 원소 하나짜리 enum 으로 만들자.
```java
public enum Elvis {
    INSTANCE:

    private String[] favoriteSongs = {"", ""};
    
    public void printFavorites() {
        System.out.println(Arrays.toString(favoriteSongs));
    }
}
```
 - 자바가 대신 싱글톤임을 보장하게 해준다.



##### 참고
[writeObject, readObject](https://madplay.github.io/post/what-is-readobject-method-and-writeobject-method)
[item89](https://madplay.github.io/post/for-instance-control-prefer-enum-types-to-readresolve)
[readResolve](https://madplay.github.io/post/what-is-readresolve-method-and-writereplace-method)