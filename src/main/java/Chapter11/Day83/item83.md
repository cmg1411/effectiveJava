# 지연 초기화는 신중히 사용하라

지연 초기화는 필드의 초기화 시점을 그 값이 처음 필요할 떄 까지 늦추는 기법이다.  
그래서 값이 전혀 쓰이지 않으면 초기화도 결코 일어나지 않는다.  

용도
 - 최적화
 - 클래스와 인스턴스의 초기화시 순환 참조 문제를 해결할 떄
 
## 지연 초기화는 필요할 떄 까지는 하지 말라.
 - 인스턴스 초기 생성 비용은 줄지만, 지연 초기화하는 필드에 접근하는 비용은 커진다. (접근할 떄 초기화하니까.)
 - 지연 초기화가 실제로 성능을 더 느리게 할 수 있다.
 - 만약 지연 초기화를 할 필드가 인스턴스에서 많이 사용되지 않고, 초기화가 무겁다면 지연 초기화는 좋다.
    - 무거운 것을 쓸 떄만 초기화하는데, 많이 쓰지 않으니 효율적.
    - 무거운 것을 항상 초기화하면 비효율적.
    - 효율적인지 알려면 적용후 측정밖에 없다.
    
  #### 다른 상황과 마찬가지로, 그리고 특히, 지연 초기화하는 필드는 멀티스레드 환경에서 반드시 동기화해야 한다.
  
  
## 대부분의 상황에서 지연 초기화보다 일반적 초기화가 낫다.

 - 인스턴스 필드의 일반적인 초기화
 ```java
private final FieldType field = computeFieldValue(); 
 ```
- 인스턴스 필드의 지연 초기화
    - 지연 초기화가 초기화 순환성 을 깨뜨릴 것 같다면, synchronized 한정자를 사용하면 된다.
 ```java
private final FieldType field;

// 지연 초기화는 값이 필요해서 가져와야 할 떄 초기화한다.
private synchronized FieldType getField() {
    if (field == null) {
        field = computeFieldValue();
    return field;
    }
}
 ```

## 성능 떄문에 `정적 필드`를 지연 초기화해야 한다면, `지연 초기화 홀더 클래스 관용구` 를 사용하라.
 - 지연 초기화 홀더 클래스 관용구
    - static 필드는 JVM 이 구동될 떄 메서드 영역에 올라감.
    - 하지만 클래스는 쓰일 때 클래스로더에 의해 동적으로 생성됨.
    - 이 특성을 이용하여, static 필드를 JVM 이 구동될 때 초기화하는 것이 아니라, 클래스가 쓰일 때 초기화.
 ```java
 private static class FieldHolder {
    static final FieldType field = computeFieldValue();    
 }

 private static FieldType getField() {
    return FieldHolder.field;
 }
 ```
 - static 메서드가 실행될 때 비로소 클래스가 만들어진다.
 
 ## 성능 떄문에 `인스턴스 필드`를 지연 초기화해야 한다면, `이중검사 관용구` 를 사용하라.
 - 이 관용구는 초기화된 필드에 접근할 때의 동기화 비용을 없애준다.
 ```java
private volatile FieldType field; // 동시성을 검사하지 않으므로 volatile 필요

private FieldType getField() {
    FieldType result = field; // result 지역변수는 초기화된 상황에서 초기화를 딱 한번만 읽도록 보장.
    if (result != null) {
        return result;
    }
    
    synchronized(this) { // 동시성 검사 한번 더 수행
        if (field == null) {    
            field = computeFieldValue();
        }
        return field;
    }
}
```

> 멀티 스레드 환경에서는 성능을 위해 메모리 이외에도 스레드마다 CPU 캐시를 사용하는데, 이 캐시값은 메모리에 언제 쓰일지 알 수 없다.  
> 따라서 가시성 문제가 발생한다.  
> 이 때 volatile 변수는 메모리에서 직접 값을 읽고 쓰기 때문에 문제를 완화시킬 수 있다.

 - 반복해서 초기화해도 상관없는 인스턴스 필드르 지연 초기화해야 할 때
    - 두번째 검사를 하지 않아도 된다.
    - volatile 은 유지한다.