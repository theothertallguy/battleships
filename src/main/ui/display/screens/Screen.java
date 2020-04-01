package ui.display.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class Screen extends JPanel {
    public void handleMousePressed(MouseEvent e)  {

    }

    public void handleMouseReleased(MouseEvent e) {

    }

    public void handleMouseClicked(MouseEvent e) {

    }

    public void handleMouseDragged(MouseEvent e) {

    }

    public int getFireX() {
        return -1;
    }

    public int getFireY() {
        return -1;
    }

    public Point getSelect() {
        return null;
    }

    public boolean stillPlacing() {
        return false;
    }
}
