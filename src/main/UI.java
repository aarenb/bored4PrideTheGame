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
  Font VCR_OSD_Mono;
  Font VCR_OSD_Mono_80;
  Font VCR_OSD_Mono_58;
  Font VCR_OSD_Mono_40;
  Font VCR_OSD_Mono_28;
  Font Pixeltype;
  Font Pixeltype_36;
  public String currentWords = "";
  public int commandNum = 0;
  public boolean messageOn = false;
  public String message = "";
  int messageCount = 0;
  public boolean resetMessageOn = false;

  BufferedImage backgroundImg1, backgroundImg2;
  BufferedImage bitImg;

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

    VCR_OSD_Mono_80 = VCR_OSD_Mono.deriveFont(80f);
    VCR_OSD_Mono_58 = VCR_OSD_Mono.deriveFont(58f);
    VCR_OSD_Mono_40 = VCR_OSD_Mono.deriveFont(40f);
    VCR_OSD_Mono_28 = VCR_OSD_Mono.deriveFont(28f);
    Pixeltype_36 = Pixeltype.deriveFont(36f);

    loadImage();
  }

  public void loadImage() {
    try {
      backgroundImg1 = ImageIO.read(getClass().getResourceAsStream("/resources/screens/prideflag.png"));
      backgroundImg2 = ImageIO.read(getClass().getResourceAsStream("/resources/screens/transflag.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void showMessage(String message) {
    this.message = message;
    messageOn = true;
  }

  public void draw(Graphics2D g2d) {
    this.g2d = g2d;

    g2d.setFont(VCR_OSD_Mono_80);
    g2d.setColor(Color.white);

    // Title screen
    if (gamePan.gameState == gamePan.titleState) {
      drawTitleScreen();
    }

    // Controls screen
    if (gamePan.gameState == gamePan.controlsState) {
      drawControlsScreen();
    }

    // Game state
    if (gamePan.gameState == gamePan.playState) {
      drawPlayerHealth();
      drawBitCounter();

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

    // Paused
    if (gamePan.gameState == gamePan.pauseState) {
      drawPlayerHealth();
      drawBitCounter();
      drawPauseMenu();
    }

    // Options
    if (gamePan.gameState == gamePan.optionsState) {
      drawPlayerHealth();
      drawBitCounter();
      drawOptionsMenu();
      if (resetMessageOn) {
        resetMessage();
      }
    }

    // Dialogue
    if (gamePan.gameState == gamePan.dialogueState) {
      drawPlayerHealth();
      drawBitCounter();
      drawDialogueScreen();
    }

    // Game over
    if (gamePan.gameState == gamePan.gameOverState) {
      drawGameOver();
    }

    // Win
    if (gamePan.gameState == gamePan.winState) {
      drawWinScreen();
    }
  }

  public void drawTitleScreen() {
    // Background image
    g2d.drawImage(backgroundImg1, 0, 0, gamePan.screenWidth, gamePan.screenHeight, null);

    // Display title
    g2d.setFont(VCR_OSD_Mono_58);
    String text = "Bored4Pride: The Game";
    int x = getXforCenterTxt(text);
    int y = gamePan.tileSize * 3;

    // Shadow
    g2d.setColor(Color.black);
    g2d.drawString(text, x + 5, y + 5);

    // Main text
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

  }

  public void drawControlsScreen() {
    // Background image
    g2d.drawImage(backgroundImg2, 0, 0, gamePan.screenWidth, gamePan.screenHeight, null);

    // Display title
    g2d.setFont(VCR_OSD_Mono_58);
    String text = "Controls";
    int x = getXforCenterTxt(text);
    int y = gamePan.tileSize * 3;

    // Shadow
    g2d.setColor(Color.white);
    g2d.drawString(text, x + 5, y + 5);

    // Main text
    g2d.setColor(Color.black);
    g2d.drawString(text, x, y);

    g2d.setFont(VCR_OSD_Mono_40);
    text = "Walk = W/A/S/D or arrow keys";
    x = getXforCenterTxt(text);
    y += gamePan.tileSize * 2;
    g2d.drawString(text, x, y);

    text = "Talk to NPC = enter";
    x = getXforCenterTxt(text);
    y += 60;
    g2d.drawString(text, x, y);

    text = "Attack = enter";
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

  public void drawBitCounter() {
    g2d.setFont(VCR_OSD_Mono_28);
    int x = gamePan.screenWidth - gamePan.tileSize;
    int y = gamePan.tileSize;

    g2d.drawImage(bitImg, x - gamePan.tileSize, y - 35, null);
    g2d.drawString(String.valueOf(gamePan.player.bits), x, y);
  }

  public void drawPlayerHealth() {
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

    while (i < gamePan.player.life) {
      g2d.drawImage(heart_half, x, y, null);
      i++;
      if (i < gamePan.player.life) {
        g2d.drawImage(heart_full, x, y, null); // draw over with full heart
      }
      i++;
      x += gamePan.tileSize;
    }

    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f)); // Reset opacity

  }

  public void drawPauseMenu() {
    // Make whole screen darker
    g2d.setColor(new Color(0, 0, 0, 120));
    g2d.fillRect(0, 0, gamePan.screenWidth, gamePan.screenHeight);

    g2d.setFont(VCR_OSD_Mono_58);
    g2d.setColor(Color.white);
    String text = "PAUSED";
    int x = getXforCenterTxt(text);
    int y = gamePan.tileSize * 5;
    g2d.drawString(text, x, y);

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

  public void drawOptionsMenu() {
    // Make whole screen darker
    g2d.setColor(new Color(0, 0, 0, 120));
    g2d.fillRect(0, 0, gamePan.screenWidth, gamePan.screenHeight);

    g2d.setFont(VCR_OSD_Mono_28);
    g2d.setColor(Color.white);
    String text = "Full screen";
    int x = getXforCenterTxt(text);
    int y = gamePan.tileSize * 5;
    g2d.drawString(text, x, y);
    if (commandNum == 0) {
      g2d.drawString(">", x - 30, y + 3);
    }

    text = "Back";
    x = getXforCenterTxt(text);
    y += gamePan.tileSize;
    g2d.drawString(text, x, y);
    if (commandNum == 1) {
      g2d.drawString(">", x - 30, y + 3);
    }

    // Full screen check box
    x = gamePan.tileSize * 12 + 20;
    y = gamePan.tileSize * 4 + 27;
    g2d.setStroke(new BasicStroke(3));
    g2d.drawRect(x, y, 20, 20);
    if (gamePan.fullScreenOn) {
      text = "X";
      y = gamePan.tileSize * 5;
      x += 2;
      g2d.drawString(text, x, y);
    }

    gamePan.config.saveConfig();
  }

  public void resetMessage() {
    g2d.setFont(VCR_OSD_Mono_28);
    String text = "Restart the game to implement change";
    int x = getXforCenterTxt(text);
    int y = gamePan.tileSize;

    g2d.drawString(text, x, y);
  }

  public void drawDialogueScreen() {
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

  public void drawGameOver() {
    // Make whole screen darker
    g2d.setColor(new Color(0, 0, 0, 160));
    g2d.fillRect(0, 0, gamePan.screenWidth, gamePan.screenHeight);

    g2d.setColor(Color.white);
    String text = "GAME OVER";
    int x = getXforCenterTxt(text);
    int y = gamePan.tileSize * 5;
    g2d.drawString(text, x, y);

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

  public void drawWinScreen() {
    // Make whole screen darker
    g2d.setColor(new Color(0, 0, 0, 160));
    g2d.fillRect(0, 0, gamePan.screenWidth, gamePan.screenHeight);

    g2d.setColor(Color.white);
    String text = "YOU WIN!";
    int x = getXforCenterTxt(text);
    int y = gamePan.tileSize * 5;
    g2d.drawString(text, x, y);

    g2d.setFont(VCR_OSD_Mono_28);
    x = gamePan.screenWidth / 2;
    y += 60;
    g2d.drawImage(bitImg, x - gamePan.tileSize, y - 35, null);
    g2d.drawString(String.valueOf(gamePan.player.bits), x, y);

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
   * Draws a smaller window on the screen
   * @param x Top and bottom of window
   * @param y Left and right of window
   * @param width Window width
   * @param height Window height
   */
  public void drawLilWindow(int x,int y, int width, int height) {
    Color c = new Color(0, 0, 0);
    g2d.setColor(c);
    g2d.fillRoundRect(x, y, width, height, 35, 35);
  }

  /**
   * Gets the x coord to display text at center.
   * @param text The text to display
   * @return The x coordinate
   */
  public int getXforCenterTxt(String text) {
    int length = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth(); // get length of text
    int x = gamePan.screenWidth / 2 - length / 2;
    return x;
  }
}
