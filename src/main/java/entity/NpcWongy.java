package entity;

import main.GamePanel;

/**
 * Represents a Wongy NPC.
 */
public class NpcWongy extends Entity {
  /**
   * Creates a new Wongy NPC.
   *
   * @param gamePan Game's GamePanel object.
   */
  public NpcWongy(GamePanel gamePan) {
    super(gamePan);
    speed = 1;

    getImage();
    setWords();
  }

  /**
   * Load NPC images.
   */
  public void getImage() {
    up1 = setup("/npc/wongy/wongy_up1", gamePan.tileSize, gamePan.tileSize);
    up2 = setup("/npc/wongy/wongy_up2", gamePan.tileSize, gamePan.tileSize);
    down1 = setup("/npc/wongy/wongy_down1", gamePan.tileSize, gamePan.tileSize);
    down2 = setup("/npc/wongy/wongy_down2", gamePan.tileSize, gamePan.tileSize);
  }

  /**
   * Sets the NPC dialogue.
   */
  public void setWords() {
    words[0] = "*oh no oh no...*\nOh hi there! Finally someone to help! I'm Wongy. \nGrass sent you, right?";
    words[1] = "Bored4Pride starts in a matter of hours, but \neverything is so messed up! Please say you \ncan help!!";
    words[2] = "Someone must have taken sery bot hostage, and \nnow there's follow bots everywhere!";
    words[3] = "I need you to find and ban all of them for me, \nthere's a mod sword not far from here. Find it" 
      + "\nand use it to ban them before it's too late!";
    words[4] = "What are you waiting for? \nGet to banning, and please hurry!!!";
  }

  /**
   * Makes Wongy pace up and down.
   */
  public void setAction() {

    antiSpinCounter++;
    
    if (antiSpinCounter == 130) {
      switch (direction) {
        case "down":
          direction = "up";
          break;
        case "up":
          direction = "down";
          break;
        default:
          break;
      }

      antiSpinCounter = 0;
    }
  }

}
