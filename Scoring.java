import java.util.ArrayList;
public class Scoring {
    private int position;

    public static ArrayList<ArrayList<String>> tiles;
    public static ArrayList<ArrayList<String>> bearTiles = new ArrayList<>();
    public static ArrayList<ArrayList<String>> salmonRun = new ArrayList<>();
    public static int scoring(ArrayList<ArrayList<String>> tiles) {
        int score=0;
        int bears = bearScoring(tiles);
        score += bears;
        System.out.println("You scored " + bears + " points from bear tiles");
        score += hawkScoring(tiles);
        System.out.println("You scored " + hawkScoring(tiles) + " points from hawk tiles");
        score += foxScoring(tiles);
        System.out.println("You scored " + foxScoring(tiles) + " points from fox tiles");
        score += elkScoring(tiles);
        System.out.println("You scored " + elkScoring(tiles) + " points from elk tiles");
        int salmon = salmonScoring(tiles);
        score += salmon;
        System.out.println("You scored " + salmon + " points from salmon tiles");
        System.out.println("You're total score from wildlife tokens is " + score);
        System.out.println("Your total score from habitat placement is " + habitatScoring(tiles));
        score += habitatScoring(tiles);
        return score;
    }

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

    public static int checkFoxes(ArrayList<ArrayList<String>> tiles, int i) {

        if (Board.getTilesToken(tiles.get(i)).equalsIgnoreCase("F")) {
            ArrayList<ArrayList<String>> surround = new ArrayList<>();
            ArrayList<String> uniqueTokens = new ArrayList<>();

            if (i - 10 >= 0)
                surround.add(tiles.get(i - 10));
            if (i + 10 < 0)
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

    public static int foxScoring(ArrayList<ArrayList<String>> tiles) {
        int score=0;
        for (int i = 0; i < tiles.size(); i++) {
            score += checkFoxes(tiles, i);
        }

        return score;
    }

    public static int checkElk(ArrayList<ArrayList<String>> tiles, int i) {
        int maxElk1=1;
        int maxElk2=1;
        int input = i;
        if (Board.getTilesToken(tiles.get(i)).equalsIgnoreCase("E")) {
            while ((input+1) % 10 != 0 && (input+1) < 100 && Board.getTilesToken(tiles.get(input+1)).equalsIgnoreCase("E") ) {
                maxElk1++;
                input++;
            }
            input = i;
            while ((input-1) % 9 != 0 && (input-1) >= 0 && Board.getTilesToken(tiles.get(input-1)).equalsIgnoreCase("E")) {
                maxElk1++;
                input--;
            }
            input = i;
            while (input+10 < 100 && Board.getTilesToken(tiles.get(input+10)).equalsIgnoreCase("E")) {
                maxElk2++;
                input += 10;
            }
            input = i;
            while (input-10 >= 0 && Board.getTilesToken(tiles.get(input-10)).equalsIgnoreCase("E")) {
                maxElk2++;
                input -= 10;
            }
            return Math.max(maxElk1, maxElk2);
        }
        return 0;
    }

    public static int elkScoring(ArrayList<ArrayList<String>> tiles) {
        int elk=0;
        int score=0;
        for (int i = 0; i < tiles.size(); i++) {
            if (checkElk(tiles, i) != 0) {
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
        }
        return score;
    }

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
//        public static final String PRAIRIE_BAR = "\u001B[43m" + "\t\t\t" + "\u001B[0m";
//    public static final String PRAIRIE_SIDE = "\u001B[43m" + "  " + "\u001B[0m";

    public static int habitatScoring(ArrayList<ArrayList<String>> tiles) {
        int prairieScore = 0;
        int mountainScore = 0;
        int wetlandScore = 0;
        int forestScore = 0;
        int riverScore = 0;
        int temp;
//        ArrayList<String> prairieTile = new ArrayList<>();
//        ArrayList<String> mountainTile = new ArrayList<>();
//        ArrayList<String> wetlandTile = new ArrayList<>();
//        ArrayList<String> forestTile = new ArrayList<>();
//        ArrayList<String> riverTile  = new ArrayList<>();
//
//        prairieTile.add(TileColours.PRAIRIE_BAR);
//        prairieTile.add(TileColours.PRAIRIE_SIDE + "        " + TileColours.PRAIRIE_SIDE);
//        prairieTile.add(TileColours.PRAIRIE_SIDE + "        " + TileColours.PRAIRIE_SIDE);
//        prairieTile.add(TileColours.PRAIRIE_BAR);
//
//        mountainTile.add(TileColours.MOUNTAIN_BAR);
//        mountainTile.add(TileColours.MOUNTAIN_SIDE + "        " + TileColours.MOUNTAIN_SIDE);
//        mountainTile.add(TileColours.MOUNTAIN_SIDE + "        " + TileColours.MOUNTAIN_SIDE);
//        mountainTile.add(TileColours.MOUNTAIN_BAR);
//
//        wetlandTile.add(TileColours.WETLAND_BAR);
//        wetlandTile.add(TileColours.WETLAND_SIDE + "        " + TileColours.WETLAND_SIDE);
//        wetlandTile.add(TileColours.WETLAND_SIDE + "        " + TileColours.WETLAND_SIDE);
//        wetlandTile.add(TileColours.WETLAND_BAR);
//
//        forestTile.add(TileColours.FOREST_BAR);
//        forestTile.add(TileColours.FOREST_SIDE + "        " + TileColours.FOREST_SIDE);
//        forestTile.add(TileColours.FOREST_SIDE + "        " + TileColours.FOREST_SIDE);
//        forestTile.add(TileColours.FOREST_BAR);
//
//        riverTile.add(TileColours.RIVER_BAR);
//        riverTile.add(TileColours.RIVER_SIDE + "        " + TileColours.RIVER_SIDE);
//        riverTile.add(TileColours.RIVER_SIDE + "        " + TileColours.RIVER_SIDE);
//        riverTile.add(TileColours.RIVER_BAR);

        for(int i = 0;i < tiles.size();i++)
        {
            if(tiles.get(i).get(0).equals(TileColours.PRAIRIE_BAR) || tiles.get(i).get(3).equals(TileColours.PRAIRIE_BAR)) {
                temp  = tileSearch(tiles, TileColours.PRAIRIE_BAR, TileColours.PRAIRIE_SIDE, i, 0);
                if(prairieScore < temp)  prairieScore = temp;
            }
            if(tiles.get(i).get(0).equals(TileColours.MOUNTAIN_BAR) || tiles.get(i).get(3).equals(TileColours.MOUNTAIN_BAR)){
                temp = tileSearch(tiles, TileColours.MOUNTAIN_BAR,TileColours.MOUNTAIN_SIDE, i, 0);
                if(mountainScore < temp) mountainScore = temp;
            }
            if(tiles.get(i).get(0).equals(TileColours.WETLAND_BAR) || tiles.get(i).get(3).equals(TileColours.WETLAND_BAR)){
                temp = tileSearch(tiles, TileColours.WETLAND_BAR,TileColours.WETLAND_SIDE,  i, 0);
                if(wetlandScore < temp) wetlandScore = temp;
            }
            if(tiles.get(i).get(0).equals(TileColours.FOREST_BAR) || tiles.get(i).get(3).equals(TileColours.FOREST_BAR)){
                temp = tileSearch(tiles, TileColours.FOREST_BAR, TileColours.FOREST_SIDE, i, 0);
                if(forestScore < temp) forestScore = temp;
            }
            if(tiles.get(i).get(0).equals(TileColours.RIVER_BAR) || tiles.get(i).get(3).equals(TileColours.RIVER_BAR)){
                temp = tileSearch(tiles, TileColours.RIVER_BAR, TileColours.RIVER_SIDE, i, 0);
                if(riverScore < temp) riverScore = temp;
            }
        }


        return mountainScore + forestScore + riverScore + wetlandScore + prairieScore;
    }

    public static int tileSearch(ArrayList<ArrayList<String>> tiles, String bar, String side, int index, int score){
        if(index < 0 || index > 100){
            return score;
        }
        int right = 0;
        int left = 0;
        int up = 0;
        int down = 0;
        if(tiles.get(index++).get(0).equals(bar) || tiles.get(index++).get(3).equals(bar))  {
             right = tileSearch(tiles,bar,side, index + 1, score + 1);
        }
        else if(tiles.get(index--).get(0).equals(bar) || tiles.get(index--).get(3).equals(bar)){
            left = tileSearch(tiles,bar, side, index - 1, score + 1);
        }
        else if(tiles.get(index + 10).get(0).equals(bar)){
            up = tileSearch(tiles,bar, side, index + 10, score + 1);
        }
        else if(tiles.get(index - 10).get(3).equals(bar)){
           down = tileSearch(tiles,bar, side, index - 10, score + 1);
        }
        else{
            return score;
        }
        int first = Math.max(left,right);
        int last = Math.max(up,down);

            return Math.max(first,last);

    }
}
