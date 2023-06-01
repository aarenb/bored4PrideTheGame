package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

/**
 * Represents an entity.
 */
public class Entity {
  protected GamePanel gamePan;
  public int worldX;
  public int worldY;
  public int speed = 0;
  public int defaultSpeed;
  public int type; // 0 = player, 1 = npc, 2 = follow bot
  public int spriteCount = 0;
  public int spriteNum = 1;
  public BufferedImage up1;
  public BufferedImage up2;
  public BufferedImage down1; 
  public BufferedImage down2;
  public BufferedImage left1;
  public BufferedImage left2;
  public BufferedImage right1;
  public BufferedImage right2;
  public String direction = "down";
  public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
  public int solidAreaDefaultX = solidArea.x;
  public int solidAreaDefaultY = solidArea.y;
  public boolean collisionOn = false;
  public int antiSpinCounter = 0; // prevents spinny moving entity
  public boolean invinsible = false; // make entity not take damage when true
  public int invinsibleCounter = 0;
  public int maxLife;
  public int life;

  // Object stuff
  public BufferedImage image;
  public BufferedImage image2;
  public BufferedImage image3;
  public String name;

  // Follow bot stuff
  int dyingCounter = 0;
  int hpBarCounter = 0;
  int knockBackCounter = 0;
  boolean hpBarOn = false;
  public boolean alive = true;
  public boolean dying = false;
  public boolean knockBack = false;

  // NPC stuff
  public String[] words = new String[20];
  public int speakIndex = 0;

  public Entity(GamePanel gamePan) {
    this.gamePan = gamePan;
  }

  public void setAction() {}

  public void damageReaction() {}

  /**
   * Updates entity.
   */
  public void update() {
    if (knockBack) { //
      direction = gamePan.player.direction;
      checkCollision();
      if (collisionOn) {
        knockBackCounter = 0;
        knockBack = false;
        speed = defaultSpeed;
        turnToPlayer();
      } else if (!collisionOn) {
        move();
        knockBackCounter++;
        if (knockBackCounter == 5) {
          knockBackCounter = 0;
          knockBack = false;
          speed = defaultSpeed;
          turnToPlayer();
        }
      }
    } else {
      checkCollision();
      move();
    }

    setAction();

    if (invinsible) {
      invinsibleCounter++;
      if (invinsibleCounter > 30) {
        invinsible = false;
        invinsibleCounter = 0;
      }
    }
  }

