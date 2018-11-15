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

        repaint();

        for(GameObject g : logic.gameObjectList){
            g.draw(graphics);
        }
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