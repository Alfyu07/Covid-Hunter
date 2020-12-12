package core;

public class Position {
    public static int RANGE = 5;

    private double x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Position copyOf(Position position) {
        return new Position(position.getX(), position.getY());
    }

    public int intX(){
        return (int) Math.round(x);
    }
    public int intY(){
        return (int) Math.round(y);
    }
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void apply(Motion motion) {
        Vector2D vector = motion.getVector();
        x += vector.getX();
        y += vector.getY ();
    }

    public boolean isRangeOf(Position position) {
        return Math.abs(x - position.getX()) < Position.RANGE && Math.abs(y - position.getY()) < Position.RANGE;
    }
}
