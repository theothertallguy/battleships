package ui.display.buttons;

import ui.display.GameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * This is the button menu for the PlayerPauseScreen screen. It allows the player to resume the game, and
 * quit either with or without saving.
 * */

public class PauseMenu extends JPanel {
    private GameGUI gameGUI;

    public PauseMenu(GameGUI gui) {
        gameGUI = gui;
        setBackground(Color.BLACK);
        add(resumeGameButton());
        add(saveAndQuitButton());
        add(quitWithoutSavingButton());
    }

    private JButton quitWithoutSavingButton() {
        JButton button4 = new JButton("Quit Without Saving");
        button4.setPreferredSize(new Dimension(300,150));
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameGUI.mainMenu();
            }
        });

        return button4;

    }

    private JButton saveAndQuitButton() {
        JButton button4 = new JButton("Save and Quit");
        button4.setPreferredSize(new Dimension(300,150));
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameGUI.getGame().gameSaver();
                gameGUI.mainMenu();
            }
        });

        return button4;

    }

    private JButton resumeGameButton() {
        JButton button4 = new JButton("Resume My Turn");
        button4.setPreferredSize(new Dimension(300,150));
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameGUI.playerTurn();
            }
        });

        return button4;

    }
}
