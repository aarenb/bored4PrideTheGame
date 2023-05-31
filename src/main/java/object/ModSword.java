package object;

import entity.Entity;
import java.awt.Rectangle;
import main.GamePanel;

/**
 * Represents a mod sword object.
 */
public class ModSword extends Entity {

  /**
   * Creates a ModSword.
   *
   * @param gamePan Game's GamePanel object.
   */
  public ModSword(GamePanel gamePan) {
    super(gamePan);
    name = "mod sword";

    solidArea = new Rectangle(0, 0, 48, 48);
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;
    
    down1 = setup("/objects/modsword", gamePan.tileSize, gamePan.tileSize);
  }
}
