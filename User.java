import java.util.ArrayList;

public class User {
    protected String name;

    protected Board myBoard = new Board();

    public String getName() {
        return name;
    }
    protected ArrayList<ArrayList<String>> allTiles;

    public User(String name) {
        this.name = name;
    }

    public Board getBoard() {
        return myBoard;
    }
    public ArrayList<ArrayList<String>> getAllTiles(){
        return allTiles;
    }
}


