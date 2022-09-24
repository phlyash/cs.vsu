public class Circle {
    // x ** 2 + y ** 2 = r ** 2;
    double r, x0, y0;
    public Circle(int r, int x0, int y0) {
        this.r = r;
        this.x0 = x0;
        this.y0 = y0;
    }
    public boolean isPointInsideOfCircle(double x, double y) {
        return Math.pow((x - x0), 2) + Math.pow((y - y0), 2) <= r * r;
    }
}
