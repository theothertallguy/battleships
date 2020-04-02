package ui.display.buttons;

import ui.display.GameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * This is the button menu for the PlayerWaitScreen screen. It allows the player to start their turn or pause.
 * */

public class PlayerWaitMenu extends JPanel {
    private GameGUI gameGUI;

    public PlayerWaitMenu(GameGUI gui) {
        gameGUI = gui;
        setBackground(Color.BLACK);
        add(startTurnButton());
        add(pauseGameButton());
    }

    private JButton pauseGameButton() {
        JButton button = new JButton("Pause Game");
        button.setPreferredSize(new Dimension(300,150));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameGUI.playerPause();
            }
        });

        return button;

    }

    private JButton startTurnButton() {
        JButton button = new JButton("Start My Turn");
        button.setPreferredSize(new Dimension(300,150));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameGUI.playerTurn();
            }
        });

        return button;

    }
}
