package entity;

import java.awt.Rectangle;

import main.GamePanel;

public class NPC_Grass extends Entity {
  public NPC_Grass(GamePanel gamePan) {
    super(gamePan);

    direction = "down";
    speed = 0;

    solidArea = new Rectangle(8, 16, 32, 32);
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;
  }
  
}
