package ui.display.buttons;

import ui.BattleshipGame;
import ui.display.GameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShipPlaceMenu extends JPanel {
    private GameGUI gameGUI;
    private BattleshipGame currGame;

    public ShipPlaceMenu(GameGUI gui) {
        gameGUI = gui;
        currGame = gameGUI.getGame();
        setBackground(Color.BLACK);
        add(new JLabel("PLACE YOUR SHIPS"));
        add(endTurnButton());
        add(pauseButton());
        add(mainMenuButton());
        add(new JLabel("PLAYER TWO"));
    }

    private JButton endTurnButton() {
        JButton button4 = new JButton("End Turn");
        button4.setPreferredSize(new Dimension(100,50));
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameGUI.getMainPanel().stillPlacing()) {
                    JOptionPane.showMessageDialog(null, "You must place all five ships.");
                } else if (currGame.getCurrTurn() == 1) {
                    currGame.swapTurn();
                    gameGUI.shipWait();
                } else if (currGame.getCurrTurn() == 2) {
                    currGame.swapTurn();
                    gameGUI.playerWait();
                }
            }
        });

        return button4;
    }

    private JButton mainMenuButton() {
        JButton button4 = new JButton("Main Menu");
        button4.setPreferredSize(new Dimension(100,50));
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameGUI.mainMenu();
            }
        });

        return button4;
    }

    private JButton pauseButton() {
        JButton button2 = new JButton("Pause");
        button2.setPreferredSize(new Dimension(100,50));
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameGUI.playerPause();
            }
        });

        return button2;
    }
}
