package main;

import javax.swing.JFrame;

public class Main {
  public static void main(String[] args){
    JFrame window = new JFrame();
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false); // cant resize window
    window.setTitle("Bored4Pride: The Game");

    GamePanel gamePanel = new GamePanel();
    window.add(gamePanel);

    window.pack();// sizes the window to gamePanel

    window.setLocationRelativeTo(null); // put window in center of screen
    window.setVisible(true);

  }
}