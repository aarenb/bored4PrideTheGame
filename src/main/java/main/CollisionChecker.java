package main;

import entity.Entity;

/**
 * Represents a collision checker.
 */
public class CollisionChecker {
  GamePanel gamePan;

  public CollisionChecker (GamePanel gamePan) {
    this.gamePan = gamePan;
  }

  /**
   * Checks collision with tiles.
   *
   * @param entity The entity to check collision with tiles for.
   */
  public void checkTile(Entity entity) {
    // Get coords of the "solid" part of entity
    int entityLeftWorldX = entity.worldX + entity.solidArea.x;
    int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
    int entityTopWorldY = entity.worldY + entity.solidArea.y;
    int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

    // Get columns and rows where entity is
    int entityLeftColumn = entityLeftWorldX/gamePan.tileSize;
    int entityRightColumn = entityRightWorldX/gamePan.tileSize;
    int entityTopRow = entityTopWorldY/gamePan.tileSize;
    int entityBottomRow = entityBottomWorldY/gamePan.tileSize;

    int tileNum1, tileNum2;

    // Check the tiles in the direction the entity is currently moving, predicting where it will go:
    switch(entity.direction) {
      case "up":
        entityTopRow = (entityTopWorldY - entity.speed) / gamePan.tileSize;
        tileNum1 = gamePan.tileManager.mapTileNum[entityLeftColumn][entityTopRow]; // left upper corner
        tileNum2 = gamePan.tileManager.mapTileNum[entityRightColumn][entityTopRow]; // right upper corner

        // If any of the tiles entity is about to hit has collision, set entity collision to true
        if (gamePan.tileManager.tile[tileNum1].collision || gamePan.tileManager.tile[tileNum2].collision) {
          entity.collisionOn = true;
        }
        break;
      case "down":
        entityBottomRow = (entityBottomWorldY + entity.speed) / gamePan.tileSize;
        tileNum1 = gamePan.tileManager.mapTileNum[entityLeftColumn][entityBottomRow]; // left bottom corner
        tileNum2 = gamePan.tileManager.mapTileNum[entityRightColumn][entityBottomRow]; // right bottom corner

         // If any of the tiles entity is about to hit has collision, set entity collision to true
        if (gamePan.tileManager.tile[tileNum1].collision || gamePan.tileManager.tile[tileNum2].collision) {
          entity.collisionOn = true;
        }
        break;
      case "left":
        entityLeftColumn = (entityLeftWorldX - entity.speed) / gamePan.tileSize;
        tileNum1 = gamePan.tileManager.mapTileNum[entityLeftColumn][entityTopRow]; // left upper corner
        tileNum2 = gamePan.tileManager.mapTileNum[entityLeftColumn][entityBottomRow]; // left bottom corner

         // If any of the tiles entity is about to hit has collision, set entity collision to true
        if (gamePan.tileManager.tile[tileNum1].collision || gamePan.tileManager.tile[tileNum2].collision) {
          entity.collisionOn = true;
        }
        break;
      case "right":
        entityRightColumn = (entityRightWorldX + entity.speed) / gamePan.tileSize;
        tileNum1 = gamePan.tileManager.mapTileNum[entityRightColumn][entityTopRow]; // right upper corner
        tileNum2 = gamePan.tileManager.mapTileNum[entityRightColumn][entityBottomRow]; // right bottom corner

         // If any of the tiles entity is about to hit has collision, set entity collision to true
        if (gamePan.tileManager.tile[tileNum1].collision || gamePan.tileManager.tile[tileNum2].collision) {
          entity.collisionOn = true;
        }
        break;
    }
  }

  /**
   * Checks NPC or monster collision.
   *
   * @param entity The player
   * @param target NPCs/Monsters
   * @return Index of NPC collision is happening with (999 if no collision)
   */
  public int checkEntity(Entity entity, Entity[] target) {
    int index = 999;

    for (int i = 0; i < target.length; i++) {
      if (target[i] != null) {
        // Get entity's solid area coords
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        // Get targets solid area coords
        target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
        target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

        switch (entity.direction) {
          case "up":
            entity.solidArea.y -= entity.speed;
            break;
          case "down":
            entity.solidArea.y += entity.speed;
            break;
          case "left":
            entity.solidArea.x -= entity.speed;
            break;
          case "right":
            entity.solidArea.x += entity.speed;
            break;
        }
        if (entity.solidArea.intersects(target[i].solidArea)) { // If they collide
          if (target[i] != entity) { // Make sure it's not the entity colliding with itself
            entity.collisionOn = true;
            index = i;
          }
        }

        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        target[i].solidArea.x = target[i].solidAreaDefaultX;
        target[i].solidArea.y = target[i].solidAreaDefaultY;
      }
    }
    return index;
  }

  /**
   * Checks object collision.
   *
   * @param entity The entity to check collision with object for.
   * @param player True if entity is player, otherwise false.
   * @return Index of object with collision (999 if no collision)
   */
  public int checkObject(Entity entity, boolean player) {
    int index = 999;

    for (int i = 0; i < gamePan.obj.length; i++) {
      if (gamePan.obj[i] != null) {
        // Get entity's solid area coords
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        // Get objects solid area coords
        gamePan.obj[i].solidArea.x = gamePan.obj[i].worldX + gamePan.obj[i].solidArea.x;
        gamePan.obj[i].solidArea.y = gamePan.obj[i].worldY + gamePan.obj[i].solidArea.y;

        switch (entity.direction) {
          case "up":
            entity.solidArea.y -= entity.speed;
            break;
          case "down":
            entity.solidArea.y += entity.speed;
            break;
          case "left":
            entity.solidArea.x -= entity.speed;
            break;
          case "right":
            entity.solidArea.x += entity.speed;
            break;
        }

        if (entity.solidArea.intersects(gamePan.obj[i].solidArea)) { // If they collide
          if (!player) {
            entity.collisionOn = true;
          } 
          index = i;
        }

        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gamePan.obj[i].solidArea.x = gamePan.obj[i].solidAreaDefaultX;
        gamePan.obj[i].solidArea.y = gamePan.obj[i].solidAreaDefaultY;
        }
      }
    return index;
  }

  /**
   * Checks if entity is colliding with player.
   *
   * @param entity NPC / monster to check
   * @return True if entity is colliding with player, otherwise false.
   */
  public boolean checkPlayer(Entity entity) {
    boolean touchPlayer = false;

    // Get entity's solid area coords
    entity.solidArea.x = entity.worldX + entity.solidArea.x;
    entity.solidArea.y = entity.worldY + entity.solidArea.y;

    // Get player's solid area coords
    gamePan.player.solidArea.x = gamePan.player.worldX + gamePan.player.solidArea.x;
    gamePan.player.solidArea.y = gamePan.player.worldY + gamePan.player.solidArea.y;

    switch (entity.direction) {
      case "up":
        entity.solidArea.y -= entity.speed;
        break;
      case "down":
        entity.solidArea.y += entity.speed;
        break;
      case "left":
        entity.solidArea.x -= entity.speed;
        break;
      case "right":
        entity.solidArea.x += entity.speed;
        break;
    }
    if (entity.solidArea.intersects(gamePan.player.solidArea)) { // If they collide
      entity.collisionOn = true;
      touchPlayer = true;
    }

    entity.solidArea.x = entity.solidAreaDefaultX;
    entity.solidArea.y = entity.solidAreaDefaultY;
    gamePan.player.solidArea.x = gamePan.player.solidAreaDefaultX;
    gamePan.player.solidArea.y = gamePan.player.solidAreaDefaultY;

    return touchPlayer;
  }
}
