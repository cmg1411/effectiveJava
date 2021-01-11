package Day3;

public class Singleton {
    // static final 로 한번만 생성되게 만든다.
    public static final Singleton INSTANCE = new Singleton();

    // public 이나 protected 생성자가 없으므로 인스턴스가 하나임을 보장
    private Singleton() {
    }
}