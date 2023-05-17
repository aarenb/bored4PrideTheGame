package entity;

import java.awt.Rectangle;

import main.GamePanel;

public class NPC_Alex extends Entity {
  public NPC_Alex(GamePanel gamePan) {
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
    down1 = setup("/resources/npc/alex/alex_down1", gamePan.tileSize, gamePan.tileSize);
    down2 = setup("/resources/npc/alex/alex_down2", gamePan.tileSize, gamePan.tileSize);
  }

  /**
   * Sets the NPC dialogue
   */
  public void setWords() {
  }

}
