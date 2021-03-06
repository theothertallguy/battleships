package ui.display.buttons;

import ui.display.GameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * This is the button menu for the GameOverScreen screen. It allows the player to exit to the main menu
 * after someone wins.
 * */

public class GameOverMenu extends JPanel {
    GameGUI gameGUI;

    public GameOverMenu(GameGUI gui) {
        gameGUI = gui;
        setBackground(Color.BLACK);
        add(returnToMenuButton());
    }

    private JButton returnToMenuButton() {
        JButton button = new JButton("Return ToMainMenu");
        button.setPreferredSize(new Dimension(300,150));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameGUI.mainMenu();
                gameGUI.getGame().gameReset();
            }
        });

        return button;
    }
}
