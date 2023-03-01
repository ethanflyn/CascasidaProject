
import java.util.ArrayList;
import java.util.Scanner;


public class Board {


    private static int counter = 0;

    ArrayList<ArrayList<String>> myTiles = new ArrayList<>();

    public Board() {

    }


    public void placeTile() {

        System.out.println("\t\t\t\t\t\t\tTILE PLACEMENT\n\t\t\t\t\t\t\tPlease enter the X cordinate of your desired tile\n");
        Scanner a = new Scanner(System.in);
        String input = a.nextLine();
        int x = Integer.parseInt(input);
        if (x < 0 || x > 9) {
            System.out.println("\t\t\t\t\t\t\tInvalid input!!!!");
            placeTile();
        }
        System.out.println("\t\t\t\t\t\t\tPlease enter the Y cordinate of your desired tile");
        Scanner b = new Scanner(System.in);
        String input2 = b.nextLine();
        int y = Integer.parseInt(input2);
        if (y < 0 || y > 9) {
            System.out.println("\t\t\t\t\t\t\tInvalid input!!!!");
            placeTile();
        }

        input += input2;
        int position = Integer.parseInt(input);

        if (this.myTiles.get(position + 1) == Tile.EmptyTile && this.myTiles.get(position - 1) == Tile.EmptyTile && this.myTiles.get(position + 10) == Tile.EmptyTile && this.myTiles.get(position - 10) == Tile.EmptyTile) {
            System.out.println("\t\t\t\t\t\t\tThere is no adjacent Tiless!!!!");
            placeTile();
        }
        if (this.myTiles.get(position) != Tile.EmptyTile) {
            System.out.println("\t\t\t\t\t\t\tThere is already a tile placed here!!!!");
            placeTile();
        }

        this.myTiles.set(position, Tile.generateTile().TileDisplay());

        showBoard(0);
        System.out.println("\n\n");
        GameIntro.NextTurn();
    }

    public void placeStarterTile() {
        Tile a = Tile.generateTile();
        Tile b = Tile.generateTile();
        Tile c = Tile.generateTile();

        this.myTiles.set(45, a.TileDisplay());
        this.myTiles.set(55, b.TileDisplay());
        this.myTiles.set(56, c.TileDisplay());

    }


    public static void main(String[] args) {

    }

    public void fillBoard() {


        Tile.EmptyTile.add(TileColours.EMPTY_TILE_BAR);
        Tile.EmptyTile.add(TileColours.EMPTY_TILE_SIDE + "        " + TileColours.EMPTY_TILE_SIDE);
        Tile.EmptyTile.add(TileColours.EMPTY_TILE_SIDE + "        " + TileColours.EMPTY_TILE_SIDE);
        Tile.EmptyTile.add(TileColours.EMPTY_TILE_BAR);


        for (int i = 0; i < 100; i++) {
            this.myTiles.add(Tile.EmptyTile);
        }
        placeStarterTile();

    }

    public void showBoard(int length) {
        int start, end;
        start = length;
        end = length + 10;


        for (int i = 0; i < 4; i++) {

            System.out.print("\t\t\t\t\t\t");

            for (int j = start; j < end; j++) {
                System.out.print(this.myTiles.get(j).get(i));
                if (j == end - 1 && i == 2) {
                    System.out.print(" " + counter);
                    counter++;
                }
            }

            System.out.println();

        }
        if (end != 100) {
            showBoard(length + 10);
        } else {
            System.out.print("\t\t\t\t\t\t\t" + " 0" + "\t\t\t  1\t\t\t 2\t\t\t 3\t\t\t 4\t\t\t 5\t\t\t 6\t\t\t 7\t\t\t 8\t\t\t 9\n");
            System.out.println("\t\t\t\t\t\t\tHere is your current board!\n\n");
            counter = 0;
        }

    }
}
