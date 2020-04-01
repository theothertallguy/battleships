package ui.display.screens;

import ui.display.GameGUI;

import java.awt.*;

public class GameOverScreen extends Screen {
    private GameGUI gameGUI;

    public GameOverScreen(GameGUI gui) {
        gameGUI = gui;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0, 2000,2000);

        Font font = new Font("Serif", Font.PLAIN, 60);

        g.setFont(font);
        g.setColor(Color.ORANGE);
        g.drawString("Game Over",100,100);
    }
}
