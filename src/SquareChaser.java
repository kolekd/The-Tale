import java.awt.*;

public class SquareChaser extends GameObject{

    int mood;
    boolean inPursuit;

    public SquareChaser(String filename, int posX, int posY, int size, int life, String type, int mood) {
        super(filename, posX, posY, size, life, type);

        if (mood < 250){
            this.inPursuit = false;
        } else {
            this.inPursuit = true;
        }

    }

    @Override
    public void draw(Graphics graphics) {
        super.draw(graphics);

        if(!inPursuit){
            mood++;
        }

    }
}
