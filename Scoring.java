import java.util.ArrayList;
public class Scoring {
    public static ArrayList<ArrayList<String>> bearTiles = new ArrayList<>();
    public static ArrayList<ArrayList<String>> salmonRun = new ArrayList<>();
    public static ArrayList<ArrayList<String>> elkRun = new ArrayList<>();

    public static int currentMountainScore;
    public static int currentForestScore;
    public static int currentRiverScore;
    public static int currentWetlandScore;
    public static int currentPrarieScore;

    public static int getCurrentMountainScore() {
        return currentMountainScore;
    }

    public static int getCurrentForestScore() {
        return currentForestScore;
    }

    public static int getCurrentRiverScore() {
        return currentRiverScore;
    }

    public static int getCurrentWetlandScore() {
        return currentWetlandScore;
    }

    public static int getCurrentPrarieScore() {
        return currentPrarieScore;
    }

    public static void setCurrentMountainScore(int currentMountainScore) {
        Scoring.currentMountainScore = currentMountainScore;
    }

    public static void setCurrentForestScore(int currentForestScore) {
        Scoring.currentForestScore = currentForestScore;
    }

    public static void setCurrentRiverScore(int currentRiverScore) {
        Scoring.currentRiverScore = currentRiverScore;
    }

    public static void setCurrentWetlandScore(int currentWetlandScore) {
        Scoring.currentWetlandScore = currentWetlandScore;
    }

    public static void setCurrentPrarieScore(int currentPrarieScore) {
        Scoring.currentPrarieScore = currentPrarieScore;
    }


    /**
     * method will take all the player's placed tiles and perform
     * all the scoring methods on it for each wildlife
     * the results will be added and returned as the final score
     *
     * @param tiles 2D array on which to perform scoring methods
     * @return the final score from all the wildlife scoring methods
     */

    public static int scoring(ArrayList<ArrayList<String>> tiles) {
        int score=0;

        int bears = bearScoring(tiles);
        bearTiles.clear();
        score += bears;
        System.out.println("You scored " + bears + " points from bear tiles");

        int hawks = hawkScoring(tiles);
        score += hawks;
        System.out.println("You scored " + hawks + " points from hawk tiles");

        int foxes = foxScoring(tiles);
        score += foxes;
        System.out.println("You scored " + foxes + " points from fox tiles");

        int elk = elkScoring(tiles);
        elkRun.clear();
        score += elk;
        System.out.println("You scored " + elkScoring(tiles) + " points from elk tiles");

        int salmon = salmonScoring(tiles);
        salmonRun.clear();
        score += salmon;
        System.out.println("You scored " + salmon + " points from salmon tiles");

        System.out.println("You're total score from wildlife tokens is " + score);

        System.out.println("Your total score from habitat placement is " + habitatScoring(tiles));
        score += habitatScoring(tiles);

        return score;
    }

    /**
     * method to check 2D for pairs of bears
     * if more than one bear is adjacent to the specified index return 0
     *
     * @param tiles 2D array as input to perform the checkBears method on
     * @param i index of the 2D array to check for bear pairs
     * @return if only connected to one other bear, return the index of the connected bear
     */
    public static int checkBears(ArrayList<ArrayList<String>> tiles, int i) {
        int bearCount = 0;
        int position = 0;

        if (Board.getTilesToken(tiles.get(i)).equalsIgnoreCase("B") && !bearTiles.contains(tiles.get(i))) {
            bearCount++;
            bearTiles.add(tiles.get(i));
            if (i - 10 >= 0 && Board.getTilesToken(tiles.get(i - 10)).equalsIgnoreCase("B")) {
                bearCount++;
                position = i - 10;
            }
            if (i + 10 < 100 && Board.getTilesToken(tiles.get(i + 10)).equalsIgnoreCase("B")) {
                bearCount++;
                position = i + 10;
            }
            if ((i - 1) % 9 != 0 && (i - 1) >= 0 && Board.getTilesToken(tiles.get(i - 1)).equalsIgnoreCase("B")) {
                bearCount++;
                position = i - 1;
            }
            if ((i + 1) % 10 != 0 && (i + 1) < 100 && Board.getTilesToken(tiles.get(i + 1)).equalsIgnoreCase("B")) {
                bearCount++;
                position = i + 1;
            }
            if (bearCount == 2)
                return position;
        }

        return 0;
    }

