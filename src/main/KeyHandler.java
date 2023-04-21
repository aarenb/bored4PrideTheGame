package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
  GamePanel gamePan;
  public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

  public KeyHandler(GamePanel gamePan) {
    this.gamePan = gamePan;
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // not used for this game
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int code = e.getKeyCode();

    if (gamePan.gameState == gamePan.playState) { // Play state:
      // if user press W key
      if (code == KeyEvent.VK_W) {
        upPressed = true;
      }

      // if user press S key
      if (code == KeyEvent.VK_S) {
        downPressed = true;
      }

      // if user press A key
      if (code == KeyEvent.VK_A) {
        leftPressed = true;
      }

      // if user press D key
      if (code == KeyEvent.VK_D) {
        rightPressed = true;
      }

      // if user press escape key [pause]
      if (code == KeyEvent.VK_ESCAPE) {
          enterPressed = true;
      }

      // If user press enter
      if (code == KeyEvent.VK_ENTER) {
        enterPressed = true;
      }

    } else if (gamePan.gameState == gamePan.pauseState) { // Pause:
      // if user press escape key [unpause]
      if (code == KeyEvent.VK_ESCAPE) {
        gamePan.gameState = gamePan.playState;
      }
    } else if (gamePan.gameState == gamePan.dialogueState) { // Dialogue
      // If user press enter
      if (code == KeyEvent.VK_ENTER) {
        gamePan.gameState = gamePan.playState;
      }
    } else if (gamePan.gameState == gamePan.titleState) { // Title screen
      // If user presses up arrow
      if (code == KeyEvent.VK_UP) {
        if (gamePan.ui.commandNum > 0) {
          gamePan.ui.commandNum--;
        }
      }

      // If user presses down arrow
      if (code == KeyEvent.VK_DOWN) {
        if (gamePan.ui.commandNum < 2) {
          gamePan.ui.commandNum++;
        }
      }
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    int code = e.getKeyCode();

    // if user press W key
    if (code == KeyEvent.VK_W) {
      upPressed = false;
    }

    // if user press S key
    if (code == KeyEvent.VK_S) {
      downPressed = false;
    }

    // if user press A key
    if (code == KeyEvent.VK_A) {
      leftPressed = false;
    }

    // if user press D key
    if (code == KeyEvent.VK_D) {
      rightPressed = false;
    }
  }

}