package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Represents a utility tool.
 */
public class UtilityTool {

  /**
   * Scales an image.
   *
   * @param og Original image
   * @param width Width to scale image to
   * @param height Height to scale image to
   * @return The scaled image
   */
  public BufferedImage scaleImage(BufferedImage og, int width, int height) {
    BufferedImage scaledImg = new BufferedImage(width, height, og.getType());
    Graphics2D g2d = scaledImg.createGraphics();
    g2d.drawImage(og, 0, 0, width, height, null);
    g2d.dispose();

    return scaledImg;
  }
}
