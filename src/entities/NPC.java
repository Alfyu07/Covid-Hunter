package entities;

import ai.AIManager;
import controller.EntityController;
import core.Motion;
import entities.humanoid.Humanoid;
import state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;


public class NPC extends Humanoid {
    private AIManager aiManager;

    public NPC(EntityController entityController, SpriteLibrary spriteLibrary) {
        super(entityController, spriteLibrary);
        animationManager = new AnimationManager(spriteLibrary.getSpriteSet("dave"));
        aiManager = new AIManager();
        this.motion = new Motion(Math.random() + 1);
    }

    @Override
    public void update(State state) {
        super.update(state);
        aiManager.update(state, this);
    }

    @Override
    protected void handleCollision(GameObject other) {
        if(other instanceof Player) {
            motion.stop(willCollideX(other), willCollideY(other));
        }
    }
}
