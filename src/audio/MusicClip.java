package audio;

import game.settings.GameSetting;

import javax.sound.sampled.Clip;

public class MusicClip extends AudioClip{

    public MusicClip(Clip clip) {
        super(clip);
    }

    @Override
    protected float getVolume(GameSetting gameSetting) {
        return gameSetting.getMusicVolume();
    }
}
