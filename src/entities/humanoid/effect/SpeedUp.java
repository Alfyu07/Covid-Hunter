package entities.humanoid.effect;

import entities.humanoid.Humanoid;
import game.GameLoop;
import game.state.State;

public class SpeedUp extends Effect{

    private double speedMultiplier;

    public SpeedUp(){
        super(GameLoop.UPDATES_PER_SECOND * 5); //kasi lima detik masa waktu speed up nya
        speedMultiplier = 2.5; //speed dikali 2.5
    }

    @Override
    public void update(State state, Humanoid humanoid) {
        super.update(state, humanoid);

        humanoid.multiplySpeed(speedMultiplier);
    }
}
