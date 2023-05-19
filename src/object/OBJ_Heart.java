package object;

import entity.Entity;
import main.GamePanel;

/**
 * Represents a heart object.
 */
public class OBJ_Heart extends Entity{
  
  public OBJ_Heart(GamePanel gamePan) {
    super(gamePan);

    name = "heart";

    image = setup("/resources/objects/heart_full", gamePan.tileSize, gamePan.tileSize);
    image2 = setup("/resources/objects/heart_half", gamePan.tileSize, gamePan.tileSize);
    image3 = setup("/resources/objects/heart_empty", gamePan.tileSize, gamePan.tileSize);
    down1 = image; // image when it's dropped on map
  }
}
