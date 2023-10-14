package main;

import entity.FollowBot;
import entity.NpcAlex;
import entity.NpcGrass;
import entity.NpcReece;
import entity.NpcWongy;
import object.Bit;
import object.ModSword;
import object.SussyBit;

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
  public void setNpc() {
    gamePan.npc[0] = new NpcWongy(gamePan);
    gamePan.npc[0].worldX = gamePan.tileSize * 18;
    gamePan.npc[0].worldY = gamePan.tileSize * 32;

    gamePan.npc[1] = new NpcGrass(gamePan);
    gamePan.npc[1].worldX = gamePan.tileSize * 7;
    gamePan.npc[1].worldY = gamePan.tileSize * 11;

    gamePan.npc[2] = new NpcAlex(gamePan);
    gamePan.npc[2].worldX = gamePan.tileSize * 44;
    gamePan.npc[2].worldY = gamePan.tileSize * 7;

    gamePan.npc[3] = new NpcReece(gamePan);
    gamePan.npc[3].worldX = gamePan.tileSize * 44;
    gamePan.npc[3].worldY = gamePan.tileSize * 19;
  }

  /**
   * Places follow bots on the game map.
   */
  public void setFollowBot() {
    gamePan.followBot[0] = new FollowBot(gamePan);
    gamePan.followBot[0].worldX = gamePan.tileSize * 12;
    gamePan.followBot[0].worldY = gamePan.tileSize * 4;

    gamePan.followBot[1] = new FollowBot(gamePan);
    gamePan.followBot[1].worldX = gamePan.tileSize * 46;
    gamePan.followBot[1].worldY = gamePan.tileSize * 12;

    gamePan.followBot[2] = new FollowBot(gamePan);
    gamePan.followBot[2].worldX = gamePan.tileSize * 42;
    gamePan.followBot[2].worldY = gamePan.tileSize * 21;

    gamePan.followBot[3] = new FollowBot(gamePan);
    gamePan.followBot[3].worldX = gamePan.tileSize * 6;
    gamePan.followBot[3].worldY = gamePan.tileSize * 16;

    gamePan.followBot[4] = new FollowBot(gamePan);
    gamePan.followBot[4].worldX = gamePan.tileSize * 9;
    gamePan.followBot[4].worldY = gamePan.tileSize * 5;

    if (gamePan.difficulty != gamePan.easy) {
      gamePan.followBot[5] = new FollowBot(gamePan);
      gamePan.followBot[5].worldX = gamePan.tileSize * 31;
      gamePan.followBot[5].worldY = gamePan.tileSize * 5;

      gamePan.followBot[6] = new FollowBot(gamePan);
      gamePan.followBot[6].worldX = gamePan.tileSize * 40;
      gamePan.followBot[6].worldY = gamePan.tileSize * 6;

      gamePan.followBot[7] = new FollowBot(gamePan);
      gamePan.followBot[7].worldX = gamePan.tileSize * 16;
      gamePan.followBot[7].worldY = gamePan.tileSize * 24;

      gamePan.followBot[8] = new FollowBot(gamePan);
      gamePan.followBot[8].worldX = gamePan.tileSize * 23;
      gamePan.followBot[8].worldY = gamePan.tileSize * 28;

      gamePan.followBot[9] = new FollowBot(gamePan);
      gamePan.followBot[9].worldX = gamePan.tileSize * 34;
      gamePan.followBot[9].worldY = gamePan.tileSize * 3;

      gamePan.followBot[10] = new FollowBot(gamePan);
      gamePan.followBot[10].worldX = gamePan.tileSize * 46;
      gamePan.followBot[10].worldY = gamePan.tileSize * 13;
    }
    
    if (gamePan.difficulty == gamePan.hard) {
      gamePan.followBot[11] = new FollowBot(gamePan);
      gamePan.followBot[11].worldX = gamePan.tileSize * 30;
      gamePan.followBot[11].worldY = gamePan.tileSize * 22;

      gamePan.followBot[12] = new FollowBot(gamePan);
      gamePan.followBot[12].worldX = gamePan.tileSize * 19;
      gamePan.followBot[12].worldY = gamePan.tileSize * 13;

      gamePan.followBot[13] = new FollowBot(gamePan);
      gamePan.followBot[13].worldX = gamePan.tileSize * 35;
      gamePan.followBot[13].worldY = gamePan.tileSize * 9;

      gamePan.followBot[14] = new FollowBot(gamePan);
      gamePan.followBot[14].worldX = gamePan.tileSize * 2;
      gamePan.followBot[14].worldY = gamePan.tileSize * 8;

      gamePan.followBot[15] = new FollowBot(gamePan);
      gamePan.followBot[15].worldX = gamePan.tileSize * 24;
      gamePan.followBot[15].worldY = gamePan.tileSize * 17;

      gamePan.followBot[16] = new FollowBot(gamePan);
      gamePan.followBot[16].worldX = gamePan.tileSize * 23;
      gamePan.followBot[16].worldY = gamePan.tileSize * 27;

      gamePan.followBot[17] = new FollowBot(gamePan);
      gamePan.followBot[17].worldX = gamePan.tileSize * 35;
      gamePan.followBot[17].worldY = gamePan.tileSize * 27;

      gamePan.followBot[18] = new FollowBot(gamePan);
      gamePan.followBot[18].worldX = gamePan.tileSize * 43;
      gamePan.followBot[18].worldY = gamePan.tileSize * 25;

      gamePan.followBot[19] = new FollowBot(gamePan);
      gamePan.followBot[19].worldX = gamePan.tileSize * 33;
      gamePan.followBot[19].worldY = gamePan.tileSize * 16;
    }
  }

  /**
   * Places objects on the game map.
   */
  public void setObject() {
    gamePan.obj[0] = new ModSword(gamePan);
    gamePan.obj[0].worldX = gamePan.tileSize * 29;
    gamePan.obj[0].worldY = gamePan.tileSize * 26;
    gamePan.obj[1] = new Bit(gamePan);
    gamePan.obj[1].worldX = gamePan.tileSize * 23;
    gamePan.obj[1].worldY = gamePan.tileSize * 37;
    gamePan.obj[2] = new SussyBit(gamePan);
    gamePan.obj[2].worldX = gamePan.tileSize * 4;
    gamePan.obj[2].worldY = gamePan.tileSize * 25;
  }
}
