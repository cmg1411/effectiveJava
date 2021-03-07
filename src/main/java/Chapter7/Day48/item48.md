# 스트림 병렬화는 주의해서 진행하라.

## 올바른 동시성 프로그램을 작성하기는 어렵다.
 - 동시성 프로그램에는 안정성과 응답 가능 상태를 유지해야 하는데 병렬 스트림 파이프라인에서도 마찬가지다.
 
## 메르센 소수를 구하는 프로그
```java
public class Mersenne {

    public static void main(String[] args) {
        primes().map(p -> BigInteger.TWO.pow(p.intValueExact()).subtract(BigInteger.ONE))
            .filter(mersenne -> mersenne.isProbablePrime(50))
            .limit(50)
            .forEach(System.out::println);
    }

    static Stream<BigInteger> primes() {
        return Stream.iterate(BigInteger.TWO, BigInteger::nextProbablePrime);
    }
}
```
 - 메르센 소수 20 개를 구하는 프로그램이다. 내컴퓨터에서는 1분넘게 걸려도 안끝났지만, 저자의 컴퓨터는 12.5초에 끝났다고 한다.
 - 이를 더 빨리 하기 위해 병렬 프로그램으로 바꾸면 어떻게 될까?
 
## 위 물음의 결론.
 - 응답 불가. 무한히 끝나지 않는다.
 - 스트림 라이브러리가 이 파이프라인을 병렬화하는 방법을 못찾기 때문.
 
## 스트림 병렬화로 성능개선을 기대할 수 없는 두가지 상황
 1. 데이터 소스가 Stream.iterate 일때.
    - iterate() 는 조심히 사용하지 않으면 무한 스트림을 반환한다.
    - 병렬로 처리하려면 데이터를 나눠야 하는데 무한 스트림은 어떻게 나눠야 할지 모른다.
 1. 중간 연산으로 limit 를 쓰는 경우.
    - 병렬 스트림은 limit 을 다룰 때, CPU 코어가 남는다면 원소를 몇개 더 처리한 후 제한된 개수 이후의 결과를 버린다.
    - 메러센 소수의 경우 다음 소수 그다음 소수로 갈 때마다 구하는 길이가 어마어마하게 길어지고 그만큼 구하는 시간도 선형으로 늘어난다.
    - 그러면 위의 언급인 limit 의 처리에서 몇개 더 처리의 시간이 어마어마하게 긴 시간이 되어 버리는 것이다.
    - 따라서 메르센소수는 병렬 프로그램을 마비시키고, 성능개선을 기대할 수 없다.
    
## 그러면 병렬화가 좋을 때는 ?
 1. ArrayList, HashMap, HashSet, ConcurrentHashMap 소스
 1. 배열
 1. int 범위
 1. long 범위
 
 이 자료구조들은 모두 데이터를 원하는 크기로 정확하고 쉽게 나눌 수 있다.
 
#### Spliterator 인터페이스
 - 병렬 스트림에서 전체 시퀀스를 청크단위로 나눠서 처리하기 위해 사용하는 인터페이스이다.
 - 이 인터페이스 덕분에 우리는 데이터를 직접 나눌 필요가 없다.
 - 이 객체는 Stream, Iterable 의 spliterator 메서드로 얻을 수 있다.
   
#### 위 자료구조들이 병렬화에 좋은 이유 : 참조 지역성이 뛰어남
 - 참조 지역성 : 이웃한 원소들의 참조가 메모리에 얼마나 가까지 모여있는가.
 - 참조 지역성이 낮으면 스레드는 데이터가 주 메모리애서 캐시메모리로 전송되어 오기를 기다리며 멍때린다.
 - 기본타입 배열은 참조가 아니라 메모리에 데이터 자체를 연속해서 저장하므로 제일 좋다.
 
## 병렬 수행 효율에 영향을 미치는 네번째 요소 : 종단 연산의 동작 방식
 - 종단 연산에서 수행하는 작업량이 파이프라인 전체 작업에서 상당한 비중을 차지하면서 순차적인 연산이라면, 이 종단연산이 늘어나는 병렬 파이프라인은 자연스럽게 효과가 제한된다.
 
#### 병렬에 가장 좋은 종단연산 : 축소 (reduce)
 - reduce : 파이프라인에서 만들어진 모든 원소를 하나로 합치는 작업
   - 스트림의 reduce 메서드들 중 하나.
   - min, max, count, sum
   
