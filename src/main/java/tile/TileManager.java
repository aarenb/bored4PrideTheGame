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

/**
 * Represents a tile manager.
 */
public class TileManager {
  GamePanel gamePan;
  public Tile[] tile;
  public int[][] mapTileNum;
  ArrayList<String> fileNames = new ArrayList<>();
  ArrayList<String> collisionStat = new ArrayList<>();

  /**
   * Creates a tile manager object.
   *
   * @param gamePan The game's GamePanel.
   */
  public TileManager(GamePanel gamePan) {
    this.gamePan = gamePan;

    // Read tile data file
    InputStream input = getClass().getResourceAsStream("/maps/tilesdata.txt");
    BufferedReader reader = new BufferedReader(new InputStreamReader(input));

    // Get tile names + collission info from file data
    String line;

    try {
      while ((line = reader.readLine()) != null) {
        fileNames.add(line);
        collisionStat.add(reader.readLine());
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    tile = new Tile[fileNames.size()];
    getTileImage();

    mapTileNum = new int[gamePan.maxWorldColumn][gamePan.maxWorldRow];
    loadMap("/maps/mainmap.txt");
  }

  /**
   * Loads tile images.
   */
  public void getTileImage() {
    for (int i = 0; i < fileNames.size(); i++) {
      String fileName;
      boolean collision;

      fileName = fileNames.get(i);
      
      // Checks if tile has collision, and if so sets it to true
      if (collisionStat.get(i).equals("true")) {
        collision = true;
      } else {
        collision = false;
      }

      setup(i, fileName, collision);
    }
  }

  /**
   * Sets up tile.
   *
   * @param index Index of new tile.
   * @param imageName Name of tile image.
   * @param collision If collision is true or false.
   */
  public void setup(int index, String imageName, boolean collision) {
    UtilityTool utool = new UtilityTool();

    try {
      tile[index] = new Tile();
      tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName));
      tile[index].image = utool.scaleImage(tile[index].image, gamePan.tileSize, gamePan.tileSize);
      tile[index].collision = collision;
    } catch (IOException e) {
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
          String[] numbers = line.split(" ");

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
      e.printStackTrace();
    }
  }

  /**
   * Draws tiles.
   *
   * @param g2d Graphics2D to draw with
   */
  public void draw(Graphics2D g2d) {
    int worldCol = 0;
    int worldRow = 0;

    while (worldCol < gamePan.maxWorldColumn && worldRow < gamePan.maxWorldRow) {
      int tileNum = mapTileNum[worldCol][worldRow];

      int worldX = worldCol * gamePan.tileSize;
      int worldY = worldRow * gamePan.tileSize;
      int screenX = worldX - gamePan.player.worldX + gamePan.player.screenX;
      int screenY = worldY - gamePan.player.worldY + gamePan.player.screenY;

      if (worldX + gamePan.tileSize > gamePan.player.worldX - gamePan.player.screenX
          && worldX - gamePan.tileSize < gamePan.player.worldX + gamePan.player.screenX
          && worldY + gamePan.tileSize > gamePan.player.worldY - gamePan.player.screenY
          && worldY - gamePan.tileSize < gamePan.player.worldY + gamePan.player.screenY) {
        g2d.drawImage(tile[tileNum].image, screenX, screenY, null);
      }
      worldCol++;  

      if (worldCol == gamePan.maxWorldColumn) {
        worldCol = 0;
        worldRow++;
      }
    }
  }
}
