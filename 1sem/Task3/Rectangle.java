public class Rectangle {
    /*
    {|x - x0| = m / 2;
    {|y - y0| = n / 2;
    a, b - центр прямоугольника
    m, n - стороны
     */
    double x0, y0, m, n;
    public Rectangle(double x0, double y0, double m, double n) {
        this.x0 = x0;
        this.y0 = y0;
        this.m = m;
        this.n = n;
    }
    public boolean isPointInsideOfRectangle(double x, double y) {
        return Math.abs(x - x0) <= m / 2 && Math.abs(y - y0) <= n / 2;
    }
}
