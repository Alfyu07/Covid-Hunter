package entities.humanoid.action;

import core.CollisionBox;
import core.Position;
import core.Size;
import entities.humanoid.Humanoid;
import entities.humanoid.effect.Sick;
import game.Game;
import game.GameLoop;
import state.State;

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
    public void update(State state, Humanoid performer) {
        if(--lifeSpan <= 0){
            Position spreadAreaPosition = new Position(
                    performer.getPosition().getX() - spreadAreaSize.getWidth()/2,
                    performer.getPosition().getY() - spreadAreaSize.getHeight() /2
            );
            CollisionBox spreadArea = CollisionBox.of(spreadAreaPosition, spreadAreaSize);

            state.getGameObjectsOfClass(Humanoid.class).stream()
                    .filter(humanoid -> humanoid.getCollisionBox().collidesWith(spreadArea))
                    .filter(humanoid -> !humanoid.isAffectedBy(Sick.class))
                    .forEach(humanoid -> {
                        double fallOut = Math.random();

                        if(fallOut < chanceToSick){
                            humanoid.addEffect(new Sick());
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

    @Override
    public String getSoundName() {
        return null;
    }


}
