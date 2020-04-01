package ui.display.screens;

import model.Grid;

import java.awt.*;
import java.awt.event.MouseEvent;

public class PlayerTurnScreen extends Screen {
    Grid myGrid;
    Grid theirGrid;
    int fireX = -1;

    int fireY = -1;

    Point select = null;

    public PlayerTurnScreen(Grid playerGrid, Grid enemyGrid) {
        myGrid = playerGrid;
        theirGrid = enemyGrid;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Font font = new Font("Serif", Font.PLAIN, 60);

        g.setFont(font);
        g.setColor(Color.BLACK);
        g.fillRect(0,0, 2000,2000);

        g.setColor(new Color(17,22,133));

        g.drawLine(600,0,600,900);

        drawBoards(g);

        if (!(select == null)) {
            Color save = g.getColor();
            g.setColor(Color.RED);
            g.drawRect((int) select.getX(),(int) select.getY(),40,40);
            g.setColor(save);
        }
    }

    private void drawBoards(Graphics g) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                g.drawRect((50 + j * 50),(50 + i * 50),50,50);
                g.setColor(Color.ORANGE);
                g.drawString(getSTR(i,j), (55 + j * 50),(95 + i * 50));
                g.setColor(new Color(17,22,133));

                g.drawRect((700 + j * 50),(50 + i * 50),50,50);
                g.setColor(Color.LIGHT_GRAY);
                g.drawString(oppSTR(i,j), (705 + j * 50),(95 + i * 50));
                g.setColor(new Color(17,22,133));
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

        g.setColor(new Color(111,22,133));
        g.drawString("" + i, (705 + i * 50),45);
        g.drawString(String.valueOf((char) (65 + i)), 655,(95 + i * 50));
        g.setColor(new Color(17,22,133));
        g.drawRect(650,(50 + i * 50),50,50);
        g.drawRect((700 + i * 50),0,50,50);
    }

    @Override
    public void handleMouseClicked(MouseEvent e) {
        placeFiringBox(e);
    }

    private void placeFiringBox(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if (inBox(x,y)) {
            x = x - 705;
            x = x / 50;
            fireX = x;
            x = x * 50;
            x = x + 705;

            y = y - 45;
            y = y / 50;
            fireY = y;
            y = y * 50;
            y = y + 10;
            y = y + 45;

            int oppSqrState = theirGrid.getCoordinateState(fireY,fireX);

            if (oppSqrState == 100 || oppSqrState == 101) {
                select = new Point(x,y);
            }
        }
    }

    private boolean inBox(int x, int y) {
        boolean a = 705 <= x;
        boolean b = 1205 >= x;
        boolean c = 45 <= y;
        boolean d = 545 >= y;
        return a && b && c && d;
    }

    private String oppSTR(int i, int j) {
        if (theirGrid.getCoordinateState(i, j) == 111) {
            return "X";
        } else if (theirGrid.getCoordinateState(i, j) == 222) {
            return "x";
        } else if (theirGrid.getCoordinateState(i, j) == 110) {
            return "//";
        } else {
            return "";
        }
    }

    private String getSTR(int i, int j) {
        String str = "";
        int a = myGrid.getBoatTypeOnSquare(i,j);
        int b = myGrid.getCoordinateState(i,j);
        return printState(a,b);
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
            return "//";
        }
    }

    @Override
    public int getFireX() {
        return fireX;
    }

    @Override
    public int getFireY() {
        return fireY;
    }

    @Override
    public Point getSelect() {
        return select;
    }
}
