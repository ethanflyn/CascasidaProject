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

    public Tile(ArrayList<String> habitats, ArrayList<String> wildlife) {
        this.tileHabitats = habitats;
        this.tileWildlife = wildlife;
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
        return new Tile(tempHabitats, tempWildlife);
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
        theTile.add(habitatLeftSide + "  " + this.tileWildlife.get(0) + "  " +
                (this.tileWildlife.size() > 1 ? this.tileWildlife.get(1): " ") + " " + habitatRightSide);
        if (this.tileHabitats.size() == 1)
            theTile.add(habitatLeftSide + (this.tileWildlife.size()>2 ? this.tileWildlife.get(2): " ") + "    K " + habitatRightSide);
        else {
            theTile.add(habitatLeftSide + "  " + (this.tileWildlife.size()>2 ? this.tileWildlife.get(2): " ") + "    " + habitatRightSide);
        }
        theTile.add(habitatBottomBar);

        return theTile;

    }
    
    public static void tileBag(int numUsers) {
        switch (numUsers) {
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
                for (int i = 0; i < 8; i++) {
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
        for (int i = presentTiles.size(); i < 4; i++) {
            presentTiles.add(allTiles.get(m));
            allTiles.remove(m--);
        }

        for (int i = 0; i < 4; i++) {
            System.out.print("\t\t\t\t\t\t");
            for (int j = 0; j < 4; j++) {
                System.out.print(presentTiles.get(j).get(i));
                System.out.print("			");
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


}
