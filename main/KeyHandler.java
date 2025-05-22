package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    boolean showDebugText = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (gp.gameState == gp.playState) {
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
