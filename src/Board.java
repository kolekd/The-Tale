import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

public class Board extends JComponent implements KeyListener {

    Logic logic = new Logic(64,11,18);
    Color myGreen = new Color(172, 255, 85);
    Color myBlue = new Color(197, 215, 255);

    public Board(){

        logic.initiateLife();

        setPreferredSize(new Dimension(logic.width * logic.tileSize, logic.height * logic.tileSize));
        setVisible(true);
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("RPG Game");
        Board board = new Board();

        frame.add(board);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.addKeyListener(board);

    }

    @Override
    public void paint (Graphics graphics){

        System.out.println("============================================");     System.out.println();
        System.out.println(logic.gameObjectList);                               System.out.println();

        graphics.setColor(myBlue);
        graphics.fillRect(0,0,1152,189);
        graphics.setColor(Color.black);
        graphics.fillRect(0,189,1152,1);
        graphics.setColor(myGreen);
        graphics.fillRect(0,190,1152,520);

        logic.checkAllObjects();

        for(GameObject g : logic.gameObjectList){
            g.draw(graphics);
        }

        graphics.setColor(new Color(0,0,0, logic.darkening));
        graphics.fillRect(0,0,1152,710);
        graphics.setColor(Color.yellow);
        graphics.fillOval(-100 + logic.time,60,62,62);
        graphics.setColor(Color.white);
        graphics.fillOval(-1551 + logic.time,60,62,62);
        drawStars(logic.darkening, graphics);

        repaint();

    }

    public void drawStars (int darkening, Graphics graphics){
        graphics.setColor(new Color (255,255,255, darkening));
        graphics.fillOval(20,20,5,5);
        graphics.fillOval(50,14,6,6);
        graphics.fillOval(86,55,5,5);
        graphics.fillOval(155,120,5,5);
        graphics.fillOval(196,99,2,2);
        graphics.fillOval(250,66,5,5);
        graphics.fillOval(300,70,3,3);
        graphics.fillOval(344,36,5,5);
        graphics.fillOval(364,95,7,7);
        graphics.fillOval(390,172,5,5);
        graphics.fillOval(435,22,4,4);
        graphics.fillOval(480,55,3,3);
        graphics.fillOval(502,10,5,5);
        graphics.fillOval(566,100,4,4);
        graphics.fillOval(597,50,5,5);
        graphics.fillOval(633,134,7,7);
        graphics.fillOval(653,165,8,8);
        graphics.fillOval(672,64,4,4);
        graphics.fillOval(702,23,5,5);
        graphics.fillOval(722,89,4,4);
        graphics.fillOval(735,110,5,5);
        graphics.fillOval(766,23,3,3);
        graphics.fillOval(792,150,3,3);
        graphics.fillOval(810,77,4,4);
        graphics.fillOval(840,11,5,5);
        graphics.fillOval(866,113,5,5);
        graphics.fillOval(890,124,5,5);
        graphics.fillOval(910,82,5,5);
        graphics.fillOval(932,33,5,5);
        graphics.fillOval(945,44,5,5);
        graphics.fillOval(978,9,5,5);
        graphics.fillOval(1010,92,5,5);
        graphics.fillOval(1080,54,5,5);
        graphics.fillOval(1124,140,5,5);
        graphics.fillOval(1140,155,5,5);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_UP){
            logic.gameObjectList.get(0).move(logic.height * logic.tileSize, logic.width * logic.tileSize,
                                             "up", 1);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            logic.gameObjectList.get(0).move(logic.height * logic.tileSize, logic.width * logic.tileSize,
                                             "down", 1);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            logic.gameObjectList.get(0).move(logic.height * logic.tileSize, logic.width * logic.tileSize,
                                             "left", 1);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            logic.gameObjectList.get(0).move(logic.height * logic.tileSize, logic.width * logic.tileSize,
                                             "right", 1 );
        }
        repaint();

    }

}