package Chapter7.Day48;

import java.math.BigInteger;
import java.util.stream.LongStream;

public class Performance {

    public static void main(String[] args) {
        //pi(10000000); // 18초
        piParallel(10000000); // 3초
    }

    static long pi(long n) {
        return LongStream.rangeClosed(2, n)
            .mapToObj(BigInteger::valueOf)
            .filter(i -> i.isProbablePrime(50))
            .count();
    }

    static long piParallel(long n) {
        return LongStream.rangeClosed(2, n)
            .parallel()
            .mapToObj(BigInteger::valueOf)
            .filter(i -> i.isProbablePrime(50))
            .count();
    }
}
