package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_Wongy extends Entity{
  public NPC_Wongy(GamePanel gamePan) {
    super(gamePan);

    direction = "down";
    speed = 1;

    solidArea = new Rectangle(8, 16, 32, 32);
    getImage();
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

  public void setAction() {

    antiSpinCounter++;
    
    if(antiSpinCounter == 80) {
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

}
