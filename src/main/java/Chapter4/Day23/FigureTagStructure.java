package Chapter4.Day23;

/**
 * 계층구조를 사용한 더러운 클래스
 *
 * 사각형을 사용하면 원이 쓸데없는 필드들이 되고,
 * 원을 사용하면 사각형이 쓸데없는 필드들이 된다. (final 필드는 생성자에서 초기화시켜줘야 하기에 쓸데없는 코드가 늘어난다.)
 *
 * switch 도 더럽다.
 */
public class FigureTagStructure {
    enum Shape {RECTANGLE, CIRCLE}

    final Shape shape;

    double length;
    double width;

    double radius;

    // 원 생성을 위한 생성자
    public FigureTagStructure(double radius) {
        shape = Shape.CIRCLE;
        this.radius = radius;
    }

    // 사각형 생성을 위한 생성
    public FigureTagStructure(double length, double width) {
        shape = Shape.RECTANGLE;
        this.length = length;
        this.width = width;
    }

    double area() {
        switch (shape) {
            case CIRCLE:
                return radius * radius * Math.PI;
            case RECTANGLE:
                return width * length;
            default:
                throw new AssertionError(shape);
        }
    }
}
