package data;

import java.io.Serializable;

/**
 * Represents a data storage.
 */
public class DataStorage implements Serializable {
  // Player stats
  int playerLife;
  int playerWorldX;
  int playerWorldY;
  int bits;
  boolean hasSword;
  boolean hasSussyBit;
  String playerDirection;

  // Objects on the map
  String[] mapObjectNames;
  int[] mapObjectWorldX;
  int[] mapObjectWorldY;

  // Follow bots stats
  int[] followBotsLife;
  int[] followBotsWorldX;
  int[] followBotsWorldY;
  String[] followBotsDirection;

  // Game difficulty
  int difficulty;
}
