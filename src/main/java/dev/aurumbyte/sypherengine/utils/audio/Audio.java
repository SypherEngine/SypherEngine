package dev.aurumbyte.sypherengine.utils.audio;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class Audio {
    private Clip clip;
    private FloatControl controller;

    public Audio(String path){
        try {
            InputStream audio = Audio.class.getResourceAsStream(path) ;
            InputStream bufferedAudio = new BufferedInputStream(Objects.requireNonNull(audio));
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedAudio);
            AudioFormat format = audioInputStream.getFormat();
            AudioFormat decodedFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    format.getSampleRate(),
                    16,
                    format.getChannels(),
                    format.getChannels() * 2,
                    format.getSampleRate(),
                    false
            );

            AudioInputStream decodedAudioStream = AudioSystem.getAudioInputStream(decodedFormat, audioInputStream);

            clip = AudioSystem.getClip();
            clip.open(decodedAudioStream);
            controller = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play(){
        if(clip == null) return;


        stop();
        clip.setFramePosition(0);

        while(!clip.isRunning()){
            clip.start();
        }
    }

    public void stop(){ if(clip.isRunning()) clip.stop(); }
    public void close(){ stop(); clip.drain(); clip.close(); }
    public void loop(){ clip.loop(Clip.LOOP_CONTINUOUSLY); play(); }
    public void setVolume(float volume){ controller.setValue(volume); }
    public boolean isRunning(){ return clip.isRunning(); }
}
