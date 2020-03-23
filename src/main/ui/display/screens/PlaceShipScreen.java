package ui.display.screens;

import ui.display.GameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class PlaceShipScreen extends Screen {
    private static final int PATROL_LENGTH = 2;
    private static final int SUBMARINE_LENGTH = 3;
    private static final int DESTROYER_LENGTH = 3;
    private static final int BATTLESHIP_LENGTH = 4;
    private static final int AIRCRAFT_CARRIER_LENGTH = 5;
    
    private GameGUI gameGUI;
    private HashMap<Integer,Point> boats;
    private HashMap<Integer,Boolean> rotated;
    private boolean moving = false;

    public PlaceShipScreen(GameGUI gui) {
        gameGUI = gui;
        makeBoats();
        setRotated();
    }

    private void makeBoats() {
        boats = new HashMap<>();
        boats.put(1,new Point(700,100));
        boats.put(2,new Point(700,200));
        boats.put(3,new Point(700,300));
        boats.put(4,new Point(700,400));
        boats.put(5,new Point(700,500));
    }

    private void setRotated() {
        rotated = new HashMap<>();
        rotated.put(1,false);
        rotated.put(2,false);
        rotated.put(3,false);
        rotated.put(4,false);
        rotated.put(5,false);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Font font = new Font("Serif", Font.PLAIN, 60);

        g.setFont(font);

        FontMetrics fontMetrics = g.getFontMetrics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0, 2000,2000);

        g.setColor(new Color(17,22,133));

        g.drawLine(600,0,600,900);

        drawBoard(g);

        drawBoats(g);
    }

    private void drawBoats(Graphics g) {
        g.setColor(Color.GREEN);
        for (int i = 1; i < 6; i++) {
            if (i < 3) {
                g.drawRect((int)boats.get(i).getX(),(int)boats.get(i).getY(),50 * (i + 1), 50);
            } else {
                g.drawRect((int)boats.get(i).getX(),(int)boats.get(i).getY(),50 * i, 50);
            }
            drawBoatLabels(g,i);
        }
    }

    private void doStudd(Graphics g, int i, int len) {
        for (int j = 0; j < len; j++) {
            g.drawString("" + printState(i,101),(int)boats.get(i).getX() + 50 * j,(int)boats.get(i).getY() + 50);
        }
    }

    private void drawBoatLabels(Graphics g, int i) {
        if (i == 1) {
            doStudd(g,i,PATROL_LENGTH);
        } else if (i == 2) {
            doStudd(g,i,SUBMARINE_LENGTH);
        } else if (i == 3) {
            doStudd(g,i,DESTROYER_LENGTH);
        } else if (i == 4) {
            doStudd(g,i,BATTLESHIP_LENGTH);
        } else if (i == 5) {
            doStudd(g,i,AIRCRAFT_CARRIER_LENGTH);
        }
    }

    private void drawBoard(Graphics g) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                g.drawRect((50 + j * 50),(50 + i * 50),50,50);
            }
            drawLabels(i, g);
        }
    }

    public void drawLabels(int i, Graphics g) {
        g.setColor(new Color(111,22,133));
        g.drawString("" + i, (55 + i * 50),45);
        g.drawString(String.valueOf((char) (65 + i)), 5,(95 + i * 50));
        g.setColor(new Color(17,22,133));
        g.drawRect(0,(50 + i * 50),50,50);
        g.drawRect((50 + i * 50),0,50,50);
    }

    private String printState(int type, int state) {
        if (type == 1) {
            return patrolIcon(state);
        } else if (type == 2) {
            return submarineIcon(state);
        } else if (type == 3) {
            return destroyerIcon(state);
        } else if (type == 4) {
            return battleshipIcon(state);
        } else if (type == 5) {
            return aircraftCarrierIcon(state);
        } else {
            return emptySquareIcon(state);
        }
    }

    //EFFECTS: produces the graphics icon for a square with a patrol boat on it
    private String patrolIcon(int state) {
        if (state == 101) {
            return "P";
        } else {
            return "p";
        }
    }

    //EFFECTS: produces the graphics icon for a square with a submarine on it
    private String submarineIcon(int state) {
        if (state == 101) {
            return "S";
        } else {
            return "s";
        }
    }

    //EFFECTS: produces the graphics icon for a square with a destroyer on it
    private String destroyerIcon(int state) {
        if (state == 101) {
            return "D";
        } else {
            return "d";
        }
    }

    //EFFECTS: produces the graphics icon for a square with a battleship on it
    private String battleshipIcon(int state) {
        if (state == 101) {
            return "B";
        } else {
            return "b";
        }
    }

    //EFFECTS: produces the graphics icon for a square with an aircraft carrier on it
    private String aircraftCarrierIcon(int state) {
        if (state == 101) {
            return "A";
        } else {
            return "a";
        }
    }

    //EFFECTS: produces the graphics icon for an empty square
    private String emptySquareIcon(int state) {
        if (state == 100) {
            return "";
        } else {
            return "o";
        }
    }
}
