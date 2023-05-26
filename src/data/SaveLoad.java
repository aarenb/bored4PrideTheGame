package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Bit;
import object.OBJ_Heart;
import object.OBJ_ModSword;
import object.OBJ_SussyBit;

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
      storage.playerWorldX = gamePan.player.worldX;
      storage.playerWorldY = gamePan.player.worldY;
      storage.bits = gamePan.player.bits;
      storage.hasSword = gamePan.player.hasSword;

      // Save objects on the map
      storage.mapObjectNames = new String[gamePan.obj.length];
      storage.mapObjectWorldX = new int[gamePan.obj.length];
      storage.mapObjectWorldY = new int[gamePan.obj.length];
      for (int i = 0; i < gamePan.obj.length; i++) {
        if (gamePan.obj[i] == null) {
          storage.mapObjectNames[i] = "NA";
        } else {
          storage.mapObjectNames[i] = gamePan.obj[i].name;
          storage.mapObjectWorldX[i] = gamePan.obj[i].worldX;
          storage.mapObjectWorldY[i] = gamePan.obj[i].worldY;
        }
      }

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
      gamePan.player.worldX = storage.playerWorldX;
      gamePan.player.worldY = storage.playerWorldY;
      gamePan.player.bits = storage.bits;
      gamePan.player.hasSword = storage.hasSword;

      // Load objects on the map
      for (int i = 0; i < gamePan.obj.length; i++) {
        if (storage.mapObjectNames[i].equals("NA")) {
          gamePan.obj[i] = null;
        } else {
          gamePan.obj[i] = getObject(storage.mapObjectNames[i]);
          gamePan.obj[i].worldX = storage.mapObjectWorldX[i];
          gamePan.obj[i].worldY = storage.mapObjectWorldY[i];
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Entity getObject(String itemName) {
    Entity obj = null;

    switch (itemName) {
      case "heart":
        obj = new OBJ_Heart(gamePan);
        break;
      case "bit":
        obj = new OBJ_Bit(gamePan);
        break;
      case "sussy bit":
        obj = new OBJ_SussyBit(gamePan);
        break;
      case "mod sword":
        obj = new OBJ_ModSword(gamePan);
        break;
    }

    return obj;
  }
}
