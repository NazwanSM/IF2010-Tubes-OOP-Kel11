package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Rectangle;

public class MouseHandler implements MouseListener, MouseMotionListener {

    GamePanel gp;

    private Rectangle newGameArea;
    private Rectangle loadGameArea;
    private Rectangle helpArea;
    private Rectangle actionArea;
    private Rectangle playerArea;
    private Rectangle objectArea;
    private Rectangle statsArea;
    private Rectangle exitGameArea;
    private Rectangle creditsArea;
    private Rectangle backArea;

    public MouseHandler(GamePanel gp) {
        this.gp = gp;
        initializeButtonAreas();
    }

    private void initializeButtonAreas() {
        if (gp.gameState == gp.titleState) {
            int buttonWidth1 = gp.tileSize * 4 / 2;
            int buttonHeight1 = gp.tileSize * 3 / 2;
    
            int x_newGame = gp.screenWidth / 2 - gp.tileSize * 5 + gp.tileSize / 4;
            int y_row1 = gp.tileSize * 8;
            newGameArea = new Rectangle(x_newGame, y_row1, buttonWidth1, buttonHeight1);
    
            int x_loadGame = gp.screenWidth / 2 - gp.tileSize * 2 - gp.tileSize / 4;
            loadGameArea = new Rectangle(x_loadGame, y_row1, buttonWidth1, buttonHeight1);
    
            int x_help = gp.screenWidth / 2 + gp.tileSize / 4;
            helpArea = new Rectangle(x_help, y_row1, buttonWidth1, buttonHeight1);
    
            int x_action = gp.screenWidth / 2 + gp.tileSize * 3 - gp.tileSize / 4;
            actionArea = new Rectangle(x_action, y_row1, buttonWidth1, buttonHeight1);
    
            int y_row2 = gp.tileSize * 10;
            playerArea = new Rectangle(x_newGame, y_row2, buttonWidth1, buttonHeight1); 
            objectArea = new Rectangle(x_loadGame, y_row2, buttonWidth1, buttonHeight1);
            statsArea = new Rectangle(x_help, y_row2, buttonWidth1, buttonHeight1);
            exitGameArea = new Rectangle(x_action, y_row2, buttonWidth1, buttonHeight1);
    
            int buttonWidth2 = gp.tileSize * 7 / 3;
            int buttonHeight2 = gp.tileSize * 3 / 3;
            int y_row3 = gp.tileSize * 6 + gp.tileSize * 3 + gp.tileSize / 4;
    
            int x_credits = gp.screenWidth / 2 - gp.tileSize * 7 - gp.tileSize / 4;
            creditsArea = new Rectangle(x_credits, y_row3, buttonWidth2, buttonHeight2);
    
            int x_back = gp.screenWidth / 2 + gp.tileSize * 5;
            backArea = new Rectangle(x_back, y_row3, buttonWidth2, buttonHeight2);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (gp.gameState != gp.titleState) {
            return;
        }

        int mouseX = e.getX();
        int mouseY = e.getY();

        int currentCommand = gp.ui.commandNum;

        if (newGameArea.contains(mouseX, mouseY)) {
            currentCommand = 0;
        } else if (loadGameArea.contains(mouseX, mouseY)) {
            currentCommand = 1;
        } else if (helpArea.contains(mouseX, mouseY)) {
            currentCommand = 2;
        } else if (actionArea.contains(mouseX, mouseY)) {
            currentCommand = 3;
        } else if (playerArea.contains(mouseX, mouseY)) {
            currentCommand = 4;
        } else if (objectArea.contains(mouseX, mouseY)) {
            currentCommand = 5;
        } else if (statsArea.contains(mouseX, mouseY)) {
            currentCommand = 6;
        } else if (exitGameArea.contains(mouseX, mouseY)) {
            currentCommand = 7;
        } else if (creditsArea.contains(mouseX, mouseY)) {
            currentCommand = 8;
        } else if (backArea.contains(mouseX, mouseY)) {
            currentCommand = 9;
        } else {
            return;
        }
        
        gp.ui.commandNum = currentCommand; 
        gp.playSE(3);

        switch (currentCommand) {
            case 0: // New Game
                gp.setupNewGame();
                break;
            case 1: // Load Game
                // Implementasi load game
                break;
            case 2: // Help
                break;
            case 3: // Action
                break;
            case 4: // Player Info
                break;
            case 5: // List of objects
                break;
            case 6: // Statistics
                break;
            case 7: // Exit
                System.exit(0);
                break;
            case 8: // Credits
                break;
            case 9: // Back to title (atau menu sebelumnya)
                if (gp.ui.commandNum == 9 && (gp.keyH.lastNum == 0 || gp.keyH.lastNum == 4 || gp.keyH.lastNum2 == 3 || gp.keyH.lastNum2 == 7 )) {
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Tidak digunakan
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Tidak digunakan
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (gp.gameState == gp.titleState) {
            handleMouseHover(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (gp.gameState == gp.titleState) {
            handleMouseHover(e.getX(), e.getY());
        }
    }

    private void handleMouseHover(int mouseX, int mouseY) {
        int newHoveredCommand = -1; 

        if (newGameArea.contains(mouseX, mouseY)) {
            newHoveredCommand = 0;
        } else if (loadGameArea.contains(mouseX, mouseY)) {
            newHoveredCommand = 1;
        } else if (helpArea.contains(mouseX, mouseY)) {
            newHoveredCommand = 2;
        } else if (actionArea.contains(mouseX, mouseY)) {
            newHoveredCommand = 3;
        } else if (playerArea.contains(mouseX, mouseY)) {
            newHoveredCommand = 4;
        } else if (objectArea.contains(mouseX, mouseY)) {
            newHoveredCommand = 5;
        } else if (statsArea.contains(mouseX, mouseY)) {
            newHoveredCommand = 6;
        } else if (exitGameArea.contains(mouseX, mouseY)) {
            newHoveredCommand = 7;
        } else if (creditsArea.contains(mouseX, mouseY)) {
            newHoveredCommand = 8;
        } else if (backArea.contains(mouseX, mouseY)) {
            newHoveredCommand = 9;
        }

        if (newHoveredCommand != -1) {
            if (gp.ui.commandNum != newHoveredCommand) {
                gp.ui.commandNum = newHoveredCommand;
            }
        } else {

        }
    }
}