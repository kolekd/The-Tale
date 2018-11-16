import java.util.ArrayList;

public class Logic {

    int tileSize;               // The size of Blue and Red.
    int height;                 // Height of the Board.
    int width;                  // Width of the Board.
    int time;                   // Value used to indicate the time of day.
    int darkening;              // Value used to modify the opacity of various objects drawn on the canvas.

    ArrayList<GameObject> gameObjectList = new ArrayList<>();


    public Logic(int tileSize, int height, int width) {
        this.tileSize = tileSize;
        this.height = height;
        this.width = width;
        this.time = 0;
        this.darkening = 0;
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
   }                // Creates Red and Blue.

    public void checkAllObjects(){

        System.out.println(                                                             // Prints the info
                "Filename: " + gameObjectList.get(0).filename +                         // about blue's elements.
                "   GameObject: " + gameObjectList.get(0) +
                "   X: " + gameObjectList.get(0).posX +
                 " Y: " + gameObjectList.get(0).posY +
                "   Life: " + gameObjectList.get(0).life +
                "   Size: " + gameObjectList.get(0).size +
                "   Mood: " + gameObjectList.get(0).mood);

        if (gameObjectList.get(0).mood > 250){                                          // When mood reached 250, blue
            gameObjectList.get(0).changePNG("img/mrRectangleHeart.png");        //  starts chasing red.
            motivatedMovement(gameObjectList.get(0), gameObjectList.get(1));
        } else {
            gameObjectList.get(0).changePNG("img/mrRectangle.png");
            randMovement(gameObjectList.get(0));
        }

        for (int i = 1; i < gameObjectList.size(); i++) {
            if (gameObjectList.get(i).life > 0){

                if (gameObjectList.get(0).samePosition(gameObjectList.get(1)) &&        // Blue and red have to be
                    gameObjectList.get(0).mood > 250 ||                                 // either at the exact same
                    gameObjectList.get(0).almostSamePosition(gameObjectList.get(1)) &&  // X and Y position, or
                    gameObjectList.get(0).mood > 250) {                                 // deviate by 1 step
                    reproduce(gameObjectList.get(i));                                   // on the Y axis.
                }

                System.out.println(                                                     // Prints the info
                        "Filename: " + gameObjectList.get(i).filename +                 // about current object
                        "   GameObject: " + gameObjectList.get(i) +                     // selected in the loop.
                        "   X: " + gameObjectList.get(i).posX +
                         " Y: " + gameObjectList.get(i).posY +
                        "   Life: " + gameObjectList.get(i).life +
                        "   Size: " + gameObjectList.get(i).size);

                randMovement(gameObjectList.get(i));

            } else {

                if (gameObjectList.size() < 7 && gameObjectList.get(i).type.equals("purple")){    // Limits the amount
                    splitSquare(gameObjectList.get(i));                                           // of squares produced.
                }

                if (gameObjectList.get(i).life < 1){                                              // Death of a square.
                    gameObjectList.remove(i);
                    i--;
                }
            }
        }

        if (time > 2802){                           // Resets the time value to zero, keeping it in a smooth loop.
            time = 0;
        }

        System.out.println(time);

        if (time > 1000 && time <= 1351){           // 1000-1351  -  Dusk - sky darkens
            if(time % 1 == 0){
                darkening += 1;
            }
        } else if (time == 1352) {                  // 1352       -  Night - sky is dark
            darkening = 175;
        } else if (time > 2350 && time < 2702){     // 2350-2702  -  Dawn - sky brightens
            if(time % 1 == 0){
                darkening -= 1;
            }
        } else if (time == 2702) {                  // 2702       -  Day  - sky is bright
            darkening = 0;
        }

        time += 2;                                  // Pace of time.

        try {
            Thread.sleep(20);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }             // Checks all the squares currently present and applies appropriate logic.

    public void randMovement(GameObject gameobject){
        gameobject.move(height * tileSize, width * tileSize, randomDir(),1);
    }               // Movement in a random direction.

    public void motivatedMovement (GameObject gameObject1, GameObject gameObject2){

            // Blue is checking the X and Y location of Red. If Blue's X or Y coordinate isn't equal to Red's,
            //      Blue moves in the correct direction, eventually reaching Red.

        if (gameObject1.whichWayIsFriend(gameObject2)[0].equals("left")){
            gameObject1.move(height * tileSize, width * tileSize, "left",1);
        } else if (gameObject1.whichWayIsFriend(gameObject2)[0].equals("right")){
            gameObject1.move(height * tileSize, width * tileSize, "right",1);
        } else if (gameObject1.whichWayIsFriend(gameObject2)[1].equals("up")){
            gameObject1.move(height * tileSize, width * tileSize, "up",1);
        } else if (gameObject1.whichWayIsFriend(gameObject2)[1].equals("down")) {
            gameObject1.move(height * tileSize, width * tileSize, "down", 1);
        }
    }       // Chase mode.

    public void reproduce (GameObject gameobject){

        SquareHalf purpleDescendant = new SquareHalf("img/mrRectanglePurpleHalf.png", gameobject.posX, gameobject.posY,
                32, 70 + getRand0toX(30), "purple");
        SquareHalf purpleDescendant2 = new SquareHalf("img/mrRectanglePurpleHalf.png", gameobject.posX, gameobject.posY,
                32, 90 + getRand0toX(30), "purple");

        gameObjectList.add(purpleDescendant);
        gameObjectList.add(purpleDescendant2);

        gameObjectList.get(0).mood = 0;
    }               // Red and Blue produce 2 Purples. Resets Blue's mood.

    public void splitSquare(GameObject gameobject) {
        SquareHalf greenDescendat
                = new SquareHalf("img/mrRectangleGreenQuarter.png", gameobject.posX, gameobject.posY,
                16, 100 + getRand0toX(20), "green");
        SquareHalf greenDescendat2
                = new SquareHalf("img/mrRectangleGreenQuarter.png", gameobject.posX+32, gameobject.posY+32,
                16, 100 + getRand0toX(20), "green");
        gameObjectList.add(greenDescendat);
        gameObjectList.add(greenDescendat2);

    }               // Purple splits into 2 Greens on death.

    public int getRand0toX(int x){
        return (int)(Math.random() * ((x - 0) + 1)) + 0;
    }   // Random number 0 to X.

    public String randomDir (){
        String[] directions = {"right", "left", "up", "down"};
        return directions[getRand0toX(3)];
    }       // Returns a random direction (up, down, right, left).

}
