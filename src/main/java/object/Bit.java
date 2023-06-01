package object;

import entity.Entity;
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

    down1 = setup("/objects/bit", gamePan.tileSize, gamePan.tileSize);
  }
}
