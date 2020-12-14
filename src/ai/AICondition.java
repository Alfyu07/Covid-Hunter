package ai;

import entities.NPC;
import state.State;

public interface AICondition {
    boolean isMet(State state, NPC currentCharacter);
}
