package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import main.GamePanel;

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
    
  }

  public void setup(int index, String imagePath, boolean collision) {
    
  }
}
