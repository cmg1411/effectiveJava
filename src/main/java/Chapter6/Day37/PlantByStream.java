package Chapter6.Day37;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class PlantByStream {
    enum LifeCycle { ANNUAL, PERENNIAR, BIENNIAL }

    final String name;
    final LifeCycle lifeCycle;

    public PlantByStream(String name, LifeCycle lifeCycle) {
        this.name = name;
        this.lifeCycle = lifeCycle;
    }

    @Override
    public String toString() {
        return name;
    }

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
}
