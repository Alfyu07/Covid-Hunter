package entities;

import controller.EntityController;
import core.*;
import game.state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;

import java.awt.Image;
import java.awt.Rectangle;

public abstract class MovingEntity extends GameObject {

    protected EntityController entityController;
    protected Motion motion;
    protected AnimationManager animationManager;
    protected Direction direction;

    protected Size collisionBoxSize;

    protected Vector2D directionVector;

    public MovingEntity(EntityController entityController, SpriteLibrary spriteLibrary) {
        super();
        this.entityController = entityController;
        this.motion = new Motion(2);
        this.direction = Direction.S;
        this.directionVector = new Vector2D(0,0);

        this.animationManager = new AnimationManager(spriteLibrary.getSpriteSet("matt"));

    }

    @Override
    public void update(State state){
        motion.update(entityController);
        handleMotion();
        animationManager.update(direction);

        handleCollisions(state);
        manageDirection();
        animationManager.playAnimation(decideAnimation());

        position.apply(motion);
    }

    protected void handleCollisions(State state){
        state.getCollidingGameObjects(this).forEach(this::handleCollision);

    }
    protected abstract void handleCollision(GameObject other);

    protected abstract void handleMotion();




    protected abstract String decideAnimation();

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




    @Override
    public Image getSprite() {
        return animationManager.getSprite();

    }

    public void multiplySpeed(double multiplier){
        motion.multiply(multiplier);
    }



    protected boolean willCollideX(GameObject other){
        CollisionBox otherBox = other.getCollisionBox();
        Position positionWithXApplied = Position.copyOf(position);
        positionWithXApplied.applyX(motion);
        positionWithXApplied.substract(collisionBoxOffset);
        return CollisionBox.of(positionWithXApplied, collisionBoxSize).collideWith(otherBox);
    }

    protected boolean willCollideY(GameObject other){
        CollisionBox otherBox = other.getCollisionBox();
        Position positionWithYApplied = Position.copyOf(position);
        positionWithYApplied.applyY(motion);
        positionWithYApplied.substract(collisionBoxOffset);
        return CollisionBox.of(positionWithYApplied, collisionBoxSize).collideWith(otherBox);
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
