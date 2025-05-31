package main;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;

public class Sound {
    
    Clip clip;
    URL soundURL[] = new URL[30];
    private final float DEFAULT_VOLUME_LEVEL = 0.8f;

    public Sound(){

        soundURL[0] = this.getClass().getResource("/sound/summer_day.wav");
        soundURL[1] = this.getClass().getResource("/sound/doorOpen.wav");
        soundURL[2] = this.getClass().getResource("/sound/button1.wav");
        soundURL[3] = this.getClass().getResource("/sound/select.wav");
        soundURL[4] = this.getClass().getResource("/sound/eat.wav");
        soundURL[5] = this.getClass().getResource("/sound/dialogue.wav");
        soundURL[6] = this.getClass().getResource("/sound/crafting.wav");
        soundURL[7] = this.getClass().getResource("/sound/newRecord.wav");
        soundURL[8] = this.getClass().getResource("/sound/spring_day.wav");
        soundURL[9] = this.getClass().getResource("/sound/fall_day.wav");
        soundURL[10] = this.getClass().getResource("/sound/winter_day.wav");
        soundURL[11] = this.getClass().getResource("/sound/give_gift.wav");
        soundURL[12] = this.getClass().getResource("/sound/purchaseClick.wav");
        soundURL[13] = this.getClass().getResource("/sound/sell.wav");
        soundURL[14] = this.getClass().getResource("/sound/hoeHit.wav");
        soundURL[15] = this.getClass().getResource("/sound/axchop.wav");
        soundURL[16] = this.getClass().getResource("/sound/toolSwap.wav");
        soundURL[17] = this.getClass().getResource("/sound/seeds.wav");
        soundURL[18] = this.getClass().getResource("/sound/harvest.wav");
        soundURL[19] = this.getClass().getResource("/sound/drawer1.wav");
        soundURL[20] = this.getClass().getResource("/sound/water_scoop1.wav");
        soundURL[21] = this.getClass().getResource("/sound/OST.wav");
        soundURL[22] = this.getClass().getResource("/sound/getNewSpecialItem.wav");
        soundURL[23] = this.getClass().getResource("/sound/bigSelect.wav");
        soundURL[24] = this.getClass().getResource("/sound/windah.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float targetDb;
                    float minDb = fc.getMinimum(); 
                    float maxDbConsidered = 0.0f; 

                    targetDb = minDb + (maxDbConsidered - minDb) * DEFAULT_VOLUME_LEVEL;


                    targetDb = Math.max(fc.getMinimum(), targetDb);
                    targetDb = Math.min(fc.getMaximum(), targetDb);
                fc.setValue(targetDb);
            } else {
                System.out.println("Kontrol MASTER_GAIN tidak didukung untuk sound: " + soundURL[i]);
            }
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
