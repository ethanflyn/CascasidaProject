import java.util.ArrayList;
import java.util.Random;
import java.util.SimpleTimeZone;

public class Bot {
     static boolean shouldCull = false;
    static int tileIndex = -1;
    static int tileCord;
    static int maxScore;
    static int presentTokenIndex = 0;
    public static int getBotToken(ArrayList<ArrayList<String>> tiles, int natureTokens) {
        ArrayList<ArrayList<String>> tempTiles = tiles;
        ArrayList<ArrayList<String>> tempTiles2 = tiles;
        int tempScore = 0;
        maxScore = 0;
        int tempNature = natureTokens;

        for (int i = 0; i < Token.presentTokens.size(); i++) {
            String tempToken = Token.presentTokens.get(i);
            // habitatBotPlace method
            placeTile(tempTiles, Tile.presentTiles.get(i));
            tempTiles.set(tileCord, Tile.presentTiles.get(i));

            for (int j = 0; j < tempTiles.size(); j++) {
                if (Board.getTilesToken(tiles.get(j)).equalsIgnoreCase("0") &&
                        tempTiles2.get(j).get(1).contains(tempToken) ||
                        tempTiles2.get(j).get(2).contains(tempToken)) {

                    if (tempTiles2.get(j).get(1).contains(tempToken)) {
                        tempTiles2.get(j).set(1, tempTiles2.get(j).get(1).replace(tempToken, "\u001B[31m" + tempToken + "\u001B[0m"));
                    }

                    if (tempTiles2.get(j).get(2).contains(tempToken)) {
                        tempTiles2.get(j).set(2, tempTiles2.get(j).get(2).replace(tempToken, "\u001B[31m" + tempToken + "\u001B[0m"));
                    }

                    if (tempTiles2.get(j).get(1).contains("K") || tempTiles2.get(j).get(2).contains("K")) {
                        tempNature++;
                    }

                    tempScore = botScoring(tiles, tempNature);
                    if (tempScore > maxScore) {
                        maxScore = tempScore;
                        tileIndex = j;
                        presentTokenIndex = i;
                    }

                    if (tempTiles2.get(j).get(1).contains("\u001B[31m" + tempToken + "\u001B[0m")) {
                        tempTiles2.get(j).set(1, tempTiles2.get(j).get(1).replace("\u001B[31m" + tempToken + "\u001B[0m", tempToken));
                    }

                    if (tempTiles2.get(j).get(2).contains("\u001B[31m" + tempToken + "\u001B[0m")) {
                        tempTiles2.get(j).set(2, tempTiles2.get(j).get(2).replace("\u001B[31m" + tempToken + "\u001B[0m", tempToken));
                    }

                    tempNature = natureTokens;
                }
            }
            tempTiles.set(tileCord, Tile.EmptyTile);
        }
        return presentTokenIndex+1;
    }

    public static int BotTokenXCoordinate() {
        if (tileIndex != -1)
            return tileIndex / 10;
        else {
            throw new IllegalArgumentException("No token can be placed");
        }
    }

    public static int BotTokenYCoordinate() {
        if (tileIndex != -1)
            return tileIndex % 10;
        else {
            throw new IllegalArgumentException("No token can be placed");
        }
    }

    public static String BotPlaceToken() {
        return "yes";
    }
    
    

   public static char placeTileXCord(ArrayList<ArrayList<String>> boardOfTiles, ArrayList<String> tile){
        placeTile(boardOfTiles, tile);
        char ans = Integer.toString(tileCord).charAt(1);
        System.out.println("The y cord is " +  ans);
        return ans;
    }


    public static char placeTileYCord(ArrayList<ArrayList<String>> boardOfTiles,  ArrayList<String> tile){
        if (tileCord < 10) return '0';
        else{
           char ans = Integer.toString(tileCord).charAt(0);
            System.out.println("The x cord is " +  ans);
           return ans;
       }
    }

    public static void placeTile(ArrayList<ArrayList<String>> boardOfTiles, ArrayList<String> tile){


        String topBar = tile.get(0);
//        char leftSide = tile.get(1).charAt(0);
//        char rightSide = tile.get(1).charAt(tile.get(1).length());
        String bottomBar = tile.get(3);

        ArrayList<Integer> tileIndexes = new ArrayList<>();




            for(int j = 0;j < boardOfTiles.size();j++){
                if(!boardOfTiles.get(j).get(0).equals(TileColours.EMPTY_TILE_BAR)) {
                    tileIndexes.add(j);
                }
                Collections.shuffle(tileIndexes);
            }
            int i = 0;


        System.out.println(tileIndexes.get(0));
            while(true) {
                if (boardOfTiles.get(tileIndexes.get(i) + 1).get(0).equals(TileColours.EMPTY_TILE_BAR)) {
                    tileCord = tileIndexes.get(0) + 1;
                    break;
                } else if (boardOfTiles.get(tileIndexes.get(i) - 1).get(0).equals(TileColours.EMPTY_TILE_BAR)) {
                    tileCord = tileIndexes.get(0) - 1;
                    break;
                } else if (boardOfTiles.get(tileIndexes.get(i) + 10).get(0).equals(TileColours.EMPTY_TILE_BAR)) {
                    tileCord = tileIndexes.get(0) + 10;
                    break;
                } else if (boardOfTiles.get(tileIndexes.get(i) - 10).get(0).equals(TileColours.EMPTY_TILE_BAR)) {
                    tileCord = tileIndexes.get(0) - 10;
                    break;
                }
                else{
                    i++;
                }
            }

    }


    

    public static int botScoring(ArrayList<ArrayList<String>> tiles, int natureTokens) {
        int score = 0;
        score += Scoring.bearScoring(tiles);
        score += Scoring.hawkScoring(tiles);
        score += Scoring.foxScoring(tiles);
        score += Scoring.elkScoring(tiles);
        score += Scoring.salmonScoring(tiles);
        score += natureTokens;
        return score;
    }

    public void botCulling(ArrayList<String> tokens) {
        if (shouldCull) {
            Culling.cullRequired();
        }
    }

//     public static int botNatureTokens(int natureTokens) {
//         if (natureTokens > 0 && shouldCull) {
//             return 2;
//         }
//         return 0;
//     }
         public static int chooseNatureOption(){
        for(int i = 0;i < GameIntro.currentPlayer.getBoard().myTiles.size();i++){
            for (int j = 0;j < GameIntro.currentPlayer.getBoard().myTiles.get(i).size();j++){
                for(int k = 0; k< GameIntro.currentPlayer.getBoard().myTiles.get(i).get(j).toCharArray().length;k++){
                    if(GameIntro.currentPlayer.getBoard().myTiles.get(i).get(j).charAt(k) == Token.presentTokens.get(0).charAt(0)){
                        natureTokenChoice = 1;
                        return 1;
                    }
                    else if(GameIntro.currentPlayer.getBoard().myTiles.get(i).get(j).charAt(k) == Token.presentTokens.get(1).charAt(0)){
                        natureTokenChoice = 2;
                        return 1;
                    }
                    else if(GameIntro.currentPlayer.getBoard().myTiles.get(i).get(j).charAt(k) == Token.presentTokens.get(2).charAt(0)){
                        natureTokenChoice = 3;
                        return 1;
                    }
                    else if(GameIntro.currentPlayer.getBoard().myTiles.get(i).get(j).charAt(k) == Token.presentTokens.get(3).charAt(0)){
                        natureTokenChoice = 4;
                        return 1;
                    }
                }
            }
        }
        return 2;
    }
}
