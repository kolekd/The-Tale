import java.awt.*;

public class SquareHalf extends GameObject {


    public SquareHalf(String filename, int posX, int posY, int size, int life, boolean fromMerge) {
        super(filename, posX, posY, size, life, fromMerge);
    }

    @Override
    public void draw(Graphics graphics) {
        super.draw(graphics);

        life--;
    }

    @Override
    public void move(String direction, int distance) {
        super.move(direction, distance * 2);
    }
}
