package object;

import entity.Entity;
import java.awt.Rectangle;
import main.GamePanel;

/**
 * Represents a sussy bit object.
 */
public class SussyBit extends Entity {
  /**
   * Creates a sussy bit.
   *
   * @param gamePan Game's GamePanel object.
   */
  public SussyBit(GamePanel gamePan) {
    super(gamePan);
    name = "sussy bit";

    solidArea = new Rectangle(0, 0, 48, 48);
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;

    down1 = setup("/objects/sussy_bit", gamePan.tileSize, gamePan.tileSize);
  }
}