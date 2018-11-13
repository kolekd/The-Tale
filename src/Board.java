import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class Board extends JComponent implements KeyListener {

    public static int boardWidth = 64*6;
    public static int boardHeight = 64*6;
    public static GameObject gameObject = new GameObject();
    HashMap<Integer, GameObject> gameObjectList = new HashMap<>();

    public Board(){
        setPreferredSize(new Dimension(boardWidth,boardHeight));
        setVisible(true);
    }

    public static void main(String[] args) {

        GameObject mrRectangle = new GameObject("img/mrRectangle.png", boardHeight/2,boardWidth/2 + 64, false);
        GameObject mrRectangle2 = new GameObject("img/mrRectangle2.png", boardHeight/2,boardWidth/2 - 64, false);

        JFrame frame = new JFrame("RPG Game");
        Board board = new Board();

        board.gameObjectList.put(board.gameObjectList.size(), mrRectangle);
        board.gameObjectList.put(board.gameObjectList.size(), mrRectangle2);

        frame.add(board);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.addKeyListener(board);

    }

    @Override
    public void paint (Graphics graphics){

        System.out.println("============================================");

        for (int i = 0; i < gameObjectList.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (i!=j && gameObjectList.get(i).samePosition(gameObjectList.get(j)) && gameObjectList.size() < 3){
                    colourMerge(gameObjectList.get(j), gameObjectList.get(i));
                }
            }
            if (gameObjectList.get(i).life > 0){
                gameObjectList.get(i).draw(graphics);

                System.out.println(gameObjectList.get(i).filename + " " + gameObjectList.get(i) + "  X: " + gameObjectList.get(i).posX + " Y: "
                        + gameObjectList.get(i).posY + "   Life: " + gameObjectList.get(i).life
                        + "   Size: " + gameObjectList.get(i).size);

                randMovement(gameObjectList.get(i));

            } else {
                if (gameObjectList.size() < 9 && gameObjectList.get(i).fromMerge){
                    splitSquare(gameObjectList.get(i));
                }

                for (int j = 0; j < gameObjectList.size(); j++) {
                    if (gameObjectList.get(i).life == 0){
                        gameObjectList.remove(gameObjectList.get(i));
                    }
                }

            }
        }

        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        System.out.println();
        System.out.println(gameObjectList);


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
            gameObjectList.get(0).move("up", 1);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            gameObjectList.get(0).move("down", 1);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            gameObjectList.get(0).move("left", 1);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            gameObjectList.get(0).move("right", 1 );
        }
        repaint();

    }

    public void randMovement(GameObject gameObject){
        gameObject.move(gameObject.randomDir(),1);
        repaint();
    }

    public void colourMerge (GameObject o1, GameObject o2){
        SquareHalf mrRectangleExtra = new SquareHalf("img/mrRectanglePurpleHalf.png", o2.posX, o2.posY, true);
        SquareHalf mrRectangleExtra2 = new SquareHalf("img/mrRectanglePurpleHalf.png", o2.posX+32, o2.posY+32, true);
        gameObjectList.put(gameObjectList.size(), mrRectangleExtra);
        gameObjectList.put(gameObjectList.size(), mrRectangleExtra2);
    }

    public void splitSquare(GameObject object) {
        SquareHalf mrRectangleExtra
                = new SquareHalf("img/mrRectangleGreenQuarter.png", object.posX, object.posY, false);
        SquareHalf mrRectangleExtra2
                = new SquareHalf("img/mrRectangleGreenQuarter.png", object.posX+32, object.posY+32, false);
        gameObjectList.put(gameObjectList.size(), mrRectangleExtra);
        gameObjectList.put(gameObjectList.size(), mrRectangleExtra2);

    }

}