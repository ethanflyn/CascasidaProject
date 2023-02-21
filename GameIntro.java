import java.util.*;

public class GameIntro {

    private int numUsers;
    private String starterTile;
    private String habitatTile;
    private String wildlifeToken;

    ArrayList<Tile> starterTiles = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>(4);
    Tile [] fourTiles = new Tile[4];
    String [] fourTokens = new String[4];
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
            tempTiles[i] = starterTiles.get(rng.nextInt(5));
            User tempUser = new User(scanner1.nextLine(), tempTiles);
            users.add(tempUser);
        }

        System.out.println("There are " + numUsers + " players\nThey will play in the following order:");

        Collections.shuffle(users);
        for (int i = 0; i < numUsers; i++) {
            System.out.println(users.get(i).name);
        }

    }

    public void getStarterTile(Tile [] starterTiles) {
        for (int i = 0; i < users.size(); i++) {
            users.get(i).tiles[i] = starterTiles[rng.nextInt(5)+1];
        }
    }

    public void getTilesTokens() {
        for (int i = 0; i < fourTiles.length; i++) {
            fourTiles[i] = Tile.tiles.get(rng.nextInt());
            fourTokens[i] = Token.wildlifeTokens.get(rng.nextInt());
        }
        if (cullRequired()) {
            System.out.println("All four tokens are the same. Therefore all tokens must be replaced");
        }
    }

    public boolean cullRequired() {
        String temp = fourTokens[0];
        int x = 1;
        for (int i = 1; i < 4; i++) {
            if (Objects.equals(fourTokens[i], temp)) {
                x++;
            }
        }
        if (x == 3) {
            System.out.println("Three of the tokens are the same, if you wish to cull enter 'yes'. If not enter 'no'");
            Scanner scanner = new Scanner(System.in);
            String userChoice = scanner.nextLine();
            if (userChoice.equalsIgnoreCase("yes"))
                return true;
            else if (userChoice.equalsIgnoreCase("no")) {
                return false;
            }
            else {
                throw new IllegalArgumentException("Incorrect entry");
            }
        }
        else return x == 4;
    }

    public void useCull() {
        getTilesTokens();
        if (cullRequired()) {
            getTilesTokens();
            System.out.println("The four tokens have been replaced");
        }
    }
        public  void NextTurn(ArrayList<User> order, User currentPlayer){
        for(int i = 0;i < order.size();i++){
            if(currentPlayer == order.get(order.size() - 1)){
                currentPlayer = order.get(0);
                break;
            }
            else if(currentPlayer == order.get(i) && currentPlayer != order.get(order.size() - 1)){
                currentPlayer = order.get(i + 1);
                break;
            }


        }

        theGame(order,currentPlayer);
    }
    public void theGame(ArrayList<User> order, User currentPlayer){
        System.out.println(currentPlayer +", it is your turn.");
        Scanner input = new Scanner(System.in);
        System.out.println("Input anything");
        String irrelvant = input.nextLine();
        System.out.println(currentPlayer + ", your turn is over.");
        NextTurn(order, currentPlayer);
    }



    public static void main(String[] args) {
        GameIntro g = new GameIntro();
        g.getUsers();
    }
}
