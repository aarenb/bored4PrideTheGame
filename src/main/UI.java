package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {
  GamePanel gamePan;
  Graphics2D g2d;
  Font arial_40;
  public boolean messageOn = false;
  public String message = "";

  public UI(GamePanel gamePan) {
    this.gamePan = gamePan;

    arial_40 = new Font("Arial", Font.PLAIN, 40);
  }

  /**
   * Display a message on screen.
   * @param text Message to display.
   */
  public void showMessage(String text) {
    message = text;
    messageOn = true;
  }

  public void draw(Graphics2D g2d) {
    this.g2d = g2d;

    g2d.setFont(arial_40);
    g2d.setColor(Color.white);

    if (gamePan.gameState == gamePan.playState) {
      // Game state stuff
    }
    if (gamePan.gameState == gamePan.pauseState) {
      drawPauseMenu();
    }
  }

  public void drawPauseMenu() {
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
