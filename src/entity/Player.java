package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
  KeyHandler keyHand;

  public final int screenX;
  public final int screenY;

  public Player(GamePanel gamePan, KeyHandler keyHand) {
    super(gamePan);
    this.keyHand = keyHand;

    screenX = gamePan.screenWidth/2;
    screenY = gamePan.screenHeight/2;

    solidArea = new Rectangle(2, 16, 44, 32); // sets player collision area
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;

    setDefaultValues();
    getPlayerImage();
  }

  public void setDefaultValues() {
    worldX = gamePan.tileSize * 24;
    worldY = gamePan.tileSize * 44;
    speed = 4;
    direction = "down";

    maxLife = 6; // 3 Hearts
    life = maxLife;
    invinsible = false;
  }

  /**
   * Load player images.
   */
  public void getPlayerImage() {
    up1 = setup("/player/player_up1");
    up2 = setup("/player/player_up2");
    down1 = setup("/player/player_down1");
    down2 = setup("/player/player_down2");
    left1 = setup("/player/player_left1");
    left2 = setup("/player/player_left2");
    right1 = setup("/player/player_right1");
    right2 = setup("/player/player_right2");
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
      gamePan.colChecker.checkTile(this); // check tile collision
      int npcIndex = gamePan.colChecker.checkEntity(this, gamePan.npc); // check npc collision
      interactNPC(npcIndex);
      int followBotIndex = gamePan.colChecker.checkEntity(this, gamePan.followBot); // check follow bot collision
      interactFollowBot(followBotIndex);
      int objIndex = gamePan.colChecker.checkObject(this, true); // Check object collision
      interactObject(objIndex);

      move();
    }
    // Add to invinsibleCounter & set invinisible back to false after a while if invinsible is true
    if (invinsible == true) {
      invinsibleCounter++;
      if (invinsibleCounter > 60) {
        invinsible = false;
        invinsibleCounter = 0;
      }
    }

    if (life <= 0) { // If the player has no health
      gamePan.gameState = gamePan.gameOverState;
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

  public void interactNPC(int i) {
    if (i != 999) { // if player is touching npc

      if (gamePan.keyHand.enterPressed) {
        gamePan.gameState = gamePan.dialogueState;
        gamePan.npc[i].speak();
        
      }
    }
    gamePan.keyHand.enterPressed = false;
  }

  public void interactObject(int i) {
    if (i != 999) { // If player is touching object
      
    }
  }

  /**
   * Makes player take damage if touching follow bot.
   * @param i The index of follow bot player is touching (or 999 if not touching any)
   */
  public void interactFollowBot(int i) {
    if (i != 999) { // if player is touching follow bot
      if (invinsible == false) {
        life -= 1;
        invinsible = true;
      }
    }
  }
}

