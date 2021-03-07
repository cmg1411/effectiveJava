package Chapter8.Day49;

import java.util.Objects;

public class CheckFromIndex {

    public static void main(String[] args) {

        Objects.checkFromIndexSize(1, 4, 5);
        Objects.checkIndex(1, 3);
    }
}
