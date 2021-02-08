package Chapter3.Day10;

import java.awt.*;

public class ColorPoint extends Point {
    private final Color color;

    public ColorPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

    /**
     * 상위 클래스 Point 의 equals 는 하위 클래스든 Point 든 x, y 만 비교하는 반면,
     *
     * 이 클래스의 equals 는 ColorPoint 의 객체가 아니면 무조건 False 반환
     *
     * 대칭성 위반
     */
//    @Override
//    public boolean equals(Object o) {
//        if (!(o instanceof ColorPoint)) {
//            return false;
//        }
//        return super.equals(o) && ((ColorPoint) o).color == color;
//    }

    /**
     * 대칭성 위반을 고치기 위해
     */
    @Override
    public boolean equals(Object o) {
        // 수퍼클래스 Point 가 아니거나 Point 를 상속하지 않은것만 false
        if (!(o instanceof Point)) {
            return false;
        }
        // 위에서 걸러진 것 중 이 조건에 맞는 것은 Point 객체인 것들
        // Point 객체라면 Point 의 equals 사용
        if (!(o instanceof ColorPoint)) {
            return o.equals(this);
        }

        return super.equals(o) && ((ColorPoint) o).color == color;
    }
}
