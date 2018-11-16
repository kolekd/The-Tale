import java.awt.*;

public class SquareHalf extends GameObject {

    // Purples and Greens represent this class. Made exclusively for the purpose of a limited life span.

    public SquareHalf(String filename, int posX, int posY, int size, int life, String type) {
        super(filename, posX, posY, size, life, type);
    }

    @Override
    public void draw(Graphics graphics) {
        super.draw(graphics);

        life--;
    }
}
