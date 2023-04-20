package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_Wongy extends Entity{
  public NPC_Wongy(GamePanel gamePan) {
    super(gamePan);

    direction = "down";
    speed = 2;

    solidArea = new Rectangle(8, 16, 32, 32);
    getImage();
  }

  /**
   * Load NPC images.
   */
  public void getImage() {
    up1 = setup("/resources/wongy/wongy_up");
    down1 = setup("/resources/wongy/wongy_down");
    left1 = setup("/resources/wongy/wongy_left");
    right1 = setup("/resources/wongy/wongy_right");
  }

  public void setAction() {
    Random random = new Random();
    int i = random.nextInt(100)+1; // pick num 1-100

    if (i <= 25) {
      direction = "up";
    }
    if (i > 25 && i <= 50) {
      direction = "down";
    }
    if (i > 50 && i <= 75) {
      direction = "left";
    }
    if (i > 75) {
      direction = "right";
    }
  }

}
