import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JComponent implements KeyListener {

    int tileSize = 64;

    GameObject mrRectangle = new GameObject("img/mrRectangle.png", 0,0);

    public Board(){
        setPreferredSize(new Dimension(1600,900 ));
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

        graphics.drawRect(550,200, 500, 500);

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
            mrRectangle.move("up", tileSize);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            mrRectangle.move("down", tileSize);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            mrRectangle.move("left", tileSize);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            mrRectangle.move("right", tileSize);
        }

    }
}
