package Chapter4.Day23;

public class Main {
    public static void main(String[] args) {
        FigureInterface circle = new Circle(1);
        FigureInterface rectangle = new Rectangle(3, 4);

        System.out.println(circle.area());
        System.out.println(rectangle.area());
    }
}
