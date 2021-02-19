# 박싱된 기본 타입보다는 기본 타입을 사용하라.

## 자바의 데이터 타입
 - primitive type : int, double, boolean ...
 - reference type : String, List, Integer, Double ...
 
## 기본 타입과 박싱된 기본 타입의 차이점
 1. 기본타입은 값 만 가지고 있으나, 박싱된 기본 타입은 값에 더해 식별성이란 속성을 갖는다.
    1. 값이 같아도 다르다고 식별될 수 있다.
 1. 기본 타입의 값은 언제나 유효. 하지만 박싱된 기본 타입은 null 이란 유효하지 않은 값을 가질 수 있다.
 1. 기본 타입이 박싱된 기본 타입보다 시간과 메모리 사용면에서 더 효율적이다.
 
## 실질적 문제
```java
Comparator<Integer> comparator = (x, y) -> (x < y) ? -1 : (x == y ? 0 : 1);

System.out.println(comparator.compare(new Integer(3), new Integer(3)));
System.out.println(comparator.compare(Integer.valueOf(111111), Integer.valueOf(111111)));
```

 - 두 결과는 1 을 낳는다.
 - <, > 연산은 올바르게 계산하지만, == 연산자는 두 참조타입의 참조값을 비교한다.
 - 아래의 Integer.valueOf 메서드는 내부를 살펴보면 캐쉬된 값 만큼은 재대로 동작하지만 저렇게 값이 커지면 같은 문제를 발생한다.
 
## 해결
 - Comparator 클래스의 naturalOrder() 정적 메서드를 이용하자.
```java
Comparator<Integer> tComparator = Comparator.naturalOrder();
System.out.println(tComparator.compare(new Integer(3), new Integer(3)));
```
 - 직접 만들려거든 박싱타입을 언박싱해서 비교하자.
 
 
## 또다른 문제
```java
    static Integer i;

    public static void main(String[] args) {
        if (i == 42) {
            System.out.println("이게 ?");
        }
    }
```
 - 위 코드는 NullPointerException 을 발생시킨다.
 - i == 42 연산을 할 때 가 문제다.
 - 기본타입과 참조타입을 혼용한 연산에서는 참조타입의 박싱이 풀린다.
 - 그런데 초기화를 하지 않아 Null 이 들어있으니, null 을 언박싱할때 예외가 발생.
 - Integer 말고 int 쓰면 된다.
 
## 또 또 다른 문제
```java
    public static void main(String[] args) {
        Long sum = 0L;

        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
    }
```
 - 이 코드는 4초가 넘게 걸린다.
 - 문제는 sum 의 초기화에서다.
 - 참조 타입 Long 으로 선언했고, 더하는 i 는 기본타입 long 이다.
 - 위에서 언급했듯이, 혼용된 상황에서는 오토 언박싱이 일어난다.
 - 이 오토 언박싱을 하는 시간 때문에 4초가 넘게 걸린다.
 - long sum = 0L; 로 바꿔주면 1초도 안걸린다.
 
## 언제 박싱타입 씀 ?
 - 제네릭 즉, 매개변수화 메서드의 타입 매개변수나 매개변수화 타입에는 어쩔수없이 박싱타입을 써야 한다.
 - 자바에서 박싱타입밖에 지원하지 않는다.
 
 
 
 