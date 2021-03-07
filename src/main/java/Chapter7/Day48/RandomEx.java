package Chapter7.Day48;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.SplittableRandom;
import java.util.concurrent.ThreadLocalRandom;

public class RandomEx {

    public static void main(String[] args) {
        random1(); // ThreadLocalRandom 35
        random2(); // SplittableRandom 6
    }

    static void random1(){
        long start = System.currentTimeMillis();
        ThreadLocalRandom.current()
            .doubles()
            .parallel()
            .limit(100)
            .sorted()
            .forEach((val) -> System.out.println(Thread.currentThread().getName()));
        long end = System.currentTimeMillis();
        System.out.println("===============");
        System.out.println(end-start);
        System.out.println("===============");
    }


    static void random2(){
        SplittableRandom splittableRandom = new SplittableRandom();
        long start = System.currentTimeMillis();
        splittableRandom.doubles()
            .parallel()
            .limit(100)
            .sorted()
            .forEach((val) -> System.out.println(Thread.currentThread().getName()));
        long end = System.currentTimeMillis();
        System.out.println("===============");
        System.out.println(end-start);
        System.out.println("===============");
    }
}
