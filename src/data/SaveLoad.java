package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import main.GamePanel;

public class SaveLoad {
  GamePanel gamePan;

  public SaveLoad(GamePanel gamePan) {
    this.gamePan = gamePan;
  }

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

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
