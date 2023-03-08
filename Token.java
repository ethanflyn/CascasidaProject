import java.util.ArrayList;
import java.util.Collections;

public class Token {

    public static ArrayList<String> wildlifeTokens = new ArrayList<>();
    public static ArrayList<String> presentTokens = new ArrayList<>();
    public static void generateTokens() {
        String Fox = "F";
        String Bear = "B";
        String Hawk = "H";
        String Elk = "E";
        String Salmon = "S";

        for (int i = 0; i < 20; i++) {
            wildlifeTokens.add(Fox);
            wildlifeTokens.add(Bear);
            wildlifeTokens.add(Hawk);
            wildlifeTokens.add(Elk);
            wildlifeTokens.add(Salmon);
        }
    }

    public static void playableTokens() {
        Collections.shuffle(wildlifeTokens);
        int m = wildlifeTokens.size()-1;
        for (int i = 0; i < 4; i++) {
            presentTokens.add(wildlifeTokens.get(m));
            wildlifeTokens.remove(m--);
        }
        System.out.print("\t\t\t\t\t\t");
        for (int i = 0; i < 4; i++) {
            System.out.print("     " + Token.presentTokens.get(i) + "\t\t\t");
        }
        System.out.println();
        Culling.cullRequired();

    }
}
