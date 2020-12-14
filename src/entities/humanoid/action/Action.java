package entities.humanoid.action;

import audio.AudioPlayer;
import entities.humanoid.Humanoid;
import state.State;

public abstract class Action {

    protected boolean interruptable;
    protected boolean soundPlaying;

    public Action() {
        interruptable = true;
    }

    public abstract void update(State state, Humanoid humanoid);
    public abstract boolean isDone();
    public abstract String getAnimationName();
    public abstract String getSoundName();

    public  void playSound(AudioPlayer audioPlayer){
        if(!soundPlaying && getSoundName() != null){
            audioPlayer.playSound(getSoundName());
            soundPlaying = true;
        }
    }

    public boolean isInterruptable() {
        return interruptable;
    }
}
