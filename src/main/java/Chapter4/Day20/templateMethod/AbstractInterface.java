package Chapter4.Day20.templateMethod;

/**
 * 추상 골격 구현 클래스.
 * 인터페이스는 인스턴스 필드를 가질 수 없다.
 * 인터페이스는 public 이 아닌 정적 멤버를 가질 수 없다.
 *
 * 인터페이스를 구현하고, 나머지 기능들을 정의하는 추상 골격 구현 클래스를 만들고 이를 확장한다.
 * 이를 템플릿 메서드 패턴이라 한다.
 *
 * <골격 구현을 작성하는 방법>
 *     1. 인터페이스를 잘 살펴 다른 메서드들의 구현에 사용되는 기반 메서드들을 선정. 이 기반 메서드들은 추상 메서드가 된다.
 *     2. 기반 메서드들을 사용해 직접 구현할 수 있는 메서드들을 모두 default 메서드로 제공.
 *     3. 만약 인터페이스의 메서드 모두가 기반 메서드와 디폴트 메서드가 된다면 골격 구현 클래스를 별도로 만들 필요는 없음.
 *     4. 기반 메서드와 default 메서드로 인터페이스가 완성되고, 나머지 남은 메서드 구현을 추상골격 클래스에 구현한다.
 */
public abstract class AbstractInterface implements Interface {
    private int literal = 99;

    public int getLiteral() {
        return literal;
    }
}
