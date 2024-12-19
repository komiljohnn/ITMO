package check;


public class Checker {

    public boolean hit(int x, double y, int r) {
        return inRect(x, y, r) || inTriangle(x, y, r) || inCircle(x, y, r);
    }

    private boolean inRect(int x, double y, int r) {
        return y <= r / 2 && x <= r && x >= 0 && y >= 0;
    }

    private boolean inTriangle(int x, double y, int r) {
        return x >= 0 && y <= 0 && -y + x - r < 0;
    }

    private boolean inCircle(int x, double y, int r) {
        return x <= 0 && y <= 0 && (x * x + y * y - r) <= 0;
    }
}

