package data;

import java.io.Serializable;

/**
 * Represents a data storage.
 */
public class DataStorage implements Serializable {
  // Player stats
  int life;
  int playerWorldX, playerWorldY;
  int bits;
  boolean hasSword;

  // Objects on the map
  String mapObjectNames[];
  int mapObjectWorldX[];
  int mapObjectWorldY[];
}
