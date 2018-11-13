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
    int size = 64;
    int life = 100;
    boolean fromMerge;

    public GameObject(String filename, int posX, int posY, int size, int life, boolean fromMerge){
        this.posX = posX* size;
        this.posY = posY* size;
        this.filename = filename;
        this.size = size;
        this.life = life;
        this.fromMerge = fromMerge;
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


    public GameObject(String filename, int posX, int posY, boolean fromMerge){
        this.filename=filename;
        this.posX = posX;
        this.posY = posY;
        this.fromMerge = fromMerge;
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

    public void changePNG(String filename){
        try {
            image = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move(String direction, int distance){
        int convDist = distance*size/2;
        if (direction.equals("up") && posY > 0){
            posY-=convDist;
        } else if (direction.equals("down") && posY < Board.boardHeight - size){
            posY+=convDist;
        } else if (direction.equals("left") && posX > 0){
            posX-=convDist;
        } else if (direction.equals("right") && posX < Board.boardWidth - size){
            posX+=convDist;
        }
    }

    public String randomDir (){
        String[] directions = {"right", "left", "up", "down"};
        return directions[getRand0toX(3)];
    }

    public int getRand0toX(int x){
        return (int)(Math.random() * ((x - 0) + 1)) + 0;
    }

    public boolean samePosition (GameObject o){
        return this.posX == o.posX && this.posY == o.posY;
    }

    public String getMergedColour (GameObject gameObject1, GameObject gameObject2){
            return "img/mrRectangleYellowHalf.png";
    }

}
