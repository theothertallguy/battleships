package ui.display.buttons;

import ui.display.GameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
    private GameGUI gameGUI;

    public MainMenu(GameGUI gui) {
        gameGUI = gui;
        setBackground(Color.BLACK);
        add(newGameButton());
        add(loadGameButton());
    }

    private JButton loadGameButton() {
        JButton button4 = new JButton("Load Saved Game");
        button4.setPreferredSize(new Dimension(300,150));
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameGUI.getGame().gameLoader();
                gameGUI.playerWait();
            }
        });

        return button4;

    }

    private JButton newGameButton() {
        JButton button4 = new JButton("Start New Game");
        button4.setPreferredSize(new Dimension(300,150));
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameGUI.shipWait();
            }
        });

        return button4;

    }
}
