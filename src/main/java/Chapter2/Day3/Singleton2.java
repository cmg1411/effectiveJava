package Chapter2.Day3;

public class Singleton2 {
    private static final Singleton2 instance = new Singleton2();

    private int count = 0;

    private Singleton2() {
        count++;
        if (count != 1) {
            throw new IllegalArgumentException();
        }
    }

    public static Singleton2 getInstance() {
        return instance;
        // return new Singleton2(); // 싱글톤이 아니게 하려면 이렇게만 바꾸면 된다.
    }
}
