package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.KeyHandler;

/**
 * Represents a player.
 */
public class Player extends Entity{
  KeyHandler keyHand;

  public final int screenX;
  public final int screenY;
  public Rectangle attackArea = new Rectangle(0, 0, 36, 26);
  BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
  boolean attacking = false;
  boolean hasSword = false;
  public int bits = 0;

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

  /**
   * Sets the default player values.
   */
  public void setDefaultValues() {
    worldX = gamePan.tileSize * 24;
    worldY = gamePan.tileSize * 44;
    speed = 4;
    direction = "down";

    maxLife = 10; // 5 Hearts
    life = maxLife;
    invinsible = false;
    hasSword = false;
    bits = 0;
  }

  /**
   * Load player images.
   */
  private void getPlayerImage() {
    up1 = setup("/resources/player/player_up1", gamePan.tileSize, gamePan.tileSize);
    up2 = setup("/resources/player/player_up2", gamePan.tileSize, gamePan.tileSize);
    down1 = setup("/resources/player/player_down1", gamePan.tileSize, gamePan.tileSize);
    down2 = setup("/resources/player/player_down2", gamePan.tileSize, gamePan.tileSize);
    left1 = setup("/resources/player/player_left1", gamePan.tileSize, gamePan.tileSize);
    left2 = setup("/resources/player/player_left2", gamePan.tileSize, gamePan.tileSize);
    right1 = setup("/resources/player/player_right1", gamePan.tileSize, gamePan.tileSize);
    right2 = setup("/resources/player/player_right2", gamePan.tileSize, gamePan.tileSize);
  }

  /**
   * Load player attack images.
   */
  private void getPlayerAttackImage() {
    attackUp1 = setup("/resources/player/player_attack_up1", gamePan.tileSize, gamePan.tileSize * 2);
    attackUp2 = setup("/resources/player/player_attack_up2", gamePan.tileSize, gamePan.tileSize * 2);
    attackDown1 = setup("/resources/player/player_attack_down1", gamePan.tileSize, gamePan.tileSize * 2);
    attackDown2 = setup("/resources/player/player_attack_down2", gamePan.tileSize, gamePan.tileSize * 2);
    attackLeft1 = setup("/resources/player/player_attack_left1", gamePan.tileSize * 2, gamePan.tileSize);
    attackLeft2 = setup("/resources/player/player_attack_left2", gamePan.tileSize * 2, gamePan.tileSize);
    attackRight1 = setup("/resources/player/player_attack_right1", gamePan.tileSize * 2, gamePan.tileSize);
    attackRight2 = setup("/resources/player/player_attack_right2", gamePan.tileSize * 2, gamePan.tileSize);
  }

