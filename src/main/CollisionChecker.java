package main;

import entity.Entity;

public class CollisionChecker {
  GamePanel gamePan;

  public CollisionChecker (GamePanel gamePan) {
    this.gamePan = gamePan;
  }

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
        if (gamePan.tileManager.tile[tileNum1].collision == true || gamePan.tileManager.tile[tileNum2].collision == true) {
          entity.collisionOn = true;
        }
        break;
      case "down":
        entityBottomRow = (entityBottomWorldY + entity.speed) / gamePan.tileSize;
        tileNum1 = gamePan.tileManager.mapTileNum[entityLeftColumn][entityBottomRow]; // left bottom corner
        tileNum2 = gamePan.tileManager.mapTileNum[entityRightColumn][entityBottomRow]; // right bottom corner

         // If any of the tiles entity is about to hit has collision, set entity collision to true
        if (gamePan.tileManager.tile[tileNum1].collision == true || gamePan.tileManager.tile[tileNum2].collision == true) {
          entity.collisionOn = true;
        }
        break;
      case "left":
        entityLeftColumn = (entityLeftWorldX - entity.speed) / gamePan.tileSize;
        tileNum1 = gamePan.tileManager.mapTileNum[entityLeftColumn][entityTopRow]; // left upper corner
        tileNum2 = gamePan.tileManager.mapTileNum[entityLeftColumn][entityBottomRow]; // left bottom corner

         // If any of the tiles entity is about to hit has collision, set entity collision to true
        if (gamePan.tileManager.tile[tileNum1].collision == true || gamePan.tileManager.tile[tileNum2].collision == true) {
          entity.collisionOn = true;
        }
        break;
      case "right":
        entityRightColumn = (entityRightWorldX + entity.speed) / gamePan.tileSize;
        tileNum1 = gamePan.tileManager.mapTileNum[entityRightColumn][entityTopRow]; // right upper corner
        tileNum2 = gamePan.tileManager.mapTileNum[entityRightColumn][entityBottomRow]; // right bottom corner

         // If any of the tiles entity is about to hit has collision, set entity collision to true
        if (gamePan.tileManager.tile[tileNum1].collision == true || gamePan.tileManager.tile[tileNum2].collision == true) {
          entity.collisionOn = true;
        }
        break;
    }
  }

  /**
   * Checks NPC or monster collision.
   * @param entity The player
   * @param target NPCs/Monsters
   * @return
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
            if (entity.solidArea.intersects(target[i].solidArea)) { // If they collide
              entity.collisionOn = true;
              index = i;
            }
            break;
          case "down":
            entity.solidArea.y += entity.speed;
            if (entity.solidArea.intersects(target[i].solidArea)) { // If they collide
              entity.collisionOn = true;
              index = i;
            }
            break;
          case "left":
            entity.solidArea.x -= entity.speed;
            if (entity.solidArea.intersects(target[i].solidArea)) { // If they collide
              entity.collisionOn = true;
              index = i;
            }
            break;
          case "right":
            entity.solidArea.x += entity.speed;
            if (entity.solidArea.intersects(target[i].solidArea)) { // If they collide
              entity.collisionOn = true;
              index = i;
            }
            break;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        target[i].solidArea.x = target[i].solidAreaDefaultX;
        target[i].solidArea.y = target[i].solidAreaDefaultY;
      }
    }
    return index;
  }
}
