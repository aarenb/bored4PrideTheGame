package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {
  GamePanel gamePan;
  Tile[] tile;
  ArrayList<String> fileNames = new ArrayList<>();
  ArrayList<String> collisionStat = new ArrayList<>();

  public TileManager(GamePanel gamePan) {
    this.gamePan = gamePan;

    // Read tile data file
    InputStream input = getClass().getResourceAsStream("/maps/tilesdata.txt");
    BufferedReader reader = new BufferedReader(new InputStreamReader(input));

    // Get tile names + collission info from file data
    String line;

    try {
      while((line = reader.readLine()) != null) {
        fileNames.add(line);
        collisionStat.add(reader.readLine());
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    tile = new Tile[fileNames.size()];
    getTileImage();
  }

  /**
   * Load tile images.
   */
  public void getTileImage() {
    for (int i = 0; i < fileNames.size(); i++) {
      String fileName;
      boolean collision;

      fileName = fileNames.get(i);
      
      if (collisionStat.get(i).equals("true")) {
        collision = true;
      } else {
        collision = false;
      }

      setup(i, fileName, collision);
    }
  }

  public void setup(int index, String imagePath, boolean collision) {
    UtilityTool uTool = new UtilityTool();

    try {
      tile[index] = new Tile();
      tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imagePath));
      tile[index].image = uTool.scaleImage(tile[index].image, gamePan.tileSize, gamePan.tileSize);
      tile[index].collision = collision;
    } catch (IOException e){
      e.printStackTrace();
    }
  }
}
