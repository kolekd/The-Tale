import java.awt.*;

public class SquareHalf extends GameObject {


    public SquareHalf(String filename, int posX, int posY, boolean fromMerge) {
        super(filename, posX, posY, fromMerge);

        this.life = 100;

    }

    @Override
    public void draw(Graphics graphics) {
        super.draw(graphics);

        life--;
    }
}
