import java.util.HashMap;

public class Unit extends GameObject {

    String name;
//  TODO:  HashMap<String, Integer> stats;

    public Unit(String name, String filename, int posX, int posY) {
        super(filename, posX, posY);
        this.name = name;
    }

}
