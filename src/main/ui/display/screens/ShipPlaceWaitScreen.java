package ui.display.screens;

import ui.display.GameGUI;

import java.awt.*;

public class ShipPlaceWaitScreen extends Screen {
    private GameGUI gameGUI;

    public ShipPlaceWaitScreen(GameGUI gui) {
        gameGUI = gui;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0, 2000,2000);
    }
}
