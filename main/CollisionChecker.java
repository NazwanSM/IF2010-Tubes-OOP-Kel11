package main;

import entity.Entity;


public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftX = entity.x + entity.solidArea.x;
        int entityRightX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.y + entity.solidArea.y;
        int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftX / gp.tileSize;
        int entityRightCol = entityRightX / gp.tileSize;
        int entityTopRow = entityTopY / gp.tileSize;
        int entityBottomRow = entityBottomY / gp.tileSize;

        int tileNum1, tileNum2;
        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < gp.objects.length; i++) {
            if (gp.objects[i] != null) {
                // Calculate entity solid area position in world
                int entitySolidAreaWorldX = entity.x + entity.solidArea.x;
                int entitySolidAreaWorldY = entity.y + entity.solidArea.y;
                
                // Calculate object solid area position in world (adjust if objects use tile-based coordinates)
                int objectSolidAreaWorldX = (gp.objects[i].x * gp.tileSize) + gp.objects[i].solidArea.x;
                int objectSolidAreaWorldY = (gp.objects[i].y * gp.tileSize) + gp.objects[i].solidArea.y;
                
                // Create temporary rectangles for collision detection
                java.awt.Rectangle entityRect = new java.awt.Rectangle(
                    entitySolidAreaWorldX, 
                    entitySolidAreaWorldY,
                    entity.solidArea.width, 
                    entity.solidArea.height
                );
                
                java.awt.Rectangle objectRect = new java.awt.Rectangle(
                    objectSolidAreaWorldX, 
                    objectSolidAreaWorldY,
                    gp.objects[i].solidArea.width, 
                    gp.objects[i].solidArea.height
                );

                // Adjust entity rectangle based on movement direction
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

                // Check collision with temporary rectangles
                if (entityRect.intersects(objectRect)) {
                    if (gp.objects[i].collision == true) {
                        entity.collisionOn = true;
                    }
                    if (player == true) {
                        index = i;
                        System.out.println("Player collided with object: " + gp.objects[i].name);
                    }
                }
            }
        }
        
        return index;
    }
}
