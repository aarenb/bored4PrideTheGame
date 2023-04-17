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
  }

  public void getPlayerImage() {
    try {
      def = ImageIO.read(getClass().getResourceAsStream("/player/playerdef.png"));
    } catch(IOException e) {
      e.printStackTrace();
    }
  }

  public void update() {
    if(keyHand.upPressed == true) {
      y = y - speed;// move player up
    } else if (keyHand.downPressed == true) {
      y = y + speed; // move player down
    } else if (keyHand.leftPressed == true) {
      x = x - speed;// move player left
    } else if (keyHand.rightPressed == true) {
      x = x + speed; // move player right
    }
  }

  public void draw(Graphics2D g2d) {

    BufferedImage image = def;
    g2d.drawImage(image, x, y, gamePan.tileSize, gamePan.tileSize, null);

  }
}
