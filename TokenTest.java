package Cascadia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class TokenTest {

	@Test
	void testGenerateTokens() {
		Token.generateTokens();
		assertEquals(Token.wildlifeTokens.size(), 100);
		assertTrue(Token.wildlifeTokens.contains("F"));
		assertTrue(Token.wildlifeTokens.contains("E"));
		assertTrue(Token.wildlifeTokens.contains("B"));
		assertTrue(Token.wildlifeTokens.contains("S"));
		assertTrue(Token.wildlifeTokens.contains("H"));
		assertFalse(Token.wildlifeTokens.contains("A"));
	} 
	
	@Test
	void testPlayableTokens() {
		Token.generateTokens();
		Token.playableTokens();
		assertEquals(Token.presentTokens.size(), 4);
		
	}
	
	@Test
	void testReplaceToken() {
		Token.generateTokens();
		Token.playableTokens();
		
		ArrayList<String> testArray = Token.presentTokens;
		
		Token.replaceToken(0);
		
		assertNotEquals(testArray, Token.presentTokens);
		assertEquals(Token.presentTokens.size(), 4);
	}

}
