package ai.state;

import entities.NPC;
import game.state.State;

public abstract class AIState {

    private AITransition transition;

    public AIState() {
        this.transition = initializeTransition();
    }

    protected abstract AITransition initializeTransition();
    public abstract void update(State state, NPC currentCharacter);

    //wrapper function
    public boolean shouldTransition(State state, NPC currentCharacter){
        return transition.shouldTransition(state, currentCharacter);
    }

    public String getNextState(){
        return transition.getNextState();
    }
}
