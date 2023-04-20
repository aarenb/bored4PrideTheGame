package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity{
  GamePanel gamePan;
  KeyHandler keyHand;

  public final int screenX;
  public final int screenY;

  public Player(GamePanel gamePan, KeyHandler keyHand) {
    this.gamePan = gamePan;
    this.keyHand = keyHand;

    screenX = gamePan.screenWidth/2;
    screenY = gamePan.screenHeight/2;

    solidArea = new Rectangle(2, 16, 44, 32); // sets player collision area

    setDefaultValues();
    getPlayerImage();
  }

  public void setDefaultValues() {
    worldX = gamePan.tileSize * 24;
    worldY = gamePan.tileSize * 44;
    speed = 4;
    direction = "down";
  }

  /**
   * Load player images.
   */
  public void getPlayerImage() {
    up1 = setup("player_up1");
    up2 = setup("player_up2");
    down1 = setup("player_down1");
    down2 = setup("player_down2");
    left1 = setup("player_left1");
    left2 = setup("player_left2");
    right1 = setup("player_right1");
    right2 = setup("player_right2");
  }

  public BufferedImage setup(String imageName) {
    UtilityTool uTool = new UtilityTool();
    BufferedImage image = null;

    try {
      image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
      image = uTool.scaleImage(image, gamePan.tileSize, gamePan.tileSize);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return image;
  }

  public void update() {
    if (keyHand.upPressed == true || keyHand.downPressed == true || keyHand.leftPressed == true || keyHand.rightPressed == true) {
      if (keyHand.upPressed == true) {
        direction = "up";
      } else if (keyHand.downPressed == true) {
        direction = "down";
      } else if (keyHand.leftPressed == true) {
        direction = "left";
      } else if (keyHand.rightPressed == true) {
        direction = "right";
      }

      // Check collision
      collisionOn = false;
      gamePan.colChecker.checkTile(this);

      // If no collision player can move
      if (collisionOn == false) {
        switch (direction) {
        case "up":
          worldY = worldY - speed;// move player up
          break;
        case "down":
          worldY = worldY + speed; // move player down
          break;
        case "left":
          worldX = worldX - speed;// move player left
          break;
        case "right":
          worldX = worldX + speed; // move player right
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
  }

  public void draw(Graphics2D g2d) {
    BufferedImage image = null;

    switch (direction) {
      case "up":
        if (spriteNum == 1) {
          image = up1;
        } 
        if (spriteNum == 2) {
          image = up2;
        }
        break;
      case "down":
        if (spriteNum == 1) {
          image = down1;
        } 
        if (spriteNum == 2) {
          image = down2;
        }
        break;
      case "left":
        if (spriteNum == 1) {
          image = left1;
        } 
        if (spriteNum == 2) {
          image = left2;
        }
        break;
      case "right":
        if (spriteNum == 1) {
          image = right1;
        } 
        if (spriteNum == 2) {
          image = right2;
        }
        break;
    }

    g2d.drawImage(image, screenX, screenY, gamePan.tileSize, gamePan.tileSize, null);

  }
}

