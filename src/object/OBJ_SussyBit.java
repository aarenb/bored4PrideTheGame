package object;

import java.awt.Rectangle;
import entity.Entity;
import main.GamePanel;

/**
 * Represents a sussy bit object.
 */
public class OBJ_SussyBit extends Entity {
  public OBJ_SussyBit(GamePanel gamePan) {
    super(gamePan);
    name = "sussy bit";

    solidArea = new Rectangle(0, 0, 48, 48);
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;

    down1 = setup("/resources/objects/sussy_bit", gamePan.tileSize, gamePan.tileSize);
  }
}