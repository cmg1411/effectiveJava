package Chapter7.Day45;

import java.math.BigInteger;
import java.util.stream.Stream;

public class InfiniteStream {

    void primesTest(){
        primes()
            .map(prime -> BigInteger.valueOf(2).pow(prime.intValueExact()).subtract(BigInteger.valueOf(1)))
            .filter(mersenne -> mersenne.isProbablePrime(50))
            .limit(20)
            .forEach(System.out::println);

    }

    static Stream<BigInteger> primes(){
        return Stream.iterate(BigInteger.valueOf(2), BigInteger::nextProbablePrime);
    }

    public static void main(String[] args) {
        new InfiniteStream().primesTest();
    }
}
