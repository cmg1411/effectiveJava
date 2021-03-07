# 반환 타입으로는 스트림보다 컬렉션이 낫다.

## 반환된 시퀀스를 사용하는 두가지 방법 : Iterable vs Stream

### Iterable
 - 스트림이 있기 이전 보통 컬렉션으로 만들지 못하는 시퀀스를 Iterable 인터페이스를 이용하여 만들었다.
 - 반복을 지원한다.
   - for-each 문을 사용할 수 있다는 이야기다.
   - 예를 들어, List.of() 가 반환하는 List 의 상위 클래스 Collection 인터페이스는 Iterable 을 구현하기 때문에, 아래와 같이 for-each 를 사용할 수 있다.
   - ```java public interface Collection<E> extends Iterable<E> {...}```  
```java    
public static void main(String[] args) {
    for (Integer integer : List.of(1, 2, 3)) {
        System.out.println(integer);
    }
}  
```

<br>


### Stream
 - Stream 클래스의 상위 클래스인 BaseStream 클래스는 Iterable 의 추상메서드를 모두 구현한다.
 ```java
public interface Stream<T> extends BaseStream<T, Stream<T>> {



public interface BaseStream<T, S extends BaseStream<T, S>>
        extends AutoCloseable {
    
    Iterator<T> iterator();
    ...
```
 - 문제는, 정작 Stream, BaseStream 은 Iterable 인터페이스를 구현하고 있지 않다는 것이다.
 - 따라서 스트림타입은 for-each 를 사용할 수 없다.
 
 <br>
 
### 그래서 뭐가문제 ?
 - 문제는 스트림을 반환하면 for-each 를 사용할 수 없다는 것과, iterable 만 구현한 시퀀스를 반환하면 stream 을 쓸 수 없다는 것이다.
 - API 에서 이렇게 짜준다면, API 를 사용하는 사용자들의 불만을 듣게 될것이다.
 
 <br>
 
### 스트림을 for-each 에서 쓰려면 ? 
 - ProcessHandle 인터페이스의 static 메서드인 allProcess() 는 스트림을 반환한다.
```java
    static Stream<ProcessHandle> allProcesses() {
        return ProcessHandleImpl.children(0);
    }
``` 
 - 따라서 이를 이용하여 for-each 를 사용하려하면 에러가 난다.
 ```java
//에러나는 코드
for (String allProcess : ProcessHandle.allProcesses()) {   
}
```
 - 스트림 iterator() 메서드로 Iterator 객체를 반환하면 ?
 - 아래의 (Iterable<ProcessHandle>) 같은 적절한 형변환이 없으면 에러가 난다.
 ```java
for (ProcessHandle allProcess : (Iterable<ProcessHandle>) ProcessHandle.allProcesses()::iterator) {
          
}
```

 - 이건 너무 더럽고 난잡하다 ! 싶다. 그땐 어댑터 메서드를 사용할 수 있다.
 ```java
public static void main(String[] args) {
    for (ProcessHandle allProcess : iterableOf(ProcessHandle.allProcesses())) {
    }
}

public static <E> Iterable<E> iterableOf(Stream<E> stream) {
    return stream::iterator;
}  
```

<br>

**참고. `stream::iterator`가 Iterable이 되는 이유**

위 Iterable 인터페이스를 보면 `iterator`를 제외한 `forEach`와 `spliterator`는 이미 구현이 되어있다.

```java
public interface Iterable<T> {
    Iterator<T> iterator();
    default void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T t : this) {
            action.accept(t);
        }
    }
    default Spliterator<T> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), 0);
    }
}
```

즉, Iterable은 `iterator`를 람다로 제공할 수 있다는 뜻이 된다.

```java
@Test
void foreach_stream() {
    // given
    Stream<Integer> numberStream = Stream.of(1, 2, 3);
    // when
    for (int number: (Iterable<Integer>) () -> numberStream.iterator()) {
        assertThat(number <= 3).isEqualTo(true);
    }
}
```

따라서 메서드 레퍼런스를 처음 `Iterable`로 형변환한 코드를 위와 같이 람다로 변환할 수 있다.
그리고 위 람다가 `Iterable#iterator`를 구현한 것이 되므로 메서드 레퍼런스로 변환이 가능한 것이다.

<br>

### Iterable 로 stream 파이프라인 쓰려면 ?
 - 이것도 어댑터 메서드를 만들 수 있다.
 
```
public static <E> Stream<E> streamOf(Iterable<E> iterable) {
    return StreamSupport.stream(iterable.spliterator(), false);
}  
```

<br>

### Collection 하위의 컬렉션들은 Iterable 도 구현하고, Stream.of 라는 정적 메서드도 제공한다.
 - 뭐, API 사용자가 스트림만 사용한다, iterable 만 사용한다 라는 확신이 있다면 상관없다.
 - 하지만 보통 공개 API 에서는 둘 다 사용할 수 있는 Collection 을 반환하는 것이 일반적으로 좋다.
 
 
<br>


### 주의점 : 컬렉션을 반환하는 것은 좋지만, 그냥 new ArrayList() 를 만들어 안에 시퀀스를 집어넣고 반환하는식으로 하면 안된다.
 - 메모리에 큰 시퀀스를 올리는 것은 위험이 따른다.
 - 멱집합은 {a, b, c} 일떄, {a}, {b}, {c}, {a,b}, {a, c} ... 의 2^3 = 8 개가 있다.
 - 멱집합을 계산하여 컬렉션으로 반환하겠다고 했을때, 그냥 메모리에 올려버리면 문제가 생길 수 있다.
   - 어떤 문제냐. 2^n 은 선형으로 증가하기 때문에, 메모리가 터져버릴 수 있다.
   - 2^n 개 x char 형 집합을 만들게 되는 것이다. int 면 문제가 더 심각해지겠지 ?
   
### 해결법 ?
 - 새로운 클래스를 만들어 전용 컬렉션을 구현해 보자.
 - 멱집합의 경우, 새로운 AbstractList 로 전용 컬렉션을 만들어서 비트벡터를 이용하면 30 개의 원소의 멱집합을 구하는 로직을 만들어 넣을 수 있다.
 - {a, b, c} 는 3비트만 있으면 표현할 수 있다.
 

##### AbstractCollection 을 이용하여 Collection 구현체를 작성할 때는 Iterable 용 size(), contains() 만 더 구현하면 된다.

### 부분리스트 예제.
 - 이 예제는, 단순히 구현하기 쉬운 쪽을 선택하기도 한다는 것을 보여준다.
 - 스트림을 이용하면, 전용 컬렉션을 이용하는 것보다 쉬우므로 스트림을 반환하는 것을 택한다는 이야기이다.
 - 물론 성능적인 단점은 있는데, 사용하는 쪽에서 iterable 로 사용하고 싶으면 가독성이 떨어지는 어뎁터 매서드를 추가 정의해야 하고, 
 - 전용 컬렉션보다 속도도 느리다.
 
## 핵심 정리
 - 양쪽을 모두 만족시키려 노력하자.
 - 반환 전부터 이미 컬렉션에 담아 관리하고 있거나, 컬렉션을 하나 더 만들어도 될 정도로 원소가 적으면 ArrayList 같은 걸로 메머리에 올려 반환하라.
 - 그렇지 않다면 전용컬렉션을 고민해보자.
 - 전용 컬렉션이 불가능하다면, 스트림과 이터러블중 자연스러운 것을 반환하라.
 - `stream 이 iterable 을 지원할 떄 까지 존버하라.`
 
  