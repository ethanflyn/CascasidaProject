
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Board {


    private static int counter = 0;

    public ArrayList<ArrayList<String>> myTiles = new ArrayList<>();
    ArrayList<String> chosenTile = new ArrayList<>();
    public static String chosenToken;
    public static int selection;

    public Board() {

    }


    public void placeTile(ArrayList<String> tile) {

        System.out.println("TILE PLACEMENT\nPlease enter the X cordinate of your desired tile\n");
        Scanner a = new Scanner(System.in);
        String input = a.nextLine();
        int x = Integer.parseInt(input);
        if (x < 0 || x > 9) {
            System.out.println("\t\t\t\t\t\t\tInvalid input!!!!");
            placeTile(tile);
        }
        System.out.println("\t\t\t\t\t\t\tPlease enter the Y cordinate of your desired tile");
        Scanner b = new Scanner(System.in);
        String input2 = b.nextLine();
        int y = Integer.parseInt(input2);
        if (y < 0 || y > 9) {
            System.out.println("\t\t\t\t\t\t\tInvalid input!!!!");
            placeTile(tile);
        }

        input2 += input;
        int position = Integer.parseInt(input2);

        if (this.myTiles.get(position + 1) == Tile.EmptyTile && this.myTiles.get(position - 1) == Tile.EmptyTile && this.myTiles.get(position + 10) == Tile.EmptyTile && this.myTiles.get(position - 10) == Tile.EmptyTile) {
            System.out.println("\t\t\t\t\t\t\tThere is no adjacent Tiles!!!!");
            placeTile(tile);
        }
        if (this.myTiles.get(position) != Tile.EmptyTile) {
            System.out.println("\t\t\t\t\t\t\tThere is already a tile placed here!!!!");
            placeTile(tile);
        }

        this.myTiles.set(position, tile);

        showBoard(0);
        System.out.println("\n\n");
    }

    public void chooseTile(){
        System.out.println("Please enter a number between 1-4 to choose which tile you would like to place!");
        Scanner a = new Scanner(System.in);
        selection = a.nextInt();

        if(selection > 4 || selection < 0){
            System.out.println("Invalid input");
            chooseTile();
        }
        chosenTile = Tile.presentTiles.get(selection - 1);
        Tile.replaceTile(selection-1);

        rotateTile(chosenTile);
    }
    public void rotateTile(ArrayList<String> tile){

        System.out.println("Would you like to rotate your tile?\nEnter 'yes' or 'no'");
        Scanner a = new Scanner(System.in);
        String input = a.nextLine();
        if(input.equalsIgnoreCase("no") ){
            placeTile(tile);
        }
        else if(input.equalsIgnoreCase("yes")){
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

            placeTile(tile);
        }
        else{
            System.out.println("Invalid input");
            rotateTile(tile);
        }

    }

    public void chooseToken() {
        boolean canPlace = false;
        chosenToken = Token.presentTokens.get(selection);
        for (ArrayList<String> myTile : myTiles) {
            if (myTile.get(1).contains(chosenToken) || myTile.get(2).contains(chosenToken)) {
                canPlace = true;
                break;
            }
        }
        if (canPlace) {
            System.out.println("Do you want to place the token? " + chosenToken);
            Scanner scanner = new Scanner(System.in);
            String in = scanner.nextLine();
            if (in.equalsIgnoreCase("yes")) {
                Token.replaceToken(selection);
                placeToken(chosenToken);
            } else {
                System.out.println("No token has been placed");
                System.out.println("\n\n");
                GameIntro.NextTurn();
            }
        } else {
            System.out.println("You cannot place this token");
            System.out.println("\n\n");
            GameIntro.NextTurn();
        }
    }

    public void placeToken(String token) {
        System.out.println("TOKEN PLACEMENT\nPlease enter the X coordinate where you want to place the token\n");
        Scanner a = new Scanner(System.in);
        String input = a.nextLine();
        int x = Integer.parseInt(input);
        if (x < 0 || x > 9) {
            System.out.println("\t\t\t\t\t\t\tInvalid input!!!!");
            placeToken(token);
        }
        System.out.println("\t\t\t\t\t\t\tPlease enter the Y coordinate where you want to place the token");
        Scanner b = new Scanner(System.in);
        String input2 = b.nextLine();
        int y = Integer.parseInt(input2);
        if (y < 0 || y > 9) {
            System.out.println("\t\t\t\t\t\t\tInvalid input!!!!");
            placeToken(token);
        }

        input2 += input;
        int position = Integer.parseInt(input2);

        if (this.myTiles.get(position) == Tile.EmptyTile) {
            System.out.println("\t\t\t\t\t\t\tThere is no tile placed here!!!!");
            placeToken(token);
        }

        if (!this.myTiles.get(position).get(1).contains(chosenToken) && !this.myTiles.get(position).get(2).contains(chosenToken)) {
            System.out.println("\t\t\t\t\t\t\tThis token cannot be placed here");
            placeToken(token);
        }

        if (this.myTiles.get(position).get(1).contains(chosenToken))
            this.myTiles.get(position).set(1, this.myTiles.get(position).get(1).replace(chosenToken, "\u001B[31m" + chosenToken + "\u001B[0m")) ;
        System.out.println("you have placed the token");
        System.out.println("\n\n");
        GameIntro.NextTurn();
    }

    public void placeStarterTile() {
        Tile a = Tile.generateTile();
        Tile b = Tile.generateTile();
        Tile c = Tile.generateTile();

        this.myTiles.set(44, a.TileDisplay());
        this.myTiles.set(54, b.TileDisplay());
        this.myTiles.set(55, c.TileDisplay());

    }


    public static void main(String[] args) {

    }

    public void fillBoard() {


        Tile.EmptyTile.add(TileColours.EMPTY_TILE_BAR);
        Tile.EmptyTile.add(TileColours.EMPTY_TILE_SIDE + "\t\t " + TileColours.EMPTY_TILE_SIDE);
        Tile.EmptyTile.add(TileColours.EMPTY_TILE_SIDE + "\t\t " + TileColours.EMPTY_TILE_SIDE);
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
