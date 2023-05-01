
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Board {


    private static int counter = 0;
    public ArrayList<ArrayList<String>> myTiles = new ArrayList<>(); // all tiles on each player's board
    ArrayList<String> chosenTile = new ArrayList<>();
    public static String chosenToken;
    public int selection; // which tile/token pair user chooses
    public int natureTokens; // number of nature tokens each player has
    public int natureChoice; // which option player chooses to spend nature token on
    public Board() {

    }

    public int PlayerScoring() {
        int playerScore=0;
        playerScore += Scoring.scoring(myTiles);
        playerScore += natureTokens;
        System.out.println("You have added " + natureTokens + " points to your score for extra nature tokens");
        System.out.println("You're total score in Cascadia is " + playerScore + "\n");
        return playerScore;
    }


    public void placeTile(ArrayList<String> tile) {
        String input;
        String input2;
        System.out.println("TILE PLACEMENT\nPlease enter the X coordinate of your desired tile\n");
        if(GameIntro.currentPlayer.getName().equals("BOT")){
          char num = Bot.placeTileXCord(GameIntro.currentPlayer.getBoard().myTiles ,tile);
            input = Character.toString(num);
        }
        else {
            Scanner a = new Scanner(System.in);
            input = a.nextLine();
        }
        int x = Integer.parseInt(input);
        if(x < 0 || x > 9) {
            System.out.println("Invalid input!!!!");
            placeTile(tile);
        }
        System.out.println("Please enter the Y coordinate of your desired tile");
        if(GameIntro.currentPlayer.getName().equals("BOT")){
            char num = Bot.placeTileYCord(GameIntro.currentPlayer.getBoard().myTiles  ,tile);
            input2 = Character.toString(num);
        }
        else {
            Scanner b = new Scanner(System.in);
            input2 = b.nextLine();
        }
        int y = Integer.parseInt(input2);
        if(y < 0 || y > 9) {
            System.out.println("Invalid input!!!!");
            placeTile(tile);
        }

        input2 += input;
        int position = Integer.parseInt(input2);

        if (this.myTiles.get(position + 1) == Tile.EmptyTile && this.myTiles.get(position - 1) == Tile.EmptyTile && this.myTiles.get(position + 10) == Tile.EmptyTile && this.myTiles.get(position - 10) == Tile.EmptyTile) {
            System.out.println("There is no adjacent Tiles!!!!");
            placeTile(tile);
            return;
        }
        if (this.myTiles.get(position) != Tile.EmptyTile) {
            System.out.println("There is already a tile placed here!!!!");
            placeTile(tile);
            return;
        }

        this.myTiles.set(position, tile);

        showBoard(0);
        System.out.println("\n\n");
    }

    public void chooseTile(){
        int input;
        natureChoice = spendNature();
        if (natureChoice == 2) {
            System.out.println("Enter the number of tokens you wish to replace");
            if(GameIntro.currentPlayer.getName().equals("BOT")){
                 input = Bot.replaceTokens();

            }
            else{
            Scanner scanner = new Scanner(System.in);
                 input = scanner.nextInt();
            }


            int k = input;
            int i = 0;
            while (i < k--){
                Token.replaceToken(i);
            }
            Tile.playableTiles();
            System.out.println(input + " tokens have been replaced");
        }

        System.out.println("Please enter a number between 1-4 to choose which tile you would like to place!");
        if(GameIntro.currentPlayer.getName().equals("BOT")){
            selection = Bot.getBotToken(myTiles, natureTokens);
        }
        else {
            Scanner a = new Scanner(System.in);
            selection = a.nextInt();
        }


        if(selection > 4 || selection < 0){
            System.out.println("Invalid input");
            chooseTile();
            return;
        }
        chosenTile = Tile.presentTiles.get(selection - 1);
        Tile.replaceTile(selection-1);

        rotateTile(chosenTile);
    }
    public void rotateTile(ArrayList<String> tile){
        String input;
        System.out.println("Would you like to rotate your tile?\nEnter 'yes' or 'no'");
        if(GameIntro.currentPlayer.getName().equals("BOT")){
            input = Bot.rotateTile();
        }
        else {
            Scanner a = new Scanner(System.in);
            input = a.nextLine();
        }

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

    public int spendNature() {
        String choice;
        int choice2=0;
        if (natureTokens > 0) {
            System.out.println("Do you want to spend a nature token? You currently have " + natureTokens);
            if(GameIntro.currentPlayer.getName().equals("BOT")){
                choice = Bot.spendNature();
            }
            else {
                Scanner scanner1 = new Scanner(System.in);
                choice = scanner1.nextLine();
            }

             if (choice.equalsIgnoreCase("yes")) {
                System.out.println("Enter 1 to choose any Token, Enter 2 to wipe any number of tokens");
                if (GameIntro.currentPlayer.getName().equals("BOT")) {
                    choice2 = Bot.chooseNatureOption();
                }
                else{
                    Scanner scanner2 = new Scanner(System.in);
                    choice2 = scanner2.nextInt();
                  }
                natureTokens--;
            }
        }
        return choice2;
    }

    public void chooseToken() {

        boolean canPlace = false;
        if (natureChoice == 1) {
            for (int i = 0; i < Token.presentTokens.size(); i++) {
                System.out.print("     " + Token.presentTokens.get(i) + "\t\t\t");
            }
            System.out.println("\n");
            System.out.println("Please enter a number between 1-4 to choose which token you would like to place!");
            if(GameIntro.currentPlayer.getName().equals("BOT")){
                selection = Bot.getBotToken(myTiles, natureTokens);
            }
            else {
                Scanner a = new Scanner(System.in);
                selection = a.nextInt();
            }
        }
        chosenToken = Token.presentTokens.get(selection-1);
        for (ArrayList<String> myTile : myTiles) {
            if (!getTilesToken(myTile).equalsIgnoreCase(chosenToken) && myTile.get(1).contains(chosenToken) || myTile.get(2).contains(chosenToken)) {
                canPlace = true;
                break;
            }
        }
        if (canPlace) {
            String in;
            System.out.println("Do you want to place the token? " + chosenToken);
            if(GameIntro.currentPlayer.getName().equals("BOT")){
                in = Bot.placeTokens();
            }
            else {
                Scanner scanner = new Scanner(System.in);
                in = scanner.nextLine();
            }
            if (in.equalsIgnoreCase("yes")) {
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
        String input;
        String input2;
        System.out.println("TOKEN PLACEMENT\nPlease enter the X coordinate where you want to place the token\n");
        if(GameIntro.currentPlayer.getName().equals("BOT")){
            input = String.valueOf(Bot.BotTokenXCoordinate());
        }
        else {
            Scanner a = new Scanner(System.in);
             input = a.nextLine();
        }
        int x = Integer.parseInt(input);
        if (x < 0 || x > 9) {
            System.out.println("Invalid input!!!!");
            placeToken(token);
        }
        System.out.println("Please enter the Y coordinate where you want to place the token");
        if(GameIntro.currentPlayer.getName().equals("BOT")){
            input2 = String.valueOf(Bot.BotTokenYCoordinate());
        }
        else {
            Scanner b = new Scanner(System.in);
            input2 = b.nextLine();
        }
        int y = Integer.parseInt(input2);
        if (y < 0 || y > 9) {
            System.out.println("Invalid input!!!!");
            placeToken(token);
        }

        input2 += input;
        int position = Integer.parseInt(input2);

        if (this.myTiles.get(position) == Tile.EmptyTile) {
            System.out.println("There is no tile placed here!!!!");
            placeToken(token);
        }

        if (!this.myTiles.get(position).get(1).contains(token) && !this.myTiles.get(position).get(2).contains(token)) {
            System.out.println("This token cannot be placed here");
            placeToken(token);
        }

        if (this.myTiles.get(position).get(1).contains("\u001B[31m" + token + "\u001B[0m") || this.myTiles.get(position).get(2).contains("\u001B[31m" + token + "\u001B[0m")) {
            System.out.println("There is already a token placed here");
            placeToken(token);
        }

        if (this.myTiles.get(position).get(1).contains(token)) {
            this.myTiles.get(position).set(1, this.myTiles.get(position).get(1).replace(token, "\u001B[31m" + token + "\u001B[0m"));
        }

        if (this.myTiles.get(position).get(2).contains(token)) {
            this.myTiles.get(position).set(2, this.myTiles.get(position).get(2).replace(token, "\u001B[31m" + token + "\u001B[0m"));
        }

        if (this.myTiles.get(position).get(1).contains("K") || this.myTiles.get(position).get(2).contains("K")) {
            natureTokens++;
            System.out.println("You now have " + natureTokens + " nature tokens");
        }

         System.out.println("you have placed the token");
        System.out.println("\n\n");
        Token.replaceToken(selection-1);
        showBoard(0);
        Scoring.scoring(myTiles);
        GameIntro.NextTurn();
    }
    }

    public static String getTilesToken(ArrayList<String> list) {
        if (list.get(1).contains("\u001B[31m" + "H" + "\u001B[0m") || list.get(2).contains("\u001B[31m" + "H" + "\u001B[0m")) {
            return "H";
        } else if (list.get(1).contains("\u001B[31m" + "B" + "\u001B[0m") || list.get(2).contains("\u001B[31m" + "B" + "\u001B[0m")) {
            return "B";
        } else if (list.get(1).contains("\u001B[31m" + "F" + "\u001B[0m") || list.get(2).contains("\u001B[31m" + "F" + "\u001B[0m")) {
            return "F";
        } else if (list.get(1).contains("\u001B[31m" + "S" + "\u001B[0m") || list.get(2).contains("\u001B[31m" + "S" + "\u001B[0m")) {
            return "S";
        } else if (list.get(1).contains("\u001B[31m" + "E" + "\u001B[0m") || list.get(2).contains("\u001B[31m" + "E" + "\u001B[0m")) {
            return "E";
        } else {
            return "0";
        }
    }

    public void placeStarterTile() {
        Tile a = Tile.generateTile();
        Tile b = Tile.generateTile();
        Tile c = Tile.generateTile();

        this.myTiles.set(44, a.TileDisplay());
        this.myTiles.set(54, b.TileDisplay());
        this.myTiles.set(55, c.TileDisplay());

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
