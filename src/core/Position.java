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
        x+=vector.getX();
        y+= vector.getY();
    }

    public boolean isRangeOf(Position position) {
        return Math.abs(x - position.getX()) < Position.RANGE && Math.abs(y - position.getY()) < Position.RANGE;
    }

    public void applyX(Motion motion) {
        x += motion.getVector().getX();
    }

    public void applyY(Motion motion) {
        x += motion.getVector().getY();
    }

    public void add(Position position) {
        x+=position.getX();
        y+= position.getY();
    }

    public void subtract(Position position) {
        x -= position.getX();
        y -= position.getY();
    }

    public double distanceTo(Position other){
        double deltaX = this.getX() - other.getX();
        double deltaY = this.getY() - other.getY();

        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
