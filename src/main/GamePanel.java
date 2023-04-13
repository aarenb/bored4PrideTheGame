package main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
  
  // Screen settings
  final int ogTileSize = 16;
  final int scale = 3;
  final int tileSize = ogTileSize * scale;
  final int maxScreenColumn = 16;
  final int maxScreenRow = 12;
  final int screenWidth = tileSize * maxScreenColumn;// 768px
  final int screenHeight = tileSize * maxScreenRow;// 576px

  public GamePanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);// improves game's rendering performance
  }
}
