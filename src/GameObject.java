import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GameObject {
    BufferedImage image;            // What is gonna be displayed when the GameObject is drawn.
    int posX, posY;                 // GameObjects X and Y coordinates.
    String filename;                // The path to the .png file, that contains the image to be drawn.
    int size;                       // The size of the GameObject. Modifies it's boundaries.
    int life;                       // When life reaches 0, object ceases to exist.
    String type;                    // Some of the functions apply only to specific types. (first, purple, green)
    int mood;                       // When above 250, Blue enters chase mode.

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

    public void move(int boardHeight, int boardWidth, String direction, int distance){
        int convDist = distance*64/4;               // Thanks to this variable, the function requires only a simple input (1,2...)
        if (direction.equals("up") && posY > 200){
            posY-=convDist;
        } else if (direction.equals("down") && posY < boardHeight - size){
            posY+=convDist;
        } else if (direction.equals("left") && posX > 200){
            posX-=convDist;
        } else if (direction.equals("right") && posX < boardWidth - size){
            posX+=convDist;
        }
    } // Moves the GameObject.

    public boolean samePosition (GameObject gameobject){
        return this.posX == gameobject.posX && this.posY == gameobject.posY;
    }       // Obj1's X and Y == Obj2's X and Y.

    public boolean almostSamePosition (GameObject gameobject){
        return this.posX == gameobject.posX && this.posY == gameobject.posY ||
               this.posX == gameobject.posX && this.posY == gameobject.posY + 16;
    }       // Same as above, but with a tolerance of 16px on the Y axis.

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
    }       // Returns directions towards Red's X and Y positions.

    public void draw(Graphics graphics){
        if (image != null) {
            graphics.drawImage(image, posX, posY, null);
        }
        mood++;
    }               // Draws GameObject on the canvas. Increments it's mood.

    public void changePNG(String filename){
        try {
            image = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
