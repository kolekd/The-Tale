import java.util.ArrayList;

public class Logic {

    int tileSize;
    int height;
    int width;

    ArrayList<GameObject> gameObjectList = new ArrayList<>();


    public Logic(int tileSize, int height, int width) {
        this.tileSize = tileSize;
        this.height = height;
        this.width = width;
    }

    public void initiateLife(){
        int boardHeight = height * tileSize;
        int boardWidth = width * tileSize;

        GameObject aze = new GameObject("img/mrRectangle.png", boardWidth/2 - 128, boardHeight/2,
               64, 100, "first");
        GameObject eve = new GameObject("img/mrRectangle2.png", boardWidth/2 + 128, boardHeight/2,
               64, 100, "first");

        gameObjectList.add(aze);
        gameObjectList.add(eve);
   }

    public void checkAllObjects(){

        for (int i = 0; i < gameObjectList.size(); i++) {
            if (gameObjectList.get(i).life > 0){
                for (int j = 0; j < i; j++) {
                    if (i!=j && gameObjectList.get(i).samePosition(gameObjectList.get(j)) && gameObjectList.size() < 3){
                        reproduce(gameObjectList.get(i));
                    }
                }

                System.out.println(gameObjectList.get(i).filename + " " + gameObjectList.get(i) + "  X: " + gameObjectList.get(i).posX + " Y: "
                        + gameObjectList.get(i).posY + "   Life: " + gameObjectList.get(i).life
                        + "   Size: " + gameObjectList.get(i).size);

                randMovement(gameObjectList.get(i));

            } else {

                if (gameObjectList.size() < 7 && gameObjectList.get(i).type.equals("purple")){
                    splitSquare(gameObjectList.get(i));
                }

                if (gameObjectList.get(i).life < 1){
                    gameObjectList.remove(i);
                    i--;
                }
            }
        }

        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    }

    public void randMovement(GameObject gameObject){
        gameObject.move(height * tileSize, width * tileSize, randomDir(),1);
//      TODO:  repaint();
    }

    public void reproduce (GameObject o2){
        SquareHalf purpleDescendant = new SquareHalf("img/mrRectanglePurpleHalf.png", o2.posX, o2.posY, 32, 100, "purple");
        SquareHalf purpleDescendant2 = new SquareHalf("img/mrRectanglePurpleHalf.png", o2.posX, o2.posY, 32, 100, "purple");
        gameObjectList.add(purpleDescendant);
        gameObjectList.add(purpleDescendant2);
    }

    public void splitSquare(GameObject object) {
        SquareHalf greenDescendat
                = new SquareHalf("img/mrRectangleGreenQuarter.png", object.posX, object.posY,
                16, 100 + getRand0toX(60), "green");
        SquareHalf greenDescendat2
                = new SquareHalf("img/mrRectangleGreenQuarter.png", object.posX+32, object.posY+32,
                16, 100 + getRand0toX(60), "green");
        gameObjectList.add(greenDescendat);
        gameObjectList.add(greenDescendat2);

    }

    public int getRand0toX(int x){
        return (int)(Math.random() * ((x - 0) + 1)) + 0;
    }

    public String randomDir (){
        String[] directions = {"right", "left", "up", "down"};
        return directions[getRand0toX(3)];
    }

}
