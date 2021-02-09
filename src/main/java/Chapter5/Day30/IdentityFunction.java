package Chapter5.Day30;

import java.util.function.UnaryOperator;

public class IdentityFunction<T> {
    public static final UnaryOperator IDENTITY_FN = (t) -> t;

    /**
     * 들어온 대로 내보내는 항등함수이므로,
     * T가 어떤 타입이든 UnaryOperator<T>로 타입변환해도 안전하다.
     */
    @SuppressWarnings("unchecked")
    public static <T> UnaryOperator<T> identityFunction() {
        return (UnaryOperator<T>) IDENTITY_FN;
    }

    public static void main(String[] args) {
        String[] strings = {"삼베", "나일론", "비단"};
        UnaryOperator<String> sameString = identityFunction();
        for (String string : strings) {
            System.out.println(sameString.apply(string));
        }

        Number[] numbers = {1, 2.0, 3L};
        UnaryOperator<Number> sameNumber = identityFunction();
        for (Number number : numbers) {
            System.out.println(sameNumber.apply(number));
        }
    }
}
