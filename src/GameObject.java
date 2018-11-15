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
    String type;
    int mood;

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

    public GameObject(String filename, int posX, int posY, int size, int life, String type, int mood) {
        this.filename = filename;
        this.posX = posX;
        this.posY = posY;
        this.size = size;
        this.life = life;
        this.type = type;
        this.mood = mood;

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

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void draw(Graphics graphics){
        if (image != null) {
            graphics.drawImage(image, posX, posY, null);
        }
            mood++;
    }

    public void move(int boardHeight, int boardWidth, String direction, int distance){
        int convDist = distance*64/4;
        if (direction.equals("up") && posY > 200){
            posY-=convDist;
        } else if (direction.equals("down") && posY < boardHeight - size){
            posY+=convDist;
        } else if (direction.equals("left") && posX > 200){
            posX-=convDist;
        } else if (direction.equals("right") && posX < boardWidth - size){
            posX+=convDist;
        }
    }

    public boolean samePosition (GameObject gameobject){
        return this.posX == gameobject.posX && this.posY == gameobject.posY;
    }

    public boolean almostSamePosition (GameObject gameobject){
        return this.posX == gameobject.posX && this.posY == gameobject.posY ||
               this.posX == gameobject.posX && this.posY == gameobject.posY + 16;
    }

    public String[] whichWayIsFriend (GameObject gameobject){
        String[] xAndY = new String[2];
        xAndY[0] = "same";
        xAndY[1] = "same";

        if (posX < gameobject.posX){
            xAndY[0] = "right";
        } else if (posX > gameobject.posX){
            xAndY[0] = "left";
        }

        if (posY < gameobject.posY){
            xAndY[1] = "down";
        } else if (posY > gameobject.posY){
            xAndY[1] = "up";
        }

        return xAndY;
    }



    public void changePNG(String filename){
        try {
            image = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
