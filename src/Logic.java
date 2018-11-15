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

        GameObject aze = new GameObject("img/mrRectangle.png", 128, boardHeight/2 +64,
               64, 100, "first",100);
        GameObject eve = new GameObject("img/mrRectangle2.png", boardWidth - 128, boardHeight/2 + 192,
               64, 100, "first");

        gameObjectList.add(aze);
        gameObjectList.add(eve);
   }

    public void checkAllObjects(){
        System.out.println(gameObjectList.get(0).filename + " " + gameObjectList.get(0) + "  X: " + gameObjectList.get(0).posX + " Y: "
                + gameObjectList.get(0).posY + "   Life: " + gameObjectList.get(0).life
                + "   Size: " + gameObjectList.get(0).size + "   Mood: " + gameObjectList.get(0).mood);

        if (gameObjectList.get(0).mood > 250){
            gameObjectList.get(0).changePNG("img/mrRectangleHeart.png");
            motivatedMovement(gameObjectList.get(0), gameObjectList.get(1));
        } else {
            gameObjectList.get(0).changePNG("img/mrRectangle.png");
            randMovement(gameObjectList.get(0));
        }

        for (int i = 1; i < gameObjectList.size(); i++) {
            if (gameObjectList.get(i).life > 0){

                if (gameObjectList.get(0).samePosition(gameObjectList.get(1)) &&
                    gameObjectList.get(0).mood > 250 ||
                    gameObjectList.get(0).almostSamePosition(gameObjectList.get(1)) &&
                    gameObjectList.get(0).mood > 250) {
                    reproduce(gameObjectList.get(i));
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

    public void randMovement(GameObject gameobject){
        gameobject.move(height * tileSize, width * tileSize, randomDir(),1);
//      TODO:  repaint();
    }

    public void motivatedMovement (GameObject gameObject1, GameObject gameObject2){
        if (gameObject1.whichWayIsFriend(gameObject2)[0].equals("left")){
            gameObject1.move(height * tileSize, width * tileSize, "left",1);
        } else if (gameObject1.whichWayIsFriend(gameObject2)[0].equals("right")){
            gameObject1.move(height * tileSize, width * tileSize, "right",1);
        } else if (gameObject1.whichWayIsFriend(gameObject2)[1].equals("up")){
            gameObject1.move(height * tileSize, width * tileSize, "up",1);
        } else if (gameObject1.whichWayIsFriend(gameObject2)[1].equals("down")) {
            gameObject1.move(height * tileSize, width * tileSize, "down", 1);
        }
    }

    public void reproduce (GameObject gameobject){
        SquareHalf purpleDescendant = new SquareHalf("img/mrRectanglePurpleHalf.png", gameobject.posX, gameobject.posY,
                32, 70 + getRand0toX(30), "purple");
        SquareHalf purpleDescendant2 = new SquareHalf("img/mrRectanglePurpleHalf.png", gameobject.posX, gameobject.posY,
                32, 90 + getRand0toX(30), "purple");
        gameObjectList.add(purpleDescendant);
        gameObjectList.add(purpleDescendant2);

        gameObjectList.get(0).mood = 0;
    }

    public void splitSquare(GameObject gameobject) {
        SquareHalf greenDescendat
                = new SquareHalf("img/mrRectangleGreenQuarter.png", gameobject.posX, gameobject.posY,
                16, 100 + getRand0toX(20), "green");
        SquareHalf greenDescendat2
                = new SquareHalf("img/mrRectangleGreenQuarter.png", gameobject.posX+32, gameobject.posY+32,
                16, 100 + getRand0toX(20), "green");
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
