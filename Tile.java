import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;

import static java.lang.System.exit;

public class Tile {
    public static ArrayList<String> EmptyTile =new ArrayList<>();
    private ArrayList<String> tileHabitats; // habitats on tile
    private static ArrayList<String> habitats = new ArrayList<>(); // 5 different habitats
    private ArrayList<String> tileWildlife; // wildlife on tile
    private static ArrayList<String> wildlife = new ArrayList<>(); // 5 different wildlife
    public static ArrayList<ArrayList<String>> presentTiles = new ArrayList<>(); // 4 playable tiles in middle of board
    public static ArrayList<ArrayList<String>> allTiles = new ArrayList<>(); // stack of all tiles
    private boolean hasToken;
    public static ArrayList<Tile> tiles = new ArrayList<>();
    private String habitatTopBar;
    private String habitatBottomBar;
    private String habitatLeftSide;
    private String habitatRightSide;
    private static final Random rng = new Random () ;

    public static void habitatArray() {
        String p = "prairie";
        habitats.add(p);
        String m = "mountain";
        habitats.add(m);
        String r = "river";
        habitats.add(r);
        String f = "forest";
        habitats.add(f);
        String w = "wetland";
        habitats.add(w);
    }

    public static void wildlifeArray() {
        String Fox = "F";
        wildlife.add(Fox);
        String Bear = "B";
        wildlife.add(Bear);
        String Hawk = "H";
        wildlife.add(Hawk);
        String Elk = "E";
        wildlife.add(Elk);
        String Salmon = "S";
        wildlife.add(Salmon);
    }

    public Tile(ArrayList<String> habitats, ArrayList<String> wildlife, boolean hasToken) {
        this.tileHabitats = habitats;
        this.tileWildlife = wildlife;
        this.hasToken = hasToken;

    }

    public static Tile generateTile() {
        Collections.shuffle(habitats);
        Collections.shuffle(wildlife);
        int x = rng.nextInt(3)+1;
        ArrayList<String> tempWildlife = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            tempWildlife.add(wildlife.get(i));
        }
        ArrayList<String> tempHabitats = new ArrayList<>();

