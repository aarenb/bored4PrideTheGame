package entity;

import java.awt.Rectangle;

import main.GamePanel;

public class FollowBot extends Entity {

  public FollowBot(GamePanel gamePan) {
    super(gamePan);

    speed = 1;
    maxLife = 4;
    life = maxLife;

    solidArea = new Rectangle(0, 0, 48, 48); // TODO: change???
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;
  }

  /**
   * Load follow bot images
   */
  public void getImage() {
    up1 = setup("/resources/followbot/followbot_up1");
    up2 = setup("/resources/followbot/followbot_up2");
    down1 = setup("/resources/followbot/followbot_down1");
    down2 = setup("/resources/followbot/followbot_down2");
    left1 = setup("/resources/followbot/followbot_left1");
    left2 = setup("/resources/followbot/followbot_left2");
    right1 = setup("/resources/followbot/followbot_right1");
    right2 = setup("/resources/followbot/followbot_right2");
  }
}
