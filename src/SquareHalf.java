import java.awt.*;

public class SquareHalf extends GameObject {


    public SquareHalf(String filename, int posX, int posY, int size, int life, String type) {
        super(filename, posX, posY, size, life, type);
    }

    @Override
    public void draw(Graphics graphics) {
        super.draw(graphics);

        life--;
    }

    @Override
    public void move(int boardHeight, int boardWidth, String direction, int distance) {
        super.move(boardHeight, boardWidth, direction, distance * 2);
    }
}
