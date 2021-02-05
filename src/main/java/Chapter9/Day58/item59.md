# 전통적인 for 문 보다는 for-each 문을 사용하라.

## 향상된 for 문 = for-each 문
 - 반복자와 인덱스 변수를 사용하지 않아 깔끔하고 오류날 일이 없다.
 - 어떤 컨테이너를 다루는지 상관없이 하나의 관용구로 사용할 수 있다.
 - 반복 대상이 배열이던 어떤 컬렉션이던 반복속도는 일정하다.
 
## 중첩 순회에서 for-each 문의 이점은 훨씬 커진다.
```java
    enum Suit { CLUB, DIAMOND, HEART, SPADE };
    enum Rank { ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING }

    Collection<Suit> suits = Arrays.asList(Suit.values());
    Collection<Rank> ranks = Arrays.asList(Rank.values());

    for (Suit suit : suits)
        for (Rank rank : ranks)
            deck.add(new Card(suit, rank));
```


## for-each 를 사용할 수 없는 세가지 경우
 1. 파괴적인 필터링
    1. 컬렉션을 순회하면서 선택된 원소를 제거해야 할 때.
    1. 반복자의 remove() 를 호출해야하므로 반복자를 사용해야한다.
    ```java
        Collection<String> list = new ArrayList<>(List.of("가", "나", "다"));

        list.removeIf(val -> val.equals("나"));
        
        for (Iterator<String> i = list.iterator(); i.hasNext(); ) {
            if (i.next().equals("다")) {
                i.remove();
            }
        }
    ```
 1. 변형
    1. 리스트나 배열을 순회하면서 원소의 값 일부 혹은 전체를 변경해야한다면 인덱스가 필요.
 1. 병렬 반복
    1. 인덱스와 반복자 변수를 사용하여 엄격하고 명시적으로 제어해야함.
    
## for-each 문은 Iterable 을 구현한 모든 객체를 순회할 수 있다.
 - 뭐, 사용자를 위해 Iterable 을 구현하는 것도 고려해보자.