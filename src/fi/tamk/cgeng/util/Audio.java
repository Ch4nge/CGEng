package fi.tamk.cgeng.util;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Class that can play and loop .wav files.
 */
public class Audio {

    /**
     * Audio clip
     */
    private Clip clip;

    /**
     * Constructor that initializes SoundClip
     * @param path path to .wav file
     */
    public Audio(String path){
        try(AudioInputStream audioIn = AudioSystem.getAudioInputStream(
                new File(path))){

            clip = AudioSystem.getClip();
            clip.open(audioIn);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Plays the audio clip
     */
    public void play(){
        clip.start();
        if (!clip.isRunning()) {
            clip.setMicrosecondPosition(0);
            clip.start();
        }
    }

    /**
     * Stops the audio clip
     */
    public void stop(){
        if(clip.isRunning()){
            clip.stop();
        }
    }

    /**
     * Loops the clip
     */
    public void loop(){
        if (!clip.isRunning()){
            clip.loop(clip.LOOP_CONTINUOUSLY);
        }
    }

    /**
     * Returns if audio clip is running or not
     * @return true = running, false = not running
     */
    public boolean isRunning(){
        return clip.isRunning();
    }

    /**
     * Disposes the audio clip
     */
    public void dispose(){
        clip.flush();
    }
}