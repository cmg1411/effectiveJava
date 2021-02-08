package Chapter4.Day20.templateMethod;

public interface Interface {
    // 기반 메서드
    int plus(int a, int b);

    // 직접 구현할 수 있는 메서드
    default int minus(int a, int b) {
        return a - b;
    }

    // 기반 메서드를 이용하여 구현할 수 있는 메서드
    default int plusAndMinus(int a, int b) {
        return plus(a, b) + minus(a, b);
    }
}
