import java.util.*;

public class GameIntro {
    static ArrayList<User> users = new ArrayList<>(4);
    static User currentPlayer;

    public void getUsers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number of players (2-4)");
        int numUsers = scanner.nextInt();

        while (numUsers < 2 || numUsers > 4) {
            System.out.println("Number of players must be between 2 and 4");
            numUsers = scanner.nextInt();
        }
        Scanner scanner1 = new Scanner(System.in);

        for (int i = 1; i < numUsers +1; i++) {
            System.out.println("Please enter the name of player " + i + ":");
            String username = scanner1.nextLine();
            User tempUser = new User(username);
            users.add(tempUser);
        }

        System.out.println("There are " + numUsers + " players\nThey will play in the following order:");

        Collections.shuffle(users);
        for (int i = 0; i < numUsers; i++) {
            System.out.println(users.get(i).name);
        }
        currentPlayer = users.get(0);
        for(int i = 0; i < numUsers; i++){
            users.get(i).getBoard().fillBoard();
        }
        currentPlayer.getBoard().showBoard(0);
        Tile.tileBag();
        Token.generateTokens();
        Tile.playableTiles();
        currentPlayer.getBoard().chooseTile();
        currentPlayer.getBoard().chooseToken();
    }

    public void playBot(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("CASCADIA VS BOT\nPlease enter your name");
        User tempUser = new User(scanner.nextLine());
        users.add(tempUser);
        User theBot = new User("BOT");
        users.add(theBot);
        System.out.println("You will play in the following order:\n");
        Collections.shuffle(users);
        for (int i = 0; i < 2; i++) {
            System.out.println(users.get(i).name);
        }
        currentPlayer = users.get(0);
        for(int i = 0; i < 2; i++){
            users.get(i).getBoard().fillBoard();
        }
        currentPlayer.getBoard().showBoard(0);
        Tile.tileBag();
        Token.generateTokens();
        Tile.playableTiles();
        currentPlayer.getBoard().chooseTile();
        currentPlayer.getBoard().chooseToken();


    }
    public static void NextTurn(){
        if (Tile.allTiles.size() < 1) {
            System.out.println("There are no more tiles in the bag. The game is over\n");
            return;
        }
        for(int i = 0;i < users.size();i++){
            if(currentPlayer == users.get(users.size() - 1)){
                currentPlayer = users.get(0);
                System.out.println("\t\t\t\t\t\t\t" + currentPlayer.getName() +", it is your turn!");
                break;
            }
            else if(currentPlayer == users.get(i) && currentPlayer != users.get(users.size() - 1)){
                currentPlayer = users.get(i + 1);
                System.out.println("\t\t\t\t\t\t\t"+ currentPlayer.getName() +", it is your turn!");
                break;
            }


        }

        currentPlayer.getBoard().showBoard(0);
        Tile.playableTiles();
        currentPlayer.getBoard().chooseTile();
        currentPlayer.getBoard().chooseToken();
    }

    public void endGame() {
        int maxScore=0;
        int winner=0;
        int winner1=0;
        int score=0;
        for (int i = 0; i < users.size(); i++) {
            currentPlayer = users.get(i);
            System.out.println(currentPlayer.getName() + "'s score for this game:");
            int tempScore = currentPlayer.getBoard().PlayerScoring();
            if (tempScore > maxScore) {
                maxScore = tempScore;
                winner = i;
            }
            else if (tempScore == maxScore) {
                winner1 = i;
                score = tempScore;
            }
        }

        if (maxScore == score) {
            System.out.println("The game is a draw between " + users.get(winner).getName() + " and " + users.get(winner1).getName());
            System.out.println("Both players had a high score of " + maxScore + " and " + score);
        }
        else {
            System.out.println("The winner of the game is " + users.get(winner).getName() + "\nCONGRATULATIONS!");
            System.out.println("You had the highest score of " + maxScore);
        }
    }

    public static void main(String[] args) {
        Tile.habitatArray();
        Tile.wildlifeArray();
        GameIntro g = new GameIntro();
        g.getUsers();
        g.endGame();

    }


}
