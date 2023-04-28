import java.util.ArrayList;
import java.util.Random;
import java.util.SimpleTimeZone;

public class Bot {
    static boolean shouldCull = false;
    static int tileIndex = -1;
    public static int getBotToken(ArrayList<ArrayList<String>> tiles, int natureTokens) {
        ArrayList<ArrayList<String>> tempTiles = tiles;
        ArrayList<ArrayList<String>> tempTiles2 = tiles;
        int tempScore = 0;
        int maxScore = 0;
        int tempNature = natureTokens;
        int presentTokenIndex = 0;
        for (int i = 0; i < Token.presentTokens.size(); i++) {
            String tempToken = Token.presentTokens.get(i);
            // habitatBotPlace method
            for (int j = 0; j < tempTiles.size(); j++) {
                if (Board.getTilesToken(tiles.get(j)).equalsIgnoreCase("0") &&
                        tempTiles.get(j).get(1).contains(tempToken) ||
                        tempTiles.get(j).get(2).contains(tempToken)) {

                    if (tempTiles.get(j).get(1).contains(tempToken)) {
                        tempTiles.get(j).set(1, tempTiles.get(j).get(1).replace(tempToken, "\u001B[31m" + tempToken + "\u001B[0m"));
                    }

                    if (tempTiles.get(j).get(2).contains(tempToken)) {
                        tempTiles.get(j).set(2, tempTiles.get(j).get(2).replace(tempToken, "\u001B[31m" + tempToken + "\u001B[0m"));
                    }

                    if (tempTiles.get(j).get(1).contains("K") || tempTiles.get(j).get(2).contains("K")) {
                        tempNature++;
                    }

                    tempScore = botScoring(tiles, tempNature);
                    if (tempScore > maxScore) {
                        maxScore = tempScore;
                        tileIndex = j;
                        presentTokenIndex = i;
                    }
                    tempTiles = tempTiles2;
                    tempNature = natureTokens;
                }
            }
            tempTiles = tiles;
        }
        return presentTokenIndex;
    }

    public int placeBotToken() {
        if (tileIndex != -1)
            return tileIndex;
        else {
            throw new IllegalArgumentException("No token can be placed");
        }
    }
    
    

    public static char placeTileXCord(ArrayList<ArrayList<String>> boardOfTiles,  ArrayList<String> tile){
        placeTile(boardOfTiles, tile);
        if (tileCord < 10) return '0';
        else{
           char ans = Integer.toString(tileCord).charAt(0);
           System.out.println(ans);
           return ans;
       }
    }
    public static char placeTileYCord(ArrayList<ArrayList<String>> boardOfTiles, ArrayList<String> tile){
        char ans = Integer.toString(tileCord).charAt(1);

        return ans;
    }


    public static void placeTile(ArrayList<ArrayList<String>> boardOfTiles, ArrayList<String> tile){

        ArrayList<Integer> tileIndexes = new ArrayList<>();




            for(int j = 0;j < boardOfTiles.size();j++){
                if(!boardOfTiles.get(j).get(0).equals(TileColours.EMPTY_TILE_BAR)) {
                    tileIndexes.add(j);
                }
            }

                Collections.shuffle(tileIndexes);
        System.out.println(tileIndexes.get(0));
            if(boardOfTiles.get(tileIndexes.get(0) + 1).get(0).equals(TileColours.EMPTY_TILE_BAR) && tileIndexes.get(0) % 10 != 9){
                tileCord =  tileIndexes.get(0) + 1;
            }
            else if(boardOfTiles.get(tileIndexes.get(0) - 1).get(0).equals(TileColours.EMPTY_TILE_BAR) && tileIndexes.get(0) % 10 != 0){
                tileCord = tileIndexes.get(0) - 1;
            }
            else if(boardOfTiles.get(tileIndexes.get(0) + 10).get(0).equals(TileColours.EMPTY_TILE_BAR) && tileIndexes.get(0) < 90){
                tileCord = tileIndexes.get(0) + 10;
            }
            else if(boardOfTiles.get(tileIndexes.get(0) - 10).get(0).equals(TileColours.EMPTY_TILE_BAR) && tileIndexes.get(0) > 10){
                tileCord = tileIndexes.get(0) - 10;
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

    public static int botNatureTokens(int natureTokens) {
        if (natureTokens > 0 && shouldCull) {
            return 2;
        }
        return 0;
    }
}
