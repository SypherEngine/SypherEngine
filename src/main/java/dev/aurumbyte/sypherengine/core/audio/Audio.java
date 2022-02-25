/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.core.audio;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import javax.sound.sampled.*;

public class Audio {
    /*
    String file;
    Media media;
    MediaPlayer mediaPlayer;

    public Audio(String file){
    	this.file = file;
    	this.media = new Media(new File(file).toURI().toString());
    	this.mediaPlayer = new MediaPlayer(media);
    }

    public void play() {
    	mediaPlayer.play();
    }

    public void stop() {
    	mediaPlayer.stop();
    }

    public void loop() {
    	mediaPlayer.setOnEndOfMedia(() -> {
    		mediaPlayer.seek(Duration.ZERO);
    		mediaPlayer.play();
    	});
    }

    public MediaPlayer getMediaPlayer() {
    	return mediaPlayer;
    }

    public Media getMedia() {
    	return media;
    }

    public void setMedia(Media media) {
    	this.media = media;
    }

    public static class Clip {
    	String file;
    	AudioClip audioClip;

    	public Clip(String file){
    		this.file = file;
    		this.audioClip = new AudioClip(new File(file).toURI().toString());
    	}

    	public void play() {
    		audioClip.play();
    	}

    	public void loop() {
    		audioClip.play();
    		if(!audioClip.isPlaying()) audioClip.play();
    	}

    	public void stop() {
    		audioClip.stop();
    	}
    }

    */
    private Clip clip;
    private FloatControl controller;

    public Audio(String path) {
        try {
            InputStream audio = Audio.class.getResourceAsStream(path);
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
                    false);

            AudioInputStream decodedAudioStream = AudioSystem.getAudioInputStream(decodedFormat, audioInputStream);

            clip = AudioSystem.getClip();
            clip.open(decodedAudioStream);
            controller = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip == null) return;

        stop();
        clip.setFramePosition(0);

        while (!clip.isRunning()) {
            clip.start();
        }
    }

    public void stop() {
        if (clip.isRunning()) clip.stop();
    }

    public void close() {
        stop();
        clip.drain();
        clip.close();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        play();
    }

    public void setVolume(float volume) {
        controller.setValue(volume);
    }

    public boolean isRunning() {
        return clip.isRunning();
    }
}
