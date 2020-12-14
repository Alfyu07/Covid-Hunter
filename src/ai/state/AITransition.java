package ai.state;

import ai.AICondition;
import entities.NPC;
import state.State;

public class AITransition {
    private String nextState;
    private AICondition condition;


    public AITransition(String nextState, AICondition condition){
        this.nextState = nextState;
        this.condition = condition;
    }

    //wrapper class
    public boolean shouldTransition(State state, NPC currentCharacter){
        return condition.isMet(state,currentCharacter);
    }

    public String getNextState() {
        return nextState;
    }
}
