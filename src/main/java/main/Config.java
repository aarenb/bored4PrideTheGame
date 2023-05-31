package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents the configuration. 
 */
public class Config {
  private GamePanel gamePan;

  public Config(GamePanel gamePan) {
    this.gamePan = gamePan;
  }

  /**
   * Saves game settings to config file.
   */
  public void saveConfig() {
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter("config.txt"));

      // Full screen
      if (gamePan.fullScreenOn) {
        writer.write("On");
      } else if (!gamePan.fullScreenOn) {
        writer.write("Off");
      }
      writer.newLine();

      // Music volume
      writer.write(String.valueOf(gamePan.music.volumeScale));
      writer.newLine();

      // Sound effects volume
      writer.write(String.valueOf(gamePan.SE.volumeScale));

      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Loads game settings from config file.
   *
   * @throws IOException Unexpected exception.
   */
  public void loadConfig() {
    try {
      BufferedReader reader = new BufferedReader(new FileReader("config.txt"));
      String s = reader.readLine();

      // Full screen
      if (s.equals("On")) {
        gamePan.fullScreenOn = true;
      } else if (s.equals("Off")) {
        gamePan.fullScreenOn = false;
      }

      // Music volume
      s = reader.readLine();
      gamePan.music.volumeScale = Integer.parseInt(s);

      // Sound effects volume
      s = reader.readLine();
      gamePan.SE.volumeScale = Integer.parseInt(s);

      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }
}