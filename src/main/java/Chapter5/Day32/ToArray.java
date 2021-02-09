package Chapter5.Day32;

import java.util.concurrent.ThreadLocalRandom;

public class ToArray {

    /**
     * 이 메서드가 반환하는 배열의 타입은 이 메서드에 인수를 넘기는 컴파일타임에 결정.
     * 하지만 그 시점에는 컴파일러에게 충분한 정보가 없어서 타입을 잘못 판단할 수 있다.
     *
     * 따라서 자신의 varargs 배열을 그대로 반환하면 힙오염을 호출한 쪽에다가도 전이될 수 있다.
     */
    static <T> T[] toArray(T... args) {
        return args;
    }

    static <T> T[] pickTwo(T a, T b, T c) {
        switch (ThreadLocalRandom.current().nextInt(3)) {
            case 0: return toArray(a, b);
            case 1: return toArray(b, c);
            case 2: return toArray(a, c);
        }

        throw new AssertionError();
    }



}
