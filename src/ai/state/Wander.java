package ai.state;

import controller.NPCController;
import core.Position;
import display.AITransition;
import entities.NPC;
import game.state.State;

import java.util.ArrayList;
import java.util.List;

public class Wander extends AIState{

    private List<Position> targets;

    public Wander(){
        targets = new ArrayList<>();
    }

    @Override
    protected AITransition initializeTransition() {
        return new AITransition("stand", (state, currentCharacter) -> arrived(currentCharacter));
    }

    @Override
    public void update(State state, NPC currentCharacter) {
        if(targets.isEmpty()){
            targets.add(state.getRandomPotition());
        }

        NPCController controller = (NPCController) currentCharacter.getController();
        controller.moveToTarget(targets.get(0), currentCharacter.getPosition());
        
        if(arrived(currentCharacter)){
            controller.stop();
        }
    
    }

    private boolean arrived(NPC currentCharacter) {
        return currentCharacter.getPosition().isRangeOf(targets.get(0));
    }
}
