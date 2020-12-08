package entities;

import controller.Controller;
import core.Motion;
import core.Direction;
import gfx.AnimationManager;
import gfx.SpriteLibrary;

import java.awt.Image;

public abstract class MovingEntity extends GameObject {

    private Controller controller;
    private Motion motion;
    private AnimationManager animationManager;
    private Direction direction;


    public MovingEntity(Controller controller, SpriteLibrary spriteLibrary) {
        super();
        this.controller = controller;
        this.motion = new Motion(2);
        this.direction = Direction.S;
        this.animationManager = new AnimationManager(spriteLibrary.getUnit("matt"));
    }
    @Override
    public void update(){
        motion.update(controller);
        position.apply(motion);
        manageDirection();
        decideAnimation();
        animationManager.update(direction);
    }

    protected void decideAnimation(){
        if(motion.isMoving()){
            animationManager.playAnimation("walk");
        }else{
            animationManager.playAnimation("stand");
        }
    }

    protected void manageDirection(){
        if(motion.isMoving()){
            this.direction = Direction.fronMotion(motion);
        }
    }

    @Override
    public Image getSprite() {
        return animationManager.getSprite();
    }
}
