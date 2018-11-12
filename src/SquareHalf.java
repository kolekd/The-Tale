import java.awt.*;

public class SquareHalf extends GameObject {

    int tileSize = 32;

    public SquareHalf(String filename, int posX, int posY) {
        super(filename, posX, posY);

        this.life = 100;

    }

    @Override
    public void draw(Graphics graphics) {
        super.draw(graphics);

        life--;
    }

    @Override
    public void move(String direction, int distance) {
        super.move(direction, distance);
    }
}
