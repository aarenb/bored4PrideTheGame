package entity;

import java.awt.Rectangle;

import main.GamePanel;

public class NPC_Wongy extends Entity{
  public NPC_Wongy(GamePanel gamePan) {
    super(gamePan);

    direction = "down";
    speed = 1;

    solidArea = new Rectangle(8, 16, 32, 32);
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;

    getImage();
    setWords();
  }

  /**
   * Load NPC images.
   */
  public void getImage() {
    up1 = setup("/resources/wongy/wongy_up");
    down1 = setup("/resources/wongy/wongy_down");
    left1 = setup("/resources/wongy/wongy_left"); // TODO: maybe remove left & right?? not currently used
    right1 = setup("/resources/wongy/wongy_right");
  }

  public void setWords() {
    words[0] = "*oh no oh no...*\nOh hi there! Finally someone to help!\nI'm wongy, Grass sent you, right?";
    words[1] = "Bored4Pride starts in a matter of \nhours, but everything is so messed \nup! Please say you can help!!";
    words[2] = "Someone must have taken sery bot\nhostage, and now there's follow bots\neverywhere!";
    words[3] = "I need you to find and ban\nall of them for me, and please\nhurry!!";
    words[4] = "What are you waiting for? \nGet to banning!";
  }

  public void setAction() {

    antiSpinCounter++;
    
    if(antiSpinCounter == 130) {
      switch (direction) {
      case "down":
        direction = "up";
        break;
      case "up":
        direction = "down";
        break;
      }

      antiSpinCounter = 0;
    }
  }

}
