import java.util.ArrayList;
import java.util.Random;
import java.util.SimpleTimeZone;

public class Bot {

    boolean shouldCull = false;
    public int getBotToken(ArrayList<ArrayList<String>> tiles, int natureTokens) {
        Random rng = new Random();
        int turnScore=0;
        String maxToken = "";

        boolean canPlace = false;
        int tokenIndex=0;
        for (int i = 0; i < Token.presentTokens.size(); i++) {
            for (ArrayList<String> tile : tiles) {
                if (!Board.getTilesToken(tile).equalsIgnoreCase(Token.presentTokens.get(i)) &&
                        tile.get(1).contains(Token.presentTokens.get(i)) ||
                        tile.get(2).contains(Token.presentTokens.get(i))) {
                    canPlace = true;
                    tokenIndex = i;
                    break;
                }
            }
        }

        if (Token.presentTokens.contains("B") && Scoring.bearScoring(tiles) > turnScore) {
            turnScore = Scoring.bearScoring(tiles);
            maxToken = "B";
        } if (Token.presentTokens.contains("S") && Scoring.salmonScoring(tiles) > turnScore) {
            turnScore = Scoring.salmonScoring(tiles);
            maxToken = "S";
        } if (Token.presentTokens.contains("H") && Scoring.hawkScoring(tiles) > turnScore) {
            turnScore =  Scoring.hawkScoring(tiles);
            maxToken = "H";
        } if (Token.presentTokens.contains("E") && Scoring.elkScoring(tiles) > turnScore) {
            turnScore = Scoring.elkScoring(tiles);
            maxToken = "E";
        } if (Token.presentTokens.contains("F") && Scoring.foxScoring(tiles) > turnScore) {
            maxToken = "F";
        }

        if (!maxToken.equals("")) {
            if (canPlace) {
                return tokenIndex;
            }
            return -1;
        }
        else {
            shouldCull = true;
            return Token.presentTokens.indexOf(maxToken);
        }
    }

    public void botCulling(ArrayList<String> tokens) {
        if (shouldCull) {
            Culling.cullRequired();
        }
    }

    public int botNatureTokens(int natureTokens) {
        if (natureTokens > 0 && shouldCull) {
            return 2;
        }
        return 0;
    }
}
