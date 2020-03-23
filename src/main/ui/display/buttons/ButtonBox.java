package ui.display.buttons;

import ui.display.GameGUI;

import javax.swing.*;
import java.awt.*;

public class ButtonBox extends JPanel {
    private GameGUI gameGUI;

    public ButtonBox(int type, GameGUI gui) {
        gameGUI = gui;
        setBackground(Color.BLACK);
        chooseBoxType(type);
    }

    private void chooseBoxType(int type) {
        switch (type) {
            case 1:
                add(new PlayerTurnMenu(gameGUI));
                break;
            case 2:
                add(new MainMenu(gameGUI));
                break;
            case 3:
                add(new PlayerWaitMenu(gameGUI));
                break;
            case 4:
                add(new PauseMenu(gameGUI));
                break;
            case 5:
                add(new ShipPlaceWaitMenu(gameGUI));
                break;
            case 6:
                add(new ShipPlaceMenu(gameGUI));
                break;
            case 7:
                add(new GameOverMenu(gameGUI));
                break;
        }
    }

    private void playerWaitMenu() {
    }
}
