package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Heart extends SuperObject{
  GamePanel gamePan;
  
  public OBJ_Heart(GamePanel gamePan) {
    this.gamePan = gamePan;
    name = "heart";

    try{
      image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/heart_full.png"));
      image2 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/heart_half.png"));
      image3 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/heart_empty.png"));

      // Scale images
      image = uTool.scaleImage(image, gamePan.tileSize, gamePan.tileSize);
      image2 = uTool.scaleImage(image2, gamePan.tileSize, gamePan.tileSize);
      image3 = uTool.scaleImage(image3, gamePan.tileSize, gamePan.tileSize);
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
