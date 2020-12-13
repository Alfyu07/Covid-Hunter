package entities;

import controller.EntityController;
import core.*;
import entities.action.Action;
import entities.effect.Effect;
import game.state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class MovingEntity extends GameObject {

    protected EntityController entityController;
    protected Motion motion;
    protected AnimationManager animationManager;
    protected Direction direction;
    protected List<Effect> effects;

    protected Optional<Action> action;
    protected Size collisionBoxSize;

    protected Vector2D directionVector;

    public MovingEntity(EntityController entityController, SpriteLibrary spriteLibrary) {
        super();
        this.entityController = entityController;
        this.motion = new Motion(2);
        this.direction = Direction.S;
        this.directionVector = new Vector2D(0,0);

        this.animationManager = new AnimationManager(spriteLibrary.getUnit("matt"));

        effects = new ArrayList<>();
        action = Optional.empty();
        this.collisionBoxSize = new Size(16,32);
        this.renderOffset = new Position(size.getWidth() /2, size.getHeight() - 12);
        this.collisionBoxOffset = new Position(collisionBoxSize.getWidth()/2, collisionBoxSize.getHeight());
    }

    @Override
    public void update(State state){
        handleAction(state);
        handleMotion();

        animationManager.update(direction);
        effects.forEach(effect -> effect.update(state, this));

        handleCollisions(state);

        manageDirection();
        decideAnimation();

        position.apply(motion);

        cleanup();
    }

    protected void handleCollisions(State state){
        state.getCollidingGameObjects(this).forEach(this::handleCollision);

    }
    protected abstract void handleCollision(GameObject other);

    protected void handleMotion(){
        if(!action.isPresent()){
            motion.update(entityController);
        }else{
            motion.stop(true, true);
        }
    }

    protected void handleAction(State state){
        if(action.isPresent()){
            action.get().update(state, this);
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
            this.directionVector = motion.getDirection();
        }
    }

    @Override
    public CollisionBox getCollisionBox() {
        Position positionWithMotion = Position.copyOf(getPosition());
        positionWithMotion.apply(motion);
        positionWithMotion.substract(collisionBoxOffset);
        return new CollisionBox(
                new Rectangle(
                    positionWithMotion.intX(),
                    positionWithMotion.intY(),
                    collisionBoxSize.getWidth(),
                    collisionBoxSize.getHeight()
                )
        );
    }


    protected void cleanup(){
        List.copyOf(effects).stream()
        .filter(Effect::shouldDelete)
        .forEach(effects::remove);

        if(action.isPresent() && action.get().isDone()){
            action = Optional.empty();
        }
    }

    @Override
    public Image getSprite() {
        return animationManager.getSprite();

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

    boolean willCollideX(GameObject other){
        CollisionBox otherBox = other.getCollisionBox();
        Position positionWithXApplied = Position.copyOf(position);
        positionWithXApplied.applyX(motion);
        positionWithXApplied.substract(collisionBoxOffset);
        return CollisionBox.of(positionWithXApplied, collisionBoxSize).collideWith(otherBox);
    }

    boolean willCollideY(GameObject other){
        CollisionBox otherBox = other.getCollisionBox();
        Position positionWithYApplied = Position.copyOf(position);
        positionWithYApplied.applyY(motion);
        positionWithYApplied.substract(collisionBoxOffset);
        return CollisionBox.of(positionWithYApplied, collisionBoxSize).collideWith(otherBox);
    }

    public boolean isAffectedBy(Class<?> clazz){
        return effects.stream()
                .anyMatch(effect -> clazz.isInstance(effect));

    }

    protected void clearEffects() {
        this.effects.clear();
    }

    public EntityController getController(){
        return entityController;
    }
    public boolean isFacing(Position other){
        Vector2D direction = Vector2D.directionBeetweenPosition(other, getPosition());
        double dotProduct = Vector2D.dotProduct(direction, directionVector);

        return dotProduct > 0;
        //kalo dotproduct di atas 0 maka target di depan player
    }
}
