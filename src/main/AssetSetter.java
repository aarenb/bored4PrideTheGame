package main;

import entity.FollowBot;
import entity.NPC_Wongy;
import object.OBJ_Bit;
import object.OBJ_ModSword;

public class AssetSetter {
  GamePanel gamePan;

  public AssetSetter(GamePanel gamePan) {
    this.gamePan = gamePan;
  }

  public void setNPC() {
    gamePan.npc[0] = new NPC_Wongy(gamePan);
    gamePan.npc[0].worldX = gamePan.tileSize * 18;
    gamePan.npc[0].worldY = gamePan.tileSize * 32;
  }

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
  }

  public void setObject() {
    gamePan.obj[0] = new OBJ_ModSword(gamePan);
    gamePan.obj[0].worldX = gamePan.tileSize * 29;
    gamePan.obj[0].worldY = gamePan.tileSize * 26;
    gamePan.obj[1] = new OBJ_Bit(gamePan);
    gamePan.obj[1].worldX = gamePan.tileSize * 23;
    gamePan.obj[1].worldY = gamePan.tileSize * 37;
  }
}
