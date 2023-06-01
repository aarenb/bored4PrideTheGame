package entity;

import java.awt.Rectangle;
import java.util.Random;
import main.GamePanel;
import object.Bit;
import object.Heart;

/**
 * Represents a follow bot.
 */
public class FollowBot extends Entity {

  /**
   * Creates a follow bot.
   *
   * @param gamePan Game's GamePanel object.
   */
  public FollowBot(GamePanel gamePan) {
    super(gamePan);

    type = 2;
    direction = "down";
    defaultSpeed = 1;
    speed = defaultSpeed;
    maxLife = 5;
    life = maxLife;

    getImage();
  }

  /**
   * Loads follow bot images.
   */
  public void getImage() {
    up1 = setup("/followbot/followbot_up1", gamePan.tileSize, gamePan.tileSize);
    up2 = setup("/followbot/followbot_up2", gamePan.tileSize, gamePan.tileSize);
    down1 = setup("/followbot/followbot_down1", gamePan.tileSize, gamePan.tileSize);
    down2 = setup("/followbot/followbot_down2", gamePan.tileSize, gamePan.tileSize);
    left1 = setup("/followbot/followbot_left1", gamePan.tileSize, gamePan.tileSize);
    left2 = setup("/followbot/followbot_left2", gamePan.tileSize, gamePan.tileSize);
    right1 = setup("/followbot/followbot_right1", gamePan.tileSize, gamePan.tileSize);
    right2 = setup("/followbot/followbot_right2", gamePan.tileSize, gamePan.tileSize);
  }

  /**
   * Makes follow bot change direction randomly.
   */
  public void setAction() {
    antiSpinCounter++;
    
    if (antiSpinCounter == 110) {
      Random random = new Random();
      int i = random.nextInt(100) + 1; // pick number 1-100

      if (i <= 25) {
        direction = "up";
      } else if (i > 25 && i <= 50) {
        direction = "down";
      } else if (i > 50 && i <= 75) {
        direction = "left";
      } else {
        direction = "right";
      }

      antiSpinCounter = 0;
    }
  }

  /**
   * Drops an item on the ground where follow bot is.
   */
  private void dropItem(Entity obj) {
    for (int i = 0; i < gamePan.obj.length; i++) {
      if (gamePan.obj[i] == null) {
        gamePan.obj[i] = obj;
        gamePan.obj[i].worldX = worldX;
        gamePan.obj[i].worldY = worldY;
        break;
      }
    }
  }

  /**
   * Randomize which item to drop, and then drop said item.
   */
  public void checkDrop() {
    int i = new Random().nextInt(100) + 1; // Pick random number

    if (i < 75) {
      dropItem(new Bit(gamePan));
    } else if (i > 75) {
      dropItem(new Heart(gamePan));
    }
  }
}