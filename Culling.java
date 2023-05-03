import java.util.Objects;
import java.util.Scanner;

public class Culling {

    public static void cullRequired() {
        String temp1 = Token.presentTokens.get(0);
        String temp2 = Token.presentTokens.get(1);
        int x = 1;
        int y = 1;
        // check if there are 3 or 4 duplicate tokens
        for (int i = 0; i < 4; i++) {
            if (Objects.equals(Token.presentTokens.get(i), temp1) && i != 0) {
                x++;
            }
            if (Objects.equals(Token.presentTokens.get(i), temp2) && i!= 1) {
                y++;
            }
        }
        int i=0;
        if (x == 3 || y == 3) {
            System.out.println("Three of the tokens are the same, if you wish to cull enter 'yes'. If not enter 'no'");
            if (GameIntro.currentPlayer.getName().equals("BOT")) {
                userChoice = Bot.culling();
                System.out.println(userChoice);
            }
            else{
                Scanner scanner = new Scanner(System.in);
                userChoice = scanner.nextLine();
            }
            if (userChoice.equalsIgnoreCase("yes")) {
                if (x == 3) {
                    while (i < Token.presentTokens.size()) {
                        if (Objects.equals(Token.presentTokens.get(i), temp1)) {
                            Token.wildlifeTokens.add(Token.presentTokens.get(i));
                            Token.presentTokens.remove(i--);
                        }
                        i++;
                    }
                } else {
                    while(i < Token.presentTokens.size()) {
                        if (Objects.equals(Token.presentTokens.get(i), temp2)) {
                            Token.wildlifeTokens.add(Token.presentTokens.get(i));
                            Token.presentTokens.remove(i--);
                        }
                        i++;
                    }
                }
                for (i = 0; i < 4; i++) {
                    System.out.print("\t\t\t\t\t\t");
                    for (int j = 0; j < 4; j++) {
                        System.out.print(Tile.presentTiles.get(j).get(i));
                        System.out.print("			");
                    }
                    System.out.println();
                }
                System.out.println();
                Token.playableTokens();
                System.out.println("Tokens have been replaced");
            } else if (userChoice.equalsIgnoreCase("no")) {
                System.out.println("No cull required");
            } else {
                System.out.println("Please enter 'yes' or 'no' ");
                cullRequired();
            }
        }
        else if (x == 4) {
            System.out.println("All four tokens are the same and must be replaced");
            while (i < Token.presentTokens.size()) {
                Token.wildlifeTokens.add(Token.presentTokens.get(i));
                Token.presentTokens.remove(i);
            }
            for (i = 0; i < 4; i++) {
                System.out.print("\t\t\t\t\t\t");
                for (int j = 0; j < 4; j++) {
                    System.out.print(Tile.presentTiles.get(j).get(i));
                    System.out.print("			");
                }
                System.out.println();
            }
            System.out.println();
            Token.playableTokens();
        }
    }

}
