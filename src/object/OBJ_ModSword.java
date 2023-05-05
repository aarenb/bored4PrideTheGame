package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_ModSword extends SuperObject {

  public OBJ_ModSword(GamePanel gamePan) {
    this.gamePan = gamePan;
    
    try {
      image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/modsword.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    image = uTool.scaleImage(image, gamePan.tileSize, gamePan.tileSize);
  }
}
