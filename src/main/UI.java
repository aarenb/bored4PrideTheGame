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
  }

  public void draw(Graphics2D g2d) {
    this.g2d = g2d;

    g2d.setFont(VCR_OSD_Mono_80);
    g2d.setColor(Color.white);

    if (gamePan.gameState == gamePan.playState) {
      // Game state stuff
    }

    // Paused
    if (gamePan.gameState == gamePan.pauseState) {
      drawPauseMenu();
    }

    // Dialogue
    if (gamePan.gameState == gamePan.dialogueState) {
      
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
