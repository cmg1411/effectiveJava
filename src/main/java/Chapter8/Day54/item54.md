#null 이 아닌, 빈 컬렉션이나 배열을 반환하라.

## null 의 반환은 클라이언트, 서버 둘다 신경쓰게 한다.
 - 클라이언트 : 항상 방어코드를 작성해야 한다. 작성하지 않으면 npe 의 위험이 있다.
 - 서버 : 반환하는 쪽에서도 null 을 반환하는 상황을 신경써야해서 더 복잡해진다.
 
## 빈 컨테이너를 생성하는 비용이 비싸다는 반대의견의 반박
 - 성능에 미치는 영향이 아주 미미하다.
 - 빈 컬렉션이나 배열은 굳이 매번 새로 할당해서 반환하지 않아도 다른 방법이 있다.
 
## 다른 방법 ?
 - 보통은 아래와 같이 빈 컬렉션이 필요하면 매번 생성하는 코드를 짠다.
 ```java
public class CheeseContainer {
    
    private List<Cheese> cheeseBox;
    
    public List<Cheese> getCheeseBox() {
        return new ArrayList<>(cheeseBox);
    }
}

class Cheese {
    
    private String name = "까망베르";

    public String getName() {
        return name;
    }
}
```

 - 보통은 이렇게해도 문제 없지만, 새로운 컨테이너 생성이 성능에 부담을 줄 수 있다.
 - 그럴 땐, 매번 똑같은 빈 **불변클래스** 를 반환하면 된다.
    - 불변 객체는 자유롭게 공유해도 안전하다.
    - Collections.emptyList 메서드가 하나의 예다.
        ```java
        public static final List EMPTY_LIST = new EmptyList<>();
        public static final <T> List<T> emptyList() {
            return (List<T>) EMPTY_LIST;
        }
        ```
    - 최적화는 성능 측정 후 성능 개선이 되는지 꼭 확인 후 성능이 좋아질 때만 사용하자.
    
 - 따라서 위의 getCheeseBox() 는 아래와 같이 바뀔 수 있다.
 ```java
 public List<Cheese> getCheeseBox() {
     return cheeseBox.empty() ? Collections.emptyList() : new ArrayList<>(cheeseBox);
 }
 ```

## 컬렉션 말고 배열은 ?
 - null 말고 길이가 0 인 배열을 반환하라.
 ```java
    public Cheese[] getCheeseArray() {
        return cheeseBox.toArray(new Cheese[0]);
    }
 ```
 ```java
 List.toArray() 정적 메서드
 - 위처럼 인자로 배열을 넣으면,
 - cheeseBox 가 들어가기에 인자 배열이 충분히 크면 인자 배열에 넣어 반환한다.
 - 인자 배열이 cheeseBox 를 담지 못하면, 새로운 배열을 생성해서 반환한다.
 - 따라서 위의 경우 인자 배열의 크기가 0 이므로, 빈 컬렉션을 배열로 만들 때에만 인자 배열이 반환된다. 
 ```
 - 물론 이 또한 미리 하나의 상수 빈 배열을 만들어놓고 하나만 반환하도록 할 수 있다.
    - toArray() 에 미리 생성된 배열을 넘기는 것이 성능이 오히러 떨어진다는 연구 결과가 있다.
    - 배열은 이렇게 쓰지 말자. 단순히 성능이 목적이라면.