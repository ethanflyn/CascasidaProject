import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;

public class GameIntro {

    private int numUsers;
    private ArrayList<Player> userNames = new ArrayList<>();
    private ArrayList<Player> orderedPlayers = new ArrayList<>();
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
            String use = scanner1.nextLine();
            Player a = new Player(20, use);
            userNames.add(a);
        }

        System.out.println("There are " + numUsers + " players\nThey will play in the following order:");

        Collections.shuffle(userNames);
        for (int i = 0; i < numUsers; i++) {
            System.out.println(userNames.get(i));
            orderedPlayers.add(userNames.get(i));
        }
        Player currentPlayer = orderedPlayers.get(0);
        theGame(orderedPlayers, currentPlayer);


    }

    public  void NextTurn(ArrayList<Player> order, Player currentPlayer){
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
    public void theGame(ArrayList<Player> order, Player currentPlayer){
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
