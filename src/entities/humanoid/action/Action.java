package entities.humanoid.action;

import entities.humanoid.Humanoid;
import game.state.State;

public abstract class Action {
    public abstract void update(State state, Humanoid humanoid);
    public abstract boolean isDone();
    public abstract String getAnimationName();


}
