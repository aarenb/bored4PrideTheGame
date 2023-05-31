package main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * The main class of the application.
 */
public class Main {

  public static JFrame window;

  /**
   * Starts the application.
   *
   * @param args Arguments sent to application.
   */
  public static void main(String[] args) {
    window = new JFrame();
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false); // cant resize window
    window.setTitle("Bored4Pride: The Game");

    // Set custom icon
    ImageIcon icon = new ImageIcon(Main.class.getResource("/icon.png"));
    window.setIconImage(icon.getImage());

    GamePanel gamePanel = new GamePanel();
    window.add(gamePanel);

    gamePanel.config.loadConfig();
    if (gamePanel.fullScreenOn) {
      window.setUndecorated(true);
    }

    window.pack(); // sizes the window to gamePanel

    window.setLocationRelativeTo(null); // put window in center of screen
    window.setVisible(true);

    gamePanel.setupGame();
    gamePanel.startgameThread();
  }
}