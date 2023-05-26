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
  boolean hasSussyBit;
  // TODO: player direction?

  // Objects on the map
  String mapObjectNames[];
  int mapObjectWorldX[];
  int mapObjectWorldY[];

  // Follow bots stats
  int followBotsLife[];
  int followBotsWorldX[];
  int followBotsWorldY[];
  String followBotsDirection[];
}
