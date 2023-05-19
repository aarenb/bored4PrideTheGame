package main;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import object.OBJ_Bit;
import object.OBJ_Heart;

public class UI { 
  GamePanel gamePan;
  Graphics2D g2d;
  BufferedImage heart_full, heart_half, heart_empty;
  BufferedImage backgroundImg1, backgroundImg2;
  BufferedImage bitImg;

  Font VCR_OSD_Mono;
  Font VCR_OSD_Mono_80;
  Font VCR_OSD_Mono_58;
  Font VCR_OSD_Mono_40;
  Font VCR_OSD_Mono_28;
  Font Pixeltype;
  Font Pixeltype_36;

  public String currentWords = "";
  public int commandNum = 0;
  public String message = "";
  int messageCount = 0;
  public boolean messageOn = false;
  public boolean resetMessageOn = false;
  public boolean loadGameMessageOn = false;

  public UI(GamePanel gamePan) {
    this.gamePan = gamePan;

    // Import font
    InputStream input1 = getClass().getResourceAsStream("/resources/font/VCR_OSD_MONO_1.001.ttf");
    InputStream input2 = getClass().getResourceAsStream("/resources/font/Pixeltype.ttf");
    try {
      VCR_OSD_Mono = Font.createFont(Font.TRUETYPE_FONT, input1);
      Pixeltype = Font.createFont(Font.TRUETYPE_FONT, input2);
    } catch (FontFormatException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Get heart images
    OBJ_Heart heart = new OBJ_Heart(gamePan);
    heart_full = heart.image;
    heart_half = heart.image2;
    heart_empty = heart.image3;

    // Get bit image
    OBJ_Bit bit = new OBJ_Bit(gamePan);
    bitImg = bit.down1;

    // Set size of fonts
    VCR_OSD_Mono_80 = VCR_OSD_Mono.deriveFont(80f);
    VCR_OSD_Mono_58 = VCR_OSD_Mono.deriveFont(58f);
    VCR_OSD_Mono_40 = VCR_OSD_Mono.deriveFont(40f);
    VCR_OSD_Mono_28 = VCR_OSD_Mono.deriveFont(28f);
    Pixeltype_36 = Pixeltype.deriveFont(36f);

    loadImage();
  }

  /**
   * Loads background images.
   */
  public void loadImage() {
    try {
      backgroundImg1 = ImageIO.read(getClass().getResourceAsStream("/resources/screens/prideflag.png"));
      backgroundImg2 = ImageIO.read(getClass().getResourceAsStream("/resources/screens/transflag.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void draw(Graphics2D g2d) {
    this.g2d = g2d;

    g2d.setFont(VCR_OSD_Mono_80);
    g2d.setColor(Color.white);

    // TITLE SCREEN:
    if (gamePan.gameState == gamePan.titleState) {
      drawTitleScreen();

      if (loadGameMessageOn) {
        loadGameMessage();
      }
    }

    // CONTROLS SCREEN:
    if (gamePan.gameState == gamePan.controlsState) {
      loadGameMessageOn = false;
      drawControlsScreen();
    }

    // GAME:
    if (gamePan.gameState == gamePan.playState) {
      loadGameMessageOn = false;
      drawPlayerHealth();
      drawBitCounter();

      /**
       * If showMessage has been called, display the message for a short while.
       */
      if (messageOn) {
        g2d.setFont(VCR_OSD_Mono_28);
        int x = getXforCenterTxt(message);
        int y = gamePan.tileSize * 2;
        g2d.drawString(message, x, y);
        messageCount++;

        if (messageCount > 100){
          messageOn = false;
          messageCount = 0;
        }
      }
    }

    // PAUSED:
    if (gamePan.gameState == gamePan.pauseState) {
      drawPlayerHealth();
      drawBitCounter();
      drawPauseMenu();
    }

    // OPTIONS:
    if (gamePan.gameState == gamePan.optionsState) {
      drawPlayerHealth();
      drawBitCounter();
      drawOptionsMenu();
      if (resetMessageOn) {
        resetMessage();
      }
    }

    // DIALOGUE:
    if (gamePan.gameState == gamePan.dialogueState) {
      drawPlayerHealth();
      drawBitCounter();
      drawDialogueScreen();
    }

    // GAME OVER:
    if (gamePan.gameState == gamePan.gameOverState) {
      drawGameOver();
    }

    // WIN:
    if (gamePan.gameState == gamePan.winState) {
      drawWinScreen();
    }
  }

  /**
   * Draws the title screen.
   */
  private void drawTitleScreen() {
    // Background image
    g2d.drawImage(backgroundImg1, 0, 0, gamePan.screenWidth, gamePan.screenHeight, null);

    // Display title
    g2d.setFont(VCR_OSD_Mono_58);
    String text = "Bored4Pride: The Game";
    int x = getXforCenterTxt(text);
    int y = gamePan.tileSize * 3;
    // Shadow:
    g2d.setColor(Color.black);
    g2d.drawString(text, x + 5, y + 5);
    // Main text:
    g2d.setColor(Color.white);
    g2d.drawString(text, x, y);

    // Menu
    g2d.setFont(VCR_OSD_Mono_40);
    g2d.setColor(Color.black);

    text = "NEW GAME";
    x = getXforCenterTxt(text);
    y += gamePan.tileSize * 3;
    // Shadow:
    g2d.setColor(Color.white);
    g2d.drawString(text, x + 3, y + 3);
    // Main text:
    g2d.setColor(Color.black);
    g2d.drawString(text, x, y);
    if (commandNum == 0) {
      // Shadow:
      g2d.setColor(Color.white);
      g2d.drawString(">", x - gamePan.tileSize + 3, y + 3);
      // Main:
      g2d.setColor(Color.black);
      g2d.drawString(">", x - gamePan.tileSize, y);
    }

    text = "LOAD GAME";
    x = getXforCenterTxt(text);
    y += 55;
    // Shadow:
    g2d.setColor(Color.white);
    g2d.drawString(text, x + 3, y + 3);
    // Main text:
    g2d.setColor(Color.black);
    g2d.drawString(text, x, y);
    if (commandNum == 1) {
      // Shadow:
      g2d.setColor(Color.white);
      g2d.drawString(">", x - gamePan.tileSize + 3, y + 3);
      // Main:
      g2d.setColor(Color.black);
      g2d.drawString(">", x - gamePan.tileSize, y);
    }

    text = "CONTROLS";
    x = getXforCenterTxt(text);
    y += 55;
    // Shadow:
    g2d.setColor(Color.white);
    g2d.drawString(text, x + 3, y + 3);
    // Main text:
    g2d.setColor(Color.black);
    g2d.drawString(text, x, y);
    if (commandNum == 2) {
      // Shadow:
      g2d.setColor(Color.white);
      g2d.drawString(">", x - gamePan.tileSize + 3, y + 3);
      // Main:
      g2d.setColor(Color.black);
      g2d.drawString(">", x - gamePan.tileSize, y);
    }


    text = "QUIT";
    x = getXforCenterTxt(text);
    y += 55;
    // Shadow:
    g2d.setColor(Color.white);
    g2d.drawString(text, x + 3, y + 3);
    // Main text:
    g2d.setColor(Color.black);
    g2d.drawString(text, x, y);
    if (commandNum == 3) {
      // Shadow:
      g2d.setColor(Color.white);
      g2d.drawString(">", x - gamePan.tileSize + 3, y + 3);
      // Main:
      g2d.setColor(Color.black);
      g2d.drawString(">", x - gamePan.tileSize, y);
    }

    // Game version
    g2d.setFont(VCR_OSD_Mono_28);
    g2d.setColor(Color.white);
    x = gamePan.screenWidth - gamePan.tileSize * 3 + - 20;
    y = gamePan.screenHeight - 17;
    text = "Alpha 1.1";
    g2d.drawString(text, x, y);
  }

  /**
   * Draws controls info screen.
   */
  private void drawControlsScreen() {
    // Background image
    g2d.drawImage(backgroundImg2, 0, 0, gamePan.screenWidth, gamePan.screenHeight, null);

    // Display title
    g2d.setFont(VCR_OSD_Mono_58);
    String text = "Controls";
    int x = getXforCenterTxt(text);
    int y = gamePan.tileSize * 3;
    // Shadow:
    g2d.setColor(Color.white);
    g2d.drawString(text, x + 5, y + 5);
    // Main text:
    g2d.setColor(Color.black);
    g2d.drawString(text, x, y);

    // Controls
    g2d.setFont(VCR_OSD_Mono_40);
    text = "Walk = W/A/S/D or arrow keys";
    x = getXforCenterTxt(text);
    y += gamePan.tileSize * 2;
    g2d.drawString(text, x, y);

    text = "Talk to NPC = space";
    x = getXforCenterTxt(text);
    y += 60;
    g2d.drawString(text, x, y);

    text = "Attack = space";
    x = getXforCenterTxt(text);
    y += 60;
    g2d.drawString(text, x, y);

    text = "Pause/settings = escape";
    x = getXforCenterTxt(text);
    y += 60;
    g2d.drawString(text, x, y);

    g2d.setFont(VCR_OSD_Mono_28);
    text = "[ESC] - Back";
    x = 10;
    y = 30;
    g2d.drawString(text, x, y);

  }


  /**
   * Draws the pause menu.
   */
  private void drawPauseMenu() {
    darkenScreen();

    g2d.setFont(VCR_OSD_Mono_58);
    g2d.setColor(Color.white);
    String text = "PAUSED";
    int x = getXforCenterTxt(text);
    int y = gamePan.tileSize * 5;
    g2d.drawString(text, x, y);

    // Menu
    g2d.setFont(VCR_OSD_Mono_28);
    text = "Settings";
    x = getXforCenterTxt(text);
    y += 60;
    g2d.drawString(text, x, y);
    if (commandNum == 0) {
      g2d.drawString(">", x - 30, y + 3);
    }

    text = "Title screen";
    x = getXforCenterTxt(text);
    y += gamePan.tileSize;
    g2d.drawString(text, x, y);
    if (commandNum == 1) {
      g2d.drawString(">", x - 30, y + 3);
    }
  }

  /**
   * Draws the options menu.
   */
  private void drawOptionsMenu() {
    darkenScreen();

    g2d.setFont(VCR_OSD_Mono_28);
    g2d.setColor(Color.white);
    String text = "Full screen";
    int x = getXforCenterTxt(text);
    int y = gamePan.tileSize * 5;
    g2d.drawString(text, x, y);
    if (commandNum == 0) {
      g2d.drawString(">", x - 30, y + 3);
    }

    text = "Music";
    y += gamePan.tileSize;
    g2d.drawString(text, x, y);
    if (commandNum == 1) {
      g2d.drawString(">", x - 30, y + 3);
    }

    text = "Sound effects";
    y += gamePan.tileSize;
    g2d.drawString(text, x, y);
    if (commandNum == 2) {
      g2d.drawString(">", x - 30, y + 3);
    }

    text = "Back";
    y += gamePan.tileSize;
    g2d.drawString(text, x, y);
    if (commandNum == 3) {
      g2d.drawString(">", x - 30, y + 3);
    }

    // Full screen check box
    x = gamePan.tileSize * 13;
    y = gamePan.tileSize * 4 + 27;
    g2d.setStroke(new BasicStroke(3));
    g2d.drawRect(x, y, 20, 20);
    if (gamePan.fullScreenOn) {
      text = "X";
      y = gamePan.tileSize * 5;
      x += 2;
      g2d.drawString(text, x, y);
    }

    // Music volume bar
    x = gamePan.tileSize * 13;
    y = gamePan.tileSize * 5 + 27;
    g2d.drawRect(x, y, 120, 24);
    int volumeWidth = 24 * gamePan.music.volumeScale; 
    g2d.fillRect(x, y, volumeWidth, 24);

    // Sound effect volume bar
    y += gamePan.tileSize;
    g2d.drawRect(x, y, 120, 24);
    volumeWidth = 24 * gamePan.SE.volumeScale;
    g2d.fillRect(x, y, volumeWidth, 24);

    gamePan.config.saveConfig();
  }

  /**
   * Draws a dialogue window with NPC dialogue.
   */
  private void drawDialogueScreen() {
    // Draw the window
    int x = gamePan.tileSize*4;
    int y = gamePan.tileSize/2;
    int width = gamePan.screenWidth - (gamePan.tileSize * 8);
    int height = gamePan.tileSize * 4;
    drawLilWindow(x, y, width, height);

    // Draw words
    x += 30;
    y += gamePan.tileSize;
    g2d.setFont(Pixeltype_36);
    g2d.setColor(Color.white);

    for (String line : currentWords.split("\n")) {
      g2d.drawString(line, x, y);
      y += 40;
    }
  }

  /**
   * Draws game over screen.
   */
  private void drawGameOver() {
    darkenScreen();

    g2d.setColor(Color.white);
    String text = "GAME OVER";
    int x = getXforCenterTxt(text);
    int y = gamePan.tileSize * 5;
    g2d.drawString(text, x, y);

    // Menu
    g2d.setFont(VCR_OSD_Mono_40);
    text = "TRY AGAIN";
    x = getXforCenterTxt(text);
    y += gamePan.tileSize * 2;
    g2d.drawString(text, x, y);
    if (commandNum == 0) {
      g2d.drawString(">", x - gamePan.tileSize, y);
    }

    text = "TITLE SCREEN";
    x = getXforCenterTxt(text);
    y += 55;
    g2d.drawString(text, x, y);
    if (commandNum == 1) {
      g2d.drawString(">", x - gamePan.tileSize, y);
    }
    
  }

  /**
   * Draws the win screen.
   */
  private void drawWinScreen() {
    darkenScreen();

    g2d.setColor(Color.white);
    String text = "YOU WIN!";
    int x = getXforCenterTxt(text);
    int y = gamePan.tileSize * 5;
    g2d.drawString(text, x, y);

    // Amount of bits collected
    g2d.setFont(VCR_OSD_Mono_28);
    x = gamePan.screenWidth / 2;
    y += 60;
    g2d.drawImage(bitImg, x - gamePan.tileSize, y - 35, null);
    g2d.drawString(String.valueOf(gamePan.player.bits), x, y);

    // Menu
    g2d.setFont(VCR_OSD_Mono_40);
    text = "RESTART GAME";
    x = getXforCenterTxt(text);
    y += 60;
    g2d.drawString(text, x, y);
    if (commandNum == 0) {
      g2d.drawString(">", x - gamePan.tileSize, y);
    }

    text = "TITLE SCREEN";
    x = getXforCenterTxt(text);
    y += 55;
    g2d.drawString(text, x, y);
    if (commandNum == 1) {
      g2d.drawString(">", x - gamePan.tileSize, y);
    }
  }

  /**
   * Draws the bit counter in upper right corner.
   */
  private void drawBitCounter() {
    g2d.setFont(VCR_OSD_Mono_28);
    int x = gamePan.screenWidth - gamePan.tileSize;
    int y = gamePan.tileSize;

    g2d.drawImage(bitImg, x - gamePan.tileSize, y - 35, null);
    g2d.drawString(String.valueOf(gamePan.player.bits), x, y);
  }

  /**
   * Draws the player's health in form of hearts.
   */
  private void drawPlayerHealth() {
    int x = 5;
    int y = 5;
    int i = 0;

    if (gamePan.player.invinsible == true) {
      g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f)); // Sets heart opacity
    }

    // Draw blank hearts (max health)
    while (i < gamePan.player.maxLife / 2) {
      g2d.drawImage(heart_empty, x, y, null);
      i++;
      x += gamePan.tileSize;
    }

    // Reset
    x = 5;
    i = 0;

    // Draw player's health
    while (i < gamePan.player.life) {
      g2d.drawImage(heart_half, x, y, null);
      i++;
      if (i < gamePan.player.life) {
        g2d.drawImage(heart_full, x, y, null);
      }
      i++;
      x += gamePan.tileSize;
    }

    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f)); // Reset opacity

  }

