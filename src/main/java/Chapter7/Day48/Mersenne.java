package Chapter7.Day48;

import java.math.BigInteger;
import java.util.stream.Stream;

public class Mersenne {

    public static void main(String[] args) {
        primes().map(p -> BigInteger.TWO.pow(p.intValueExact()).subtract(BigInteger.ONE))
            .filter(mersenne -> mersenne.isProbablePrime(50))
            .limit(50)
            .forEach(System.out::println);
    }

    static Stream<BigInteger> primes() {
        return Stream.iterate(BigInteger.TWO, BigInteger::nextProbablePrime);
    }
}
