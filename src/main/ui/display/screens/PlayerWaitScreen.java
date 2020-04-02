package ui.display.screens;

import ui.BattleshipGame;

import java.awt.*;

public class PlayerWaitScreen extends Screen {
    BattleshipGame currGame;

    public PlayerWaitScreen(BattleshipGame game) {
        currGame = game;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0, 2000,2000);
        g.setColor(Color.BLUE);

        Font font = new Font("Serif", Font.PLAIN, 60);

        g.setFont(font);

        g.drawString("CURRENT TURN", 100,100);
        g.drawString("LOOK AWAY", 700,100);

        g.setColor(Color.cyan);

        g.drawString("PLAYER " + currGame.getCurrTurn(), 150,300);
        g.drawString("PLAYER " + otherTurn(), 750,300);
    }

    private int otherTurn() {
        if (currGame.getCurrTurn() == 2) {
            return 1;
        }

        return 2;
    }
}
