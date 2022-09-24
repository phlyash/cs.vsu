public class HorizontalParabola {
    // x = k * (y - y0) ** 2 + x0;
    double k, x0, y0;
    public HorizontalParabola(double k, double x0, double y0) {
        this.k = k;
        this.x0 = x0;
        this.y0 = y0;
    }
    public boolean isPointLeftOfParabola(double x, double y) {
        return x <= k * Math.pow(y - y0, 2) + x0;
    }
}
