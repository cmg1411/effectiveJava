# 과도한 동기화는 피하라.

`응답 불가와 안전 실패를 피하려면 동기화 메서드나 동기화 들럭 안에서는 제어를 절대로 클라이언트에 양도하면 안된다 !`

 - 제어를 클라이언트에게 양도 ?
    - 재정의 가능한 메서드를 호출하는 행위
    - 클라이언트가 넘겨준 함수객체를 호출하는 행위
    
 이렇게 외부에서 온 메서드들을 `외계인 메서드` 라고 하며, 이 외계인 메서드들은 무슨 짓을 할 지 예측할 수 없고, 예외나 교착상태, 데이터를 훼손할 수 있다.
 
 
## 예시 코드.
 - (같은 패키지의 ObservableSet 을 보자.) 이 클래스는 옵저버 클래스이고, 함수형 인터페이스 리스트를 공유자원으로 사용하며, 구독/해지/알람에서 동기화를 진행하고 있다.
 - (같은 패키지의 ObserverUser 을 보자.) 여기서는 함수형 인터페이스를 넣고, 사용하는데 1~99 까지 출력하는 루프문에서 23에서 옵저버의 구독을 해제하고있다.
 
 이 사용의 결과는 23에서 ConcurrentModificationException 이다.  
 다음은 23 에서 add 를 하면 실행되는 메서드를 순서대로 적은 것이다.
 
 ```java
    @Override
    public boolean add(E e) {
        boolean added = super.add(e);
        if (added) notifyElementAdded(e);
        return added;
    }

    private void notifyElementAdded(E element) {
        synchronized (observers) {
            for (SetObserver<E> observer : observers) {
                observer.added(this, element);
            }
        }
    }

    public boolean removeObserver(SetObserver<E> observer) {
        synchronized (observers) {
            return observers.remove(observer);
        }
    }
```

 - 두번째 메서드에서 보면 added 메서드가 observers 를 순회하다가,  removeObserver 를 실행하고, removeObserver 는 observers 의 원소를 삭제한다.  
 - 동기화 블럭에서 외부의 메서드를 사용한것이다.  
 - 이 부분에서 synchronized 의 동기화 블럭을 수정하려 했기에 에러가 발생한다.
 
## 다른 예
 - (같은 패키지의 ObserverUserExecutor 을 보자.) ExecutorService 를 이용하여 스레드풀의 스레드에게 작업을 맡겼다.
 - 이 경우에는 예외는 나지 않지만, 교착상태에 빠진다.
 - 백그라운드 스레드가 s.removeObserver 를 호출하면, 관찰자를 잠그려 시도한다.
 - 하지만 이미 메인 스레드가 락을 쥐고 있기 때문에 락을 얻을 수 없다.
 - 동시에 메인 스레드 백그라운드 스레드가 관찰자를 제거하기를 기다리고 있으므로 교착상태에 빠진다.
 
 <br>
 
## 불변식이 꺠지는 경우
 - 위의 두 예는 실패하긴 하지만, 객체의 상태가 유지된 상태이다.
 - 만약 불변식이 꺠진 상태라면?
 - 자바 언어는 재진입을 허용한다.
    - 재진입 : 락의 획득이 호출 단위가 아닌 스레드 단위로 일어나기 떄문에, 이미 락을 획득한 스레드는 같은 락을 얻기 위해 대기할 필요 없다.
    - 자바의 synchronized 블록은 재진입이 허용된다. 한 쓰레드가 synchronized 블록에 진입하며 모니터 객체의 락을 획득하면, 쓰레드는 자신이 획득한 모니터 객체에 대한 다른 synchronized 블록으로도 진입할 수 있다. 
    
 - 락이 재 구실을 하지 못하기에, 교착상태의 데이터를 안전 실패 (데이터 훼손) 으로 이어질 수 있다.

<br>

## 위 두 문제를 해결하는 방법
 - notify 메서드의 동기화가 문제다.
    - 첫번째 예시에서는, notify 메서드의 동기화된 리스트를 순회 도중 수정을 하려 해서 일어난 문제.
    - 두번쨰 예시에서는, notify 메서드의 동기화 블럭이 메인 메서드의 락 떄문에 락을 얻을 수 없음. (같은 객체의 락을 얻으려함)
 - 따라서 notify 의 순회(외계인 메서드의 호출)를 밖으로 빼면 문제를 해결할 수 있다.
    - 동기화 영역 밖에서 호출되는 외계인 메서드를 `열린 호출` 이라 한다.
 - 또는 이 상황에 맞게 설계된 `CopyOnWriteArrayList` 를 사용하면, 동기화 없이 문제를 해결할 수 있다.
 
 <br>
 
## 기본 규칙
 - 동기화 영역에서는 가능한 적은 일을 해야한다.
 - `1 락을 얻고`, `2 공유 데이터를 검사하고`, `3 필요하면 수정하고`, `4 락을 놓는다`.
 
 <br>
 
## 가변 클래스를 작성하는 요령
 1. 동기화를 전혀 하지 말고, 그 클래스를 사용하는 클래스가 외부에서 동기화 하도록 떠넘기기
    - java.util (Vector, HashTable 제외)
 1. 동기화를 내부에서 수행해 스레드 안전 클래스로 만들기
    - 외부에서 객체 전체에 락을 거는 것 보다 동시성을 월등히 개선 할 수 있을때만 사용.
    - java.util.concurrent
    - StringBuffer 는 이 방식으로 설계되었다. 하지만 StringBuffer 는 보통 단일 스레드에서 사용하기에, 이 방법이 성능을 떨어뜨렸다.
        - 그 결과 내부 동기화를 하지 않는 StringBuilder 가 나왔다.