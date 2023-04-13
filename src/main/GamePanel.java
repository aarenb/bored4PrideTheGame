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
  int FPS = 60;

  KeyHandler keyHand = new KeyHandler();
  Thread gameThread;// keeps the game running

  // Player default position:
  int playerX = 100;
  int playerY = 100;
  int playerSpeed = 4;

  public GamePanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);// improves game's rendering performance
    this.addKeyListener(keyHand);
    this.setFocusable(true);// can be set "focused" to get key input
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

    double drawInterval = 1000000000/FPS;
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;

    while (gameThread != null) {
      currentTime = System.nanoTime();
  
      delta += (currentTime - lastTime) / drawInterval;

      lastTime = currentTime;

      if (delta >= 1) {
        update();
        repaint();// calls paintComponent
        delta--;
      }
    }
  }

  public void update () {
    if(keyHand.upPressed == true) {
      playerY = playerY - playerSpeed;// move player up
    } else if (keyHand.downPressed == true) {
      playerY = playerY + playerSpeed; // move player down
    } else if (keyHand.leftPressed == true) {
      playerX = playerX - playerSpeed;// move player left
    } else if (keyHand.rightPressed == true) {
      playerX = playerX + playerSpeed; // move player right
    }
  }

  /**
   * Paint component in window
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D)g;

    g2d.setColor(Color.white);
    g2d.fillRect(playerX, playerY, tileSize, tileSize);

    g2d.dispose();// saves some memory
  }
}
