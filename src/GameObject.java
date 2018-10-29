import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameObject {
    BufferedImage image;
    int posX, posY;
    String filename;

    public GameObject(String filename, int posX, int posY){
        this.posX = posX;
        this.posY = posY;
        this.filename = filename;
        try{
            image = ImageIO.read(new File(filename));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics graphics){
        if (image != null) {
            graphics.drawImage(image, posX, posY, null);
        }
    }

    public void move(String direction, int distance){
        if (direction.equals("up")){
            posY-=distance;
        } else if (direction.equals("down")){
            posY+=distance;
        } else if (direction.equals("left")){
            posX-=distance;
        } else if (direction.equals("right")){
            posX+=distance;
        }
    }

}
