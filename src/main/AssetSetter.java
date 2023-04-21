package main;

import entity.NPC_Wongy;

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
}
