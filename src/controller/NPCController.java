package controller;

import core.Position;

public class NPCController implements Controller {

    private boolean up;
    private boolean right;
    private boolean down;
    private boolean left;

    @Override
    public boolean isRequestingUp() {
        return up;
    }

    @Override
    public boolean isRequestingDown() {
        return down;
    }

    @Override
    public boolean isRequestingLeft() {
        return left;
    }

    @Override
    public boolean isRequestingRight() {
        return right;
    }

    public void moveToTarget(Position target, Position current) {
        double deltaX = target.getX() - current.getX();
        double deltaY = target.getY() - current.getY();

        up = deltaY < 0 && Math.abs(deltaY) > Position.RANGE;
        right = deltaX > 0 && Math.abs(deltaX) > Position.RANGE;
        down = deltaY > 0 && Math.abs(deltaY) > Position.RANGE;
        left = deltaX < 0 && Math.abs(deltaX) > Position.RANGE;
    }

    public void stop() {
        up = right = left = down = false;
    }
}
