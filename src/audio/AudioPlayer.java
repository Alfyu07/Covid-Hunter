package audio;

import game.settings.GameSetting;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AudioPlayer {

    private List<AudioClip> audioClips;

    public void update(GameSetting gameSetting){
        //update volume and everything first
        audioClips.forEach(audioClip -> audioClip.update(gameSetting));

        List.copyOf(audioClips).forEach(audioClip -> {
            if(audioClip.hasFinishedPlaying()){
                audioClip.cleanUp();
                audioClips.remove(audioClip);
            }
        });
    }

    public AudioPlayer(){
        audioClips = new ArrayList<>();
    }

    public void playMusic(String fileName){
        final Clip clip = getClip(fileName);
        audioClips.add(new MusicClip(clip));
    }

    public void playSound(String fileName){
        final Clip clip = getClip(fileName);
        audioClips.add(new SoundClip(clip));
    }
    private Clip getClip(String fileName){
        final URL soundFile = AudioPlayer.class.getResource(fileName);
        try(AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile)) {
           final Clip clip = AudioSystem.getClip();
           clip.open(audioInputStream);
           clip.setMicrosecondPosition(0);
           return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e ) {
            e.printStackTrace();
        }
        return null;
    }
}
