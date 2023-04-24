package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import object.OBJ_Heart;
import object.SuperObject;

public class UI {
  GamePanel gamePan;
  Graphics2D g2d;
  BufferedImage heart_full, heart_half, heart_empty;
  Font VCR_OSD_Mono;
  Font VCR_OSD_Mono_80;
  Font VCR_OSD_Mono_58;
  Font VCR_OSD_Mono_40;
  Font VCR_OSD_Mono_24;
  public String currentWords = "";
  public int commandNum = 0; 

  BufferedImage backgroundImg;

  public UI(GamePanel gamePan) {
    this.gamePan = gamePan;

    // Import font
    InputStream input = getClass().getResourceAsStream("/resources/font/VCR_OSD_MONO_1.001.ttf");
    try {
      VCR_OSD_Mono = Font.createFont(Font.TRUETYPE_FONT, input);
    } catch (FontFormatException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Get heart images
    SuperObject heart = new OBJ_Heart(gamePan);
    heart_full = heart.image;
    heart_half = heart.image2;
    heart_empty = heart.image3;

    VCR_OSD_Mono_80 = VCR_OSD_Mono.deriveFont(80f);
    VCR_OSD_Mono_58 = VCR_OSD_Mono.deriveFont(58f);
    VCR_OSD_Mono_40 = VCR_OSD_Mono.deriveFont(40f);
    VCR_OSD_Mono_24 = VCR_OSD_Mono.deriveFont(24f);

    loadImage();
  }

  public void loadImage() {
    try {
      backgroundImg = ImageIO.read(getClass().getResourceAsStream("/resources/titlescreen/prideflag.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void draw(Graphics2D g2d) {
    this.g2d = g2d;

    g2d.setFont(VCR_OSD_Mono_80);
    g2d.setColor(Color.white);

    // Title scrren
    if (gamePan.gameState == gamePan.titleState) {
      drawTitleScreen();
    }

    // Game state
    if (gamePan.gameState == gamePan.playState) {
      drawPlayerHealth();
    }

    // Paused
    if (gamePan.gameState == gamePan.pauseState) {
      drawPlayerHealth();
      drawPauseMenu();
    }

    // Dialogue
    if (gamePan.gameState == gamePan.dialogueState) {
      drawPlayerHealth();
      drawDialogueScreen();
    }

    // Game over
    if (gamePan.gameState == gamePan.gameOverState) {
    }
  }

  public void drawTitleScreen() {
    // Background image
    g2d.drawImage(backgroundImg, 0, 0, gamePan.screenWidth, gamePan.screenHeight, null);

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
    g2d.setColor(new Color(0, 0, 0, 120));
    g2d.fillRect(0, 0, gamePan.screenWidth, gamePan.screenHeight);

    g2d.setColor(Color.white);
    String text = "PAUSED";
  
    int x = getXforCenterTxt(text);
    int y = gamePan.screenHeight / 2;

    g2d.drawString(text, x, y);
  }

  public void drawDialogueScreen() {
    // Draw the window
    int x = gamePan.tileSize*2;
    int y = gamePan.tileSize/2;
    int width = gamePan.screenWidth - (gamePan.tileSize * 4);
    int height = gamePan.tileSize * 4;
    drawLilWindow(x, y, width, height);

    // Draw words
    x += 20;
    y += gamePan.tileSize;
    g2d.setFont(VCR_OSD_Mono_24 );
    g2d.setColor(Color.white);

    for (String line : currentWords.split("\n")) {
      g2d.drawString(line, x, y);
      y += 40;
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
