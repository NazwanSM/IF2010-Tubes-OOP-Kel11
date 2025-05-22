package main;

import javax.sound.sampled.Clip;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;

public class Sound {
    
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound(){

        soundURL[0] = this.getClass().getResource("/resource/sound/summer_day.wav");
        soundURL[1] = this.getClass().getResource("/resource/sound/doorOpen.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(){
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }
}
