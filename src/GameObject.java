import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GameObject {
    BufferedImage image;
    int posX, posY;
    String filename;
    int size;
    int life;
//    boolean fromMerge;
    String type;

    public GameObject(String filename, int posX, int posY, int size, int life, String type){
        this.posX = posX;
        this.posY = posY;
        this.filename = filename;
        this.size = size;
        this.life = life;
        this.type = type;
        try{
            image = ImageIO.read(new File(filename));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public GameObject(String filename, int posX, int posY, int life){
        this.posX = posX;
        this.posY = posY;
        this.filename = filename;
        this.life = life;
        try{
            image = ImageIO.read(new File(filename));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public GameObject(String filename, int posX, int posY, String type){
        this.filename=filename;
        this.posX = posX;
        this.posY = posY;
        this.type = type;
        try{
            image = ImageIO.read(new File(filename));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public GameObject(){
    }

    public void draw(Graphics graphics){
        if (image != null) {
            graphics.drawImage(image, posX, posY, null);
        }
    }

    public void move(int boardHeight, int boardWidth, String direction, int distance){
        int convDist = distance*size/2;
        if (direction.equals("up") && posY > 0){
            posY-=convDist;
        } else if (direction.equals("down") && posY < boardHeight - size){
            posY+=convDist;
        } else if (direction.equals("left") && posX > 0){
            posX-=convDist;
        } else if (direction.equals("right") && posX < boardWidth - size){
            posX+=convDist;
        }
    }

    public boolean samePosition (GameObject o){
        return this.posX == o.posX && this.posY == o.posY;
    }

    public void changePNG(String filename){
        try {
            image = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
