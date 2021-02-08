package Chapter2.Day3;

public enum EnumSingleton {
    INSTANCE;
    // enum 의 생성자는 기본적으로 private 이고, private 만 허용한다.

    public int add(int a, int b) {
        return a + b;
    }
}
