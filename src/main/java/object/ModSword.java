package object;

import entity.Entity;
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
    
    down1 = setup("/objects/modsword", gamePan.tileSize, gamePan.tileSize);
  }
}
