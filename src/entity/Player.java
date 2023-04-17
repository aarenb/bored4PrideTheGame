package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
  GamePanel gamePan;
  KeyHandler keyHand;

  public Player(GamePanel gamePan, KeyHandler keyHand) {
    this.gamePan = gamePan;
    this.keyHand = keyHand;

    setDefaultValues();
    getPlayerImage();
  }

  public void setDefaultValues() {
    x = 100;
    y = 100;
    speed = 4;
    direction = "down";
  }

  /**
   * Load player images.
   */
  public void getPlayerImage() {
    try {
      up1 = ImageIO.read(getClass().getResourceAsStream("/player/player_up1.png"));
      up2 = ImageIO.read(getClass().getResourceAsStream("/player/player_up2.png"));
      down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_down1.png"));
      down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_down2.png"));
      left1 = ImageIO.read(getClass().getResourceAsStream("/player/player_left1.png"));
      left2 = ImageIO.read(getClass().getResourceAsStream("/player/player_left2.png"));
      right1 = ImageIO.read(getClass().getResourceAsStream("/player/player_right1.png"));
      right2 = ImageIO.read(getClass().getResourceAsStream("/player/player_right2.png"));
    } catch(IOException e) {
      e.printStackTrace();
    }
  }

  public void update() {
    if(keyHand.upPressed == true) {
      direction = "up";
      y = y - speed;// move player up
    } else if (keyHand.downPressed == true) {
      direction = "down";
      y = y + speed; // move player down
    } else if (keyHand.leftPressed == true) {
      direction = "left";
      x = x - speed;// move player left
    } else if (keyHand.rightPressed == true) {
      direction = "right";
      x = x + speed; // move player right
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

    switch(direction) {
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

    g2d.drawImage(image, x, y, gamePan.tileSize, gamePan.tileSize, null);

  }
}
