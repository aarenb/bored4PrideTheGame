package object;

import entity.Entity;
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

    down1 = setup("/objects/sussy_bit", gamePan.tileSize, gamePan.tileSize);
  }
}