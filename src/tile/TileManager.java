package tile;

import java.awt.Graphics2D;
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
  int mapTileNum[][];
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
    loadMap("/maps/mainmap.txt");

    mapTileNum = new int[gamePan.maxWorldColumn][gamePan.maxWorldRow];
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

  public void setup(int index, String imageName, boolean collision) {
    UtilityTool uTool = new UtilityTool();

    try {
      tile[index] = new Tile();
      tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName));
      tile[index].image = uTool.scaleImage(tile[index].image, gamePan.tileSize, gamePan.tileSize);
      tile[index].collision = collision;
    } catch (IOException e){
      e.printStackTrace();
    }
  }

  /**
   * Loads the world map.
   */
  public void loadMap(String filePath) {
    try {
      InputStream input = getClass().getResourceAsStream(filePath);
      BufferedReader reader = new BufferedReader(new InputStreamReader(input));

      int col = 0;
      int row = 0;

      while (col < gamePan.maxWorldColumn && row < gamePan.maxWorldRow) {
        String line = reader.readLine();

        while (col < gamePan.maxWorldColumn) {
          String numbers[] = line.split(" ");

          int num = Integer.parseInt(numbers[col]);
          mapTileNum[col][row] = num;
          col++;
        }

        if (col == gamePan.maxWorldColumn) {
          col = 0;
          row++;
        }
      }
      reader.close();
    } catch (Exception e) {

    }
  }

  public void draw(Graphics2D g2d) {
    int col = 0;
    int row = 0;
    int x = 0;
    int y = 0;

    while (col < gamePan.maxWorldColumn && row < gamePan.maxWorldRow) {
      int tileNum = mapTileNum[col][row];

      g2d.drawImage(tile[tileNum].image, x, y, null);
      col++;
      x += gamePan.tileSize;

      if (col == gamePan.maxWorldColumn) {
        col = 0;
        x = 0;
        row++;
        y += gamePan.tileSize;
      }
    }
  }
}