        tempHabitats.add(habitats.get(0));
        if (x>1) {
            tempHabitats.add(habitats.get(1));
        }
        return new Tile(tempHabitats, tempWildlife, false);
    }

    public void KeyStoneDisplay(Tile tile) {
        if (tile.tileHabitats.size()==1) {
            if (this.tileHabitats.get(0).equalsIgnoreCase("prairie")) {
                habitatBottomBar = TileColours.PRAIRIE_BAR;
                habitatRightSide = TileColours.PRAIRIE_SIDE;
            }
            else if (this.tileHabitats.get(0).equalsIgnoreCase("mountain")) {
                habitatBottomBar = TileColours.MOUNTAIN_BAR;
                habitatRightSide = TileColours.MOUNTAIN_SIDE;
            }
            else if (this.tileHabitats.get(0).equalsIgnoreCase("river")) {
                habitatBottomBar = TileColours.RIVER_BAR;
                habitatRightSide = TileColours.RIVER_SIDE;
            }
            else if (this.tileHabitats.get(0).equalsIgnoreCase("wetland")) {
                habitatBottomBar = TileColours.WETLAND_BAR;
                habitatRightSide = TileColours.WETLAND_SIDE;
            }
            else if (this.tileHabitats.get(0).equalsIgnoreCase("forest")) {
                habitatBottomBar = TileColours.FOREST_BAR;
                habitatRightSide = TileColours.FOREST_SIDE;
            }
            else {
                throw new IllegalArgumentException("Unknown habitat");
            }
        }
    }

    public ArrayList<String> TileDisplay() {

        if (this.tileHabitats.get(0).equalsIgnoreCase("prairie")) {
            habitatTopBar = TileColours.PRAIRIE_BAR;
            habitatLeftSide = TileColours.PRAIRIE_SIDE;
        }
        else if (this.tileHabitats.get(0).equalsIgnoreCase("mountain")) {
            habitatTopBar = TileColours.MOUNTAIN_BAR;
            habitatLeftSide = TileColours.MOUNTAIN_SIDE;
        }
        else if (this.tileHabitats.get(0).equalsIgnoreCase("river")) {
            habitatTopBar = TileColours.RIVER_BAR;
            habitatLeftSide = TileColours.RIVER_SIDE;
        }
        else if (this.tileHabitats.get(0).equalsIgnoreCase("wetland")) {
            habitatTopBar = TileColours.WETLAND_BAR;
            habitatLeftSide = TileColours.WETLAND_SIDE;
        }
        else if (this.tileHabitats.get(0).equalsIgnoreCase("forest")){
            habitatTopBar = TileColours.FOREST_BAR;
            habitatLeftSide = TileColours.FOREST_SIDE;
        }
        else {
            throw new IllegalArgumentException("Unknown habitat");
        }

        if (this.tileHabitats.size()==1)
            KeyStoneDisplay(this);
        else {
            if (this.tileHabitats.get(1).equalsIgnoreCase("prairie")) {
                habitatBottomBar = TileColours.PRAIRIE_BAR;
                habitatRightSide = TileColours.PRAIRIE_SIDE;
            }
            else if (this.tileHabitats.get(1).equalsIgnoreCase("mountain")) {
                habitatBottomBar = TileColours.MOUNTAIN_BAR;
                habitatRightSide = TileColours.MOUNTAIN_SIDE;
            }
            else if (this.tileHabitats.get(1).equalsIgnoreCase("river")) {
                habitatBottomBar = TileColours.RIVER_BAR;
                habitatRightSide = TileColours.RIVER_SIDE;
            }
            else if (this.tileHabitats.get(1).equalsIgnoreCase("wetland")) {
                habitatBottomBar = TileColours.WETLAND_BAR;
                habitatRightSide = TileColours.WETLAND_SIDE;
            }
            else if (this.tileHabitats.get(1).equalsIgnoreCase("forest")) {
                habitatBottomBar = TileColours.FOREST_BAR;
                habitatRightSide = TileColours.FOREST_SIDE;
            }
            else {
                throw new IllegalArgumentException("Unknown habitat");
            }
        }
        ArrayList<String> theTile = new ArrayList<>();

        theTile.add(habitatTopBar);
        theTile.add(habitatLeftSide + "\t" + this.tileWildlife.get(0) + "\t" +
                (this.tileWildlife.size() > 1 ? this.tileWildlife.get(1): " ") + " " + habitatRightSide);
        if (this.tileHabitats.size() == 1)
            theTile.add(habitatLeftSide + (this.tileWildlife.size()>2 ? this.tileWildlife.get(2): " ") + "\t\tK " + habitatRightSide);
        else {
            theTile.add(habitatLeftSide + "\t" + (this.tileWildlife.size()>2 ? this.tileWildlife.get(2): " ") + "\t  " + habitatRightSide);
        }
        theTile.add(habitatBottomBar);
//        allTiles.add(theTile);

        return theTile;
//
    }
    
    public static void tileBag() {
        switch (GameIntro.users.size()) {
            case 4:
                for (int i = 0; i < 83; i++) {
                    Tile temp = generateTile();
                    allTiles.add(temp.TileDisplay());
                }
                break;
            case 3:
                for (int i = 0; i < 63; i++) {
                    Tile temp = generateTile();
                    allTiles.add(temp.TileDisplay());
                }
                break;
            case 2:
                for (int i = 0; i < 43; i++) {
                    Tile temp = generateTile();
                    allTiles.add(temp.TileDisplay());
                }
                break;
            default:
                throw new IllegalArgumentException("Illegal number of users");
        }
    }

    public static void playableTiles() {
        Collections.shuffle(allTiles);
        int m = allTiles.size()-1;
        for (int i = 0; i < 4; i++) {
            presentTiles.add(allTiles.get(m));
            allTiles.remove(m--);
        }

        for (int i = 0; i < 4; i++) {
            System.out.print("\t\t\t\t\t\t");
            for (int j = 0; j < 4; j++) {
                System.out.print(presentTiles.get(j).get(i));
                System.out.print("\t");
            }
            System.out.println();
        }
        System.out.println();

        Token.playableTokens();

    }

    public static void replaceTile(int index) {
        presentTiles.remove(index);
        Collections.shuffle(allTiles);
        presentTiles.add(allTiles.get(0));
        allTiles.remove(0);
        Collections.shuffle(allTiles);
    }

    public void rotateTile(){
        ArrayList<String> tile = Tile.generateTile().TileDisplay();
        for(int i = 0;i < 4; i++){
            System.out.println(tile.get(i));
        }
        System.out.println();
        String habitat1 = tile.get(0);
        String habitat2 = tile.get(3);
        String bar1 = tile.get(1);
        String bar2 = tile.get(2);
        tile.set(0, habitat2);
        tile.set(3,habitat1);
        tile.set(1,bar2);
        tile.set(2,bar1);
        for(int i = 0;i < 4; i++){
            System.out.println(tile.get(i));
        }
    }

    @Override
    public String toString() {
        return "Tile{}";
    }

    public static void main(String[] args) {
        habitatArray();
        wildlifeArray();
        Token.generateTokens();
        tileBag();
        playableTiles();
    }
}
