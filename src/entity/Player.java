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
    getPlayerAttackImage();
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
    up1 = setup("/player/player_up1", gamePan.tileSize, gamePan.tileSize);
    up2 = setup("/player/player_up2", gamePan.tileSize, gamePan.tileSize);
    down1 = setup("/player/player_down1", gamePan.tileSize, gamePan.tileSize);
    down2 = setup("/player/player_down2", gamePan.tileSize, gamePan.tileSize);
    left1 = setup("/player/player_left1", gamePan.tileSize, gamePan.tileSize);
    left2 = setup("/player/player_left2", gamePan.tileSize, gamePan.tileSize);
    right1 = setup("/player/player_right1", gamePan.tileSize, gamePan.tileSize);
    right2 = setup("/player/player_right2", gamePan.tileSize, gamePan.tileSize);
  }

  /**
   * Load player attack images.
   */
  public void getPlayerAttackImage() {
    attackUp1 = setup("/player/player_attack_up1", gamePan.tileSize, gamePan.tileSize * 2);
    attackUp2 = setup("/player/player_attack_up2", gamePan.tileSize, gamePan.tileSize * 2);
    attackDown1 = setup("/player/player_attack_down1", gamePan.tileSize, gamePan.tileSize * 2);
    attackDown2 = setup("/player/player_attack_down2", gamePan.tileSize, gamePan.tileSize * 2);
    attackLeft1 = setup("/player/player_attack_left1", gamePan.tileSize * 2, gamePan.tileSize);
    attackLeft2 = setup("/player/player_attack_left2", gamePan.tileSize * 2, gamePan.tileSize);
    attackRight1 = setup("/player/player_attack_right1", gamePan.tileSize * 2, gamePan.tileSize);
    attackRight2 = setup("/player/player_attack_right2", gamePan.tileSize * 2, gamePan.tileSize);
  }

  public void update() {

    if (attacking == true) {
      attacking();
    } else if (keyHand.upPressed == true || keyHand.downPressed == true || keyHand.leftPressed == true || keyHand.rightPressed == true) {
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

  public void attacking() {
    spriteCount++;

    // Attack animation
    if (spriteCount <= 5) {
      spriteNum = 1;
    }
    if (spriteCount > 5 && spriteCount <= 25) {
      spriteNum = 2;
    }
    if (spriteCount > 25) {
      spriteNum = 1;
      spriteCount = 0;
      attacking = false;
    }

  }

  public void draw(Graphics2D g2d) {
    BufferedImage image = null;

    switch (direction) {
      case "up":
        if (attacking == false) {
          if (spriteNum == 1) {
            image = up1;
          } 
          if (spriteNum == 2) {
            image = up2;
          }
        } else if (attacking == true) {
          if (spriteNum == 1) {
            image = attackUp1;
          } 
          if (spriteNum == 2) {
            image = attackUp2;
          }
        }
        break;
      case "down":
        if (attacking == false) {
          if (spriteNum == 1) {
            image = down1;
          } 
          if (spriteNum == 2) {
            image = down2;
          }
        } else if (attacking == true) {
          if (spriteNum == 1) {
            image = attackDown1;
          } 
          if (spriteNum == 2) {
            image = attackDown2;
          }
        }
        break;
      case "left":
        if (attacking == false) {
          if (spriteNum == 1) {
            image = left1;
          } 
          if (spriteNum == 2) {
            image = left2;
          }
        } else if (attacking == true) {
          if (spriteNum == 1) {
            image = attackLeft1;
          } 
          if (spriteNum == 2) {
            image = attackLeft2;
          }
        }
        break;
      case "right":
        if (attacking == false) {
          if (spriteNum == 1) {
            image = right1;
          } 
          if (spriteNum == 2) {
            image = right2;
          }
        } else if (attacking == true) {
          if (spriteNum == 1) {
            image = attackRight1;
          } 
          if (spriteNum == 2) {
            image = attackRight2;
          }
        }
        break;
    }
  
    if (attacking == false) {
      g2d.drawImage(image, screenX, screenY, gamePan.tileSize, gamePan.tileSize, null);
    } else if (attacking == true && direction.equals("up")) {
      g2d.drawImage(image, screenX, screenY - gamePan.tileSize, gamePan.tileSize, gamePan.tileSize 
      * 2, null);
    } else if (attacking == true && direction.equals("down")) {
      g2d.drawImage(image, screenX, screenY, gamePan.tileSize, gamePan.tileSize * 2, null);
    } else if (attacking == true && direction.equals("left")) {
      g2d.drawImage(image, screenX - gamePan.tileSize, screenY, gamePan.tileSize * 2, gamePan.tileSize, null);
    } else if (attacking == true && direction.equals("right")) {
      g2d.drawImage(image, screenX, screenY, gamePan.tileSize * 2, gamePan.tileSize, null);
    }
  }

  public void interactNPC(int i) {

    if (gamePan.keyHand.enterPressed == true) {
      if (i != 999) { // if player is touching npc
        gamePan.gameState = gamePan.dialogueState;
        gamePan.npc[i].speak();
      } else {
        // If enter key is pressed but player isn't touching npc
          attacking = true;
      }
    }
  }

  public void interactObject(int i) {
    if (i != 999) { // If player is touching object
      gamePan.obj[i] = null;
      gamePan.ui.showMessage("You picked up a mod sword!");
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

