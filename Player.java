import java.util.ArrayList;

public class Player {
    private String tiles;
    private int noOfTiles;
    private String tokens;
    private int noOfTokens;
    private String name;

    public Player(int noOfTiles,  String name) {
        this.noOfTiles = noOfTiles;
        this.name = name;
    }


    @Override
    public String toString() {

        return name;
    }
}





