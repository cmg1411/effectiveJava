# 추상화 수준에 맞는 예외를 던져라.

하려는 일과 관련없는 예외가 튀어나오면 당황스러울 것이다.  
종종 예외를 무책임하게 throw 할때 이런 상황이 발생할 수 있따.

하지만 내부 구현 방식을 상위로 드러내어 상위 API 를 오염시킨다.

따라서 **상위 계층에서 저수준 예외를 그대로 던지지 말고, 예외를 잡아서 자신의 추상화 수준에 맞는 예외로 바꿔 던져야한다**.  
이를 예외번역이라고 한다.

```java
try {
    ... // 저수준의 추상화를 이용한 처리
} catch(저수준예외 e) {
    throw new 고수준예외(); // 저수준의 추상화를 이용해 처리하다 저수준 예외가 발생하면 잡아서 고수준의 예외로 바꿔 던진다.
}
```

AbstractSequentialList 는 List 인터페이스의 골격구현 클래스이다. (LinkedList 가 확장하는 클래스이다.)  
이 클래스의 get 메서드는 예외번역을 사용하고 있다.  
```java
    /**
     * Returns the element at the specified position in this list.
     *
     * <p>This implementation first gets a list iterator pointing to the
     * indexed element (with {@code listIterator(index)}).  Then, it gets
     * the element using {@code ListIterator.next} and returns it.
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E get(int index) {
        try {
            return listIterator(index).next();
        } catch (NoSuchElementException exc) {
            throw new IndexOutOfBoundsException("Index: "+index);
        }
    }
```
 - NoSuchElementException 즉, 리스트를 순회하다가 만난 이 예외는 인덱스를 벗어났다는 의미이다.
 - 따라서 이 의미를 담은 고수준의 예외로 바꿔서 던지는 것이다.
 
 
#### 예외연쇄
 - 예외 연쇄 : 저수준 예외의 원인 즉 문제의 근본 원인 고수준 예외에 실어 보내는 방법
 - 저수준 예외가 디버깅에 도움이 된다면 선택할 수 있다.
 
```java
try {
    ... // 저수준의 추상화를 이용한 처리
} catch(저수준예외 cause) {
    throw new 고수준예외(cause);
}
```

 - 보통의 표준 예외는 예왜 연쇄용 생성자를 가지고 있다.
 ```java
public class RuntimeException extends Exception {

    public RuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
   
    public RuntimeException(Throwable cause) {
        super(cause);
    }
    ...
}
```
 - 이 예외들은 super 로 최상위로 전달하여 Throwable 까지 건네진다.
 - 또 이런게 없더라도, Throwable 클래스의 initCause() 메서드로 원인을 지정할 수 있따. (Throwable 은 이름 생긴것과 다르게 클래스다.)
 ```java
     public synchronized Throwable initCause(Throwable cause) {
         if (this.cause != this)
             throw new IllegalStateException("Can't overwrite cause with " +
                                             Objects.toString(cause, "a null"), this);
         if (cause == this)
             throw new IllegalArgumentException("Self-causation not permitted", this);
         this.cause = cause;
         return this;
     }
```

