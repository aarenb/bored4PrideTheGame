package entity;

import java.awt.Rectangle;
import main.GamePanel;

/**
 * Represents a Grass NPC.
 */
public class NpcGrass extends Entity {
  /**
   * Creates a Grass NPC.
   *
   * @param gamePan Game's GamePanel object.
   */
  public NpcGrass(GamePanel gamePan) {
    super(gamePan);

    direction = "down";
    speed = 0;

    solidArea = new Rectangle(8, 16, 32, 32);
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;

    getImage();
    setWords();
  }

  /**
   * Load NPC images.
   */
  public void getImage() {
    down1 = setup("/npc/grass/grass_down1", gamePan.tileSize, gamePan.tileSize);
    down2 = setup("/npc/grass/grass_down2", gamePan.tileSize, gamePan.tileSize);
  }

  /**
   * Sets the NPC dialogue.
   */
  public void setWords() {
    words[0] = "GAHHHH!!! These sussy follow bots are \nEVERYWHERE! Please please go away you sussy \nbots!!!";
    words[1] = "Oh we spoke on the phone right? I'm Grass! I was \nmid setting up the among us event when these" 
      + "\nbots just came out of nowhere!";
    words[2] = "Now ban them all, and please hurry!! \n*oh god so sussy..*";
  }
  
}
