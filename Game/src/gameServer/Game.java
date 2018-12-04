package gameServer;

import java.awt.Color;

import gamepackage.Board;

class Game
{	
	private Player[] players;
	private int numberOfPlayers;
	private Board board;
	
	public Game(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
		board = new Board(numberOfPlayers,null);
	}
	public void setPlayers(Player[] players) {
		this.players = players;
	}
	public Player[] getPlayers() {
		return players;
	}
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	
	public boolean checkMoveProperiety(String line, Color playerColor) {
		String[] words = line.split(" ");
		Color color = new Color(Integer.parseInt(words[2]),Integer.parseInt(words[3]),Integer.parseInt(words[4]));
		if(playerColor.equals(color)) {
			board.getCircles().get(Integer.parseInt(words[0])).setColor(Color.WHITE);
			board.getCircles().get(Integer.parseInt(words[1])).setColor(color);
			return true;
		}
		else {
			return false;
		}
	}

	
}
