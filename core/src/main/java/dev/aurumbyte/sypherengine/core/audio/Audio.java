/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.core.audio;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import javax.sound.sampled.*;

/**
 * The Audio Engine
 * @author AurumByte
 * @since v0.3.0
 */
public class Audio {
    /**
     * The audio file sample
     */
    private Clip clip;

    /**
     * The float controller for the audio file
     */
    private FloatControl controller;

    /**
     * <p>Constructor to get the audio resource</p>
     * @param path The path to the specified audio file.
     * @since 0.3.0
     */
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

    /**
     * <p>Playing the audioclip</p>
     * @since 0.3.0
     */
    public void play() {
        if (clip == null) return;

        stop();
        clip.setFramePosition(0);

        while (!clip.isRunning()) {
            clip.start();
        }
    }

    /**
     * <p>Stopping the audioclip</p>
     * @since 0.3.0
     */
    public void stop() {
        if (clip.isRunning()) clip.stop();
    }

    /**
     * <p>Closing the audioclip and deallocating it from memory</p>
     * @since 0.3.0
     */
    public void close() {
        stop();
        clip.drain();
        clip.close();
    }

    /**
     * <p>Playing the audioclip on loop</p>
     * @since 0.3.0
     */
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        play();
    }

    /**
     * <p>Setting the volume of the audioclip</p>
     * @param volume The volume to be specified
     * @since 0.3.0
     */
    public void setVolume(float volume) {
        controller.setValue(volume);
    }

    /**
     * <p>Returning whether the audioclip is playing or not</p>
     * @since 0.3.0
     */
    public boolean isRunning() {
        return clip.isRunning();
    }
}
