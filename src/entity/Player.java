package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
  GamePanel gamePan;
  KeyHandler keyHand;

  public Player(GamePanel gamePan, KeyHandler keyHand) {
    this.gamePan = gamePan;
    this.keyHand = keyHand;

    setDefaultValues();
  }

  public void setDefaultValues() {
    x = 100;
    y = 100;
    speed = 4;
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
    g2d.setColor(Color.white);
    g2d.fillRect(x, y, gamePan.tileSize, gamePan.tileSize);
  }
}
