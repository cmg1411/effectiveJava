package Chapter5.Day31;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StackApp {
    public static void main(String[] args) {
        Stack2<Number> stringStack = new Stack2<>();

        Iterable<Integer> iterable = new ArrayList<>(List.of(1, 2, 3));
        // 제네릭은 불공변이므로 하위 타입이라고 들어가지 않는다.
        // 한정적 와일드카드를 사용하면 된다.
        stringStack.pushAll(iterable);

        Collection<Object> objects = new ArrayList<>();
        // Collection<Object> 는 Collection<Number> 의 하위 타입이 아니다. 따라서 넣을 컬렉션으로 Collection<Object> 를 쓸 수 없다.
        // 한정적 와일드카드 super 를 이용하여 Number 상위 타입 모두로 한정한다.
        stringStack.popAll(objects);
    }
}