  /**
   * Displays message about restarting the game after changing settings.
   */
  private void resetMessage() {
    g2d.setFont(VCR_OSD_Mono_28);
    String text = "Restart the game to implement change";
    int x = getXforCenterTxt(text);
    int y = gamePan.tileSize;

    g2d.drawString(text, x, y);
  }

  /**
   * Shows message on screen.
   * @param message
   */
  public void showMessage(String message) {
    this.message = message;
    messageOn = true;
  }

  /**
   * Displays message about load game feature.
   */
  private void loadGameMessage() {
    g2d.setColor(Color.white);
    g2d.setFont(VCR_OSD_Mono_28);
    String text = "Load game feature coming soon";
    int x = getXforCenterTxt(text);
    int y = gamePan.tileSize;

    g2d.drawString(text, x, y);
  }

  /**
   * Makes the entire screen darker.
   */
  private void darkenScreen() {
    g2d.setColor(new Color(0, 0, 0, 120));
    g2d.fillRect(0, 0, gamePan.screenWidth, gamePan.screenHeight);
  }

  /**
   * Draws a smaller window on the screen
   * @param x Top and bottom of window
   * @param y Left and right of window
   * @param width Window width
   * @param height Window height
   */
  private void drawLilWindow(int x,int y, int width, int height) {
    Color c = new Color(0, 0, 0);
    g2d.setColor(c);
    g2d.fillRoundRect(x, y, width, height, 35, 35);
  }

  /**
   * Gets the x coord to display text at center.
   * @param text The text to display
   * @return The x coordinate
   */
  private int getXforCenterTxt(String text) {
    int length = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth(); // get length of text
    int x = gamePan.screenWidth / 2 - length / 2;
    return x;
  }
}
