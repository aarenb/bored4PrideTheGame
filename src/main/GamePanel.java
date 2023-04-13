package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
  
  // Screen settings
  final int ogTileSize = 16;
  final int scale = 3;
  final int tileSize = ogTileSize * scale;
  final int maxScreenColumn = 16;
  final int maxScreenRow = 12;
  final int screenWidth = tileSize * maxScreenColumn;// 768px
  final int screenHeight = tileSize * maxScreenRow;// 576px

  Thread gameThread;// keeps the game running

  public GamePanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);// improves game's rendering performance
  }

  /**
   * Starts the game thread.
   */
  public void startgameThread() {
    gameThread = new Thread(this);
    gameThread.start();
  }

  @Override
  public void run() {
    while (gameThread != null) {
      update();
      repaint();// calls paintComponent
    }
  }

  public void update () {

  }

  /**
   * Paint component in window
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D)g;

    g2d.setColor(Color.white);
    g2d.fillRect(100, 100, tileSize, tileSize);

    g2d.dispose();// saves some memory
  }
}
