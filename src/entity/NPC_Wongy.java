package entity;

import java.awt.Rectangle;

import main.GamePanel;

public class NPC_Wongy extends Entity{
  public NPC_Wongy(GamePanel gamePan) {
    super(gamePan);

    direction = "down";
    speed = 1;

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
    up1 = setup("/resources/wongy/wongy_up");
    down1 = setup("/resources/wongy/wongy_down");
    left1 = setup("/resources/wongy/wongy_left"); // TODO: maybe remove left & right?? not currently used
    right1 = setup("/resources/wongy/wongy_right");
  }

  public void setWords() {
    words[0] = "Hello there, young gay";
    words[1] = "I too do be gay";
    words[2] = "Now hop away";
  }

  public void setAction() {

    antiSpinCounter++;
    
    if(antiSpinCounter == 130) {
      switch (direction) {
      case "down":
        direction = "up";
        break;
      case "up":
        direction = "down";
        break;
      }

      antiSpinCounter = 0;
    }
  }

  public void speak() {

    if (words[speakIndex] != null) {
      gamePan.ui.currentWords = words[speakIndex];
      speakIndex++;
    }
  }

}
