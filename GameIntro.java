import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;

public class GameIntro {

    private int numUsers;
    private ArrayList<String> userNames = new ArrayList<>();
    private ArrayList<String> orderedPlayers = new ArrayList<>();
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
            userNames.add(scanner1.nextLine());
        }

        System.out.println("There are " + numUsers + " players\nThey will play in the following order:");

        Collections.shuffle(userNames);
        for (int i = 0; i < numUsers; i++) {
            System.out.println(userNames.get(i));
        }

    }

    public static void main(String[] args) {
        GameIntro g = new GameIntro();
        g.getUsers();
    }
}
