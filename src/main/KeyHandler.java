package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
  GamePanel gamePan;
  public boolean upPressed, downPressed, leftPressed, rightPressed;

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
      if (gamePan.gameState == gamePan.playState) {
        gamePan.gameState = gamePan.pauseState;
      } else if (gamePan.gameState == gamePan.pauseState) {
        gamePan.gameState = gamePan.playState;
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