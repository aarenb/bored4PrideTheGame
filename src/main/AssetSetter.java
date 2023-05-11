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
    gamePan.followBot[0].worldX = gamePan.tileSize * 29;
    gamePan.followBot[0].worldY = gamePan.tileSize * 32;

    gamePan.followBot[1] = new FollowBot(gamePan);
    gamePan.followBot[1].worldX = gamePan.tileSize * 29;
    gamePan.followBot[1].worldY = gamePan.tileSize * 33;
  }

  public void setObject() {
    gamePan.obj[0] = new OBJ_ModSword(gamePan);
    gamePan.obj[0].worldX = gamePan.tileSize * 19;
    gamePan.obj[0].worldY = gamePan.tileSize * 32;
    gamePan.obj[1] = new OBJ_Bit(gamePan);
    gamePan.obj[1].worldX = gamePan.tileSize * 24;
    gamePan.obj[1].worldY = gamePan.tileSize * 40;
    gamePan.obj[2] = new OBJ_Bit(gamePan);
    gamePan.obj[2].worldX = gamePan.tileSize * 22;
    gamePan.obj[2].worldY = gamePan.tileSize * 40;

  }
}
