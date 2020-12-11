package entities;

import controller.Controller;
import entities.effect.SpeedUp;
import gfx.SpriteLibrary;

public class Player extends MovingEntity {

    public Player(Controller controller, SpriteLibrary spriteLibrary){
        super(controller, spriteLibrary);

        effects.add(new SpeedUp());
    }


}
