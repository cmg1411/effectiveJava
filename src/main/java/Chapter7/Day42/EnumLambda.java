package Chapter7.Day42;

import java.util.function.DoubleBinaryOperator;

public enum  EnumLambda {
    PLUS("+", (x, y) -> x + y),
    MINUS("-", (x, y) -> x - y),
    TIMES("*", (x, y) -> x * y),
    DIVIDE("/", (x, y) -> x / y);

    private final String symbol;
    private final DoubleBinaryOperator op;

    EnumLambda(String symbol, DoubleBinaryOperator op) {
        this.symbol = symbol;
        this.op = op;
    }

    public double apply(double x, double y) {
        return op.applyAsDouble(x, y);
    }
}

class Test {
    public static void main(String[] args) {
        System.out.println(EnumLambda.PLUS.apply(5, 10));
    }
}