package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Represents a key handler.
 */
public class KeyHandler implements KeyListener{
  GamePanel gamePan;
  public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed;
  private int npcIndex = 0;

  public KeyHandler(GamePanel gamePan) {
    this.gamePan = gamePan;
  }

  public void setNPCIndex(int i) {
    npcIndex = i;
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // not used for this game
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int code = e.getKeyCode();

    // PLAY STATE:
    if (gamePan.gameState == gamePan.playState) {
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

      // If user press space bar
      if (code == KeyEvent.VK_SPACE) {
        spacePressed = true;
      }
      // PAUSE:
    } else if (gamePan.gameState == gamePan.pauseState) {
      // if user press escape key [unpause]
      if (code == KeyEvent.VK_ESCAPE) {
        gamePan.gameState = gamePan.playState;
      }

      // If user presses W key / up arrow
      if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
        gamePan.playSE(2);
        gamePan.ui.commandNum--;
        if (gamePan.ui.commandNum < 0) {
          gamePan.ui.commandNum = 1;
        }
      }

      // If user presses S key / down arrow
      if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
        gamePan.playSE(2);
        gamePan.ui.commandNum++;
        if (gamePan.ui.commandNum > 1) {
          gamePan.ui.commandNum = 0;
        }
      }

      // If user presses space bar
      if (code == KeyEvent.VK_SPACE) {
        if (gamePan.ui.commandNum == 0) {
          gamePan.gameState = gamePan.optionsState;
        } else if (gamePan.ui.commandNum == 1) {
          gamePan.ui.commandNum = 0;
          gamePan.gameState = gamePan.titleState;
          gamePan.stopMusic();
          gamePan.playMusic(0);
        }
      }
      // OPTIONS:
    } else if (gamePan.gameState == gamePan.optionsState) {
      // If user presses W key / up arrow
      if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
        gamePan.playSE(2);
        gamePan.ui.commandNum--;
        if (gamePan.ui.commandNum < 0) {
          gamePan.ui.commandNum = 3;
        }
      }

      // If user presses S key / down arrow
      if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
        gamePan.playSE(2);
        gamePan.ui.commandNum++;
        if (gamePan.ui.commandNum > 3) {
          gamePan.ui.commandNum = 0;
        }
      }

      // If user press space bar
      if (code == KeyEvent.VK_SPACE) {
        if (gamePan.ui.commandNum == 0) {
          // Full screen:
          gamePan.playSE(2);
          if (!gamePan.fullScreenOn) {
            gamePan.fullScreenOn = true;
          } else if (gamePan.fullScreenOn) {
            gamePan.fullScreenOn = false;
          }
          gamePan.ui.resetMessageOn = true;
        } else if (gamePan.ui.commandNum == 3) {
          // Title screen:
          gamePan.ui.commandNum = 0;
          gamePan.gameState = gamePan.pauseState;
        }
      }

      // if user press A key / left arrow
      if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
        if (gamePan.ui.commandNum == 1 && gamePan.music.volumeScale > 0) {
          gamePan.playSE(2);
          gamePan.music.volumeScale--;
          gamePan.music.checkVolume();
        }
        if (gamePan.ui.commandNum == 2 && gamePan.SE.volumeScale > 0) {
          gamePan.SE.volumeScale--;
          gamePan.playSE(2);
        }
      }

      // if user press D key / right arrow
      if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
          if (gamePan.ui.commandNum == 1 && gamePan.music.volumeScale < 5) {
            gamePan.playSE(2);
            gamePan.music.volumeScale++;
            gamePan.music.checkVolume();
          }
          if (gamePan.ui.commandNum == 2 && gamePan.SE.volumeScale < 5) {
            gamePan.SE.volumeScale++;
            gamePan.playSE(2);
          }
      }
      // DIALOGUE:
    } else if (gamePan.gameState == gamePan.dialogueState) {
      // If user press space bar
      if (code == KeyEvent.VK_SPACE) {
        if (gamePan.npc[npcIndex].words[gamePan.npc[npcIndex].speakIndex] == null) { // if there are no more npc dialogue left
          gamePan.gameState = gamePan.playState;
          gamePan.npc[npcIndex].speakIndex = 0;
        } else {
          gamePan.npc[npcIndex].speak();
        }
      }
      // TITLE SCREEN:
    } else if (gamePan.gameState == gamePan.titleState) { 
      // If user presses W key / up arrow
      if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
        gamePan.playSE(2);
        gamePan.ui.commandNum--;
        if (gamePan.ui.commandNum < 0) {
          gamePan.ui.commandNum = 3;
        }
      }

      // If user presses S key / down arrow
      if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
        gamePan.playSE(2);
        gamePan.ui.commandNum++;
        if (gamePan.ui.commandNum > 3) {
          gamePan.ui.commandNum = 0;
        }
      }

      // If user press space bar
      if (code == KeyEvent.VK_SPACE) {
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
      // CONTROLS SCREEN:
    } else if (gamePan.gameState == gamePan.controlsState) {
      if (code == KeyEvent.VK_ESCAPE) {
        gamePan.gameState = gamePan.titleState;
      }
      // GAME OVER/WIN:
    } else if (gamePan.gameState == gamePan.gameOverState || gamePan.gameState == gamePan.winState) {
      // If user presses W key / up arrow
      if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
        gamePan.playSE(2);
        gamePan.ui.commandNum--;
        if (gamePan.ui.commandNum < 0) {
          gamePan.ui.commandNum = 1;
        }
      }

      // If user presses S key / down arrow
      if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
        gamePan.playSE(2);
        gamePan.ui.commandNum++;
        if (gamePan.ui.commandNum > 1) {
          gamePan.ui.commandNum = 0;
        }
      }

      // If user press space bar
      if (code == KeyEvent.VK_SPACE) {
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

    // if user press space bar
    if (code == KeyEvent.VK_SPACE) {
      spacePressed = false;
    }
  }

}