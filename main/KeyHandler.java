package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shiftPressed, spacePressed, escPressed, cPressed, iPressed;
    boolean showDebugText = false;
    int lastNum = 0;
    int lastNum2 = 7;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (gp.gameState == gp.titleState) {
            titleState(keyCode);
        }

        else if (gp.gameState == gp.playState) {
            if (keyCode == KeyEvent.VK_I) {
                iPressed = true;
            } else if (keyCode == KeyEvent.VK_C) {
                cPressed = true;
            } else {
                playState(keyCode);
            }
        }
        else if (gp.gameState == gp.pauseState) {
            pauseState(keyCode);
        }
        else if (gp.gameState == gp.dialogueState) {
            switch (keyCode) {
                case KeyEvent.VK_ENTER:
                    gp.gameState = gp.playState;
                    break;
                default:
                    break;
            }
        }
        else if (gp.gameState == gp.inventoryState) {
            inventoryState(keyCode);
        }
        else if (gp.gameState == gp.worldMapState) {
            worldMapState(keyCode);
        }
        else if (gp.gameState == gp.cookingState) {
            cookingState(keyCode);
        }
        else if (gp.gameState == gp.watchState) {
            switch (keyCode) {
                case KeyEvent.VK_ENTER:
                    gp.gameState = gp.playState;
                    break;
                case KeyEvent.VK_ESCAPE:
                    gp.gameState = gp.playState;
                    break;
                default:
                    break;
            }
        }
        else if (gp.gameState == gp.npcState){
            npcState(keyCode);
        }
        else if (gp.gameState == gp.giftingState){
            inventoryState(keyCode);
        }
        else if (gp.gameState == gp.sellingState){
            inventoryState(keyCode);
        }
    }

    public void titleState(int keyCode){
        gp.playSE(3);
        switch (keyCode) {
                case KeyEvent.VK_A:
                    if (gp.ui.commandNum == 0) {
                        lastNum = 0;
                        gp.ui.commandNum = 4;
                    }
                    else {
                        gp.ui.commandNum--;
                    }
                    gp.playSE(2);
                    break;
                case KeyEvent.VK_D:
                    if (gp.ui.commandNum == 4) {
                        gp.ui.commandNum = 0;
                    }
                    else {
                        gp.ui.commandNum++;
                    }
                    gp.playSE(2);
                    break;
                case KeyEvent.VK_W:
                    if (gp.ui.commandNum == 4) {
                        gp.ui.commandNum = 0;
                    }
                    else {
                        gp.ui.commandNum++;
                    }
                    gp.playSE(2);
                    break;
                case KeyEvent.VK_S:
                    if (gp.ui.commandNum == 4) {
                        gp.ui.commandNum = 0;
                    }
                    else {
                        gp.ui.commandNum++;
                    }
                    gp.playSE(2);
                    break;
                case KeyEvent.VK_ENTER:
                    if (gp.ui.commandNum == 0) {
                        gp.setupNewGame();
                    }
                    else if (gp.ui.commandNum == 1) {
                        // load game
                    }
                    else if (gp.ui.commandNum == 2) {
                        // Help
                    }
                    else if (gp.ui.commandNum == 3) {
                        // Exit
                        System.exit(0);
                    }
                    else if (gp.ui.commandNum == 4) {
                        // Credits
                    }
                    break;
                default:
                    break;
            }
    }

    public void pauseState(int keyCode){
        gp.playSE(3);
        switch (keyCode) {
                case KeyEvent.VK_A:
                    if (gp.ui.commandNum == 0) {
                        lastNum = 0;
                        gp.ui.commandNum = 8;
                    }
                    else if (gp.ui.commandNum == 8) {
                        gp.ui.commandNum = 9;
                    }
                    else if (gp.ui.commandNum == 9) {
                        gp.ui.commandNum = lastNum2;
                    }
                    else if (gp.ui.commandNum == 4) {
                        lastNum = 4;
                        gp.ui.commandNum = 8;
                    }
                    else {
                        gp.ui.commandNum--;
                    }
                    gp.playSE(2);
                    break;
                case KeyEvent.VK_D:
                    if (gp.ui.commandNum == 8) {
                        gp.ui.commandNum = lastNum;
                    }
                    else if (gp.ui.commandNum == 9) {
                        gp.ui.commandNum = 8;
                    }
                    else if (gp.ui.commandNum == 3) {
                        lastNum2 = 3;
                        gp.ui.commandNum = 9;
                    }
                    else if (gp.ui.commandNum == 7) {
                        lastNum2 = 7;
                        gp.ui.commandNum = 9;
                    }
                    else {
                        gp.ui.commandNum++;
                    }
                    gp.playSE(2);
                    break;
                case KeyEvent.VK_W:
                    if (gp.ui.commandNum == 0) {
                        gp.ui.commandNum = 4;
                    }
                    else if (gp.ui.commandNum == 1) {
                        gp.ui.commandNum = 5;
                    }
                    else if (gp.ui.commandNum == 2) {
                        gp.ui.commandNum = 6;
                    }
                    else if (gp.ui.commandNum == 3) {
                        gp.ui.commandNum = 7;
                    }
                    else if (gp.ui.commandNum == 4) {
                        gp.ui.commandNum = 0;
                    }
                    else if (gp.ui.commandNum == 5) {
                        gp.ui.commandNum = 1;
                    }
                    else if (gp.ui.commandNum == 6) {
                        gp.ui.commandNum = 2;
                    }
                    else if (gp.ui.commandNum == 7) {
                        gp.ui.commandNum = 3;
                    }
                    gp.playSE(2);
                    break;
                case KeyEvent.VK_S:
                    if (gp.ui.commandNum == 0) {
                        gp.ui.commandNum = 4;
                    }
                    else if (gp.ui.commandNum == 1) {
                        gp.ui.commandNum = 5;
                    }
                    else if (gp.ui.commandNum == 2) {
                        gp.ui.commandNum = 6;
                    }
                    else if (gp.ui.commandNum == 3) {
                        gp.ui.commandNum = 7;
                    }
                    else if (gp.ui.commandNum == 4) {
                        gp.ui.commandNum = 0;
                    }
                    else if (gp.ui.commandNum == 5) {
                        gp.ui.commandNum = 1;
                    }
                    else if (gp.ui.commandNum == 6) {
                        gp.ui.commandNum = 2;
                    }
                    else if (gp.ui.commandNum == 7) {
                        gp.ui.commandNum = 3;
                    }
                    gp.playSE(2);
                    break;
                case KeyEvent.VK_ENTER:
                    if (gp.ui.commandNum == 0) {
                        gp.setupNewGame();
                    }
                    else if (gp.ui.commandNum == 1) {
                        // load game
                    }
                    else if (gp.ui.commandNum == 2) {
                        // Help
                    }
                    else if (gp.ui.commandNum == 3) {
                        // Action
                    }
                    else if (gp.ui.commandNum == 4) {
                        // Player Info
                    }
                    else if (gp.ui.commandNum == 5) {
                        // List of objects
                    }
                    else if (gp.ui.commandNum == 6) {
                        // Statistics
                    }
                    else if (gp.ui.commandNum == 7) {
                        // Exit
                        System.exit(0);
                    }
                    else if (gp.ui.commandNum == 8) {
                        // Credits
                    }
                    else if (gp.ui.commandNum == 9) {
                        gp.gameState = gp.playState;
                    }
                    break;
                default:
                    break;
            }
    }

    public void playState(int keyCode) {
        switch (keyCode) {
                case KeyEvent.VK_W:
                    upPressed = true;
                    break;
                case KeyEvent.VK_A:
                    leftPressed = true;
                    break;
                case KeyEvent.VK_S:
                    downPressed = true;
                    break;
                case KeyEvent.VK_D:
                    rightPressed = true;
                    break;
                case KeyEvent.VK_UP:
                    upPressed = true;
                    break;  
                case KeyEvent.VK_LEFT:
                    leftPressed = true;
                    break;
                case KeyEvent.VK_DOWN:
                    downPressed = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    rightPressed = true;
                    break;
                case KeyEvent.VK_ESCAPE:
                    gp.gameState = gp.pauseState;
                    break;
    
                case KeyEvent.VK_R:
                    switch (gp.currentMap) {
                        case 0:
                            gp.tileM.loadMap("map1.txt", 0);
                            break;
                        case 1:
                            gp.tileM.loadMap("house.txt", 1);
                            break;
                        default:
                            break;
                    }
                    break;
                case KeyEvent.VK_T:
                    if (gp.gameState == gp.playState) {
                        showDebugText = !showDebugText;
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    enterPressed = true;
                default:
                    break;
            }
    }
    
    public void inventoryState(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_W:
                if (gp.ui.slotRow > 0) {
                    gp.ui.slotRow--;
                }
                gp.playSE(3);
                break;
            case KeyEvent.VK_S:
                if (gp.ui.slotRow < 3) {
                    gp.ui.slotRow++;
                }
                gp.playSE(3);
                break;
            case KeyEvent.VK_A:
                if (gp.ui.slotCol > 0) {
                    gp.ui.slotCol--;
                }
                gp.playSE(3);
                break;
            case KeyEvent.VK_D:
                if (gp.ui.slotCol < 4) {
                    gp.ui.slotCol++;
                }
                gp.playSE(3);
                break;
            case KeyEvent.VK_ENTER:
                enterPressed = true;
                break;
            case KeyEvent.VK_ESCAPE:
                gp.gameState = gp.playState;
                break;
            case KeyEvent.VK_I:
                gp.gameState = gp.playState;
                break;
            default:
                break;
        }
    }

    public void worldMapState(int keyCode) {
        switch (keyCode) {
                case KeyEvent.VK_A:
                    if (gp.ui.worldMapNum == 0) {
                        gp.ui.worldMapNum = 5;
                    }
                    else if (gp.ui.worldMapNum == 1) {
                        gp.ui.worldMapNum = 6;
                    }
                    else if (gp.ui.worldMapNum == 2) {
                        gp.ui.worldMapNum = 7;
                    }
                    else if (gp.ui.worldMapNum == 3) {
                        gp.ui.worldMapNum = 8;
                    }
                    else if (gp.ui.worldMapNum == 4) {
                        gp.ui.worldMapNum = 9;
                    }
                    else if (gp.ui.worldMapNum == 5) {
                        gp.ui.worldMapNum = 0;
                    }
                    else if (gp.ui.worldMapNum == 6) {
                        gp.ui.worldMapNum = 1;
                    }
                    else if (gp.ui.worldMapNum == 7) {
                        gp.ui.worldMapNum = 2;
                    }    
                    else if (gp.ui.worldMapNum == 8) {
                        gp.ui.worldMapNum = 3;
                    }
                    else if (gp.ui.worldMapNum == 9) {
                        gp.ui.worldMapNum = 4;
                    }
                    gp.playSE(2);
                    break;
                case KeyEvent.VK_D:
                    if (gp.ui.worldMapNum == 0) {
                        gp.ui.worldMapNum = 5;
                    }
                    else if (gp.ui.worldMapNum == 1) {
                        gp.ui.worldMapNum = 6;
                    }
                    else if (gp.ui.worldMapNum == 2) {
                        gp.ui.worldMapNum = 7;
                    }
                    else if (gp.ui.worldMapNum == 3) {
                        gp.ui.worldMapNum = 8;
                    }
                    else if (gp.ui.worldMapNum == 4) {
                        gp.ui.worldMapNum = 9;
                    }
                    else if (gp.ui.worldMapNum == 5) {
                        gp.ui.worldMapNum = 0;
                    }
                    else if (gp.ui.worldMapNum == 6) {
                        gp.ui.worldMapNum = 1;
                    }
                    else if (gp.ui.worldMapNum == 7) {
                        gp.ui.worldMapNum = 2;
                    }    
                    else if (gp.ui.worldMapNum == 8) {
                        gp.ui.worldMapNum = 3;
                    }
                    else if (gp.ui.worldMapNum == 9) {
                        gp.ui.worldMapNum = 4;
                    }
                    gp.playSE(2);
                    break;
                case KeyEvent.VK_W:
                    if (gp.ui.worldMapNum == 0) {
                        gp.ui.worldMapNum = 4;
                    }
                    else if (gp.ui.worldMapNum == 5) {
                        gp.ui.worldMapNum = 9;
                    }
                    else {
                        gp.ui.worldMapNum--;
                    }
                    gp.playSE(2);
                    break;
                case KeyEvent.VK_S:
                    if (gp.ui.worldMapNum == 4) {
                        gp.ui.worldMapNum = 0;
                    }
                    else if (gp.ui.worldMapNum == 9) {
                        gp.ui.worldMapNum = 5;
                    }
                    else {
                        gp.ui.worldMapNum++;
                    }
                    gp.playSE(2);
                    break;
                case KeyEvent.VK_ENTER:
                    if (gp.ui.worldMapNum == 0) {
                        gp.playerData.performAction("visit", "Forest River", null);
                    }
                    else if (gp.ui.worldMapNum == 1) {
                        gp.playerData.performAction("visit", "Mountain Lake", null);
                    }
                    else if (gp.ui.worldMapNum == 2) {
                        gp.playerData.performAction("visit", "Ocean", null);
                    }
                    else if (gp.ui.worldMapNum == 3) {
                        gp.playerData.performAction("visit", "Store", null);
                    }
                    else if (gp.ui.worldMapNum == 4) {
                        gp.playerData.performAction("visit", "Mayor Tadi House", null);
                    }
                    else if (gp.ui.worldMapNum == 5) {
                        gp.playerData.performAction("visit", "Caroline House", null);
                    }
                    else if (gp.ui.worldMapNum == 6) {
                        gp.playerData.performAction("visit", "Perry House", null);
                    }
                    else if (gp.ui.worldMapNum == 7) {
                        gp.playerData.performAction("visit", "Dasco House", null);
                    }
                    else if (gp.ui.worldMapNum == 8) {
                        gp.playerData.performAction("visit", "Abigail House", null);
                    }
                    gp.gameState = gp.playState;
                    gp.playSE(3);
                    break;
                default:
                    break;
            }
    }
    
    public void cookingState(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_W:
                if (gp.ui.cookRow > 0) {
                    gp.ui.cookRow--;
                }
                gp.playSE(3);
                break;
            case KeyEvent.VK_S:
                if (gp.ui.cookRow < 3) {
                    gp.ui.cookRow++;
                }
                gp.playSE(3);
                break;
            case KeyEvent.VK_A:
                if (gp.ui.cookCol > 0) {
                    gp.ui.cookCol--;
                }
                gp.playSE(3);
                break;
            case KeyEvent.VK_D:
                if (gp.ui.cookCol < 4) {
                    gp.ui.cookCol++;
                }
                gp.playSE(3);
                break;
            case KeyEvent.VK_ENTER:
                enterPressed = true;
                break;
            case KeyEvent.VK_ESCAPE:
                gp.gameState = gp.playState;
                break;
            default:
                break;
        }
    }

    public void npcState(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_W:
                if (gp.ui.npcNum == 0) {
                    gp.ui.npcNum = 3;
                }
                else {
                    gp.ui.npcNum--;
                }
                gp.playSE(3);
                break;
            case KeyEvent.VK_S:
                if (gp.ui.npcNum == 3) {
                    gp.ui.npcNum = 0;
                }
                else {
                    gp.ui.npcNum++;
                }
                gp.playSE(3);
                break;
            case KeyEvent.VK_A:
                if (gp.ui.npcNum == 0) {
                    gp.ui.npcNum = 3;
                }
                else {
                    gp.ui.npcNum--;
                }
                gp.playSE(3);
                break;
            case KeyEvent.VK_D:
                if (gp.ui.npcNum == 3) {
                    gp.ui.npcNum = 0;
                }
                else {
                    gp.ui.npcNum++;
                }
                gp.playSE(3);
                break;
            case KeyEvent.VK_ENTER:
                if (gp.ui.npcNum == 0) {
                    if (gp.currentMap == 5){
                        gp.playerData.performAction("chat", "Emily", null);
                    }
                    else if (gp.currentMap == 6){
                        gp.playerData.performAction("chat", "Mayor Tadi", null);
                    }
                    else if (gp.currentMap == 7){
                        gp.playerData.performAction("chat", "Caroline", null);
                    }
                    else if (gp.currentMap == 8){
                        gp.playerData.performAction("chat", "Perry", null);
                    }
                    else if (gp.currentMap == 9){
                        gp.playerData.performAction("chat", "Dasco", null);
                    }
                    else if (gp.currentMap == 10){
                        gp.playerData.performAction("chat", "Abigail", null);
                    }
                }
                else if (gp.ui.npcNum == 1) {
                    gp.gameState = gp.giftingState;
                }
                else if (gp.ui.npcNum == 2) {
                    gp.playerData.performAction("propose", null, null);
                }
                else if (gp.ui.npcNum == 3) {
                    gp.playerData.performAction("marry", null, null);
                }
                break;
            case KeyEvent.VK_ESCAPE:
                gp.gameState = gp.playState;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_W:
                upPressed = false;
                break;
            case KeyEvent.VK_A:
                leftPressed = false;
                break;
            case KeyEvent.VK_S:
                downPressed = false;
                break;
            case KeyEvent.VK_D:
                rightPressed = false;
                break;
            case KeyEvent.VK_UP:
                upPressed = false;
                break;
            case KeyEvent.VK_LEFT:
                leftPressed = false;
                break;
            case KeyEvent.VK_DOWN:
                downPressed = false;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = false;
                break;
            case KeyEvent.VK_I:
                iPressed = false;
                break;
            case KeyEvent.VK_C:
                cPressed = false;
                break;
            case KeyEvent.VK_ENTER:
                enterPressed = false;
                break;
            default:
                break;
        }
    }
}
