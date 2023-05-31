package object;

import entity.Entity;
import java.awt.Rectangle;
import main.GamePanel;

/**
 * Represents a bit object.
 */
public class Bit extends Entity {
  /**
   * Creates a bit.
   *
   * @param gamePan Game's GamePanel object.
   */
  public Bit(GamePanel gamePan) {
    super(gamePan);
    name = "bit";

    solidArea = new Rectangle(0, 0, 48, 48);
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;

    down1 = setup("/objects/bit", gamePan.tileSize, gamePan.tileSize);
  }
}
