package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
  GamePanel gamePan;

  public Config(GamePanel gamePan) {
    this.gamePan = gamePan;
  }

  /**
   * Saves game settings to config file.
   */
  public void saveConfig(){
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter("config.txt"));

      // Full screen
      if (gamePan.fullScreenOn) {
        writer.write("On");
      } else if (!gamePan.fullScreenOn) {
        writer.write("Off");
      }

      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Loads game settings from config file.
   * @throws IOException
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

      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }
}