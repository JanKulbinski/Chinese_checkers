package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.awt.Color;
import org.junit.Test;
import gameServer.Game;
import states.State;

public class GameTest {


	@Test
	public void singeleMoveTest() {
		Game game = new Game(2);
		String line = "5 9 255 0 0 0";
		assertTrue(game.checkMoveProperiety(line, Color.RED, State.player5));
	}
	@Test
	public void tooFarMove() {
		Game game = new Game(2);
		String line = "5 40 255 0 0 0";
		assertFalse(game.checkMoveProperiety(line, Color.RED, State.player5));
	}
	@Test
	public void jumpTest() {
		Game game = new Game(2);
		String line = "5 18 255 0 0 0";
		assertTrue(game.checkMoveProperiety(line, Color.RED, State.player5));
		line = "18 19 255 0 0 0";
		assertFalse(game.checkMoveProperiety(line, Color.RED, State.player5));
	}
	
}
