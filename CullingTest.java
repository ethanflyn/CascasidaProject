package Cascadia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Objects;

import org.junit.jupiter.api.Test;

class CullingTest {

	@Test
	void test() {
		Tile.habitatArray();
		
		Tile.wildlifeArray();
		int numUsers = 2;
		Tile.tileBag(numUsers);
		
		Token.generateTokens();
		
		
		
		Tile.playableTiles();
		
		
		ArrayList<String> testArray = Token.presentTokens;
		
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
		
		Culling.cullRequired();
		
		
		if (x > 2 || y > 2)
			assertNotEquals(testArray, Token.presentTokens);
		else {
			assertEquals(testArray, Token.presentTokens);
		}
		
	}

}
