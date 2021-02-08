package Chapter3.Day10;

import java.util.ArrayList;
import java.util.List;

/**
 * x.equals(x) -> true
 */
public class 반사성 {
    public static void main(String[] args) {
        Point p1 = new Point(1, 2);
        System.out.println(p1.equals(p1));

        List<Point> pointList = new ArrayList<>();
        pointList.add(p1);
        System.out.println(pointList.contains(p1));
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
