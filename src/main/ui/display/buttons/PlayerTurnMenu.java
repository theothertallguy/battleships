package ui.display.buttons;

import ui.display.GameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerTurnMenu extends JPanel {
    private GameGUI gameGUI;

    private boolean done = false;

    public PlayerTurnMenu(GameGUI gui) {
        gameGUI = gui;
        setBackground(Color.BLACK);
        add(new JLabel("PLAYER ONE"));
        add(fireButton());
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
                if (done) {
                    if (gameGUI.getGame().boatSinkTester() == 5) {
                        gameGUI.gameOver();
                    } else {
                        gameGUI.getGame().swapTurn();
                        gameGUI.playerWait();
                    }
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

    private JButton fireButton() {
        JButton button1 = new JButton("Fire");
        button1.setPreferredSize(new Dimension(100,50));
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireAction();
            }
        });

        return button1;
    }

    private void fireAction() {
        int column = gameGUI.getMainPanel().getFireX();
        int row = gameGUI.getMainPanel().getFireY();

        if (done) {
            JOptionPane.showMessageDialog(null, "You already fired this turn..");
        } else if (-1 == column && -1 == row) {
            JOptionPane.showMessageDialog(null, "Please select a square to fire at.");
        } else if (gameGUI.getMainPanel().getSelect() != null) {
            int preCheck = gameGUI.getGame().boatSinkTester();
            gameGUI.getGame().fireAtGrid(column,row);
            if (gameGUI.getGame().boatSinkTester() > preCheck) {
                gameGUI.getGameFrame().repaint();
                JOptionPane.showMessageDialog(null, "Whoa! You just sunk one of their ships!.");
            } else if (111 == gameGUI.getGame().getOppPlayer().getCoordinateState(row,column)) {
                gameGUI.getGameFrame().repaint();
                JOptionPane.showMessageDialog(null, "You hit one of their ships!.");
            } else {
                gameGUI.getGameFrame().repaint();
                JOptionPane.showMessageDialog(null, "You missed your shot...");
            }
            done = true;
        }
    }
}