#### 병렬에 좋은 종단연산 : Match
 - anyMatch, allMatch, noneMatch
 - 조건에 맞으면 바로 반환되는 메서드들
 
#### 병렬에 좋지 않은 종단연산 : 가변 축소를 수행하는 Stream, collect

> 직접 구현한 Stream, Iterable, Collection 이 병렬화의 이점을 누리게 하려면 spliterator 메서드를 반드시 재정의하고, 병렬화의 성능을 강도높게 테스트해야 한다.

## (다섯번째 고려) 스트림을 잘못 병렬화하면 결과 자체가 잘못되거나 예상 못하는 동작을 유발한다.
map, filter 등 함수객체가 명세대로 동작하지 않을 때 발생한다.
#### Stream 명세에는 map, filter 등 함수객체에 관한 엄중한 규약들이 있다.
 - reduce 연산에 건네지는 accumulator, combiner 함수는 반드시 결합법칙을 만족한다.
    - (a op b) op c == a op (b op c)
    - 이를 만족하지 않으면, 순서가 보장되지 않는 병렬 스트림에서 결과가 틀리게 나올 수 있다.
 - 간섭받지 않아야 한다.
    - 파이프라인이 수행되는 동안 데이터 소스가 변하지 않아야 한다.
 - 상태를 갖지 않아야 한다.
    - 파이프라인이 수행되는 동안 상태가 수정되지 않는 final 이어야 한다. (side effect 가 없어야 한다.)

##### 위와 같은 내용으로, 메르센 소수를 구하는 프로그램은 소수의 순서가 올바르지 않을 수 있다.
 - 이럴때는 forEach 를 forEachOrdered 로 바꿔주면 된다.
 
## (여섯번째 고려) 파이프라인이 수행하는 진짜 작업이 병렬화에 드는 추가 비용을 상쇄하지 못하면 성능향상은 미미하다.
 - 스트림 안의 원소 수 x 원소당 수행되는 코드 라인 수
 - 위의 결과가 최소 수십만은 되어야 병렬화에 성능을 기대할 수 있다.
 
## 병렬화는 오직 성능 향상을 위해 사용해야 한다.
 - 병렬화를 했다면, 성능이 좋아졌는지 가치가 있는지 테스트한다.
 - `보통 병렬 스트림 파이프라인도 공통의 fork-join 풀에서 수행되므로, 잘못된 병렬 파이프라인은 시스템 다른 부분 성능에까지 악영향을 미칠 수 있다.`
 
 
## 이 많은 어려움을 이겨내고 좋은 조건을 만든다면, 병렬화는 성능 향상에 정말 좋다.
```java
public class Performance {

    public static void main(String[] args) {
        pi(100000000); // 18초

        piParallel(100000000); // 3초
    }

    static long pi(long n) {
        return LongStream.rangeClosed(2, n)
            .mapToObj(BigInteger::valueOf)
            .filter(i -> i.isProbablePrime(50))
            .count();
    }

    static long piParallel(long n) {
        return LongStream.rangeClosed(2, n)
            .parallel()
            .mapToObj(BigInteger::valueOf)
            .filter(i -> i.isProbablePrime(50))
            .count();
    }
}

```

#### 랜덤 수로 이뤄진 스트림을 병렬화
 - ThreadLocalRandom 보다는 : 단일 스레드에서 쓰기 위해 설계
 - Random 보다는 : 모든 연산을 동기화하기 때문에 병렬에서는 최악
 - SplittableRandom 을 사용하자. : 병렬에 쓰기 위해 설계
 
 ```java
public class RandomEx {

    public static void main(String[] args) {
        random1(); // ThreadLocalRandom 35
        random2(); // SplittableRandom 6
    }

    static void random1(){
        long start = System.currentTimeMillis();
        ThreadLocalRandom.current()
            .doubles()
            .parallel()
            .limit(100)
            .sorted()
            .forEach((val) -> System.out.println(Thread.currentThread().getName()));
        long end = System.currentTimeMillis();
        System.out.println("===============");
        System.out.println(end-start);
        System.out.println("===============");
    }


    static void random2(){
        SplittableRandom splittableRandom = new SplittableRandom();
        long start = System.currentTimeMillis();
        splittableRandom.doubles()
            .parallel()
            .limit(100)
            .sorted()
            .forEach((val) -> System.out.println(Thread.currentThread().getName()));
        long end = System.currentTimeMillis();
        System.out.println("===============");
        System.out.println(end-start);
        System.out.println("===============");
    }
}
```