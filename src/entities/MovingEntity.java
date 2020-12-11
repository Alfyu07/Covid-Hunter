package entities;

import controller.Controller;
import core.Motion;
import core.Direction;
import entities.effect.Effect;
import game.state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public abstract class MovingEntity extends GameObject {

    protected Controller controller;
    protected Motion motion;
    protected AnimationManager animationManager;
    protected Direction direction;

    List<Effect> effects;

    public MovingEntity(Controller controller, SpriteLibrary spriteLibrary) {
        super();
        this.controller = controller;
        this.motion = new Motion(2);
        this.direction = Direction.S;
        this.animationManager = new AnimationManager(spriteLibrary.getUnit("matt"));

        effects = new ArrayList<>();
    }
    @Override
    public void update(State state){
        motion.update(controller);
        animationManager.update(direction);
        effects.forEach(effect -> effect.update(state, this));

        manageDirection();
        decideAnimation();

        position.apply(motion);

        cleanup();
    }

    protected void cleanup(){
        List.copyOf(effects).stream()
        .filter(Effect::shouldDelete)
        .forEach(effects::remove);
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

    public Controller getController(){
        return controller;
    }

    public void multiplySpeed(double multiplier){
        motion.multiply(multiplier);
    }
}
