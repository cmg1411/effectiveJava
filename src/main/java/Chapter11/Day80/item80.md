# 스레드보다는 실행자, 테스크, 스트림을 애용하라.

## 실행자 프레임워크
 - java.util.concurrent
 - 실행자를 생성하고, 작업을 실행자에게 떠넘기는 프레임워크.
 ```java
ExcuteorService exec = Executors.newSingleThreadExecutor();
exec.execute(runnable);
exec.shutdown();
 ```
- 실행자 서비스의 주요 기능
    - get() : 특정 테스크 완료되기를 기다린다.()
    - invokeAny() : 테스크 모음 중 아무 것 하나가 완료되기를 기다린다. 
    - invokeAll() : 모든 테스트가 완료되기를 기다린다.
    - awaitTermination() : 실행자 서비스가 종료하기를 기다린다.
    - 완료된 태스크들의 결과를 차례로 받는다 : ExecutorCompletionService 이용.
    - 데스크를 특정 시간에 혹은 주기적으로 실행하게 한다 : ScheduledThreadPoolExecutor 이용.
    
## 실행자 서비스 : 스레드 풀

### Executors.newCachedThreadPool
 - 작은 서비스에 적합.
 - 특별히 셋팅할게 없다.
 - 요청받은 태스크들이 큐에 쌓이지 않고 즉시 스레드에 위임되어 실행된다.
 - 사용 가능한 스레드가 없다면, 새로 하나를 만든다. 서버가 아주 무겁다면, CPU 사용율이 100% 까지 치솟을 것이다.
 - 그래서 무거운 프로그램은 아래 스레드풀을 쓴다.
 
### Executors.newFixedThreadPool
 - 스레드 개수를 고정한다. 

### Executors.ThreadPoolExecutor
 - 완전히 통제 가능.
 - 자기 맛대로 동작방식을 구현 가능하다는 뜻.
 
## 태스크
 - 작업 단위를 나타내는 추상 개념.
 - 실행자 프레임워크에서는 태스크와 실행 메커니즘이 분리된다.
 - 테스크의 종류
    - Runnable
    - Callable (Runnable + 값을 반환하거나 예외를 던지는 동작도 가능)
 - 실행자 프레임워크는 태스크를 수행하는 일반적인 메커니즘이다.
 - 태스크 작업의 실행은 실행자 프레임워크에 맡기고, 우리는 태스크의 정책만 정해주면 된다.

## 포크-조인 프레임워크
 - 포크-조인 풀이라는 특별한 실행자 서비스가 실행한다.
 1. ForkJoinTask 의 인스턴스는 작은 하위 태스크로 나눌 수 있다.
 1. ForkJoinPool 을 구성하는 스레드들이 이 태스크들을 처리한다.
 1. 일을 먼저 끝낸 스레드는 다른 스레드의 남은 태스크를 가져와 대신 처리할 수 있다.
 1. `이렇게 하여 모든 CPU 를 최대한 활용하여 높은 처리량과 낮은 지연시간을 달성한다.`