package main;

import entity.FollowBot;
import entity.NPC_Alex;
import entity.NPC_Grass;
import entity.NPC_Reece;
import entity.NPC_Wongy;
import object.OBJ_Bit;
import object.OBJ_ModSword;
import object.OBJ_SussyBit;

/**
 * Represents an asset setter.
 */
public class AssetSetter {
  GamePanel gamePan;

  public AssetSetter(GamePanel gamePan) {
    this.gamePan = gamePan;
  }

/**
 * Places NPCs on the game map.
 */
  public void setNPC() {
    gamePan.npc[0] = new NPC_Wongy(gamePan);
    gamePan.npc[0].worldX = gamePan.tileSize * 18;
    gamePan.npc[0].worldY = gamePan.tileSize * 32;

    gamePan.npc[1] = new NPC_Grass(gamePan);
    gamePan.npc[1].worldX = gamePan.tileSize * 7;
    gamePan.npc[1].worldY = gamePan.tileSize * 11;

    gamePan.npc[2] = new NPC_Alex(gamePan);
    gamePan.npc[2].worldX = gamePan.tileSize * 44;
    gamePan.npc[2].worldY = gamePan.tileSize * 7;

    gamePan.npc[3] = new NPC_Reece(gamePan);
    gamePan.npc[3].worldX = gamePan.tileSize * 44;
    gamePan.npc[3].worldY = gamePan.tileSize * 19;
  }

  /**
   * Places follow bots on the game map.
   */
  public void setFollowBot() {
    gamePan.followBot[0] = new FollowBot(gamePan);
    gamePan.followBot[0].worldX = gamePan.tileSize * 20;
    gamePan.followBot[0].worldY = gamePan.tileSize * 12;

    gamePan.followBot[1] = new FollowBot(gamePan);
    gamePan.followBot[1].worldX = gamePan.tileSize * 10;
    gamePan.followBot[1].worldY = gamePan.tileSize * 8;

    gamePan.followBot[2] = new FollowBot(gamePan);
    gamePan.followBot[2].worldX = gamePan.tileSize * 18;
    gamePan.followBot[2].worldY = gamePan.tileSize * 16;

    gamePan.followBot[3] = new FollowBot(gamePan);
    gamePan.followBot[3].worldX = gamePan.tileSize * 6;
    gamePan.followBot[3].worldY = gamePan.tileSize * 16;

    gamePan.followBot[4] = new FollowBot(gamePan);
    gamePan.followBot[4].worldX = gamePan.tileSize * 9;
    gamePan.followBot[4].worldY = gamePan.tileSize * 5;

    gamePan.followBot[5] = new FollowBot(gamePan);
    gamePan.followBot[5].worldX = gamePan.tileSize * 8;
    gamePan.followBot[5].worldY = gamePan.tileSize * 16;

    gamePan.followBot[6] = new FollowBot(gamePan);
    gamePan.followBot[6].worldX = gamePan.tileSize * 40;
    gamePan.followBot[6].worldY = gamePan.tileSize * 6;

    gamePan.followBot[7] = new FollowBot(gamePan);
    gamePan.followBot[7].worldX = gamePan.tileSize * 40;
    gamePan.followBot[7].worldY = gamePan.tileSize * 12;

    gamePan.followBot[8] = new FollowBot(gamePan);
    gamePan.followBot[8].worldX = gamePan.tileSize * 33;
    gamePan.followBot[8].worldY = gamePan.tileSize * 7;

    gamePan.followBot[9] = new FollowBot(gamePan);
    gamePan.followBot[9].worldX = gamePan.tileSize * 34;
    gamePan.followBot[9].worldY = gamePan.tileSize * 3;

    gamePan.followBot[10] = new FollowBot(gamePan);
    gamePan.followBot[10].worldX = gamePan.tileSize * 40;
    gamePan.followBot[10].worldY = gamePan.tileSize * 17;

    gamePan.followBot[11] = new FollowBot(gamePan);
    gamePan.followBot[11].worldX = gamePan.tileSize * 42;
    gamePan.followBot[11].worldY = gamePan.tileSize * 24;

    gamePan.followBot[12] = new FollowBot(gamePan);
    gamePan.followBot[12].worldX = gamePan.tileSize * 36;
    gamePan.followBot[12].worldY = gamePan.tileSize * 18;
  }

  /**
   * Places objects on the game map.
   */
  public void setObject() {
    gamePan.obj[0] = new OBJ_ModSword(gamePan);
    gamePan.obj[0].worldX = gamePan.tileSize * 29;
    gamePan.obj[0].worldY = gamePan.tileSize * 26;
    gamePan.obj[1] = new OBJ_Bit(gamePan);
    gamePan.obj[1].worldX = gamePan.tileSize * 23;
    gamePan.obj[1].worldY = gamePan.tileSize * 37;
    gamePan.obj[2] = new OBJ_SussyBit(gamePan);
    gamePan.obj[2].worldX = gamePan.tileSize * 4;
    gamePan.obj[2].worldY = gamePan.tileSize * 25;
  }
}
