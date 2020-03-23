package ui.display.screens;

import ui.display.GameGUI;

import java.awt.*;

public class PlayerWaitScreen extends Screen {
    GameGUI gameGUI;

    public PlayerWaitScreen(GameGUI gui) {
        gameGUI = gui;
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

        g.drawString("PLAYER" + gameGUI.getGame().getCurrTurn(), 150,300);
        g.drawString("PLAYER" + otherTurn(), 750,300);
    }

    private int otherTurn() {
        if (gameGUI.getGame().getCurrTurn() == 2) {
            return 1;
        }

        return 2;
    }
}