  /**
   * Draw entity on screen.
   *
   * @param g2d Graphics2D to draw with.
   */
  public void draw(Graphics2D g2d) {
    BufferedImage image = null;
    int screenX = worldX - gamePan.player.worldX + gamePan.player.screenX;
    int screenY = worldY - gamePan.player.worldY + gamePan.player.screenY;

    if (worldX + gamePan.tileSize > gamePan.player.worldX - gamePan.player.screenX
        && worldX - gamePan.tileSize < gamePan.player.worldX + gamePan.player.screenX 
        && worldY + gamePan.tileSize > gamePan.player.worldY - gamePan.player.screenY 
        && worldY - gamePan.tileSize < gamePan.player.worldY + gamePan.player.screenY) {
  
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
        default:
          break;
      }

      // Follow bot health bar
      if (type == 2 && hpBarOn) {
        double oneScale = (double) gamePan.tileSize / maxLife; // Length of 1 hp in health bar
        double hpBarScale = oneScale * life; // Current length of health bar

        // Outline:
        g2d.setColor(new Color(35, 35, 35));
        g2d.fillRect(screenX - 1, screenY - 19, gamePan.tileSize + 2, 9);
        // Main part:
        g2d.setColor(new Color(255, 0, 30));
        g2d.fillRect(screenX, screenY - 18, (int) hpBarScale, 7);

        hpBarCounter++;
        if (hpBarCounter > 600) {
          hpBarCounter = 0;
          hpBarOn = false;
        }

      }

      if (invinsible) {
        hpBarOn = true;
        hpBarCounter = 0;
        changeAlpha(g2d, 0.4f);
      }

      if (dying) {
        dyingAnimation(g2d);
      }

      g2d.drawImage(image, screenX, screenY, null);
      changeAlpha(g2d, 1f); // Reset opacity
    }
  }

  /**
   * Checks collision.
   */
  private void checkCollision() {
    collisionOn = false;
    gamePan.colChecker.checkTile(this);
    gamePan.colChecker.checkEntity(this, gamePan.npc);
    gamePan.colChecker.checkEntity(this, gamePan.followBot);
    gamePan.colChecker.checkObject(this, false);
    boolean touchPlayer = gamePan.colChecker.checkPlayer(this);

    if (this.type == 2 && touchPlayer) { // If follow bot touches player
      if (!gamePan.player.invinsible) { // If player isn't invinsible, it takes damage
        gamePan.player.life -= 1;
        gamePan.player.invinsible = true;
      }
    }
  }

  /**
   * Moves entity if there is no collision.
   */
  protected void move() {
    // If no collision entity can move
    if (!collisionOn && !gamePan.keyHand.spacePressed) {
      switch (direction) {
        case "up":
          worldY = worldY - speed; // move entity up
          break;
        case "down":
          worldY = worldY + speed; // move entity down
          break;
        case "left":
          worldX = worldX - speed; // move entity left
          break;
        case "right":
          worldX = worldX + speed; // move entity right
          break;
        default:
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
  
  /**
   * Sets NPC Dialogue words for UI and moves NPC towards player if NPC can move up.
   */
  public void speak() {

    if (up1 != null) {
      switch (gamePan.player.direction) {
        case "up":
          direction = "down";
          break;
        case "down":
          direction = "up";
          break;
        case "left":
          direction = "down";
          break;
        case "right": 
          direction = "down";
          break;
        default:
          break;
      }
    }

    gamePan.ui.currentWords = words[speakIndex];
    speakIndex++;
  }

  /**
   * Displays follow bot dying animation.
   *
   * @param g2d Graphics2D to draw with.
   */
  public void dyingAnimation(Graphics2D g2d) {
    dyingCounter++;
    int i = 5;
    if (dyingCounter <= i) {
      changeAlpha(g2d, 0f);
    } else if (dyingCounter > i && dyingCounter <= i * 2) {
      changeAlpha(g2d, 1f);
    } else if (dyingCounter > i * 2 && dyingCounter <= i * 3) {
      changeAlpha(g2d, 0f);
    } else if (dyingCounter > i * 3 && dyingCounter <= i * 4) {
      changeAlpha(g2d, 1f);
    } else if (dyingCounter > i * 4 && dyingCounter <= i * 5) {
      changeAlpha(g2d, 0f);
    } else if (dyingCounter > i * 5 && dyingCounter <= i * 6) {
      changeAlpha(g2d, 1f);
    } else if (dyingCounter > i * 6 && dyingCounter <= i * 7) {
      changeAlpha(g2d, 0f);
    } else if (dyingCounter > i * 7 && dyingCounter <= i * 8) {
      changeAlpha(g2d, 1f);
    } else if (dyingCounter > i * 8 && dyingCounter <= i * 9) {
      changeAlpha(g2d, 0f);
    } else if (dyingCounter > i * 9 && dyingCounter <= i * 10) {
      changeAlpha(g2d, 1f);
    } else if (dyingCounter > i * 10) {
      dying = false;
      alive = false;
    }
  }

  /**
   * Changes opacity of entity.
   *
   * @param g2d Graphics2D to draw with.
   * @param alphaValue The alpha value to change entity to.
   */
  private void changeAlpha(Graphics2D g2d, float alphaValue) {
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
  }

  /**
   * Makes the entity turn towards the player.
   */
  private void turnToPlayer() {
    antiSpinCounter = 0;
    switch (gamePan.player.direction) {
      case "up":
        direction = "down";
        break;
      case "down":
        direction = "up";
        break;
      case "left":
        direction = "right";
        break;
      case "right":
        direction = "left";
        break;
      default:
        break;
    }
  }

  /**
   * Sets up images for entity.
   *
   * @param imagePath Image path for image to load.
   * @param width Width to scale image to.
   * @param height Height to scale image to.
   * @return The image.
   */
  protected BufferedImage setup(String imagePath, int width, int height) {
    UtilityTool utool = new UtilityTool();
    BufferedImage image = null;

    try {
      image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
      image = utool.scaleImage(image, width, height);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return image;
  }
}
