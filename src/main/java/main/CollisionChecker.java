package main;

import entity.Entity;


public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftX = entity.worldX + entity.solidArea.x;
        int entityRightX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.worldY + entity.solidArea.y;
        int entityBottomY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftX / gp.tileSize;
        int entityRightCol = entityRightX / gp.tileSize;
        int entityTopRow = entityTopY / gp.tileSize;
        int entityBottomRow = entityBottomY / gp.tileSize;

        int tileNum1, tileNum2;
        int maxCols = gp.maxWorldCol; 
        int maxRows = gp.maxWorldRow;    

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopY - entity.speed) / gp.tileSize;

                if (entityLeftCol < 0 || entityLeftCol >= maxCols ||
                    entityRightCol < 0 || entityRightCol >= maxCols || 
                    entityTopRow < 0 || entityTopRow >= maxRows) {
                    entity.collisionOn = true;
                } else {
                    tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                    tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                    if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                        entity.collisionOn = true;
                    }
                }
                break;
            case "down":
                entityBottomRow = (entityBottomY + entity.speed) / gp.tileSize;

                if (entityLeftCol < 0 || entityLeftCol >= maxCols ||
                    entityRightCol < 0 || entityRightCol >= maxCols ||
                    entityBottomRow < 0 || entityBottomRow >= maxRows) {
                    entity.collisionOn = true;
                } else {
                    tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                    tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                    if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                        entity.collisionOn = true;
                    }
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.speed) / gp.tileSize;

                if (entityLeftCol < 0 || entityLeftCol >= maxCols ||
                    entityTopRow < 0 || entityTopRow >= maxRows ||
                    entityBottomRow < 0 || entityBottomRow >= maxRows) {
                    entity.collisionOn = true;
                } else {
                    tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                    tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                    if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                        entity.collisionOn = true;
                    }
                }
                break;
            case "right":
                entityRightCol = (entityRightX + entity.speed) / gp.tileSize;

                if (entityRightCol < 0 || entityRightCol >= maxCols ||
                    entityTopRow < 0 || entityTopRow >= maxRows ||
                    entityBottomRow < 0 || entityBottomRow >= maxRows) {
                    entity.collisionOn = true;
                } else {
                    tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                    tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                    if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                        entity.collisionOn = true;
                    }
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < gp.objects[gp.currentMap].length; i++) {
            if (gp.objects[gp.currentMap][i] != null) {

                int entitySolidAreaWorldX = entity.worldX + entity.solidArea.x;
                int entitySolidAreaWorldY = entity.worldY + entity.solidArea.y;
                
                int objectSolidAreaWorldX = (gp.objects[gp.currentMap][i].worldX * gp.tileSize) + gp.objects[gp.currentMap][i].solidArea.x;
                int objectSolidAreaWorldY = (gp.objects[gp.currentMap][i].worldY * gp.tileSize) + gp.objects[gp.currentMap][i].solidArea.y;
                
                java.awt.Rectangle entityRect = new java.awt.Rectangle(
                    entitySolidAreaWorldX, 
                    entitySolidAreaWorldY,
                    entity.solidArea.width, 
                    entity.solidArea.height
                );
                
                java.awt.Rectangle objectRect = new java.awt.Rectangle(
                    objectSolidAreaWorldX, 
                    objectSolidAreaWorldY,
                    gp.objects[gp.currentMap][i].solidArea.width, 
                    gp.objects[gp.currentMap][i].solidArea.height
                );

                switch (entity.direction) {
                    case "up":
                        entityRect.y -= entity.speed;
                        break;
                    case "down":
                        entityRect.y += entity.speed;
                        break;
                    case "left":
                        entityRect.x -= entity.speed;
                        break;
                    case "right":
                        entityRect.x += entity.speed;
                        break;
                }

                if (entityRect.intersects(objectRect)) {
                    if (gp.objects[gp.currentMap][i].collision == true) {
                        entity.collisionOn = true;
                    }
                    if (player == true) {
                        index = i;
                    }
                }
            }
        }
        
        return index;
    }

    public int checkNPC(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < gp.npcs[gp.currentMap].length; i++) {
            if (gp.npcs[gp.currentMap][i] != null) {

                int entitySolidAreaWorldX = entity.worldX + entity.solidArea.x;
                int entitySolidAreaWorldY = entity.worldY + entity.solidArea.y;
                
                int npcSolidAreaWorldX = (gp.npcs[gp.currentMap][i].worldX * gp.tileSize) + gp.npcs[gp.currentMap][i].solidArea.x;
                int npcSolidAreaWorldY = (gp.npcs[gp.currentMap][i].worldY * gp.tileSize) + gp.npcs[gp.currentMap][i].solidArea.y;
                
                java.awt.Rectangle entityRect = new java.awt.Rectangle(
                    entitySolidAreaWorldX, 
                    entitySolidAreaWorldY,
                    entity.solidArea.width, 
                    entity.solidArea.height
                );
                
                java.awt.Rectangle npcRect = new java.awt.Rectangle(
                    npcSolidAreaWorldX, 
                    npcSolidAreaWorldY,
                    gp.npcs[gp.currentMap][i].solidArea.width, 
                    gp.npcs[gp.currentMap][i].solidArea.height
                );

                switch (entity.direction) {
                    case "up":
                        entityRect.y -= entity.speed;
                        break;
                    case "down":
                        entityRect.y += entity.speed;
                        break;
                    case "left":
                        entityRect.x -= entity.speed;
                        break;
                    case "right":
                        entityRect.x += entity.speed;
                        break;
                }

                if (entityRect.intersects(npcRect)) {
                    if (gp.npcs[gp.currentMap][i].collision == true) {
                        entity.collisionOn = true;
                    }
                    if (player == true) {
                        index = i;
                    }
                }
            }
        }
        
        return index;
    }
}