    /** method to score bear pairs in the 2D array
     * Uses the checkBears method to help find and count bear pairs in the array
     *
     * @param tiles 2D array on which to score for bears
     * @return score for bear pairs in the tile 2D array
     */

    public static int bearScoring(ArrayList<ArrayList<String>> tiles) {
        int bearPairs=0;
        int score=0;
        for (int i = 0; i < tiles.size(); i++) {
            int index = checkBears(tiles, i);
            if (index != 0) {
                if (checkBears(tiles, index) == i) {
                    bearPairs++;
                }
            }
        }
        switch (bearPairs) {
            case 1:
                score += 4;
                break;
            case 2:
                score += 11;
                break;
            case 3:
                score += 19;
                break;
            case 4:
                score += 27;
                break;
            default:
                break;
        }
        return score;
    }

    /**
     * method to check the surrounding tokens of a fox token
     * and find the number of unique tokens
     *
     * @param tiles 2D array on which to score for foxes
     * @param i index of the 2D array to check for foxes
     * @return the number of unique tokens surrounding the fox token
     */

    public static int checkFoxes(ArrayList<ArrayList<String>> tiles, int i) {

        if (Board.getTilesToken(tiles.get(i)).equalsIgnoreCase("F")) {
            ArrayList<ArrayList<String>> surround = new ArrayList<>();
            ArrayList<String> uniqueTokens = new ArrayList<>();

            if (i - 10 >= 0)
                surround.add(tiles.get(i - 10));
            if (i + 10 < 100)
                surround.add(tiles.get(i + 10));
            if ((i - 1) % 9 != 0 && (i - 1) > 0)
                surround.add(tiles.get(i - 1));
            if ((i + 1) % 10 != 0 && (i + 1) < 100)
                surround.add(tiles.get(i + 1));
            if ((i + 11) % 10 != 0 && (i + 11) < 100)
                surround.add(tiles.get(i + 11));
            if ((i + 9) % 10 != 0 && (i + 9) < 100)
                surround.add(tiles.get(i + 9));
            if ((i - 11) % 9 != 0 && (i - 11) > 0)
                surround.add(tiles.get(i - 11));
            if ((i - 9) % 9 != 0 && (i - 9) > 0)
                surround.add(tiles.get(i - 9));

            for (ArrayList<String> strings : surround) {
                if (!uniqueTokens.contains(Board.getTilesToken(strings)) && !Board.getTilesToken(strings).equals("0")) {
                    uniqueTokens.add(Board.getTilesToken(strings));
                }
            }

            return uniqueTokens.size();
        }
        return 0;
    }

    /**
     * method to find the total score from fox tokens in the 2D array
     *
     * @param tiles 2D array on which to score for foxes
     * @return score for fox tokens in given 2D array
     */
    public static int foxScoring(ArrayList<ArrayList<String>> tiles) {
        int score=0;
        for (int i = 0; i < tiles.size(); i++) {
            score += checkFoxes(tiles, i);
        }

        return score;
    }

    /**
     * method to check for straight lines of elk in the 2D array
     * Checks for both horizontal and vertical lines of elk and return the max
     * @param tiles 2D array on which to score for elk
     * @param i index of the 2D array to check for elk
     * @return max length of elk run
     */
    public static int checkElk(ArrayList<ArrayList<String>> tiles, int i) {
        int maxElk1=1;
        int maxElk2=1;
        int input = i;
        if (Board.getTilesToken(tiles.get(i)).equalsIgnoreCase("E") && !elkRun.contains(tiles.get(i))) {
            elkRun.add(tiles.get(i));
            while ((input+1) % 10 != 0 && (input+1) < 100 && Board.getTilesToken(tiles.get(input+1)).equalsIgnoreCase("E") && !elkRun.contains(tiles.get(input+1)) ) {
                elkRun.add(tiles.get(input+1));
                maxElk1++;
                input++;
            }
            input = i;
            while ((input-1) % 9 != 0 && (input-1) >= 0 && Board.getTilesToken(tiles.get(input-1)).equalsIgnoreCase("E") && !elkRun.contains(tiles.get(input-1))) {
                elkRun.add(tiles.get(input-1));
                maxElk1++;
                input--;
            }
            input = i;
            while (input+10 < 100 && Board.getTilesToken(tiles.get(input+10)).equalsIgnoreCase("E") && !elkRun.contains(tiles.get(input+10))) {
                elkRun.add(tiles.get(input+10));
                maxElk2++;
                input += 10;
            }
            input = i;
            while (input-10 >= 0 && Board.getTilesToken(tiles.get(input-10)).equalsIgnoreCase("E") && !elkRun.contains(tiles.get(input-10))) {
                elkRun.add(tiles.get(input-10));
                maxElk2++;
                input -= 10;
            }
            return Math.max(maxElk1, maxElk2);
        }
        return 0;
    }

