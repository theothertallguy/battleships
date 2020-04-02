package ui.display.screens;

import java.awt.*;

public class MainMenuScreen extends Screen {

    public MainMenuScreen() {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0, 2000,2000);

        g.setColor(Color.ORANGE);

        Font font = new Font("Serif", Font.PLAIN, 180);

        g.setFont(font);

        g.drawString("BATTLESHIPS", 11,150);

        g.setColor(Color.WHITE);

        Font font2 = new Font("Serif", Font.PLAIN, 60);

        g.setFont(font2);

        g.drawString("Designed and Programmed by", 250,250);
        g.drawString("Nate Sternberg", 420,333);
    }
}
