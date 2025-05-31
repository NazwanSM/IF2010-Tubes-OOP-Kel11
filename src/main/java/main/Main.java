package main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent; // Import JOptionPane

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Spakbor Hills");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();

        // Prompt to load game or start new
        int choice = JOptionPane.showConfirmDialog(
            window,
            "Do you want to load the last saved game?",
            "Load Game",
            JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            // Panggil loadGame dengan nama file default
            gamePanel.loadGame("autosave.json"); //
        } else {
            // Tidak perlu tindakan khusus di sini, game akan tetap di titleState
        }

        // Add a WindowListener to shut down the executor when the window is closed
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Save game automatically when exiting
                gamePanel.saveGame("autosave.json"); //
                gamePanel.saveLoadManager.shutdownExecutor();
                System.exit(0);
            }
        });
    }
}