package object;

import java.awt.Rectangle;
import entity.Entity;
import main.GamePanel;

/**
 * Represents a mod sword object.
 */
public class OBJ_ModSword extends Entity {

  public OBJ_ModSword(GamePanel gamePan) {
    super(gamePan);
    name = "mod sword";

    solidArea = new Rectangle(0, 0, 48, 48);
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;
    
    down1 = setup("/objects/modsword", gamePan.tileSize, gamePan.tileSize);
  }
}