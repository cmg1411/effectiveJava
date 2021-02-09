package Chapter5.Day32;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class VarargsGeneric {
    public static <T> void go(T...a) {
    }


    public static void dangerous(List<String>... stringLists) {
        List<Integer> intList = List.of(42);
        Object[] objects = stringLists;
        objects[0] = intList; // 힙 오염
        String s = stringLists[0].get(0); // classNotFound
    }

    public static void main(String[] args) {
        go(1, 2, 3, 4);

        List<Integer> list = new ArrayList<>(List.of(1,2,3,4));
        //go(list);

        List<String> list2 = new ArrayList<>(List.of("hi", "my"));
        //dangerous(list2, list2, list2);
    }
}
