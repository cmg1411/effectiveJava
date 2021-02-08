package Chapter4.Day24;

import java.util.function.BiFunction;

public class Calculator {
    public enum Operation1 {
        PLUS(Integer::sum),
        MINUS((x, y) -> x - y);

        private BiFunction<Integer, Integer, Integer> calculate;

        Operation1(BiFunction<Integer, Integer, Integer> calculate) {
            this.calculate = calculate;
        }

        public BiFunction<Integer, Integer, Integer> getFunction() {
            return calculate;
        }
    }


    public int Sum(int x, int y) {
        return Operation1.PLUS.getFunction().apply(x, y);
    }
}
