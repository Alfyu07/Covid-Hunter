package entities.humanoid.effect;

import entities.humanoid.Humanoid;
import state.State;

public abstract class Effect {

    private int lifeSpanInUpdates; //life span effect

    public Effect(int lifeSpanInUpdates){
        this.lifeSpanInUpdates = lifeSpanInUpdates;
    }

    public void update(State state, Humanoid humanoid){
        lifeSpanInUpdates--;
    }

    public boolean shouldDelete(){
        return lifeSpanInUpdates <=0;
    }


}
