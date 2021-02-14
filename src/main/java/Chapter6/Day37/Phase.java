package Chapter6.Day37;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public enum Phase {
    SOLID, LIQUID, GAS;

    public enum Transition {
        MELT(SOLID, LIQUID),
        FREEZE(LIQUID, SOLID),
        BOIL(LIQUID, GAS),
        CONDENSE(GAS, LIQUID),
        SUBLIME(SOLID, GAS),
        DEPOSIT(GAS, SOLID);

        private final Phase from;
        private final Phase to;

        Transition(Phase from, Phase to) {
            this.from = from;
            this.to = to;
        }

        private static final Map<Phase, Map<Phase, Transition>> m
            = Stream.of(Transition.values())
            .collect(groupingBy(t -> t.from, () -> new EnumMap<>(Phase.class), toMap(t -> t.to, t -> t, (x, y) -> y, () -> new EnumMap<>(Phase.class))));
            // 전이들을 from 으로 그루핑을 하려 하는데, 반환 타입은 Phase.class 를 한정적 타입 토큰인 key 로 사용하는 (제네릭은 런타입에 정보가 소거되기 때문에) EnumMap 으로 한다. value는 Map으로 만들 건데,
            // 그 맵은 t.to 로 그루핑을 하고, 대응되는 value 는 t -> t 결국 t (Transition) 으로 한다.
            // 그런데 만약 Key (이후 상태)가 같으면 기존 value 를 사용하지 않고 새로운 value 로 갱신. (x, y) -> y 은 스트림을 돌리는 클래스를 병합.
            // 그리고 만들 맵을 EnumMap 으로 구성

        public static Transition from(Phase from, Phase to) {
            return m.get(from).get(to);
        }
    }
}
