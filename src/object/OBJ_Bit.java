package object;

import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;

/**
 * Represents a bit object.
 */
public class OBJ_Bit extends Entity{
  public OBJ_Bit(GamePanel gamePan) {
    super(gamePan);
    name = "bit";

    solidArea = new Rectangle(0, 0, 48, 48);
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;

    down1 = setup("/resources/objects/bit", gamePan.tileSize, gamePan.tileSize);
  }
}
