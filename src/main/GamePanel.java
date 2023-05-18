package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.FollowBot;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
  
  // Screen settings
  final int ogTileSize = 16;
  final int scale = 3;
  public final int tileSize = ogTileSize * scale;
  public final int maxScreenColumn = 20;
  public final int maxScreenRow = 12;
  public final int screenWidth = tileSize * maxScreenColumn;// 960px
  public final int screenHeight = tileSize * maxScreenRow;// 576px
  int FPS = 60;

  // Full screen
  int screenWidth2 = screenWidth;
  int screenHeight2 = screenHeight;
  BufferedImage tempScreen;
  Graphics2D g2d;
  public boolean fullScreenOn = true;

  // World settings
  public final int maxWorldColumn = 50;
  public final int maxWorldRow = 50;

  TileManager tileManager = new TileManager(this);
  public KeyHandler keyHand = new KeyHandler(this);
  public CollisionChecker colChecker = new CollisionChecker(this);
  AssetSetter assSetter = new AssetSetter(this);
  public Player player = new Player(this, keyHand);
  public UI ui = new UI(this);
  Config config = new Config(this);
  Sound music = new Sound();
  Sound SE = new Sound();

  Thread gameThread;// keeps the game running

  public Entity obj[] = new Entity[10];
  public Entity npc[] = new Entity[10];
  public FollowBot followBot[] = new FollowBot[20];
  ArrayList<Entity> allEntities = new ArrayList<>();

  // Game state
  public int gameState;
  public final int titleState = 0;
  public final int playState = 1;
  public final int pauseState = 2;
  public final int dialogueState = 3;
  public final int gameOverState = 4;
  public final int controlsState = 5;
  public final int optionsState = 6;
  public final int winState = 7;

  public GamePanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);// improves game's rendering performance
    this.addKeyListener(keyHand);
    this.setFocusable(true);// can be set "focused" to get key input
  }

  public void setupGame() {
    assSetter.setObject();
    assSetter.setNPC();
    assSetter.setFollowBot();
    gameState = titleState;

    tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
    g2d = (Graphics2D)tempScreen.getGraphics();

    if (fullScreenOn) {
      setFullScreen();
    }

    music.volumeScale = 2;
    SE.volumeScale = 3;
    playMusic(0);
  }

  public void setFullScreen() {

    // Get monitor screen information
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice gd = ge.getDefaultScreenDevice();
    gd.setFullScreenWindow(Main.window);

    screenWidth2 = Main.window.getWidth();
    screenHeight2 = Main.window.getHeight();

  }

  /**
   * Starts the game thread.
   */
  public void startgameThread() {
    gameThread = new Thread(this);
    gameThread.start();
  }

  /**
   * Restarts the game.
   */
  public void restart() {
    player.setDefaultValues();
    assSetter.setObject();
    assSetter.setNPC();
    assSetter.setFollowBot();
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
        drawToTempScreen();
        drawToScreen();
        delta--;
      }
    }
  }

  public void update () {
    if (gameState == playState) {
      player.update();
      for(int i = 0; i < npc.length; i++) {
        if (npc[i] != null) {
          npc[i].update();
        }
      }
      for(int i = 0; i < followBot.length; i++) {
        if (followBot[i] != null) {
          if (followBot[i].alive && !followBot[i].dying) {
            followBot[i].update();
          } else if (!followBot[i].alive) {
            followBot[i].dropItem();
            followBot[i] = null;
          }
        }
      }
    }

    Boolean empty = true;

    for (FollowBot bot : followBot){
      if (bot != null){
        empty = false;
      }
    }
    if (empty) {
      gameState = winState;
    }
  }

  /**
   * Draws the game to the temp screen.
   */
  public void drawToTempScreen() {
    // Title screen
    if (gameState == titleState) {
      ui.draw(g2d);
    } else {
    // Tiles
    tileManager.draw(g2d);

    // Add player
    allEntities.add(player);
    
    // Add NPCs
    for (int i = 0; i < npc.length; i++) {
      if (npc[i] != null) {
        allEntities.add(npc[i]);
      }
    }

    // Add objects
    for (int i = 0; i < obj.length; i++) {
      if (obj[i] != null) {
        allEntities.add(obj[i]);
      }
    }

    // Add follow bots
    for (int i = 0; i < followBot.length; i++) {
      if (followBot[i] != null) {
        allEntities.add(followBot[i]);
      }
    }

    // Sort entities based on worldY
    Collections.sort(allEntities, new Comparator<Entity>() {
      @Override
      public int compare(Entity e1, Entity e2) {
        return Integer.compare(e1.worldY, e2.worldY);
      }
    });

    // Draw entities
    for (int i = 0; i < allEntities.size(); i++) {
      allEntities.get(i).draw(g2d);
    }

    // Empty entities list
    allEntities.clear();

    // UI
    ui.draw(g2d);
    }
  }

  /**
   * Draws temp screen to the actual screen.
   */
  public void drawToScreen(){
    Graphics g = getGraphics();
    g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
    g.dispose();
  }

  public void playMusic(int i) {
    music.setFile(i);
    music.play();
    music.loop();
  }

  public void stopMusic() {
    music.stop();
  }

  public void playSE(int i) {
    SE.setFile(i);
    SE.play();
  }
}
