package Day4;

/**
 * static 메서드만 모여있는 유틸성 클래스는 인스턴스를 생성하지 않는다.
 *
 * private 생성자로 객체를 생성하지 못하게 할 수 있다.
 * 생성자가 있다는 것은 객체를 만든다는 것으로 보통 보기에 직관적으로 혼란이 올 수 있다.
 * 때문에 주석을 달아 놓는 것이 좋다.
 *
 * abstract class 로 만들면 인스턴스를 생성할 수 있긴 하지만 그 인스턴스로 메서드를 실행할 수 없기에
 * Spring 개발자들이 쓰는 패턴이기도 하다. 하지만 객체를 만드는것 자체는 가능하기에 책에서는 private 생성자를 추천한다.
 */

public class PrivateConstructor {
    // 유틸 클래스로 객체생성을 방지
    private PrivateConstructor() {
    }

    public static int countCharacterUtil(String name) {
        return name.length();
    }
}
