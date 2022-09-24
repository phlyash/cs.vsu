public class Line {
    // y = k(x - x0) + b;
    double k, b, x0;
    public Line(double k, double b, double x0) {
        this.k = k;
        this.b = b;
        this.x0 = x0;
    }

    public boolean isPointAboveLine(double x, double y) {
        return y >= k * (x - x0) + b;
    }
}
