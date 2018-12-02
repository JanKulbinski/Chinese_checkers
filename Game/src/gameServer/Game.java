package gameServer;

class Game
{	
	private Player[] players;
	private int numberOfPlayers;
	
	public Game(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
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
	
	// Ca³a logika gry
	
}
