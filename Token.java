import java.util.ArrayList;
import java.util.Collections;

public class Token {

    public static ArrayList<String> wildlifeTokens = new ArrayList<>();
    public static void generateTokens() {
        String F = "Fox";
        String B = "Bear";
        String H = "Hawk";
        String E = "Elk";
        String S = "Salmon";

        for (int i = 0; i < 20; i++) {
            wildlifeTokens.add(F);
            wildlifeTokens.add(B);
            wildlifeTokens.add(H);
            wildlifeTokens.add(E);
            wildlifeTokens.add(S);
        }
        Collections.shuffle(wildlifeTokens);
    }
}
