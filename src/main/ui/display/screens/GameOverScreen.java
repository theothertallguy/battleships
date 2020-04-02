package ui.display.screens;

import ui.BattleshipGame;

import java.awt.*;

public class GameOverScreen extends Screen {
    private BattleshipGame currGame;

    public GameOverScreen(BattleshipGame game) {
        currGame = game;
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

        g.drawString("The Winner Is: " + currGame.getCurrTurn(),100,100);
    }
}
