package main;

public class EventHandler {
    
    GamePanel gp;
    EventRect eventRect [][][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int randomMapIndex;

    public EventHandler(GamePanel gp, int randomMapIndex) {
        this.gp = gp;
        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        this.randomMapIndex = randomMapIndex;
        
        int map = 0;
        int col = 0;
        int row = 0;

        while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
                if (row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }
    }

    public void checkEvent() {
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize) {
            canTouchEvent = true;
        }

        if (canTouchEvent) {
            // Keluar masuk rumah
            if(hit(0,13,8,"any") && randomMapIndex == 1) {
                teleport(1, 15, 24);
            }
            else if(hit(0,14,8,"any") && randomMapIndex == 1) {
                teleport(1, 15, 24);
            }
            else if(hit(0,15,8, "any") && randomMapIndex == 1) {
                teleport(1, 15, 24);
            }
            else if(hit(0,12,9,"any") && randomMapIndex == 2) {
                teleport(1, 15, 24);
            }
            else if(hit(0,13,9,"any") && randomMapIndex == 2) {
                teleport(1, 15, 24);
            }
            else if(hit(0,14,9, "any") && randomMapIndex == 2) {
                teleport(1, 15, 24);
            }
            else if(hit(0,13,16,"up") && randomMapIndex == 3) {
                teleport(1, 15, 24);
            }
            else if(hit(0,14,16,"up") && randomMapIndex == 3) {
                teleport(1, 15, 24);
            }
            else if(hit(0,15,16, "up") && randomMapIndex == 3) {
                teleport(1, 15, 24);
            }
            else if(hit(0,13,15,"up") && randomMapIndex == 4) {
                teleport(1, 15, 24);
            }
            else if(hit(0,14,15,"up") && randomMapIndex == 4) {
                teleport(1, 15, 24);
            }
            else if(hit(0,15,15, "up") && randomMapIndex == 4) {
                teleport(1, 15, 24);
            }
            else if(hit(0,12,12,"up") && randomMapIndex == 5) {
                teleport(1, 15, 24);
            }
            else if(hit(0,13,12,"up") && randomMapIndex == 5) {
                teleport(1, 15, 24);
            }
            else if(hit(0,14,12, "up") && randomMapIndex == 5) {
                teleport(1, 15, 24);
            }
            else if(hit(1,14,27,"any")) {
                if (randomMapIndex == 1) {
                    teleport(0, 14, 8);
                }
                else if (randomMapIndex == 2) {
                    teleport(0, 13, 9);
                }
                else if (randomMapIndex == 3) {
                    teleport(0,14,16);
                }
                else if (randomMapIndex == 4) {
                    teleport(0,14,15);
                }
                else if (randomMapIndex == 5) {
                    teleport(0,13,12);
                }
            }
            else if(hit(1,15,27,"any")) {
                if (randomMapIndex == 1) {
                    teleport(0, 14, 8);
                }
                else if (randomMapIndex == 2) {
                    teleport(0, 13, 9);
                }
                else if (randomMapIndex == 3) {
                    teleport(0,14,16);
                }
                else if (randomMapIndex == 4) {
                    teleport(0,14,15);
                }
                else if (randomMapIndex == 5) {
                    teleport(0,13,12);
                }
            }
            else if(hit(1,16,27,"any")) {
                if (randomMapIndex == 1) {
                    teleport(0, 14, 8);
                }
                else if (randomMapIndex == 2) {
                    teleport(0, 13, 9);
                }
                else if (randomMapIndex == 3) {
                    teleport(0,14,16);
                }
                else if (randomMapIndex == 4) {
                    teleport(0,14,15);
                }
                else if (randomMapIndex == 5) {
                    teleport(0,13,12);
                }
            }
            else if(hit(1,17,27,"any")) {
                if (randomMapIndex == 1) {
                    teleport(0, 14, 8);
                }
                else if (randomMapIndex == 2) {
                    teleport(0, 13, 9);
                }
                else if (randomMapIndex == 3) {
                    teleport(0,14,16);
                }
                else if (randomMapIndex == 4) {
                    teleport(0,14,15);
                }
                else if (randomMapIndex == 5) {
                    teleport(0,13,12);
                }
            }

            // World Map
            else if(hit(0,31,11,"right" ) && randomMapIndex == 1) {
                gp.gameState = gp.worldMapState;
            }
            else if(hit(0,31,12,"right" ) && randomMapIndex == 1) {
                gp.gameState = gp.worldMapState;
            }
            else if(hit(0,0,13,"left" ) && randomMapIndex == 2) {
                gp.gameState = gp.worldMapState;
            }
            else if(hit(0,0,14,"left" ) && randomMapIndex == 2) {
                gp.gameState = gp.worldMapState;
            }
            else if(hit(0,31,19,"right" ) && randomMapIndex == 3) {
                gp.gameState = gp.worldMapState;
            }
            else if(hit(0,31,20,"right" ) && randomMapIndex == 3) {
                gp.gameState = gp.worldMapState;
            }
            else if(hit(0,31,17,"right" ) && randomMapIndex == 4) {
                gp.gameState = gp.worldMapState;
            }
            else if(hit(0,31,18,"right" ) && randomMapIndex == 4) {
                gp.gameState = gp.worldMapState;
            }
            else if(hit(0,0,16,"left" ) && randomMapIndex == 5) {
                gp.gameState = gp.worldMapState;
            }
            else if(hit(0,0,17,"left" ) && randomMapIndex == 5) {
                gp.gameState = gp.worldMapState;
            }
            else if(hit(2, 18, 15,"any")) {
                if (randomMapIndex == 1){
                    teleport(0, 31, 11);
                }
                else if (randomMapIndex == 2){
                    teleport(0, 0, 13);
                }
                else if (randomMapIndex == 3){
                    teleport(0,31,19);
                }
                else if (randomMapIndex == 4){
                    teleport(0,31,17);
                }
                else if (randomMapIndex == 5){
                    teleport(0,0,16);
                }
            }
            else if(hit(3, 16, 12, "any")) {
                if (randomMapIndex == 1){
                    teleport(0, 31, 11);
                }
                else if (randomMapIndex == 2){
                    teleport(0, 0, 13);
                }
                else if (randomMapIndex == 3){
                    teleport(0,31,19);
                }
                else if (randomMapIndex == 4){
                    teleport(0,31,17);
                }
                else if (randomMapIndex == 5){
                    teleport(0,0,16);
                }
            }
            else if(hit(4, 16, 12, "any")) {
                if (randomMapIndex == 1){
                    teleport(0, 31, 11);
                }
                else if (randomMapIndex == 2){
                    teleport(0, 0, 13);
                }
                else if (randomMapIndex == 3){
                    teleport(0,31,19);
                }
                else if (randomMapIndex == 4){
                    teleport(0,31,17);
                }
                else if (randomMapIndex == 5){
                    teleport(0,0,16);
                }
            }
            else if(hit(5, 13, 12, "any")) {
                if (randomMapIndex == 1){
                    teleport(0, 31, 11);
                }
                else if (randomMapIndex == 2){
                    teleport(0, 0, 13);
                }
                else if (randomMapIndex == 3){
                    teleport(0,31,19);
                }
                else if (randomMapIndex == 4){
                    teleport(0,31,17);
                }
                else if (randomMapIndex == 5){
                    teleport(0,0,16);
                }
            }
            else if(hit(6, 16, 17, "any")) {
                if (randomMapIndex == 1){
                    teleport(0, 31, 11);
                }
                else if (randomMapIndex == 2){
                    teleport(0, 0, 13);
                }
                else if (randomMapIndex == 3){
                    teleport(0,31,19);
                }
                else if (randomMapIndex == 4){
                    teleport(0,31,17);
                }
                else if (randomMapIndex == 5){
                    teleport(0,0,16);
                }
            }
            else if(hit(7, 13, 16, "any")) {
                if (randomMapIndex == 1){
                    teleport(0, 31, 11);
                }
                else if (randomMapIndex == 2){
                    teleport(0, 0, 13);
                }
                else if (randomMapIndex == 3){
                    teleport(0,31,19);
                }
                else if (randomMapIndex == 4){
                    teleport(0,31,17);
                }
                else if (randomMapIndex == 5){
                    teleport(0,0,16);
                }
            }
            else if(hit(8, 18, 16, "any")) {
                if (randomMapIndex == 1){
                    teleport(0, 31, 11);
                }
                else if (randomMapIndex == 2){
                    teleport(0, 0, 13);
                }
                else if (randomMapIndex == 3){
                    teleport(0,31,19);
                }
                else if (randomMapIndex == 4){
                    teleport(0,31,17);
                }
                else if (randomMapIndex == 5){
                    teleport(0,0,16);
                }
            }
            else if(hit(9, 18, 14,"right")) {
                if (randomMapIndex == 1){
                    teleport(0, 31, 11);
                }
                else if (randomMapIndex == 2){
                    teleport(0, 0, 13);
                }
                else if (randomMapIndex == 3){
                    teleport(0,31,19);
                }
                else if (randomMapIndex == 4){
                    teleport(0,31,17);
                }
                else if (randomMapIndex == 5){
                    teleport(0,0,16);
                }
            }
            else if(hit(9, 18, 15,"any")) {
                if (randomMapIndex == 1){
                    teleport(0, 31, 11);
                }
                else if (randomMapIndex == 2){
                    teleport(0, 0, 13);
                }
                else if (randomMapIndex == 3){
                    teleport(0,31,19);
                }
                else if (randomMapIndex == 4){
                    teleport(0,31,17);
                }
                else if (randomMapIndex == 5){
                    teleport(0,0,16);
                }
            }
            else if(hit(10,16,17,"any")) {
                if (randomMapIndex == 1){
                    teleport(0, 31, 11);
                }
                else if (randomMapIndex == 2){
                    teleport(0, 0, 13);
                }
                else if (randomMapIndex == 3){
                    teleport(0,31,19);
                }
                else if (randomMapIndex == 4){
                    teleport(0,31,17);
                }
                else if (randomMapIndex == 5){
                    teleport(0,0,16);
                }
            }
            else if(hit(10, 15, 12, "any")){
                teleport(11,15,15);
            }
            else if(hit(10, 14, 12, "any")){
                teleport(11,15,15);
            }
            else if(hit(11, 15, 17, "any")) {
                teleport(10, 15, 12);
            }

            else if(hit(1, 12, 8 , "any")) {
                if (gp.keyH.enterPressed) {
                    gp.playerData.performAction("sleep", null, null);
                }

                gp.keyH.enterPressed = false;
                canTouchEvent = false;
            }
        }
    }

    public boolean hit(int map, int col, int row, String reqDirection){
        boolean hit = false;

        if (map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;
    
            if (gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;
                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }
    
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }
        return hit;
    }

    public void teleport(int map, int col, int row) {
        gp.currentMap = map;
        gp.player.worldX = col * gp.tileSize;
        gp.player.worldY = row * gp.tileSize;
        previousEventX = gp.player.worldX;
        previousEventY = gp.player.worldY;
        canTouchEvent = false;
        gp.aSetter.setObject(randomMapIndex);
        gp.aSetter.setNPC();
        gp.playSE(1);
    }
}
