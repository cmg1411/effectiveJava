# ITEM 1. 생성자 대신 정적 팩터리 메서드를 고려하라.

## 팩터리 메서드

전통적으로 public 생성자를 사용하여 객체를 생성하는 방법 이외에,  
public static 메서드를 이용하여 해당 클래스의 인스턴스를 얻을 수 있다.

```
public class Book {
    private String name;
    private String author;
    private String publisher;

    public static createBook() {
        return (Book 인스턴스);
    }
}
```



---
### 정적 팩터리 메서드의 장점

#### 1. 이름을 가질 수 있다.
Book 클래스
```
public class Book {
    private String name;
    private String author;
    private String publisher;

    // 생성자를 이용한 객체 초기화
    public Book(String name) {
        this.name = name;
    }

    // 펙토리 메서드를 이용한 객체 초기화
    public static createBookWithName(name) {
        return new Book(name);
    }
}
```

클라이언트 호출 코드
```
Book book1 = new Book("effectiveJava"); 
// "effectiveJava" 가 어떤 인스턴스 변수인지 알기 어렵다.
        
Book book2 = Book.createBookWithName("effectiveJava"); 
// 이름이 "effectiveJava" 임을 한눈에 알 수 있다.
```


#### 2. 하나의 시그니쳐로 여러가지 객체를 생성할 수 있다.
매개변수의 타입과 갯수가 같은 생성자를 여러개 만들 수 없다.
```
public class Book {
    private String name;
    private String author;
    private String publisher;

    public Book(String name) {
        this.name = name;
    }
    
    // 불가능
    public Book(String author) {
        this.author = author;
    }
}
```

하지만 아래와 같이 정적 팩토리 매서드에서는 가능하다.
```
public class Book {
    private String name;
    private String author;
    private String publisher;

    public static Book createBookWithName(String name) {
        Book book = new Book();
        book.name = name;
        return book;
    }

    public static Book createBookWithAuthor(String author) {
        Book book = new Book();
        book.author = author;
        return book;
    }
}
```


#### 3. 호출 할 때마다 인스턴스를 새로 생성하지 않아도 된다.
```
public final class Boolean implements java.io.Serializable, Comparable<Boolean> {
    public static final Boolean TRUE = new Boolean(true);
    public static final Boolean FALSE = new Boolean(false);

    public static Boolean valueOf(boolean b) {
        return (b ? TRUE : FALSE);
    }
    ...
```

정적 팩토리 메서드로 객체 안에 미리 정의된 static final 상수 객체를 반환.  
매번 새로운 객체를 만들지 않는다.


#### 4. 반환 타입의 하위 타입 객체를 반환할 수 있다.
리턴 타입은 인터페이스로 지정하고, 실제로는 인터페이스의 구현체를 리턴함으로서  
구현체의 API 는 노출시키지 않고 객체를 생성할 수 있다.


java.util.Collections 동반 클래스  
-> 자바 8 이전에는 인터페이스 내에 정적 메소드를 가질 수 없었기 때
```
public class Collections {
    // 인스턴스 생성불가
    private Collections() {
    }

    // 정적 펙토리 메서드
    public static <T> List<T> unmodifiableList(List<? extends T> list) {
        return (list instanceof RandomAccess ?
                new UnmodifiableRandomAccessList<>(list) :
                new UnmodifiableList<>(list));
    }

    // 구현체 : non-public 이다.
    static class UnmodifiableList<E> extends UnmodifiableCollection<E> implements List<E> {
        ...
    }
}
```

구현체를 숨기고 public 정적 팩토리 메서드로 제공하므로 API 를 작게 만들 수 있다.
또한 프로그래머가 익혀야 하는 개념과 난이도가 낮아졌다. (개념적 무게가 낮아진다.) 

자바 8 버전 이후로는 인터페이스에서 public static 메소드를 추가할 수 있기 때문에 인터페이스로 만들 수 있다.
private static 메서드는 자바 9 부터 이용할 수 있다.

#### 5. 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다.
위의 예시에서 unmodifialbeList() 의 인수 list 가 RandomAccess 의 인스턴스인지 아닌지에 따라 다른 클래스의 객체를 반환한다.

#### 6. 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.
서비스 제공자 프레임워크 : 서비스의 구현체를 클라이언트에 제공하는 것을 프레임워크가 통제하여 클라이언트를 구현체로부터 분리

 - 서비스 제공자 프레임워크의 구성요소
   - 서비스 인터페이스 : 구현체의 동작을 정의
   - 서비스 등록 API : 제공자가 구현체를 등록할 때 사용
   - 서비스 접근 API : 클라이언트가 서비스의 인스턴스를 얻을 때 사용
   - 서비스 제공자 인터페이스 : 서비스 인터페이스의 인스턴스를 생성하는 팩터리 객체
   
```
Class.forName("oracle.jdbc.driver.OracleDriver"); 
Connection conn = null; 
conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORA92", "scott", "tiger"); 
Statement..
```

---
### 정적 팩터리 메서드의 단점
1. 상속을 하려면 public, protected 생성자가 필요하기 때문에, 정적 펙토리 메서드만 제공하면 하위 클래스를 만들 수 없다.
2. 정적 팩터리 메서드는 찾기가 어렵다.



---
### 정적 팩터리 메서드 명명규칙
```
 - from : 매개변수 하나로 하나의 인스턴스 만듦  
 - of : 여러 매개변수로 인스턴스 만듦  
 - valueOf : from 과 of의 자세한 버전
 - getInstance, instance : (매개변수를 갖거나 갖지 않거나) 같은 인스턴스임을 보장하지 않는 인스턴스 반환
 - create, newInstance : 매번 새로운 인스턴스를 생성하여 반환
 - getType : 인스턴스를 생성할 때, 해당 클래스가 아닌 다른 클래스에서 생성할 때 사용
 - newType : getType 과 동일
 - Type : getType, newType 의 간략버전
```