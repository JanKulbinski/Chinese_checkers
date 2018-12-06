package gameServer;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class Player extends Thread
{
	Socket client;
	PrintWriter out;
	BufferedReader in;
	Game game;
	private int playerId;
	private Color playerColor;
	
	Player(Socket socket, Game game, int playerId)
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
		out.println("TURN");
		out.println(Integer.toString(player));
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
					
					for(int i=0;i<game.getPlayers().length;i++) {
						game.getPlayers()[i].out.println("TURN");
						game.getPlayers()[i].out.println((playerId+1)%game.getNumberOfPlayers());
					}
				}
				
				else {	
				
					if ( game.checkMoveProperiety(line,playerColor) ) {
							for(int i=0;i<game.getPlayers().length;i++) {
								game.getPlayers()[i].out.println(line);
							}
						}
					else {
							this.out.println("INCORRECT !");
						}
				}
			}
		} catch (IOException e) {
			System.out.append("Nie mozna przeczytac lini");
			System.exit(0);
		}
	}
}
