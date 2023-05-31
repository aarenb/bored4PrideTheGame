package entity;

import java.awt.Rectangle;
import main.GamePanel;

/**
 * Represents an Alex NPC.
 */
public class NpcAlex extends Entity {
  /**
   * Creates a new Alex NPC.
   *
   * @param gamePan Game's GamePanel object.
   */
  public NpcAlex(GamePanel gamePan) {
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
    down1 = setup("/npc/alex/alex_down1", gamePan.tileSize, gamePan.tileSize);
    down2 = setup("/npc/alex/alex_down2", gamePan.tileSize, gamePan.tileSize);
  }

  /**
   * Sets the NPC dialogue.
   */
  public void setWords() {
    words[0] = "Hello! My outfit? I'm obviously dressed as a chess \nrook piece. " 
      + "My name is AlexJC by the way. You here \nto fix the follow bot issue?";
    words[1] = "I really just want to play some jackbox man, so \nget rid of them fast please.";
  }

}
