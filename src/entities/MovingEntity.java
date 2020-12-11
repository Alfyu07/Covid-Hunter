package entities;

import controller.Controller;
import core.Motion;
import core.Direction;
import entities.action.Action;
import entities.action.Cough;
import entities.effect.Effect;
import entities.effect.Sick;
import game.state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class MovingEntity extends GameObject {

    protected Controller controller;
    protected Motion motion;
    protected AnimationManager animationManager;
    protected Direction direction;
    protected List<Effect> effects;

    protected Optional<Action> action;

    public MovingEntity(Controller controller, SpriteLibrary spriteLibrary) {
        super();
        this.controller = controller;
        this.motion = new Motion(2);
        this.direction = Direction.S;
        this.animationManager = new AnimationManager(spriteLibrary.getUnit("matt"));

        effects = new ArrayList<>();
        action = Optional.empty();
    }
    @Override
    public void update(State state){
        handleAction(state);
        handleMotion();

        animationManager.update(direction);
        effects.forEach(effect -> effect.update(state, this));

        manageDirection();
        decideAnimation();

        position.apply(motion);

        cleanup();
    }

    protected void handleMotion(){
        if(!action.isPresent()){
            motion.update(controller);
        }else{
            motion.stop();
        }
    }

    protected void handleAction(State state){
        if(action.isPresent()){
            action.get().update(state, this);
        }
    }

    protected void cleanup(){
        List.copyOf(effects).stream()
        .filter(Effect::shouldDelete)
        .forEach(effects::remove);

        if(action.isPresent() && action.get().isDone()){
            action = Optional.empty();
        }
    }

    protected void decideAnimation(){
        if(action.isPresent()){
            animationManager.playAnimation(action.get().getAnimationName());
        }else if(motion.isMoving()){
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

    public void perform(Action action) {
        this.action = Optional.of(action);
    }

    public void addEffect(Effect effect) {
        effects.add(effect);
    }
}
