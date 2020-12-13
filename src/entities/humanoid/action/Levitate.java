package entities.humanoid.action;

import entities.humanoid.Humanoid;
import game.state.State;

public class Levitate extends Action {

    public Levitate() {
        interruptable = false;
    }

    @Override
    public void update(State state, Humanoid humanoid) {

    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public String getAnimationName() {
        return "levitate";
    }
}
