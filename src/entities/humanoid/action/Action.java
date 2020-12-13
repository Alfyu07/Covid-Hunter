package entities.humanoid.action;

import entities.humanoid.Humanoid;
import game.state.State;

public abstract class Action {

    protected boolean interruptable;

    public Action() {
        interruptable = true;
    }

    public abstract void update(State state, Humanoid humanoid);
    public abstract boolean isDone();
    public abstract String getAnimationName();

    public boolean isInterruptable() {
        return interruptable;
    }
}
