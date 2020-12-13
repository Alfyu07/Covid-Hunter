package entities;

import controller.Controller;
import gfx.SpriteLibrary;

public class Player extends MovingEntity {

    public Player(Controller controller, SpriteLibrary spriteLibrary){
        super(controller, spriteLibrary);

    }

    @Override
    protected void handleCollision(GameObject other) {
        if(other instanceof NPC){
            NPC rec = (NPC) other;
            rec.clearEffects();
        }
    }


}
