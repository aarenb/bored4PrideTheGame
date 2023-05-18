package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
  Clip clip;
  URL soundURL[] = new URL[30];
  FloatControl fc;
  int volumeScale = 3;
  float volume;
  
  public Sound() {
    // Music
    soundURL[0] = getClass().getResource("/resources/sound/Voyage.wav");
    soundURL[1] = getClass().getResource("/resources/sound/PixelParty.wav");

    // Sound effects
    soundURL[2] = getClass().getResource("/resources/sound/cursor.wav");
    soundURL[3] = getClass().getResource("/resources/sound/bit.wav");
    soundURL[4] = getClass().getResource("/resources/sound/sword.wav");
    soundURL[5] = getClass().getResource("/resources/sound/oof.wav");
    soundURL[6] = getClass().getResource("/resources/sound/gameOver.wav");
    soundURL[7] = getClass().getResource("/resources/sound/sparkle.wav");
    soundURL[8] = getClass().getResource("/resources/sound/botDamage.wav");
    soundURL[9] = getClass().getResource("/resources/sound/botDeath.wav");
    soundURL[10] = getClass().getResource("/resources/sound/talk.wav");
  }

  /**
   * Opens an audio file.
   * @param int Index of audio file.
   */
    public void setFile(int i) {
      try {
        AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
        clip = AudioSystem.getClip();
        clip.open(ais);
        fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
        checkVolume();
      } catch (Exception e) {

      }
  }

  public void play() {
    clip.start();
  }

  public void loop() {
    clip.loop(Clip.LOOP_CONTINUOUSLY);
  }

  public void stop() {
    clip.stop();
  }

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
    }
    fc.setValue(volume);
  }
}