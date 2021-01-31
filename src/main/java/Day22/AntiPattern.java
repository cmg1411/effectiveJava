package Day22;

/**
 * 인터페이스는 자신을 구현한 클래스의 인스턴스를 참조할 수 있는 타입 역할만 해야 한다.
 *
 * 공통상수 사용을 위한 인터페이스는 안티패턴이다.
 * 클래스 내부에서 사용하는 상수를 인터페이스에서 공개하므로 내부구현을 외부로 노출하는 상황이다.
 *
 */
public interface AntiPattern {
    static final double PI = 3.141592;
    static final String GREETING = "Hello";
}
