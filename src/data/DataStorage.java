package data;

import java.io.Serializable;

/**
 * Represents a data storage.
 */
public class DataStorage implements Serializable {
  // Player stats
  int life;
  int worldX, worldY;
  int bits;
  boolean hasSword;
}
