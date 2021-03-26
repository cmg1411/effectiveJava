# 공유 중인 가변 데이터는 동기화해 사용하라.

## synchronized 키워드의 역할.
 1. 배타적 수행 : 해당 블록을 한번에 한 스레드씩 수행되도록 보장한다.
    - 해당 블록이 실행될 때는 상태가 일관되지 않은 상태이다.
    - 따라서 그 순간에 다른 스레드가 보지 못하게 막는다.
    - 보지 못하게 막는 것을 **락 을 건다** 라고 한다.
    - 락을 건 블록은, 그 객체를 일관된 상태에서 다른 일관된 상태로 만들어, Thread-safe 하게 만든다.
 1. 동기화 없이는 한 스레드가 만든 변화 다른 스레드에서 확인하지 못하게 한다.
    - 동기화는 동기화된 메서드나 블록에 들어간 스레드가 같은 락의 보호하에 수행된 모든 이전 수정의 최종 결과를 보게 해준다.
    
## long, double 을 제외한 변수를 읽고 쓰는 동작은 atomic (원자적) 이다.
 - [stackOverflow](https://stackoverflow.com/questions/517532/writing-long-and-double-is-not-atomic-in-java/517539)
 - [JLS 17.7](https://docs.oracle.com/javase/specs/jls/se7/html/jls-17.html#jls-17.7)
 - double, long 은 64 bits 자료형인데, JVM 에서 32 bits 로 반으로 쪼개 읽고 쓰는 작업을 한다.
 - 따라서 그 사이에 멀티스레드들이 비집고 들어올 수 있으므로, 원자적이지 않다고 할 수 있다.
 
 - 그렇다고, 동기화하지 않아도 안전하다는 것은 오산이다.
    - 위에서도 보았듯, 동기화의 역할은 두개이기 때문에 배타적 수행은 보장하지만 한 스레드가 저장한 값이 다른 스레드에게 '보이는가' 를 보장하지 않는다.
    - 스레드 사이의 안정적인 통신에 꼭 필요하다.
   
 - 이는 한 스레드가 만든 변화가 다른 스레드에게 언제 어떻게 보이는지를 규정한 자바의 메모리 모델 때문이다.
    - [JLS 17.4](https://docs.oracle.com/javase/specs/jls/se7/html/jls-17.html#jls-17.4) 
    - [참고](https://medium.com/@qwefgh90/jsr-133-java-memory-model-faq-%EB%B2%88%EC%97%AD-128487aebc1e)
    ```java
    너무 어렵지만 읽어본 내용을 정리해보자면,
   
    스레드 1	        스레드 2
    1: r2 = A;	3: r1 = B;
    2 : B = 1;	4 : A = 2;
   
   두 스레드에 이런 명령이 있다고 생각해보자.
   r2 = 2, r1 = 1 의 결과가 가능할까 ?
   
   직관적으로는 그렇지 않다. 
   1의 읽기에서는 3이 있기 전에는 4의 쓰기를 볼 수 없고,
   3의 읽기에서는 2의 쓰기를 볼 수 없다.
   
   하지만 실상은, JVM 의 JIT 컴파일러는 이 프로세스의 순서를 [재배열] 할 수 있다.
   프로그램의 의미를 해치지 않는 선에서.
   
   스레드 1	스레드 2
   B = 1;	r1 = B;
   r2 = A;	A = 2;
   
   - 한 스레드에는 읽기작업이, 다른 스레드에는 쓰기작업이 있는 경쟁상태의 예이다.
   - 또한, 적절한 동기화가 없다고 가정한다.
   - 이 때, 쓰기연산 B=1 의 순서가 바뀌어 예측했던 결과와는 다를 수 있다는 것이다.
   
   자바의 메모리 모델은 이 재배열의 순서를 정의한다. 
   
   결과적으로, 재배열 이라는 것 때문에 경쟁상테에서 동기화되지 않은 코드는 예상 외의 결과를 낼 수 있는 것이다.
  
   <올바르지 못한 동기화 : 경쟁 상태인 것.>
   1. 하나의 쓰레드에서 하나의 변수에 값을 쓴다.
   2. 다른 하나의 쓰레드에서 같은 변수에 값을 읽어 들인다.
   3. 읽고 쓰는 코드에 순서가 없다.
   
    ```
## 두번째 이유인 통신 동기화의 문제

```java
public class StopThread {

    private static boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
           int i = 0;
           while (!stopRequested) {
               i++;
           }
        });

        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }
}
```

 - 위의 코드가 1초후에 끝날 것 같은가?
 - 아니다. 왜냐하면, 메인 스레드에서 바꾼 stopRequested = true 를 백그라운드 스레드에서 언제쯤 볼지 보증할 수 없기 때문이다.
 - 위에서 설명했듯, JIT 컴파일러가 다음과같이 최적화할 수 있기 때문이다.
 ```java
// 원래 코드를
while (!stopRequested) {
    i++;
}
// 이렇게 변경
if (!stopRequested)
    while (true) {
        i++;
    }
}
```
 - 이렇게 변경한다면, 메인 스레드에서 바꾼 결과를 영원히 볼 수 없을 것이다.


## 정상적인 변경

```java
public class StopThreadRefectoring {

    private static boolean stopRequested;

    private static synchronized void stop() {
        stopRequested = true;
    }

    private static synchronized boolean stopRequested() {
        return stopRequested;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested()) {
                i++;
            }
        });

        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        stop();
    }
}
```
 - 쓰기 메서드와 읽기 메서드를 모두 동기화했다.
 - 동기화 했다는 것은, 스레드간 통신에서 한 스레드가 만든 결과를 다른 스레드에서 볼 수 있도록 만드는 것이다.
 - 이 예가 배타적 수행 이외에 스레드간 통신을 위해서만 synchronized 를 쓴 방법이라 할 수 있다.
 
 <br>
 
 
 ## volatile
  - 단어의 뜻은 휘발성 이라는 뜻이다.
  - 이 키워드는 CPU 캐시가 아닌 메인 메모리에 값을 저장하도록 한다.
  - 따라서 멀티스레드 환경에서 가장 최근에 기록된 값을 읽도록 보장한다.
  
  위에서 원래 안됬던 코드에서 `private static boolean stopRequested;` 를 `private static volatile boolean stopRequested;` 로 바꾸면 동작한다.
  
  
 <br>
 
 ## 안전실패
  - 코드상으로는 한 줄이지만, 실제로는 여러줄로 해당 필드에 두번이상 접근할 수 있는 경우가 있다.
  - 이 상황은 코드가 한줄이라 원자적으로 착각하여 멀티스레드에서 동시성 문제가 발생할 수 있다.
  - volatile 은 이를 해결해주지 않는게, volatile 은 통신쪽 문제만 해결해준다.
  - 이를 안전실패 라 한다.
  - 싱글톤의 멀티스레드 문제점이 대표적이다.
  
  
 <br>
 
## 근본적 문제의 해결
 - 사실 근본적으로는, 가변 데이터를 공유하지 않으면
 - 다시말에 가변 데이터는 싱글 스레드에서만 쓰면, 위의 문제는 발생하지 않는다.
 
## 사실상 불변, 안전 발행
 - 한 스레드가 데이터를 다 수정한 후, 다른 스레드에 공유할 때에는 해당 객체에서 공유하는 부분만 동기화해도 된다.
 - 그러면 그 객체를 다시 수정할 일이 생기기 전까지는, 다른 스레드들이 동기화없이 값을 읽어갈 수 있다.
 - 정적필드, volatile 필드, final 필드, 락, 동시성 컬렉션 을 이용하여 사실상 불변 객체를 만들 수 잇다.
 - 사실상 불변 클래스를 건네는 행위를 안전발행 이라 한다.