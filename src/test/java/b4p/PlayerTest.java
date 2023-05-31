package b4p;

import main.GamePanel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test for the Player class.
 */
public class PlayerTest {
  @Test
  public void checkInteractFollowBot() {
    GamePanel gamePan = new GamePanel();

    gamePan.player.interactFollowBot(1);

    Assertions.assertEquals(9, gamePan.player.life);
  }
}
