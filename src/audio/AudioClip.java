package audio;

import game.settings.GameSetting;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public abstract class AudioClip {
    private final Clip clip;

    public AudioClip(Clip clip) {
        this.clip = clip;
        clip.start();
    }

    public void update(GameSetting gameSetting){
        final FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue(getVolume(gameSetting));
    }
    protected abstract float getVolume(GameSetting gameSetting);

    public boolean hasFinishedPlaying(){
        return !clip.isRunning();
    }

    public void cleanUp(){
        clip.close();
    }

}
