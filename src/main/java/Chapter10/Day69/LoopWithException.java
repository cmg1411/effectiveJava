package Chapter10.Day69;

import Chapter7.Day46.Collectors;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Collector;

public class LoopWithException {

    public static void main(String[] args) throws InterruptedException {

        Mountain[] range = new Mountain[1000000];
        for (int i = 0; i < range.length; i++) {
            range[i] = new Mountain();
        }

        long start = System.currentTimeMillis();

//        try {
//            int i = 0;
//            while (true) {
//                range[i++].climb();
//            }
//        } catch (Exception e) {
//        }

        for (Mountain mountain : range) {
            mountain.climb();
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    static class Mountain {

        public void climb() throws InterruptedException {
            System.out.println("한걸음~");
        }
    }
}