package Chapter5.Day28;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ArrayChooser {
    private final Object[] choiceArray;

    public ArrayChooser(Collection choiceArray) {
        this.choiceArray = choiceArray.toArray(); // toArray 는 Object[] 을 반환
    }

    public Object choose() {
        Random rnd = ThreadLocalRandom.current();
        return choiceArray[rnd.nextInt(choiceArray.length)];
    }

    public static void main(String[] args) {
        Collection<Integer> collection = new ArrayList<>(Arrays.asList(1, 2, 3)); // integer 컬렉션을 집어넣음
        ArrayChooser arrayChooser = new ArrayChooser(collection); // 컴파일타임에서 오류를 잡지 못함

        /**
         * 1. Object 형을 String 으로 받을 수 없으니 형변환이 필요.
         * 2. 하지만 들어 있는 값이 integer 다.
         * 3. 따라서 런타임에 오류가 발생.
         * 4. 제네릭으로 애초에 integer 만 받게 해야 한다.
         */
        String s = (String) arrayChooser.choose();
    }
}