    /**
     * method to calculate total score from elk tiles in the 2D array
     * @param tiles 2D array on which to score for elk
     * @return total score for elk tiles in the array
     */
    public static int elkScoring(ArrayList<ArrayList<String>> tiles) {
        int score=0;
        for (int i = 0; i < tiles.size(); i++) {
            switch (checkElk(tiles, i)) {
                case 1:
                    score += 2;
                    break;
                case 2:
                    score += 5;
                    break;
                case 3:
                    score += 9;
                    break;
                case 4:
                case 5:
                case 6:
                    score += 13;
                    break;
                default:
                    break;
            }
        }
        return score;
    }

    /**
     * method to check for hawk tokens in the given 2D array
     * If any other hawks are adjacent 0 points are scored
     * @param tiles 2D array on which to score for hawks
     * @param i index of the 2D array to check for hawks
     * @return 1 point if it is a lone hawk
     */
    public static int checkHawks(ArrayList<ArrayList<String>> tiles, int i) {
        int hawkCount=0;

        if (Board.getTilesToken(tiles.get(i)).equalsIgnoreCase("H")) {
            hawkCount++;
            if (i-10 >= 0 && Board.getTilesToken(tiles.get(i-10)).equalsIgnoreCase("H"))
                return 0;
            if (i+10 < 100 && Board.getTilesToken(tiles.get(i+10)).equalsIgnoreCase("H"))
                return 0;
            if ((i-1) % 9 != 0 && (i-1) >= 0 && Board.getTilesToken(tiles.get(i-1)).equalsIgnoreCase("H"))
                return 0;
            if ((i+1) % 9 != 0 && (i+1) >= 0 && Board.getTilesToken(tiles.get(i+1)).equalsIgnoreCase("H"))
                return 0;
            if ((i+11) % 10 != 0 && (i+11) < 100 && Board.getTilesToken(tiles.get(i+11)).equalsIgnoreCase("H"))
                return 0;
            if ((i+9) % 10 != 0 && (i+9) < 100 && Board.getTilesToken(tiles.get(i+9)).equalsIgnoreCase("H"))
                return 0;
            if ((i-11) % 9 != 0 && (i-1) >= 0 && Board.getTilesToken(tiles.get(i-11)).equalsIgnoreCase("H"))
                return 0;
            if ((i-9) % 9 != 0 && (i-9) >= 0 && Board.getTilesToken(tiles.get(i-9)).equalsIgnoreCase("H"))
                return 0;
        }

        return hawkCount;
    }

    /**
     * method to calculate total score for hawk tokens in the array
     * @param tiles 2D array on which to score for hawks
     * @return total score for hawks in the given array
     */
    public static int hawkScoring(ArrayList<ArrayList<String>> tiles) {
        int hawks=0;
        int score=0;
        for (int i = 0; i < tiles.size(); i++) {
            if (checkHawks(tiles, i) != 0) {
                hawks++;
            }
        }
        switch (hawks) {
            case 1:
                score += 2;
                break;
            case 2:
                score += 5;
                break;
            case 3:
                score += 8;
                break;
            case 4:
                score += 11;
                break;
            case 5:
                score += 14;
                break;
            case 6:
                score += 18;
                break;
            case 7:
                score += 22;
                break;
            default:
                break;
        }
        return score;
    }

