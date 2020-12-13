package entities;

import controller.EntityController;
import gfx.SpriteLibrary;

public class Player extends MovingEntity {

    public Player(EntityController entityController, SpriteLibrary spriteLibrary){
        super(entityController, spriteLibrary);

    }

    @Override
    protected void handleCollision(GameObject other) {
        if(other instanceof NPC){
            NPC rec = (NPC) other;
            rec.clearEffects();
        }
    }


}
