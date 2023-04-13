package entity;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
  GamePanel gamePan;
  KeyHandler keyHand;

  public Player(GamePanel gamePan, KeyHandler keyHand) {
    this.gamePan = gamePan;
    this.keyHand = keyHand;
  }
}
