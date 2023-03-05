import java.util.Objects;
import java.util.Scanner;

public class Culling {

    public static void cullRequired() {
        String temp1 = Token.presentTokens.get(0);
        String temp2 = Token.presentTokens.get(1);
        int x = 1;
        int y = 1;
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
            Scanner scanner = new Scanner(System.in);
            String userChoice = scanner.nextLine();
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
                    }
                }
                Token.playableTokens();
                System.out.println("Tokens have been replaced");
            }
            else {
                System.out.println("No cull required");
            }
        }
        else if (x == 4) {
            System.out.println("All four tokens are the same and must be replaced");
            while (i < Token.presentTokens.size()) {
                Token.wildlifeTokens.add(Token.presentTokens.get(i));
                Token.presentTokens.remove(i--);
            }
            Token.playableTokens();
        }
    }

}
