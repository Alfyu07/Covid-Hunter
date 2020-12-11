package entities.effect;

import entities.MovingEntity;
import game.GameLoop;
import game.state.State;

public class SpeedUp extends Effect{

    private double speedMultiplier;

    public SpeedUp(){
        super(GameLoop.UPDATES_PER_SECOND * 5); //kasi lima detik masa waktu speed up nya
        speedMultiplier = 2.5; //speed dikali 2.5
    }

    @Override
    public void update(State state, MovingEntity entity) {
        super.update(state, entity);

        entity.multiplySpeed(speedMultiplier);
    }
}
