package Chapter8.Day62;

public class ThreadLocalEx {
    private ThreadLocalEx() {}

//    // 현 스레드의 값을 키로 구분해 저장한다.
//    public static void set(String key, Object value) {};
//
//    // 키가 가리키는 현 스레드의 값을 반환한다.
//    public static Object get(String key) {
//        return null;
//    }

    public static class Key {
        Key() {
        }
    }

    public static void set(Key key, Object value) {
    }

    public static Key get(Key key) {
        return new Key();
    }

    // 위조가 불가능한 고유 키를 생성한다.
    public static Key getKey() {
        return new Key();
    }
}
