# ordinal 인덱싱 대신 EnumMap 을 사용하라.

## EnumSet 의 장점
 1. 더 짧고, 명료하고, 안전하고, 성능도 배열과 비등
 1. 안전하지 않은 형변환을 쓰지 않음
 1. 맵의 키인 열거타입이 출력을 제공해서 레이블을 달지 않아도 됨
 1. 인덱스를 계산하는 과정에서 오류날 일이 없음
 1. 내부에서 배열을 사용하는데, 구현을 내부로 숨겨서 Map 의 타입 안전성과 배열의 성능 모두 얻음.
 
EnumSet 을 생성할 때 생성자에 키 타입의 Class 객체를 넘겨준다.  
이 객체는 한정적 타입 토큰이라 하며, 런타임 제네릭 타입 정보를 제공한다.  

### stream
```java
    public static void main(String[] args) {
        PlantByStream[] garden = {new PlantByStream("san", LifeCycle.ANNUAL), new PlantByStream("se", LifeCycle.BIENNIAL)};

        /**
         * stream 의 최종연산 collect()
         * 스트림의 아이템들을 List 또는 Set 자료형으로 변환
         * 스트림의 아이템들을 joining
         * 스트림의 아이템들을 Sorting하여 가장 큰 객체 리턴
         * 스트림의 아이템들의 평균 값을 리턴
         *
         * Collectors.groupingBy : Collector<T, ?, Map<K, List<T>>> 반환, groupBy 연산을 실행, Map 반환
         *  Collectors<T, A, R>
         *  * @param <T> the type of input elements to the reduction operation
         *  * @param <A> the mutable accumulation type of the reduction operation (often
         *  *            hidden as an implementation detail)
         *  * @param <R> the result type of the reduction operation
         */
        Map<LifeCycle, List<PlantByStream>> m = Arrays.stream(garden).collect(groupingBy(p -> p.lifeCycle));
        System.out.println(m);

        // 맵 구현체를 두번째 매개변수 Supplier<M> mapFactory 에 지정할 수 있다. 람다식으로.
        // 세번째는 downStream 으로 value 값을 어떤 구현체를 쓸 것인지 명시 가능하다.
        Map<LifeCycle, Set<PlantByStream>> m2 = Arrays.stream(garden)
                                                        .collect(groupingBy(p -> p.lifeCycle, () -> new EnumMap<>(LifeCycle.class), toSet()));
        System.out.println(m2);

        Map<LifeCycle, List<PlantByStream>> m3 = Arrays.stream(garden)
            .collect(groupingBy(p -> p.lifeCycle, () -> new EnumMap<>(LifeCycle.class), toList()));
        System.out.println(m3);
    }
```

 - stream 을 이용하여 그루핍을 할 수 있다.
 - EnumMap 만 사용했을 때와는 다르게, 원소가 없으면 Map 을 생성하지 않는다.
 
 
 
## 두 enum 의 값을 매핑할 때에도 EnumMap 을 중첩으로 사용하면 된다.

Phase 예제를 보면, 이전상태에서 '이후 상태에서 전이로의 맵'에 대응시키는 맵 을 초기화한다.  
Map<from, Map<to, transition>> 형태로, from 에 대응되는 map 을 get 으로 찾고, to 에 대응되는 transition 을 get 으로 두번에 거쳐 찾는다.  

이렇게 하면 새로운 상태가 생겨도, Phase, Transition 의 상수들만 추가하면 되며, 아래 초기화 식들은 건드릴 필요가 없어서 좋다.


## 결론

배열의 인덱스를 얻기 위해 ordinal 을 쓰는 것은 일반적으로 좋지 않으니 EnumMap 을 사용하라.  
다차원 관계는 ```Map<key, Map<key, Map<key, value>>>``` 형태를 사용하면 좋다