    /**
     * method to check for salmon runs in the given 2D array
     * recursively checks for adjacent salmon, adding 1 to the run each time it is called
     * @param tiles 2D array on which to score for salmon
     * @param i index of the 2D array to check for salmon
     * @return the total number of salmon in the run
     */
    public static int checkSalmon(ArrayList<ArrayList<String>> tiles, int i) {
        int salmonCount = 0;
        int position=0;
        int position1=0;

        if (Board.getTilesToken(tiles.get(i)).equalsIgnoreCase("S") && !salmonRun.contains(tiles.get(i))) {
            salmonRun.add(tiles.get(i));
            salmonCount++;

            if (i - 10 >= 0 && Board.getTilesToken(tiles.get(i - 10)).equalsIgnoreCase("S")) {
                salmonCount++;
                position = i - 10;
            }
            if (i + 10 < 100 && Board.getTilesToken(tiles.get(i + 10)).equalsIgnoreCase("S")) {
                salmonCount++;
                if (position == 0)
                    position = i+10;
                else {
                    position1 = i+10;
                }
            }
            if ((i - 1) % 9 != 0 && (i - 1) >= 0 && Board.getTilesToken(tiles.get(i - 1)).equalsIgnoreCase("S")) {
                salmonCount++;
                if (position == 0)
                    position = i-1;
                else {
                    position1 = i-1;
                }
            }
            if ((i + 1) % 10 != 0 && (i + 1) < 100 && Board.getTilesToken(tiles.get(i + 1)).equalsIgnoreCase("S")) {
                salmonCount++;
                if (position == 0)
                    position = i+1;
                else {
                    position1 = i+1;
                }
            }
            if (salmonCount == 1)
                return 1;
            else if (salmonCount == 2)
                return 1 + checkSalmon(tiles, position);
            else if (salmonCount == 3)
                return 1 + checkSalmon(tiles, position) + checkSalmon(tiles, position1);

            else {
                return -50;
            }
        }
        return 0;
    }

    /**
     * calculates total score for salmon on given array
     * @param tiles 2D array on which to score for salmon
     * @return total score for salmon tokens
     */
    public static int salmonScoring(ArrayList<ArrayList<String>> tiles) {
        int score=0;
        for (int i = 0; i < tiles.size(); i++) {
            int temp = checkSalmon(tiles, i);
            if (temp > 0) {
                switch (temp) {
                    case 1:
                        score += 2;
                        break;
                    case 2:
                        score += 4;
                        break;
                    case 3:
                        score += 7;
                        break;
                    case 4:
                        score += 11;
                        break;
                    case 5:
                        score += 15;
                        break;
                    case 6:
                        score += 20;
                        break;
                    case 7:
                        score += 26;
                        break;
                    default:
                        break;
                }
            }
        }
        return score;
    }

    public static int habitatScoring(ArrayList<ArrayList<String>> tiles) {
        int prairieScore = 0;
        int mountainScore = 0;
        int wetlandScore = 0;
        int forestScore = 0;
        int riverScore = 0;
        int temp;

        ArrayList<Integer> habitatRunIndexes = new ArrayList<>();
        for(int i = 0;i < tiles.size();i++)
        {
            if(tiles.get(i).get(0).equals(TileColours.PRAIRIE_BAR) || tiles.get(i).get(3).equals(TileColours.PRAIRIE_BAR)) {
                habitatRunIndexes.add(i);
                temp  = tileSearch(tiles.get(i), tiles, TileColours.PRAIRIE_BAR, TileColours.PRAIRIE_SIDE, i, 0, habitatRunIndexes);
                habitatRunIndexes.clear();
                if(prairieScore < temp)  prairieScore = temp;
            }
            if(tiles.get(i).get(0).equals(TileColours.MOUNTAIN_BAR) || tiles.get(i).get(3).equals(TileColours.MOUNTAIN_BAR)){
                habitatRunIndexes.add(i);
                temp = tileSearch(tiles.get(i), tiles, TileColours.MOUNTAIN_BAR,TileColours.MOUNTAIN_SIDE, i, 0, habitatRunIndexes);
                habitatRunIndexes.clear();
                if(mountainScore < temp) mountainScore = temp;
            }
            if(tiles.get(i).get(0).equals(TileColours.WETLAND_BAR) || tiles.get(i).get(3).equals(TileColours.WETLAND_BAR)){
                habitatRunIndexes.add(i);
                temp = tileSearch(tiles.get(i), tiles, TileColours.WETLAND_BAR,TileColours.WETLAND_SIDE,  i, 0, habitatRunIndexes);
                habitatRunIndexes.clear();
                if(wetlandScore < temp) wetlandScore = temp;
            }
            if(tiles.get(i).get(0).equals(TileColours.FOREST_BAR) || tiles.get(i).get(3).equals(TileColours.FOREST_BAR)){
                habitatRunIndexes.add(i);
                temp = tileSearch(tiles.get(i), tiles, TileColours.FOREST_BAR, TileColours.FOREST_SIDE, i, 0, habitatRunIndexes);
                habitatRunIndexes.clear();
                if(forestScore < temp) forestScore = temp;
            }
            if(tiles.get(i).get(0).equals(TileColours.RIVER_BAR) || tiles.get(i).get(3).equals(TileColours.RIVER_BAR)){
                habitatRunIndexes.add(i);
                temp = tileSearch(tiles.get(i), tiles, TileColours.RIVER_BAR, TileColours.RIVER_SIDE, i, 0, habitatRunIndexes);
                habitatRunIndexes.clear();
                if(riverScore < temp) riverScore = temp;
            }
        }
        setCurrentForestScore(forestScore);
        setCurrentMountainScore(mountainScore);
        setCurrentRiverScore(riverScore);
        setCurrentWetlandScore(wetlandScore);
        setCurrentPrarieScore(prairieScore);


        return mountainScore + forestScore + riverScore + wetlandScore + prairieScore;
    }

