package ui.display.screens;

import model.GridSquare;
import ui.BattleshipGame;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class PlaceShipScreen extends Screen {
    private static final int PATROL_LENGTH = 2;
    private static final int SUBMARINE_LENGTH = 3;
    private static final int DESTROYER_LENGTH = 3;
    private static final int BATTLESHIP_LENGTH = 4;
    private static final int AIRCRAFT_CARRIER_LENGTH = 5;

    private BattleshipGame currGame;

    int fireX = -1;
    int fireY = -1;

    int currTool = 0;

    Point select = new Point(-1,-1);
    Point placeBoat = new Point(100,100);

    boolean placing = false;
    boolean endingPlacing = false;
    
    private HashMap<Integer,Integer> lengthMap;
    private HashMap<Integer,Boolean> isPlacedMap;
    private HashMap<Integer,Point> locationOfBoatMap;
    private HashMap<Integer,String> dirMap;

    public PlaceShipScreen(BattleshipGame game) {
        currGame = game;
        lengthMapSetup();
        isPlaceMapSetup();
        locationOfBoatMapSetup();
        dirMapSetup();
    }

    private void dirMapSetup() {
        dirMap = new HashMap<>();
        dirMap.put(1,"");
        dirMap.put(2,"");
        dirMap.put(3,"");
        dirMap.put(4,"");
        dirMap.put(5,"");
    }

    private void locationOfBoatMapSetup() {
        locationOfBoatMap = new HashMap<>();
        locationOfBoatMap.put(1,null);
        locationOfBoatMap.put(2,null);
        locationOfBoatMap.put(3,null);
        locationOfBoatMap.put(4,null);
        locationOfBoatMap.put(5,null);
    }

    private void isPlaceMapSetup() {
        isPlacedMap = new HashMap<>();
        isPlacedMap.put(1,false);
        isPlacedMap.put(2,false);
        isPlacedMap.put(3,false);
        isPlacedMap.put(4,false);
        isPlacedMap.put(5,false);
    }

    private void lengthMapSetup() {
        lengthMap = new HashMap<>();
        lengthMap.put(1,PATROL_LENGTH);
        lengthMap.put(2,SUBMARINE_LENGTH);
        lengthMap.put(3,DESTROYER_LENGTH);
        lengthMap.put(4,BATTLESHIP_LENGTH);
        lengthMap.put(5,AIRCRAFT_CARRIER_LENGTH);
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
        drawSelects(g);
        drawTool(g);

        if (placing & !endingPlacing) {
            Color save = g.getColor();
            g.setColor(Color.RED);
            g.drawRect((int) select.getX(),(int) select.getY(),40,40);
            g.setColor(save);
            drawArrows(g);
        }

        drawPlacedBoats(g);
    }

    private void drawPlacedBoats(Graphics g) {
        for (int i = 1; i <= 5; i++) {
            if (isPlacedMap.get(i)) {
                if (dirMap.get(i).equals("N")) {
                    drawNorth(g,i);
                } else if (dirMap.get(i).equals("S")) {
                    drawSouth(g,i);
                } else if (dirMap.get(i).equals("E")) {
                    drawEast(g,i);
                } else if (dirMap.get(i).equals("W")) {
                    drawWest(g,i);
                }
            }
        }
    }

    private void drawWest(Graphics g, int i) {
        for (int j = 0; j < lengthMap.get(i); j++) {
            int x = locationOfBoatMap.get(i).x * 50 + 55 - 50 * j;
            int y = locationOfBoatMap.get(i).y * 50 + 95;
            g.drawString(printState(i, GridSquare.BOAT_ON_SQUARE), x, y);
        }
    }

    private void drawEast(Graphics g, int i) {
        for (int j = 0; j < lengthMap.get(i); j++) {
            int x = locationOfBoatMap.get(i).x * 50 + 55 + 50 * j;
            int y = locationOfBoatMap.get(i).y * 50 + 95;
            g.drawString(printState(i,GridSquare.BOAT_ON_SQUARE), x, y);
        }
    }

    private void drawSouth(Graphics g, int i) {
        for (int j = 0; j < lengthMap.get(i); j++) {
            int x = locationOfBoatMap.get(i).x * 50 + 55;
            int y = locationOfBoatMap.get(i).y * 50 + 95 + 50 * j;
            g.drawString(printState(i,GridSquare.BOAT_ON_SQUARE), x, y);
        }
    }

    private void drawNorth(Graphics g, int i) {
        for (int j = 0; j < lengthMap.get(i); j++) {
            int x = locationOfBoatMap.get(i).x * 50 + 55;
            int y = locationOfBoatMap.get(i).y * 50 + 95 - 50 * j;
            g.drawString(printState(i,GridSquare.BOAT_ON_SQUARE), x, y);
        }
    }

    private void drawArrows(Graphics g) {
        Point zeroNine = getZeroNine(select);
        int x = (int) select.getX();
        int y = (int) select.getY();
        if (zeroNine.getX() - (lengthMap.get(currTool) - 1) >= 0 && noBoat(zeroNine,"W")) {
            g.drawLine(x - 10,y + 22,x - 40,y + 22);
            g.drawLine(x - 25,y + 12,x - 40,y + 22);
            g.drawLine(x - 25,y + 32,x - 40,y + 22);
        }

        if (zeroNine.getX() + (lengthMap.get(currTool)  - 1) <= 9 && noBoat(zeroNine,"E")) {
            g.drawLine(x + 50, y + 22, x + 80, y + 22);
            g.drawLine(x + 65, y + 12, x + 80, y + 22);
            g.drawLine(x + 65, y + 32, x + 80, y + 22);
        }

        if (zeroNine.getY() - (lengthMap.get(currTool)  - 1) >= 0 && noBoat(zeroNine,"N")) {
            g.drawLine(x + 22,y - 10,x + 22,y - 40);
            g.drawLine(x + 12,y - 25,x + 22,y - 40);
            g.drawLine(x + 32,y - 25,x + 22,y - 40);
        }

        if (zeroNine.getY() + (lengthMap.get(currTool)  - 1) <= 9 && noBoat(zeroNine,"S")) {
            g.drawLine(x + 22,y + 50,x + 22,y + 80);
            g.drawLine(x + 12,y + 65,x + 22,y + 80);
            g.drawLine(x + 32,y + 65,x + 22,y + 80);
        }
    }

    private boolean noBoat(Point zeroNine,String dir) {
        boolean thereIsNoBoat = true;
        if (dir.equals("N")) {
            for (int j = 0; j < lengthMap.get(currTool); j++) {
                thereIsNoBoat = noOverlapIfPlaced(zeroNine.x,zeroNine.y - j);
            }
        } else if (dir.equals("S")) {
            for (int j = 0; j < lengthMap.get(currTool); j++) {
                thereIsNoBoat = noOverlapIfPlaced(zeroNine.x,zeroNine.y + j);
            }
        } else if (dir.equals("E")) {
            for (int j = 0; j < lengthMap.get(currTool); j++) {
                thereIsNoBoat = noOverlapIfPlaced(zeroNine.x + j,zeroNine.y);
            }
        } else if (dir.equals("W")) {
            for (int j = 0; j < lengthMap.get(currTool); j++) {
                thereIsNoBoat = noOverlapIfPlaced(zeroNine.x - j,zeroNine.y);
            }
        }

        return thereIsNoBoat;
    }

    private boolean noOverlapIfPlaced(int x, int y) {
        if (0 <= x && x <= 9 && 0 <= y && y <= 9) {
            if (currGame.currPlayerBoatOnSquare(y,x) != 0) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    private Point getZeroNine(Point currSpot) {
        int x = currSpot.x;
        int y = currSpot.y;

        x = x - 55;
        x = x / 50;

        y = y - 45;
        y = y / 50;

        return new Point(x,y);
    }

    private void drawTool(Graphics g) {
        if (currTool == 1) {
            g.drawString("X",655,95);
        } else if (currTool == 2) {
            g.drawString("X",655,195);
        } else if (currTool == 3) {
            g.drawString("X",655,295);
        } else if (currTool == 4) {
            g.drawString("X",655,395);
        } else if (currTool == 5) {
            g.drawString("X",655,495);
        }
    }

    private void drawSelects(Graphics g) {
        g.setColor(Color.ORANGE);
        g.drawRect(650,50,50,50);
        g.drawRect(650,150,50,50);
        g.drawRect(650,250,50,50);
        g.drawRect(650,350,50,50);
        g.drawRect(650,450,50,50);

        g.drawString("Patrol Boat", 750,100);
        g.drawString("Submarine", 750,200);
        g.drawString("Destroyer", 750,300);
        g.drawString("Battleship", 750,400);
        g.drawString("Aircraft Carrier", 750,500);
    }

    private void drawBoards(Graphics g) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                g.drawRect((50 + j * 50),(50 + i * 50),50,50);
                g.drawString(getSTR(i,j), (55 + j * 50),(95 + i * 50));
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

    @Override
    public void handleMouseClicked(MouseEvent e) {
        endingPlacing = false;
        clickedArrow(e);
        placeFiringBox(e);
        shipPlaceAreaDetect(e);
    }

    private void clickedArrow(MouseEvent e) {
        if (placing) {
            Point centre = getZeroNine(select);
            Point clicked = getZeroNine(new Point(e.getX(),e.getY()));

            if (centre.x - clicked.x == 1 && centre.y == clicked.y && noBoat(centre,"W")) {
                placeInDirection("W", centre);
            } else if (clicked.x - centre.x == 1 && centre.y == clicked.y && noBoat(centre,"E")) {
                placeInDirection("E", centre);
            } else if (centre.x == clicked.x && centre.y - clicked.y == 1 && noBoat(centre,"N")) {
                placeInDirection("N", centre);
            } else if (centre.x == clicked.x && clicked.y - centre.y == 1 && noBoat(centre,"S")) {
                placeInDirection("S", centre);
            }
        }
    }

    private void placeInDirection(String dir, Point centre) {
        currGame.currPlayerRemoveBoatWithID(currTool);
        placeCurrBoat(centre, dir);
        isPlacedMap.put(currTool,true);
        locationOfBoatMap.put(currTool,centre);
        dirMap.put(currTool,dir);
        endingPlacing = true;
        select = new Point(-1,-1);
    }

    private void placeCurrBoat(Point centre, String dir) {
        switch (currTool) {
            case 1:
                currGame.currPlayerMakePatrolBoat(coordinateOf(centre),dir.charAt(0));
                break;
            case 2:
                currGame.currPlayerMakeSubmarineBoat(coordinateOf(centre),dir.charAt(0));
                break;
            case 3:
                currGame.currPlayerMakeDestroyerBoat(coordinateOf(centre),dir.charAt(0));
                break;
            case 4:
                currGame.currPlayerMakeBattleshipBoat(coordinateOf(centre),dir.charAt(0));
                break;
            default:
                currGame.currPlayerMakeAircraftCarrierBoat(coordinateOf(centre),dir.charAt(0));
                break;
        }
    }

    private String coordinateOf(Point centre) {
        char rowChar = (char) (centre.y + 65);
        String row = Character.toString(rowChar);
        char columnChar = (char) (centre.x + 48);
        String column = Character.toString(columnChar);
        return row + column;
    }

    private void shipPlaceAreaDetect(MouseEvent e) {
        int tool = whichBoat(e);
        if (inBox(e.getX(),e.getY())) {
            if (tool == 1) {
                placeBoat = new Point(1,1);
            } else if (tool == 2) {
                placeBoat = new Point(1,1);
            } else if (tool == 3) {
                placeBoat = new Point(1,1);
            } else if (tool == 4) {
                placeBoat = new Point(1,1);
            } else if (tool == 5) {
                placeBoat = new Point(1,1);
            }
        }
    }

    private int whichBoat(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (650 <= x && x <= 700) {
            if (50 <= y && y <= 100) {
                placeBoat = new Point(1,1);
                currTool = 1;
            } else if (150 <= y && y <= 200) {
                placeBoat = new Point(1,1);
                currTool = 2;
            } else if (250 <= y && y <= 300) {
                placeBoat = new Point(1,1);
                currTool = 3;
            } else if (350 <= y && y <= 400) {
                placeBoat = new Point(1,1);
                currTool = 4;
            } else if (450 <= y && y <= 500) {
                placeBoat = new Point(1,1);
                currTool = 5;
            }
        }

        return currTool;
    }

    private void placeFiringBox(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if (currTool != 0 && inBox(x,y) && !endingPlacing) {
            placing = true;

            x = x - 55;
            x = x / 50;
            fireX = x;
            x = x * 50;
            x = x + 55;

            y = y - 45;
            y = y / 50;
            fireY = y;
            y = y * 50;
            y = y + 10;
            y = y + 45;

            select = new Point(x,y);
        } else {
            placing = false;
        }
    }

    private boolean inBox(int x, int y) {
        boolean a = 55 <= x;
        boolean b = 555 >= x;
        boolean c = 45 <= y;
        boolean d = 545 >= y;
        return a && b && c && d;
    }

    private String getSTR(int i, int j) {
        int a = currGame.currPlayerBoatOnSquare(i,j);
        int b = currGame.currPlayerGridCoordinateState(i,j);
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
            return "o";
        }
    }

    @Override
    public boolean stillPlacing() {
        boolean isPlacing = false;
        for (int i = 1; i < 5; i++) {
            isPlacing = isPlacing || !(isPlacedMap.get(i));
        }
        return isPlacing;
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
