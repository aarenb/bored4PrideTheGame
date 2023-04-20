package entity;

import java.awt.Rectangle;

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

}
