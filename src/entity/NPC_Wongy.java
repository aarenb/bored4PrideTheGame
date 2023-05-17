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
    up1 = setup("/resources/npc/wongy/wongy_up", gamePan.tileSize, gamePan.tileSize);
    up2 = setup("/resources/npc/wongy/wongy_up", gamePan.tileSize, gamePan.tileSize);
    down1 = setup("/resources/npc/wongy/wongy_down", gamePan.tileSize, gamePan.tileSize);
    down2 = setup("/resources/npc/wongy/wongy_down", gamePan.tileSize, gamePan.tileSize);
    left1 = setup("/resources/npc/wongy/wongy_left", gamePan.tileSize, gamePan.tileSize);
    left2 = setup("/resources/npc/wongy/wongy_left", gamePan.tileSize, gamePan.tileSize); // TODO: maybe remove left & right?? not currently used
    right1 = setup("/resources/npc/wongy/wongy_right", gamePan.tileSize, gamePan.tileSize);
    right2 = setup("/resources/npc/wongy/wongy_right", gamePan.tileSize, gamePan.tileSize);
  }

  /**
   * Setsthe NPC dialogue
   */
  public void setWords() {
    words[0] = "*oh no oh no...*\nOh hi there! Finally someone to help! I'm Wongy. \nGrass sent you, right?";
    words[1] = "Bored4Pride starts in a matter of hours, but \neverything is so messed up! Please say you \ncan help!!";
    words[2] = "Someone must have taken sery bot hostage, and \nnow there's follow bots everywhere!";
    words[3] = "I need you to find and ban all of them for me, \nthere's a mod sword not far from here. Find it \nand use it to ban them before it's too late!";
    words[4] = "What are you waiting for? \nGet to banning, and please hurry!!!";
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
