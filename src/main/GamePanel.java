package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
  
  // Screen settings
  final int ogTileSize = 16;
  final int scale = 3;
  public final int tileSize = ogTileSize * scale;
  public final int maxScreenColumn = 16;
  public final int maxScreenRow = 12;
  public final int screenWidth = tileSize * maxScreenColumn;// 768px
  public final int screenHeight = tileSize * maxScreenRow;// 576px
  int FPS = 60;

  // World settings
  public final int maxWorldColumn = 50;
  public final int maxWorldRow = 50;
  public final int worldWidth = tileSize * maxWorldColumn;
  public final int worldHeight = tileSize * maxWorldRow;

  TileManager tileManager = new TileManager(this);
  KeyHandler keyHand = new KeyHandler(this);
  public CollisionChecker colChecker = new CollisionChecker(this);
  public Player player = new Player(this, keyHand);
  public UI ui = new UI(this);

  Thread gameThread;// keeps the game running

  public Entity npc[] = new Entity[10];

  // Game state
  public int gameState;
  public final int playState = 1;
  public final int pauseState = 2;

  public GamePanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);// improves game's rendering performance
    this.addKeyListener(keyHand);
    this.setFocusable(true);// can be set "focused" to get key input
  }

  public void setupGame() {
    gameState = playState;
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
    if (gameState == playState) {
      player.update();
    }
    if (gameState == pauseState) {

    }
  }

  /**
   * Paint component in window
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D)g;

    tileManager.draw(g2d);
    player.draw(g2d);
    ui.draw(g2d);

    g2d.dispose();// saves some memory
  }
}
