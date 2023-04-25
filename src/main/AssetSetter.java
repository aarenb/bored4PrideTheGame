package main;

import entity.FollowBot;
import entity.NPC_Wongy;
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
  }
}
