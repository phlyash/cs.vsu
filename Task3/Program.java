import java.util.Scanner;

public class Program {
    // task 3, 49;
    public static final HorizontalParabola P1 = new HorizontalParabola(-0.25, 1, 5);
    public static final Line L1 = new Line(2.5, 0, -4), L2 = new Line(-2, 0, 2.5);
    public static final Rectangle R1 = new Rectangle(-3, -0.5, 2, 5);
    public static final Circle C1 = new Circle(2, 3, -4);
    public static SimpleColor getColor(double x, double y) {
        if (C1.isPointInsideOfCircle(x, y) && L2.isPointAboveLine(x, y)) return SimpleColor.ORANGE;
        if (C1.isPointInsideOfCircle(x, y) && !L2.isPointAboveLine(x, y)) return SimpleColor.GREEN;
        if (R1.isPointInsideOfRectangle(x, y) && L1.isPointAboveLine(x, y) && P1.isPointLeftOfParabola(x, y)) return SimpleColor.YELLOW;
        if (R1.isPointInsideOfRectangle(x, y) && L1.isPointAboveLine(x, y) && !P1.isPointLeftOfParabola(x, y)) return SimpleColor.GRAY;
        if (R1.isPointInsideOfRectangle(x, y) && !L1.isPointAboveLine(x, y)) return SimpleColor.ORANGE;
        if (P1.isPointLeftOfParabola(x, y) && L1.isPointAboveLine(x, y) && L2.isPointAboveLine(x, y)) return SimpleColor.BLUE;
        if (P1.isPointLeftOfParabola(x, y) && !L1.isPointAboveLine(x, y) && L2.isPointAboveLine(x, y)) return SimpleColor.GREEN;
        if (P1.isPointLeftOfParabola(x, y) && !L1.isPointAboveLine(x, y) && !L2.isPointAboveLine(x, y)) return SimpleColor.GRAY;
        if (P1.isPointLeftOfParabola(x, y)) return SimpleColor.GREEN;
        if (!P1.isPointLeftOfParabola(x, y) && L1.isPointAboveLine(x, y) && L2.isPointAboveLine(x, y)) return SimpleColor.GREEN;
        if (!P1.isPointLeftOfParabola(x, y) && L1.isPointAboveLine(x, y) && !L2.isPointAboveLine(x, y) && y > 5) return SimpleColor.WHITE;
        if (!P1.isPointLeftOfParabola(x, y) && L1.isPointAboveLine(x, y)) return SimpleColor.GRAY;
        return SimpleColor.BLUE;
    }
    public static void main(String[] args) {
        /*
        my tests
         */
        System.out.print("Input X: ");
        double x = input();
        System.out.print("Input Y: ");
        double y = input();
        System.out.printf("(%f, %f) -> %s", x, y, getColor(x, y));
    }
    public static double input() {
        return new Scanner(System.in).nextDouble();
    }
}
