package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;

public class UI {
  GamePanel gamePan;
  Graphics2D g2d;
  Font VCR_OSD_Mono;
  Font VCR_OSD_Mono_80;
  Font VCR_OSD_Mono_24;
  public String currentWords = "";

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

    VCR_OSD_Mono_80 = VCR_OSD_Mono.deriveFont(80f);
    VCR_OSD_Mono_24 = VCR_OSD_Mono.deriveFont(24f);
  }

  public void draw(Graphics2D g2d) {
    this.g2d = g2d;

    g2d.setFont(VCR_OSD_Mono_80);
    g2d.setColor(Color.white);

    // Game state
    if (gamePan.gameState == gamePan.playState) {
      // TODO: Add game state stuff
    }

    // Paused
    if (gamePan.gameState == gamePan.pauseState) {
      drawPauseMenu();
    }

    // Dialogue
    if (gamePan.gameState == gamePan.dialogueState) {
      drawDialogueScreen();
    }
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
    x += gamePan.tileSize;
    y += gamePan.tileSize;
    g2d.setFont(VCR_OSD_Mono_24 );
    g2d.setColor(Color.white);
    g2d.drawString(currentWords, x, y);
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
