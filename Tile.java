import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;

public class Tile {
    public static ArrayList<String> EmptyTile =new ArrayList<>();
    private ArrayList<String> tileHabitats; // habitats on tile
    private static ArrayList<String> habitats = new ArrayList<>(); // 5 different habitats
    private ArrayList<String> tileWildlife; // wildlife on tile
    private static ArrayList<String> wildlife = new ArrayList<>();

    public static ArrayList<ArrayList<String>> allTiles = new ArrayList<>(50);
    public static ArrayList<String> topBar = new ArrayList<>(50);
    public static ArrayList<String> topMiddle = new ArrayList<>(50);
    public static ArrayList<String> bottomMiddle = new ArrayList<>(50);
    public static ArrayList<String> bottomBar = new ArrayList<>(50);
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

        Collections.shuffle(habitats);
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

        Collections.shuffle(wildlife);
    }

    public Tile(ArrayList<String> habitats, ArrayList<String> wildlife, boolean hasToken) {
        this.tileHabitats = habitats;
        this.tileWildlife = wildlife;
        this.hasToken = hasToken;

    }


    public static Tile generateTile() {
        habitatArray();
        wildlifeArray();
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


//        for(String s: theTile){
//            System.out.println(s);
//        }
//
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
       Tile a = Tile.generateTile();

       a.rotateTile();
    }
}
