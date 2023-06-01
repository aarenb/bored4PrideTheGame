package object;

import entity.Entity;
import java.awt.Rectangle;
import main.GamePanel;

/**
 * Represents a heart object.
 */
public class Heart extends Entity {
  /**
   * Creates a heart.
   *
   * @param gamePan Game's GamePanel object.
   */
  public Heart(GamePanel gamePan) {
    super(gamePan);
    name = "heart";

    image = setup("/objects/heart_full", gamePan.tileSize, gamePan.tileSize);
    image2 = setup("/objects/heart_half", gamePan.tileSize, gamePan.tileSize);
    image3 = setup("/objects/heart_empty", gamePan.tileSize, gamePan.tileSize);
    down1 = image; // image when it's dropped on map
  }
}
