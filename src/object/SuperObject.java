package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.UtilityTool;

public class SuperObject {
  public BufferedImage image, image2, image3;
  public int worldX, worldY;
  public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
  public int solidAreaDefaultX = 0;
  public int solidAreaDefaultY = 0;


  UtilityTool uTool = new UtilityTool();
  GamePanel gamePan;

  public void draw(Graphics2D g2d) {
    int screenX = worldX - gamePan.player.worldX + gamePan.player.screenX;
    int screenY = worldY - gamePan.player.worldY + gamePan.player.screenY;

    if (worldX + gamePan.tileSize > gamePan.player.worldX - gamePan.player.screenX &&
        worldX - gamePan.tileSize < gamePan.player.worldX + gamePan.player.screenX && 
        worldY + gamePan.tileSize > gamePan.player.worldY - gamePan.player.screenY && 
        worldY - gamePan.tileSize < gamePan.player.worldY + gamePan.player.screenY) {
          g2d.drawImage(image, screenX, screenY, null);
    }
  }

}
