package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Bit extends SuperObject{
  public OBJ_Bit(GamePanel gamePan) {
    this.gamePan = gamePan;
    name = "bit";

    try {
      image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/bit.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    image = uTool.scaleImage(image, gamePan.tileSize, gamePan.tileSize);
  }
}
