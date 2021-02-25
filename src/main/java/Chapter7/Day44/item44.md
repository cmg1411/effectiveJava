## 표준 함수형 인터페이스를 사용하라.

 - 함수 객체를 매개변수로 받는 생성자나 메서드를 더 많이 만들어야 한다. 
 
 put -> putVal -> afterNodeInsertion -> removeEldestEntry
 
 
```java
@FunctionalInterface
public interface RemoveFunctional<K, V> {
    boolean remove(Map<K, V> map, Map.Entry<K, V> eldest);
}
```
 
하지만 이렇게 만들어 쓸 필요가 없다.  
기본 제공하는 표준 함수형 인터페이스를 사용하자.
 
### 기본 함수형 인터페이스 정리 표
| 인터페이스 | 함수 시그니처 | 의미 |  예 |
| - | - | - | - |
| `UnaryOperator<T>` | `T apply(T t)` | 반환 값과 인수의 타입이 같은 함수, 인수는 1개 | `String::toLowerCase` |
|`BinaryOperator<T>` | `T apply(T t1, T t2)` | 반환 값과 인수의 타입이 같은 함수, 인수는 2개 | `BigInteger::add` |
| `Predicate<T>` | `boolean test(T t)` | 인수 하나를 받아 boolean을 반환하는 함수 | `Collection::isEmpty` |
| `Function<T, R>` | `R apply(T t)` | 인수와 반환 타입이 다른 함수 | `Arrays::asList` |
| `Supplier<T>` | `T get()` | 인수를 받지 않고 값을 반환(혹은 제공)하는 함수 | `Instant::now` |
| `Consumer<T>` | `void accept(T t)` | 인수 하나 받고 반환 값은 없는 함수 | `System.out::println` |


### 주의사항
 - 기본 함수형 인터페이스에 박싱된 기본타입을 넣어 사용하지 말자.
    - 보통 표준 함수형 인터페이스 는 기본 타입만 지원한다. 박싱으로인한 성능저하는 피하자.
    
 - 전용 함수형 인터페이스를 구현해야 하는가 고민하자. Comparator<T> 처럼. 아래의 3가지 사항을 고려하고, 하나이상 만족하면 고민하자.
    - 자주 쓰이며, 새로운 이름 자체가 용도를 명확히 설명할 수 있다.
    - 반드시 따라야 하는 규약이 있어서 주석으로 담아야 한다.
    - 유용한 디폴트 메서드를 제공할 수 있다.
    
 - @FunctionalInterface 를 꼭 붙여라
 ```java
public interface ExecutorService extends Executor {
    // Callable<T>와 Runnable을 각각 인수로 받게 Overloading함
    // submit 메서드를 사용할 때마다 형변환이 필요하다
    <T> Future<T> submit(Callback<T> task);
    Future<?> submit(Runnable task);
}
```
위와 같이 함수형 인터페이스를 같은 위치의 인수로 받는 메서드들을 다중정의하는 것은 피해야 한다.