package ui.display.buttons;

import ui.display.GameGUI;

import javax.swing.*;
import java.awt.*;

/*
 * This is the button menu for the ShipPlaceWaitScreen screen. It allows the player to proceed to place their ships,
 * or pause the game.
 * */

public class ShipPlaceWaitMenu extends JPanel {
    GameGUI gameGUI;

    public ShipPlaceWaitMenu(GameGUI gui) {
        gameGUI = gui;
        setBackground(Color.BLACK);
        add(placeShipsButton());
        add(pauseGameButton());
    }

    private JButton pauseGameButton() {
        JButton button4 = new JButton("Pause Game");
        button4.setPreferredSize(new Dimension(300,150));
        button4.addActionListener(e -> gameGUI.playerPause());

        return button4;
    }

    private JButton placeShipsButton() {
        JButton button4 = new JButton("Place My Ships");
        button4.setPreferredSize(new Dimension(300,150));
        button4.addActionListener(e -> gameGUI.shipPlace());

        return button4;

    }
}
