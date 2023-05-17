package entity;

import main.GamePanel;

public class NPC_Alex extends Entity {
  public NPC_Alex(GamePanel gamePan) {
    super(gamePan);

    direction = "down";
    speed = 0;

    getImage();
    setWords();
  }

  /**
   * Load NPC images.
   */
  public void getImage() {
  }

  /**
   * Sets the NPC dialogue
   */
  public void setWords() {
  }

}
