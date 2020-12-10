package ai.state;

import display.AITransition;
import entities.NPC;
import game.state.State;

public class Stand extends AIState {

    private int updatesAlive; //how many updates that stands for this state
    @Override
    protected AITransition initializeTransition() {
        return new AITransition("stand", (state, currentCharacter) -> updatesAlive >= state.getTime().getUpdateSFromSeconds(3));
    }

    @Override
    public void update(State state, NPC currentCharacter) {
        updatesAlive++;
    }
}
