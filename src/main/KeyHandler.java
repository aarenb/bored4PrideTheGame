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
      // if user press W key / up arrow
      if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP ) {
        upPressed = true;
      }

      // if user press S key / down arrow
      if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
        downPressed = true;
      }

      // if user press A key / left arrow
      if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
        leftPressed = true;
      }

      // if user press D key / right arrow
      if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
        rightPressed = true;
      }

      // if user press escape key [pause]
      if (code == KeyEvent.VK_ESCAPE) {
        gamePan.gameState = gamePan.pauseState;
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

      // If user presses W key / up arrow
      if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
        gamePan.ui.commandNum--;
        if (gamePan.ui.commandNum < 0) {
          gamePan.ui.commandNum = 1;
        }
      }

      // If user presses S key / down arrow
      if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
        gamePan.ui.commandNum++;
        if (gamePan.ui.commandNum > 1) {
          gamePan.ui.commandNum = 0;
        }
      }

      // If user presses enter
      if (code == KeyEvent.VK_ENTER) {
        if (gamePan.ui.commandNum == 0) {
          gamePan.gameState = gamePan.optionsState;
        } else if (gamePan.ui.commandNum == 1) {
          gamePan.ui.commandNum = 0;
          gamePan.gameState = gamePan.titleState;
          gamePan.stopMusic();
          gamePan.playMusic(0);
        }
      }
    } else if (gamePan.gameState == gamePan.optionsState) { // Options:
      // If user presses W key / up arrow
      if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
        gamePan.ui.commandNum--;
        if (gamePan.ui.commandNum < 0) {
          gamePan.ui.commandNum = 1;
        }
      }

      // If user presses S key / down arrow
      if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
        gamePan.ui.commandNum++;
        if (gamePan.ui.commandNum > 1) {
          gamePan.ui.commandNum = 0;
        }
      }

      if (code == KeyEvent.VK_ENTER) {
        if (gamePan.ui.commandNum == 0) {
          if (!gamePan.fullScreenOn) {
            gamePan.fullScreenOn = true;
          } else if (gamePan.fullScreenOn) {
            gamePan.fullScreenOn = false;
          }
          gamePan.ui.resetMessageOn = true;
        } else if (gamePan.ui.commandNum == 1) {
          gamePan.ui.commandNum = 0;
          gamePan.gameState = gamePan.pauseState;
        }
      }
    } else if (gamePan.gameState == gamePan.dialogueState) { // Dialogue:
      // If user press enter
      if (code == KeyEvent.VK_ENTER) {
        gamePan.gameState = gamePan.playState;
      }
    } else if (gamePan.gameState == gamePan.titleState) { // Title screen:
      // If user presses W key / up arrow
      if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
        gamePan.ui.commandNum--;
        if (gamePan.ui.commandNum < 0) {
          gamePan.ui.commandNum = 3;
        }
      }

      // If user presses S key / down arrow
      if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
        gamePan.ui.commandNum++;
        if (gamePan.ui.commandNum > 3) {
          gamePan.ui.commandNum = 0;
        }
      }

      // If user press enter
      if (code == KeyEvent.VK_ENTER) {
        if (gamePan.ui.commandNum == 0) {
          // New game
          gamePan.restart();
          gamePan.gameState = gamePan.playState;
          gamePan.stopMusic();
          gamePan.playMusic(1);
        } else if (gamePan.ui.commandNum == 1) {
          // Load game
          gamePan.ui.loadGameMessageOn = true;
        } else if (gamePan.ui.commandNum == 2) {
          // Controls
          gamePan.gameState = gamePan.controlsState;
        } else if (gamePan.ui.commandNum == 3) {
          // Quit
          System.exit(0);
        }
      }
    } else if (gamePan.gameState == gamePan.controlsState) { // Controls screen:
      if (code == KeyEvent.VK_ESCAPE) {
        gamePan.gameState = gamePan.titleState;
      }
    } else if (gamePan.gameState == gamePan.gameOverState || gamePan.gameState == gamePan.winState) { // Game over/win:
      // If user presses W key / up arrow
      if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
        gamePan.ui.commandNum--;
        if (gamePan.ui.commandNum < 0) {
          gamePan.ui.commandNum = 1;
        }
      }

      // If user presses S key / down arrow
      if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
        gamePan.ui.commandNum++;
        if (gamePan.ui.commandNum > 1) {
          gamePan.ui.commandNum = 0;
        }
      }

      // If user press enter
      if (code == KeyEvent.VK_ENTER) {
        if (gamePan.ui.commandNum == 0) {
          gamePan.restart();
          gamePan.gameState = gamePan.playState;
        } else if (gamePan.ui.commandNum == 1) {
          gamePan.restart();
          gamePan.gameState = gamePan.titleState;
          gamePan.stopMusic();
          gamePan.playMusic(0);
          gamePan.ui.commandNum = 0;
        }
      }
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    int code = e.getKeyCode();

    // if user press W key / up arrow
    if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
      upPressed = false;
    }

    // if user press S key / up arrow
    if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
      downPressed = false;
    }

    // if user press A key / left arrow
    if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
      leftPressed = false;
    }

    // if user press D key / right arrow
    if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
      rightPressed = false;
    }

    if (code == KeyEvent.VK_ENTER) {
      enterPressed = false;
    }
  }

}