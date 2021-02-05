# 다른 타입이 적절하다면 문자열 사용을 피하라
 - String 은 의도한 용도로만 사용하라 !
 
 
## 문자열은 다른 값 타입을 대신하기에 적합하지 않다.
 - 수치형 : int, float, BigInteger 등 타입을 사용하자.
 - 예 / 아니오 : 적절한 enum 이나 boolean 타입을 사용하자.
 
 ==> 기본타입이든 참조타입이든 적절한 것이 있으면 그것을 사용하고, 적절하지 않다면 **새로운 타입**을 정의해라.
 
## 문자열은 열거 타입을 대신하기에 적합하지 않다.
 - 상수를 열거할 때는 문자열보다는 열거타입이 훨 씬 낫 다.
 
## 문자열은 혼합 타입을 대신하기에 적합하지 않다.
 ```java
String compoundKey = className + "#" + i.next();
```
 - 위와 같은 코드는 적합하지 않다.
    - 혹시라도 className, i.next() 같이 옆의 변수에서 # 을 사용했다면 혼란을 준다.
    - 각 문자열을 개별로 접근하려면, 문자열을 파싱해야 해서 느리고 귀찮고 오류가능성도 커진다.
    - 적절한 equals, toString, compareTo 메서드를 제공할 수 없다. String 이 제공하는 기능에만 의존해야 한다.
 
==> 전용 멤버 클래스 하나 새로 만들라.  
==> 보통 이런 용도의 전용 멤버 클래스는 private 정적 멤버 클래스로 만든다. (item24)

## 문자열은 권한을 표현하기에 좋지 않다.
 - 권한을 문자열로 표현하는 경우가 종종 있다.
 - 예를 들어, 스레드마다 자신의 지역변수를 가지게 해 보자.
 ```java
public class ThreadLocalEx {
    private ThreadLocalEx() {}
    
    // 현 스레드의 값을 키로 구분해 저장한다.
    public static void set(String key, Object value) {};

    // 키가 가리키는 현 스레드의 값을 반환한다.
    public static Object get(String key) {
        return null;
    };
}
```
 - 위 방식의 문제
    - static 으로, 문자열 구분용 키가 전역 이름 공간에서 공유된다.
    - 만약 두 클라이언트가 소통하지 못해 같은 키를 사요하게 된다면, 의도치않게 같은 변수를 공유하게 된다.
    - 보안도 취약하다.
    
## 문자열 대신 위조할 수 없는 키를 사용하면 된다. 이 키를 권한이라고도 한다.
```java
public class ThreadLocalEx {
    private ThreadLocalEx() {}
    
    public static class Key {
        Key() {
        }
    }

    public static void set(Key key, Object value) {
    }
    
    public static Key get(Key key) {
        return new Key();
    }
    
    // 위조가 불가능한 고유 키를 생성한다.
    public static Key getKey() {
        return new Key();
    } 
}
```

 - 사실 위의 방법도 개선의 여지가 있다.
    - set, get 은 정적 메서드일 필요가 없다. Key 클래스의 인스턴스 메서드로 바꾼다.
    - 그렇게 하면 Key 는 더 이상 스레드 지역변수를 구분하기 위한 키가 아니라, 그 자체가 스레드 지역변수가 된다.
    - 그러면 굳이 클래스를 중첩으로 쓸 피룡도 없다.
    
    
```java
public final class ThreadLocalEx {
    private ThreadLocalEx() {}

    public void set(Object value) { }
    
    public Object get() { } 
}
```


 - 마지막으로 형변환 하지 않아도 되게, 타입 안전성을 확보한다.
    - 제네릭을 쓰자
    
 ```java
 public final class ThreadLocalEx<T> {
     private ThreadLocalEx() {}
 
     public void set(T value) { }
     
     public T get() { } 
 }
 ```

 - 위 클래스는 자바에서 제공하는 ThreadLocal 클래스와 비슷한 형태로 만들어졌다 !
 
 
## 결론 
 - 문자열을 잘못 사용한 흔한 예로 기본타입, 열거타입, 혼합타입이 있다.
 - 적절한 타입이 있다면 적절한 타입으로 찾아 쓰자 !