  /**
   * Updates player.
   */
  public void update() {

    if (attacking) {
      attacking();
    } else if (keyHand.upPressed || keyHand.downPressed || keyHand.leftPressed || keyHand.rightPressed || keyHand.spacePressed) {
      if (keyHand.upPressed) {
        direction = "up";
      } else if (keyHand.downPressed) {
        direction = "down";
      } else if (keyHand.leftPressed) {
        direction = "left";
      } else if (keyHand.rightPressed) {
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
      keyHand.spacePressed = false;
    }
    // Add to invinsibleCounter & set invinisible back to false after a while if invinsible is true
    if (invinsible) {
      invinsibleCounter++;
      if (invinsibleCounter > 80) {
        invinsible = false;
        invinsibleCounter = 0;
      }
    }

    if (life <= 0) { // If the player has no health
      gamePan.gameState = gamePan.gameOverState;
      gamePan.playSE(6);
    }
  }

  /**
   * Draws player.
   */
  public void draw(Graphics2D g2d) {
    BufferedImage image = null;
    int tempScreenX = screenX;
    int tempScreenY = screenY;

    switch (direction) {
      case "up":
        if (!attacking || !hasSword) {
          if (spriteNum == 1) {
            image = up1;
          } 
          if (spriteNum == 2) {
            image = up2;
          }
        } else if (attacking) {
          if (spriteNum == 1) {
            image = attackUp1;
          } 
          if (spriteNum == 2) {
            image = attackUp2;
          }
          tempScreenY = screenY - gamePan.tileSize;
        }
        break;
      case "down":
        if (!attacking || !hasSword) {
          if (spriteNum == 1) {
            image = down1;
          } 
          if (spriteNum == 2) {
            image = down2;
          }
        } else if (attacking) {
          if (spriteNum == 1) {
            image = attackDown1;
          } 
          if (spriteNum == 2) {
            image = attackDown2;
          }
        }
        break;
      case "left":
        if (!attacking || !hasSword) {
          if (spriteNum == 1) {
            image = left1;
          } 
          if (spriteNum == 2) {
            image = left2;
          }
        } else if (attacking) {
          if (spriteNum == 1) {
            image = attackLeft1;
          } 
          if (spriteNum == 2) {
            image = attackLeft2;
          }
          tempScreenX = screenX - gamePan.tileSize;
        }
        break;
      case "right":
        if (!attacking || !hasSword) {
          if (spriteNum == 1) {
            image = right1;
          } 
          if (spriteNum == 2) {
            image = right2;
          }
        } else if (attacking) {
          if (spriteNum == 1) {
            image = attackRight1;
          } 
          if (spriteNum == 2) {
            image = attackRight2;
          }
        }
        break;
    }

    g2d.drawImage(image, tempScreenX, tempScreenY, null);
  }

  /**
   * Makes player do attack animation, and checks if player hits a follow bot.
   */
  private void attacking() {
    spriteCount++;

    // Attack animation
    if (spriteCount <= 5) {
      spriteNum = 1;
    }
    if (spriteCount > 5 && spriteCount <= 25) {
      spriteNum = 2;

      // Save current data
      int currentWorldX = worldX;
      int currentWorldY = worldY;
      int solidAreaWidth = solidArea.width;
      int solidAreaHeight = solidArea.height;

      switch (direction) {
        case "up":
          worldY -= attackArea.height;
          break;
        case "down":
          worldY += attackArea.height;
          break;
        case "left":
          worldX -= attackArea.width; 
          break;
        case "right":
          worldX += attackArea.width;
          break;
      }
      solidArea.width = attackArea.width;
      solidArea.height = attackArea.height;

      // Check if sword collides with follow bot
      int followBotIndex = gamePan.colChecker.checkEntity(this, gamePan.followBot);
      damageFollowBot(followBotIndex);

      // Reset data back to what it was
      worldX = currentWorldX;
      worldY = currentWorldY;
      solidArea.width = solidAreaWidth;
      solidArea.height = solidAreaHeight;

    }
    if (spriteCount > 25) {
      spriteNum = 1;
      spriteCount = 0;
      attacking = false;
    }

  }

  /**
   * Does damage to a follow bot.
   *
   * @param i Index of follow bot player is touching (or 999 if not touching any).
   */
  private void damageFollowBot(int i) {
    if (i != 999) {
      if (!gamePan.followBot[i].invinsible) {
        gamePan.playSE(8);
        gamePan.followBot[i].life -= 1;
        gamePan.followBot[i].invinsible = true;
        gamePan.followBot[i].damageReaction();

        if (gamePan.followBot[i].life <= 0) {
          gamePan.playSE(9);
          gamePan.followBot[i].dying = true;
        }
      }
    }
  }

  /**
   * Interacts with an NPC (or attacks if not touching npc).
   *
   * @param i Index of NPC player is touching (or 999 if not touching any).
   */
  private void interactNPC(int i) {

    if (gamePan.keyHand.spacePressed) {
      if (i != 999) { // if player is touching npc
        gamePan.playSE(10);
        gamePan.gameState = gamePan.dialogueState;
        gamePan.npc[i].speak();
      } else {
        // If enter key is pressed but player isn't touching npc
          if (hasSword) {
            attacking = true;
            gamePan.playSE(4);
          }
      }
    }
  }

  /**
   * Interacts with an object.
   *
   * @param i Index of object player is touching (or 999 if not touching any).
   */
  private void interactObject(int i) {
    if (i != 999) { // If player is touching object

      String objName = gamePan.obj[i].name;
      
      gamePan.ui.showMessage(("You picked up a " + objName + "!"));

      switch(objName) {
        case "mod sword": 
          gamePan.playSE(7);
          gamePan.obj[i] = null;
          hasSword = true;
          break;
        case "bit":
          gamePan.playSE(3);
          gamePan.obj[i] = null;
          bits++;
      }
    }
  }

  /**
   * Makes player take damage if touching follow bot.
   *
   * @param i The index of follow bot player is touching (or 999 if not touching any)
   */
  private void interactFollowBot(int i) {
    if (i != 999) { // if player is touching follow bot
      if (!invinsible) {
        gamePan.playSE(5);
        life -= 1;
        invinsible = true;
      }
    }
  }
}

