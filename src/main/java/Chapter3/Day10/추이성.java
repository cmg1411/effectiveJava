package Chapter3.Day10;

import java.awt.*;

/**
 * x.equals(y) true
 * y.equals(z) true 이면
 * x.equals(z) true
 */

/**
 * 구체 클래스를 확장해 새로운 값을 추가하면서 equals 규약을 만족시키는 방법은 존재하지 않는다 !
 *
 * 리플렉션 getClass 를 사용해도 리스코프 치환 법칙을 어긴다.
 * 리스코프 치환 법칙 : 상위 클래스를 상속받은 하위 클래스도 여전히 상위 클래스의 일부이므로 어디서든 상위 클래스로써 활용될 수 있어야 한다.
 *
 * 이를 완전히 파훼할수 있는 방법은 없지만
 * 구현 방식을 조금 달리하여 컴포지션 방식을 이용하면 우회할 수 있다.
 * 컴포지션 : 값을 추가하는 상속클래스를 만들지 말고, ColorPoint 의 인스턴스 변수로 Point 를 private 로 두고
 * Point 를 반환하는 메소드를 public 으로 추가하는 방식
 *
 * 예외 )) 상위 클래스가 추상 클래스라면 하위 클래스에서 값을 추가하여도 equals 메서드를 규약을 지키면서 구현할 수 있다.
 * 추상 클래스는 인스턴스 생성이 불가능하므로 비교할 대상을 아예 생성할 수 없어서 가능하다.
 */
public class 추이성 {
    public static void main(String[] args) {
//        Point p = new Point(1,2);
//        ColorPoint cp = new ColorPoint(1, 2, Color.WHITE);
//
//        / 대칭성 위반
//        System.out.println(p.equals(cp));
//        System.out.println(cp.equals(p));
//
        ColorPoint p1 = new ColorPoint(1, 2, Color.WHITE);
        Point p2 = new Point(1, 2);
        ColorPoint p3 = new ColorPoint(1, 2, Color.BLACK);

        // 추이성을 위반
        System.out.println(p1.equals(p2));
        System.out.println(p2.equals(p3));
        System.out.println(p1.equals(p3));
    }
}
