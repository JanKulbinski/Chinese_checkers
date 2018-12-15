package tests;

import static org.junit.Assert.*;

import org.junit.Ignore;
import gameServer.Bot;
import gameServer.Game;
import gameServer.Player;
import gamepackage.Board;

public class BotTests {

	@Ignore
	public void test() throws InterruptedException {
		Game game = new Game(2);
		Board initialBoard = game.getBoard();
		Player bot = new Bot(game,1,2);
		bot.sendTurn(1);
		bot.start();
		Board changedBoard = game.getBoard();
		assertNotEquals(initialBoard.getCircles().get(111).getColor(),changedBoard.getCircles().get(111).getColor());// eh czemu
	}

}