    public static int tileSearch( ArrayList<String> currentTile, ArrayList<ArrayList<String>> tiles, String bar, String side, int index, int score, ArrayList<Integer> habitatRunIndexes){
        if(index < 0 || index > 100){
            return score + 1;
        }
        int right = 0;
        int left = 0;
        int up = 0;
        int down = 0;
        boolean topBar = false, bottomBar = false;
        if(currentTile.get(0).equals(bar)) topBar = true;
        if(currentTile.get(3).equals(bar)) bottomBar = true;

        if(tiles.get(index + 1).get(0).equals(currentTile.get(0)) && topBar && !habitatRunIndexes.contains(index + 1)){
            habitatRunIndexes.add(index + 1);
            right = tileSearch(tiles.get(index + 1),tiles,bar,side, index + 1, score + 1, habitatRunIndexes);
        }
        else if(tiles.get(index + 1).get(3).equals(currentTile.get(3)) && bottomBar && !habitatRunIndexes.contains(index + 1)){
            habitatRunIndexes.add(index + 1);
            right = tileSearch(tiles.get(index + 1),tiles,bar,side, index + 1, score + 1, habitatRunIndexes);
        }
        else if(tiles.get(index - 1).get(0).equals(currentTile.get(0)) && topBar && !habitatRunIndexes.contains(index - 1)){
            habitatRunIndexes.add(index - 1);
            left = tileSearch(tiles.get(index -1 ),tiles,bar, side, index - 1, score + 1, habitatRunIndexes);
        }
        else if(tiles.get(index - 1).get(3).equals(currentTile.get(3)) && bottomBar && !habitatRunIndexes.contains(index - 1)){
            habitatRunIndexes.add(index - 1);
            left = tileSearch(tiles.get(index -1),tiles,bar, side, index - 1, score + 1, habitatRunIndexes);
        }
        else if(tiles.get(index + 10).get(0).equals(currentTile.get(3)) && bottomBar && !habitatRunIndexes.contains(index + 10)){
            habitatRunIndexes.add(index + 10);
            up = tileSearch(tiles.get(index + 10), tiles,bar, side, index + 10, score + 1, habitatRunIndexes);
        }
        else if(tiles.get(index - 10).get(3).equals(currentTile.get(0)) && topBar &&  !habitatRunIndexes.contains(index - 10)){
            habitatRunIndexes.add(index - 10);
            down = tileSearch(tiles.get(index - 10),tiles,bar, side, index - 10, score + 1, habitatRunIndexes);
        }
        else{
            return score + 1;
        }
        int first = Math.max(left,right);
        int last = Math.max(up,down);

        return Math.max(first,last);

    }
}
