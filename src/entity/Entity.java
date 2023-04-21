package entity;

import java.awt.Graphics2D;
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
  public int solidAreaDefaultX, solidAreaDefaultY;
  
  public boolean collisionOn = false;
  public int antiSpinCounter = 0; // prevents spinny moving entity
  String words[] = new String[20];
  int speakIndex = 0;

  public Entity(GamePanel gamePan) {
    this.gamePan = gamePan;
  }

  public void setAction() {}
  
  public void speak() {

    if (words[speakIndex] != null) {
      gamePan.ui.currentWords = words[speakIndex];
      speakIndex++;
    } else {
      speakIndex = 0;
    }
  }

  public void update() {
    setAction();

    // Check collision
    collisionOn = false;
    gamePan.colChecker.checkTile(this);
    gamePan.colChecker.checkPlayer(this);
  
    move();
  }

  public void move() {

    // If no collision entity can move
    if (collisionOn == false) {
      switch (direction) {
      case "up":
        worldY = worldY - speed;// move entity up
        break;
      case "down":
        worldY = worldY + speed; // move entity down
        break;
      case "left":
        worldX = worldX - speed;// move entity left
        break;
      case "right":
        worldX = worldX + speed; // move entity right
        break;
      }
    }

    spriteCount++;
    if (spriteCount > 15) {
      if (spriteNum == 1) {
        spriteNum = 2;
      } else if (spriteNum == 2) {
        spriteNum = 1;
      }
      spriteCount = 0;
    }
  }


  public void draw(Graphics2D g2d) {
    BufferedImage image = null;
    int screenX = worldX - gamePan.player.worldX + gamePan.player.screenX;
    int screenY = worldY - gamePan.player.worldY + gamePan.player.screenY;

    if (worldX + gamePan.tileSize > gamePan.player.worldX - gamePan.player.screenX &&
    worldX - gamePan.tileSize < gamePan.player.worldX + gamePan.player.screenX && 
    worldY + gamePan.tileSize > gamePan.player.worldY - gamePan.player.screenY && 
    worldY - gamePan.tileSize < gamePan.player.worldY + gamePan.player.screenY) {
  
      switch (direction) {
        case "up":
          image = up1;
          break;
        case "down":
          image = down1;
          break;
        case "left":
          image = left1;
          break;
        case "right":
          image = right1;
          break;
        }

      g2d.drawImage(image, screenX, screenY, gamePan.tileSize, gamePan.tileSize, null);
    }
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
