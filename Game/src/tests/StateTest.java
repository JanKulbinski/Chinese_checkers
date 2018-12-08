package tests;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;

import org.junit.Test;

import gamepackage.Board;
import gamepackage.ColorCircle;
import states.State;
import states.StateController;

public class StateTest {
	
	@Test
	public void testForTwoPlayers() {
		StateController controller = new StateController();
		controller.setState(2);
		assertArrayEquals(controller.getState().setDestinationForPlayer(0),State.player5);
		assertArrayEquals(controller.getState().setDestinationForPlayer(1),State.player);
		Board board = new Board(2,null);
		controller.getState().setAreasForPlayers(board);
	}
	@Test
	public void testForThreePlayers() {
		Board board = new Board(3,null);
		StateController controller = new StateController();
		controller.setState(3);
		assertArrayEquals(controller.getState().setDestinationForPlayer(0),State.player5);
		assertArrayEquals(controller.getState().setDestinationForPlayer(1),State.player1);
		assertArrayEquals(controller.getState().setDestinationForPlayer(2),State.player2);
		controller.getState().setAreasForPlayers(board);
	}
	@Test
	public void testForFourPlayers() {
		Board board = new Board(4,null);
		StateController controller = new StateController();
		controller.setState(4);
		assertArrayEquals(controller.getState().setDestinationForPlayer(0),State.player4);
		assertArrayEquals(controller.getState().setDestinationForPlayer(1),State.player3);
		assertArrayEquals(controller.getState().setDestinationForPlayer(2),State.player1);
		assertArrayEquals(controller.getState().setDestinationForPlayer(3),State.player2);
		controller.getState().setAreasForPlayers(board);
	}
	@Test
	public void testForSixPlayers() {
		Board board = new Board(6,null);
		StateController controller = new StateController();
		controller.setState(6);
		assertArrayEquals(controller.getState().setDestinationForPlayer(0),State.player5);
		assertArrayEquals(controller.getState().setDestinationForPlayer(1),State.player3);
		assertArrayEquals(controller.getState().setDestinationForPlayer(2),State.player1);
		assertArrayEquals(controller.getState().setDestinationForPlayer(3),State.player);
		assertArrayEquals(controller.getState().setDestinationForPlayer(4),State.player2);
		assertArrayEquals(controller.getState().setDestinationForPlayer(5),State.player4);
		controller.getState().setAreasForPlayers(board);
	}
}
