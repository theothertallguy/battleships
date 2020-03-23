package ui.display.buttons;

import ui.display.GameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverMenu extends JPanel {
    GameGUI gameGUI;

    public GameOverMenu(GameGUI gui) {
        gameGUI = gui;
        setBackground(Color.BLACK);
        add(newGameButton());
        add(loadGameButton());
        add(instructionButton());
    }

    private JButton instructionButton() {
        JButton button4 = new JButton("Place Ships");
        button4.setPreferredSize(new Dimension(300,150));
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameGUI.playerTurn();
            }
        });

        return button4;

    }

    private JButton loadGameButton() {
        JButton button4 = new JButton("Save and Exit");
        button4.setPreferredSize(new Dimension(300,150));
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameGUI.mainMenu();
            }
        });

        return button4;

    }

    private JButton newGameButton() {
        JButton button4 = new JButton("Exit Without Saving");
        button4.setPreferredSize(new Dimension(300,150));
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameGUI.mainMenu();
            }
        });

        return button4;

    }
}
