package entity;

import java.awt.Rectangle;

import main.GamePanel;

/**
 * Represents a Reece NPC.
 */
public class NPC_Reece extends Entity {
  public NPC_Reece(GamePanel gamePan) {
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
    down1 = setup("/resources/npc/reece/reece_down1", gamePan.tileSize, gamePan.tileSize);
    down2 = setup("/resources/npc/reece/reece_down2", gamePan.tileSize, gamePan.tileSize);
  }

  /**
   * Sets the NPC dialogue.
   */
  public void setWords() {
    words[0] = "Hi there, my name is Reece. Are you in need of a minecraft server? 404 hosting got lots of options for you!";
    words[1] = "Oh, you're here to fix the follow bot problem. Thank god! But please do let me know if you need a minecraft server too...";
  }
}
