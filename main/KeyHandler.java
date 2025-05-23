package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
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
                    break;
                case KeyEvent.VK_ENTER:
                    if (gp.ui.commandNum == 0) {
                        gp.gameState = gp.playState; // ini nanti buat start game beda lagi
                        gp.playMusic(0);
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
                        // Back to title
                        gp.gameState = gp.titleState;
                    }
                    break;
                default:
                    break;
            }
        }

        else if (gp.gameState == gp.playState) {
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
                default:
                    break;
            }
        }
        else if (gp.gameState == gp.pauseState) {
            switch (keyCode) {
                case KeyEvent.VK_ESCAPE:
                    gp.gameState = gp.playState;
                    break;
                default:
                    break;
            }
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
            default:
                break;
        }
    }
}
