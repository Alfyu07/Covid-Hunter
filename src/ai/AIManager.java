package ai;

import ai.state.AIState;
import ai.state.Stand;
import entities.NPC;
import game.state.State;
import jdk.jfr.TransitionTo;

/*
* jadi otak dari AI yg dibuat.
* */
public class AIManager {

    private AIState currentAIState;

    public AIManager(){
        transitionTo("stand");
    }

    public void update(State state, NPC currentCharacter){
        currentAIState.update(state, currentCharacter);

        if(currentAIState.shouldTransition(state, currentCharacter)){
            transitionTo(currentAIState.getNextState());
        }
    }

    private void transitionTo(String nextState) {
        System.out.println("Transitioning to " + nextState);
        switch (nextState){
            case "stand":
            default:
                currentAIState = new Stand();
        }
    }


}
