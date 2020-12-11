package entities.action;

import entities.MovingEntity;
import game.GameLoop;
import game.state.State;

public class Cough extends Action{

    private int lifeSpan; //action lifespan

    public Cough(){
        lifeSpan = GameLoop.UPDATES_PER_SECOND;
    }
    @Override
    public void update(State state, MovingEntity entity) {
        lifeSpan--;
    }

    @Override
    public boolean isDone() {
        return lifeSpan <= 0;
    }

    @Override
    public String getAnimationName() {
        return "cough";
    }
}
