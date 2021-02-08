package Chapter5.Day28;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GenericChooser<T> {
    private final List<T> choiceList;

    public GenericChooser(Collection<T> choiceArray) {
        this.choiceList = new ArrayList<>(choiceArray); // toArray 는 Object[] 을 반환, T[] 로 형변환
    }

    public Object choose() {
        Random rnd = ThreadLocalRandom.current();
        return choiceList.get(rnd.nextInt(choiceList.size()));
    }

    public static void main(String[] args) {
        Collection<Integer> collection = new ArrayList<>(Arrays.asList(1, 2, 3)); // integer 컬렉션을 집어넣음
        // GenericChooser<String> genericChooser = new GenericChooser<String>(collection); // 컴파일타임에서 오류를 잡음
    }
}
