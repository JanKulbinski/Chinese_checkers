package gameServer;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import states.StateController;

public class Player extends Thread
{
	private Socket client;
	private PrintWriter out;
	private BufferedReader in;
	private Game game;
	private int playerId;
	private Color playerColor;
	private int destination[] = new int[10];
	private boolean hasWon;
	
	public Player(Socket socket, Game game, int playerId, int numberOfPlayers)
	{
		this.game = game;
		client=socket;
		try {
			out = new PrintWriter(client.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		} catch (IOException e) {
			System.out.println("Nie mozna nawiazac komunikacji");
			System.exit(0);
		}
		StateController s = new StateController();
		s.setState(numberOfPlayers);
		destination = s.getState().setDestinationForPlayer(playerId);
		if(playerId == 0) playerColor = Color.RED;
		else if(playerId == 1) playerColor = Color.GREEN;
		else if(playerId == 2) playerColor = Color.BLUE;
		else if(playerId == 3) playerColor = Color.CYAN;
		else if(playerId == 4) playerColor = Color.PINK;
		else if(playerId == 5) playerColor = Color.YELLOW;
		
		this.playerId = playerId;
		playerId++;
	}
	
	public void turn(int player) {
		for(int i=0;i<game.getNumberOfPlayers();i++) {
			game.getPlayers()[i].out.println("TURN");
			game.getPlayers()[i].out.println(Integer.toString(player));
		}
	}
	public boolean hasWon() {
		return hasWon;
	}
	
	public void run() {
		String line;
		try {
			while(true) {
				
				line=in.readLine();
				if(line.equals("CONNECT")) {
					
					out.println(game.getNumberOfPlayers());
					out.println(Integer.toString(playerColor.getRGB()));
					out.println(Integer.toString(playerId));
					
				} else if  ( line.equals("END TURN") ) {
					game.reset();
					int nextPlayer = (playerId+1)%game.getNumberOfPlayers();
					while(game.getPlayers()[nextPlayer].hasWon) {
						nextPlayer = (nextPlayer+1)%game.getNumberOfPlayers();
						if(nextPlayer == playerId) {
							for(int i=0;i<game.getPlayers().length;i++) {
								nextPlayer = -1;
							}
							break;
						}
					}
					for(int i=0;i<game.getPlayers().length;i++) {
						game.getPlayers()[i].out.println("TURN");
						game.getPlayers()[i].out.println(nextPlayer);
					}
				}
				
				else {	
					if ( game.checkMoveProperiety(line,playerColor,destination) ) {
						if (!hasWon && game.checkWin(destination,playerColor)) {
							hasWon = true;
						}
						for(int i=0;i<game.getPlayers().length;i++) {
							game.getPlayers()[i].out.println(line);
							if(hasWon) {
								game.getPlayers()[i].out.println(playerId);
							} else {
								game.getPlayers()[i].out.println("");
							}
						}
							
					}
					else {
							this.out.println("INCORRECT");
						}
				}
			}
		} catch (IOException e) {
			System.out.append("Nie mozna przeczytac lini");
			System.exit(0);
		}
	}
}
