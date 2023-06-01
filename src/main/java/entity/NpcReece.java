package entity;

import main.GamePanel;

/**
 * Represents a Reece NPC.
 */
public class NpcReece extends Entity {
  /**
   * Creates a Reece NPC.
   *
   * @param gamePan Game's GamePanel object.
   */
  public NpcReece(GamePanel gamePan) {
    super(gamePan);

    getImage();
    setWords();
  }

  /**
   * Load NPC images.
   */
  public void getImage() {
    down1 = setup("/npc/reece/reece_down1", gamePan.tileSize, gamePan.tileSize);
    down2 = setup("/npc/reece/reece_down2", gamePan.tileSize, gamePan.tileSize);
  }

  /**
   * Sets the NPC dialogue.
   */
  public void setWords() {
    words[0] = "Hi there, my name is Reece. Are you in need of a \nminecraft server? 404 hosting got lots of" 
      + "\noptions for you!";
    words[1] = "Oh, you're here to fix the follow bot problem. \nThank god! The 100 player survival games is"  
      + "\nstarting soon, and they're ruining it!!";
    words[2] = "Anyways if you do need a minecraft server you \nknow where to find me... *wink*";
  }
}
