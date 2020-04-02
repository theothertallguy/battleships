package ui.display;

import ui.BattleshipGame;
import ui.display.buttons.ButtonBox;
import ui.display.screens.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class GameGUI {

    private static final int WIDTH = 1219;
    private static final int HEIGHT = 700;

    public Screen getMainPanel() {
        return mainPanel;
    }

    private Screen mainPanel = new MainMenuScreen();
    private ButtonBox buttonBox = new ButtonBox(1,this);

    public ButtonBox getButtonBox() {
        return buttonBox;
    }

    public JFrame getGameFrame() {
        return gameFrame;
    }

    private JFrame gameFrame = new JFrame("Battleships");

    private BattleshipGame game;

    public BattleshipGame getGame() {
        return game;
    }

    public GameGUI() {
        gameFrame.addMouseListener(new BattleshipsMouseListener());
        game = new BattleshipGame();
        initializeGraphics();
    }

    private void initializeGraphics() {
        mainMenu();
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.getContentPane().setBackground(Color.BLACK);
        gameFrame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }

    public void mainMenu() {
        gameFrame.remove(mainPanel);
        gameFrame.remove(buttonBox);
        mainPanel = new MainMenuScreen();
        gameFrame.add(mainPanel);
        buttonBox = new ButtonBox(2, this);
        gameFrame.add(BorderLayout.SOUTH, buttonBox);
    }

    public void playerTurn() {
        gameFrame.remove(mainPanel);
        gameFrame.remove(buttonBox);
        mainPanel = new PlayerTurnScreen(game);
        buttonBox = new ButtonBox(1, this);
        gameFrame.add(BorderLayout.SOUTH, buttonBox);
        gameFrame.add(mainPanel);
    }

    public void playerWait() {
        gameFrame.remove(mainPanel);
        gameFrame.remove(buttonBox);
        mainPanel = new PlayerWaitScreen(game);
        buttonBox = new ButtonBox(3,this);
        gameFrame.add(BorderLayout.SOUTH, buttonBox);
        gameFrame.add(mainPanel);
    }

    public void playerPause() {
        gameFrame.remove(mainPanel);
        gameFrame.remove(buttonBox);
        mainPanel = new PauseMenuScreen(game);
        buttonBox = new ButtonBox(4,this);
        gameFrame.add(BorderLayout.SOUTH, buttonBox);
        gameFrame.add(mainPanel);
    }

    public void gameOver() {
        gameFrame.remove(mainPanel);
        gameFrame.remove(buttonBox);
        mainPanel = new GameOverScreen(game);
        buttonBox = new ButtonBox(7,this);
        gameFrame.add(BorderLayout.SOUTH, buttonBox);
        gameFrame.add(mainPanel);
    }

    public void shipPlace() {
        gameFrame.remove(mainPanel);
        gameFrame.remove(buttonBox);
        mainPanel = new PlaceShipScreen(game);
        buttonBox = new ButtonBox(6,this);
        gameFrame.add(BorderLayout.SOUTH, buttonBox);
        gameFrame.add(mainPanel);
    }

    public void shipWait() {
        gameFrame.remove(mainPanel);
        gameFrame.remove(buttonBox);
        mainPanel = new ShipPlaceWaitScreen(game);
        buttonBox = new ButtonBox(5,this);
        gameFrame.add(BorderLayout.SOUTH, buttonBox);
        gameFrame.add(mainPanel);
    }

    private void handleMousePressed(MouseEvent e)  {
        mainPanel.handleMousePressed(e);
        gameFrame.revalidate();
        gameFrame.repaint();
    }

    private void handleMouseReleased(MouseEvent e) {
        mainPanel.handleMouseReleased(e);
        gameFrame.revalidate();
        gameFrame.repaint();
    }

    private void handleMouseClicked(MouseEvent e) {
        mainPanel.handleMouseClicked(e);
        gameFrame.revalidate();
        gameFrame.repaint();
    }

    private void handleMouseDragged(MouseEvent e) {
        mainPanel.handleMouseDragged(e);
        gameFrame.revalidate();
        gameFrame.repaint();
    }

    private class BattleshipsMouseListener extends MouseAdapter {

        // EFFECTS: Forward mouse pressed event to the active tool
        public void mousePressed(MouseEvent e) {
            handleMousePressed(translateEvent(e));
        }

        // EFFECTS: Forward mouse released event to the active tool
        public void mouseReleased(MouseEvent e) {
            handleMouseReleased(translateEvent(e));
        }

        // EFFECTS:Forward mouse clicked event to the active tool
        public void mouseClicked(MouseEvent e) {
            handleMouseClicked(translateEvent(e));
        }

        // EFFECTS:Forward mouse dragged event to the active tool
        public void mouseDragged(MouseEvent e) {
            handleMouseDragged(translateEvent(e));
        }

        // EFFECTS: translates the mouse event to current drawing's coordinate system
        private MouseEvent translateEvent(MouseEvent e) {
            return SwingUtilities.convertMouseEvent(e.getComponent(), e, mainPanel);
        }
    }
}


