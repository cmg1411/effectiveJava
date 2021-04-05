# 커스텀 직렬화 형태를 고려해보라

자바 기본 직렬화 형태를 사용하면, 다음 릴리즈때 버리려 한 현재 구현 (과거 구현) 묶여서 버릴 수 없다. 

## 기본 직렬화를 사용해도 괜찮을 떄
 - 우리가 직접 커스텀 직렬화 형태를 만들어도 기본 직렬화 형태와 같을 때 사용해도 된다.
    - 객체가 포함한 데이터, 그 객체에서 시작해 접근할 수 있는 모든 객체, 객체가 연결된 토폴로지 모두가 포함되어야한다.
    
 - 객체의 물리적인 표현과 논리적인 표현이 같다면 사용해도 괜찮다.
 ```java
 public class Name implements Serializable {
    /**
    * 성. null 아니어야함.
    * @Serial
    */
    private final String lastName;

    /**
    * 이름. null 아니어야함.
    * @Serial
    */
    private final String firstName;
    
    /**
    * 중간이름. null 아니어야함.
    * @Serial
    */
    private final String middleName;
}
 ```
    
- 이름 객체는 이름, 성, 중간이름의 논리적 물리적 형태가 같다.
- 따라서 기본 직렬화를 사용한다.
- 기본 직렬화 형태를 사용하기로 판단했어도, 불변식 보장과 보안을 위해 readObject 메서드를 제공해야 할 떄가 많다.
- 위에서 private 임에도 javadoc 을 남긴 것은, 직렬화 시에 직렬화 형태에 포함되는 공개 API 이기 때문이다.


### 직렬화 형태에 적함하지 않는 예
```java
public final class StringList implements Serializable {
    
    private int size = 0;
    private Entry head = null;
    
    private static class Entry implements Serializable {
        String data;
        Entry next;
        Entry previous;
    }
}
```
 - 문자열을 표현하는 클래스이지만, 연결리스트로 구현하다 보니 Entry 라는 것이 사용되었고 물리적 표현과 논리적 표현의 차이가 커졌다.
 
#### 아래 네가지 면에서 문제가 생긴다.
 1. 공개 API 가 현재 내부 표현 방식에 영원히 묶인다.
    - 연결 리스트를 사용한 직렬화가 공개로 풀리면 여러 곳에서 사용된다.
    - 연결 리스트를 다음 릴리즈에서 뺀다면, 이미 풀린 바이트 스트림들을 역직렬화할 때 오류나기 떄문에 연결리스트를 빼지 못한다.
 1. 너무 많은 공간을 차지한다.
    - 엔트리 연결정보는 내부 구현에 해당하니 직렬화 형태에 포함할 필요 없는데 포함해애야한다.
 1. 느려진다
 1. 스택 오버플로우를 일으킬 수 있다.
 
 
### 재대로 구현한 StringList
```java
public final class StringList implements Serializable {
    private transient int size = 0;
    private transient Entry head = null;
    
    // 이제는 직렬화되지 않는다.
    private static class Entry {
        String data;
        Entry next;
        Entry previous;
    }
    
    // 지정한 문자열을 이 리스트에 추가한다.
    public final void add(String s) {...}
    
    /**
     * 이 {@code StringList} 인스턴스를 직렬화한다.
     * 
     * @serialData 이 리스트의 크기(포함된 문자열의 개수)를 기록한 후
     * ({@code int}), 이어서 모든 원소를(각각은 {@code String})
     * 순서대로 기록한다.
     */
    private void writeObject(ObjectOutputStream s) throws IOException {
     	//기본 직렬화를 수행한다.
        s.defaultWriteObject();
        s.writeInt(size);
        
        // 커스텀 역직렬화를 수행한다.
        // 모든 원소를 올바른 순서로 기록한다.
        for (Entry e = head; e != null; e = e.next)
            s.writeObject(e.data);
    }
    
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        //기본 역직렬화를 수행한다.
        s.defaultReadObject();
        int numElements = s.readInt();
        
        // 커스텀 역직렬화 부분
        // 모든 원소를 읽어 이 리스트에 삽입한다.
        for(int i = 0; i < numElements; i++) {
            add((String) s.readObject());
        }
    }
}
```
 - transient : 직렬화 시 포함하지 않는다.
 - writeObject, readObject : 직렬화를 처리하는 커스텀 직렬화 형태 메서드
 - 위 메서드에도 javadoc 주석이 달려있는데, @SerialData 로 정보를 추가하게 된다.
 