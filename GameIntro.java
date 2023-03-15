import java.util.*;

public class GameIntro {

    private int numUsers;
    private String starterTile;
    private String habitatTile;
    private String wildlifeToken;

    ArrayList<Tile> starterTiles = new ArrayList<>();
    static ArrayList<User> users = new ArrayList<>(4);
    static User currentPlayer;
    private static final Random rng = new Random () ;


    public void getUsers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number of players (2-4)");
        numUsers = scanner.nextInt();

        while (numUsers < 2 || numUsers > 4) {
            System.out.println("Number of players must be between 2 and 4");
            numUsers = scanner.nextInt();
        }
        Scanner scanner1 = new Scanner(System.in);

        for (int i = 1; i < numUsers+1; i++) {
            System.out.println("Please enter the name of player " + i + ":");
            Tile [] tempTiles = new Tile[30];
//            tempTiles[i] = starterTiles.get(rng.nextInt(5));
            User tempUser = new User(scanner1.nextLine(), tempTiles);
            users.add(tempUser);
        }

        System.out.println("There are " + numUsers + " players\nThey will play in the following order:");

        Collections.shuffle(users);
        for (int i = 0; i < numUsers; i++) {
            System.out.println(users.get(i).name);
        }
        currentPlayer = users.get(0);
        for(int i = 0;i <numUsers;i++){
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
            System.out.println("There are no more tiles in the bag. The game is over");
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




    public static void main(String[] args) {
        Tile.habitatArray();
        Tile.wildlifeArray();
        GameIntro g = new GameIntro();
        g.getUsers();
    }
}
