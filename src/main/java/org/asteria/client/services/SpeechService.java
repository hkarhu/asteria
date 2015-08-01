package org.asteria.client.services;

import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.util.data.audio.AudioPlayer;
import org.flowutils.Check;
import org.flowutils.service.ServiceBase;
import org.flowutils.service.ServiceProvider;

import javax.sound.sampled.AudioInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Service that provides text to speech.
 */
public final class SpeechService extends ServiceBase {

    private MaryInterface maryTTS;
    private List<String> voices = new ArrayList<>();
    private String currentVoice = null;

    @Override protected void doInit(ServiceProvider serviceProvider) {
        try {
            maryTTS = new LocalMaryInterface();
        }
        catch (MaryConfigurationException e) {
            throw new IllegalStateException("Could not initialize text to speech service: " + e.getMessage(), e);
        }

        // Get the available voices
        voices.addAll(maryTTS.getAvailableVoices());
        if (voices.isEmpty()) {
            throw new IllegalStateException("No voices available fo text to speech service.");
        }

        // Set voice to use
        if (currentVoice == null) {
            currentVoice = voices.get(0);
        }
        maryTTS.setVoice(currentVoice);
    }

    /**
     * @return the available voices.
     */
    public List<String> getVoices() {
        return voices;
    }

    /**
     * Set the voice to use by default.  Must be one of the available voices from getVoice.
     */
    public void setVoice(String voiceName) {
        Check.notNull(voiceName, "voiceName");
        if (!voiceName.equals(currentVoice)) {
            currentVoice = voiceName;
            if (maryTTS != null) {
                maryTTS.setVoice(voiceName);
            }
        }
    }

    /**
     * Speaks a message using the most recently used or default voice, returns immediately without waiting for it to complete.
     * @param message message to speak.
     */
    public void say(String message) {
        say(message, null);
    }

    /**
     * Speaks a message, returns immediately without waiting for it to complete.
     * @param message message to speak.
     * @param voice voice to use, or null to use the most recently used or default voice.
     */
    public void say(String message, String voice) {
        say(message, voice, false);
    }

    /**
     * Speaks a message.
     * @param message message to speak.
     * @param voice voice to use, or null to use the most recently used or default voice.
     * @param blockUntilReady if true, doesn't return from this function until the speaking is finished.  if false, returns immediately.
     */
    public void say(String message, String voice, boolean blockUntilReady) {
        if (maryTTS == null) throw new IllegalStateException("init has not been called on the SpeechService (or init failed), can not generate speech.");
        Check.notNull(message, "message");

        // No point to speak silence.
        if (message.trim().isEmpty()) return;

        if (voice != null) setVoice(voice);

        // Generate audio for message
        final AudioInputStream audio;
        try {
            audio = maryTTS.generateAudio(message);
        }
        catch (SynthesisException e) {
            log.warn("Could not generate speech for message: '"+message+"': " + e.getMessage(), e);
            return;
        }

        // Play message
        AudioPlayer player = new AudioPlayer(audio);
        player.setDaemon(true); // Don't wait for this to play if we quit the game
        player.start();

        // If requested, wait until message is completely spoken
        if (blockUntilReady) try {
            player.join();
        }
        catch (InterruptedException e) {
            // Ignore
        }
    }

    @Override protected void doShutdown() {
        maryTTS = null;
    }
}
