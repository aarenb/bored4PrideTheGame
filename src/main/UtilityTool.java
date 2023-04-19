package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {

  public BufferedImage scaledImage(BufferedImage og, int width, int height) {
    BufferedImage scaledImg = new BufferedImage(width, height, og.getType());
    Graphics2D g2d = scaledImg.createGraphics();
    g2d.drawImage(og, 0, 0, width, height, null);
    g2d.dispose();

    return scaledImg;
  }
}
