package tile;

import main.GamePanel;

public class TileManager {
  GamePanel gamePan;
  Tile[] tile;

  public TileManager(GamePanel gamePan) {
    this.gamePan = gamePan;
    tile = new Tile[250];

    getTileImage();
  }

  /**
   * Load tile images.
   */
  public void getTileImage() {

  }
}
