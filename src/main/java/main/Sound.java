package main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * Represents a sound object.
 */
public class Sound {
  Clip clip;
  URL[] soundUrl = new URL[30];
  FloatControl fc;
  int volumeScale = 3;
  float volume;
  
  /**
   * Creates a sound object.
   */
  public Sound() {
    // Music
    soundUrl[0] = getClass().getResource("/sound/Voyage.wav");
    soundUrl[1] = getClass().getResource("/sound/PixelParty.wav");

    // Sound effects
    soundUrl[2] = getClass().getResource("/sound/cursor.wav");
    soundUrl[3] = getClass().getResource("/sound/bit.wav");
    soundUrl[4] = getClass().getResource("/sound/sword.wav");
    soundUrl[5] = getClass().getResource("/sound/oof.wav");
    soundUrl[6] = getClass().getResource("/sound/gameOver.wav");
    soundUrl[7] = getClass().getResource("/sound/sparkle.wav");
    soundUrl[8] = getClass().getResource("/sound/botDamage.wav");
    soundUrl[9] = getClass().getResource("/sound/botDeath.wav");
    soundUrl[10] = getClass().getResource("/sound/talk.wav");

    // More music ;)
    soundUrl[11] = getClass().getResource("/sound/teehee.wav");
  }

  /**
   * Opens an audio file.
   *
   * @param i Index of audio file.
   */
  public void setFile(int i) {
    try {
      AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl[i]);
      clip = AudioSystem.getClip();
      clip.open(ais);
      fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      checkVolume();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Plays sound.
   */
  public void play() {
    clip.start();
  }

  /*
   * Loops audio file.
   */
  public void loop() {
    clip.loop(Clip.LOOP_CONTINUOUSLY);
  }

  /**
   * Stops playing sound.
   */
  public void stop() {
    clip.stop();
  }

  /**
   * Checks volume settings and sets volume accordingly.
   */
  public void checkVolume() {
    switch (volumeScale) {
      case 0:
        volume = -80f;
        break;
      case 1:
        volume = -20f;
        break;
      case 2:
        volume = -12f;
        break;
      case 3:
        volume = -5f;
        break;
      case 4:
        volume = 1f;
        break;
      case 5:
        volume = 6f;
        break;
      default:
        break;
    }
    fc.setValue(volume);
  }
}
