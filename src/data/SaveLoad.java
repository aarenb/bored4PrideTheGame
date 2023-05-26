package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import main.GamePanel;

/**
 * Represents a save load object.
 */
public class SaveLoad {
  GamePanel gamePan;

  public SaveLoad(GamePanel gamePan) {
    this.gamePan = gamePan;
  }

  /**
   * Saves the game data.
   */
  public void save() {
    try {
      ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
      DataStorage storage = new DataStorage();

      // Save player stats
      storage.life = gamePan.player.life;
      storage.worldX = gamePan.player.worldX;
      storage.worldY = gamePan.player.worldY;
      storage.bits = gamePan.player.bits;
      storage.hasSword = gamePan.player.hasSword;

      // Write data to file
      output.writeObject(storage);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Loads the game data.
   */
  public void load() {
    try {
      ObjectInputStream input = new ObjectInputStream(new FileInputStream(new File("save.dat")));
      DataStorage storage = (DataStorage)input.readObject();

      // Load player stats
      gamePan.player.life = storage.life;
      gamePan.player.worldX = storage.worldX;
      gamePan.player.worldY = storage.worldY;
      gamePan.player.bits = storage.bits;
      gamePan.player.hasSword = storage.hasSword;

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
