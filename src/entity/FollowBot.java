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
  
}
