package entities.action;

import core.CollisionBox;
import core.Position;
import core.Size;
import entities.MovingEntity;
import entities.effect.Sick;
import game.Game;
import game.GameLoop;
import game.state.State;

public class Cough extends Action{

    private int lifeSpan; //action lifespan
    private Size spreadAreaSize;
    private double chanceToSick;

    public Cough(){
        lifeSpan = GameLoop.UPDATES_PER_SECOND;
        spreadAreaSize = new Size(2 * Game.SPRITE_SIZE, 2 * Game.SPRITE_SIZE);
        chanceToSick = 0.1;
    }
    @Override
    public void update(State state, MovingEntity entity) {
        if(--lifeSpan <= 0){
            Position spreadAreaPosition = new Position(
                    entity.getPosition().getX() - spreadAreaSize.getWidth()/2,
                    entity.getPosition().getY() - spreadAreaSize.getHeight() /2
            );
            CollisionBox spreadArea = CollisionBox.of(spreadAreaPosition, spreadAreaSize);

            state.getGameObjectsOfClass(MovingEntity.class).stream()
                    .filter(movingEntity -> movingEntity.getCollisionBox().collideWith(spreadArea))
                    .filter(movingEntity -> !movingEntity.isAffectedBy(Sick.class))
                    .forEach(movingEntity -> {
                        double fallOut = Math.random();

                        if(fallOut < chanceToSick){
                            movingEntity.addEffect(new Sick());
                        }
                    });
        }
    }

    @Override
    public boolean isDone() {
        return lifeSpan <= 0;
    }

    @Override
    public String getAnimationName() {
        return "cough";
    }
}
