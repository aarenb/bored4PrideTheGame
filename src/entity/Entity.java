package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
  GamePanel gamePan;
  public int worldX, worldY;
  public int speed;

  public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
  public String direction;

  public int spriteCount = 0;
  public int spriteNum = 1;

  public Rectangle solidArea;
  public boolean collisionOn = false;

  public Entity(GamePanel gamePan) {
    this.gamePan = gamePan;
  }

  public BufferedImage setup(String imagePath) {
    UtilityTool uTool = new UtilityTool();
    BufferedImage image = null;

    try {
      image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
      image = uTool.scaleImage(image, gamePan.tileSize, gamePan.tileSize);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return image;
  }
}
