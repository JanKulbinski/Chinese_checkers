package gameServer;

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
	Player(Socket socket, Game game)
	{
		this.game = game;
		client=socket;
		try {
			out = new PrintWriter(client.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		} catch (IOException e) {
			System.out.println("Nie mo¿na naziwazac komunikacji");
			System.exit(0);
		}
	}
	public void run() {
		String line;
		try {
			while(true) {
				line=in.readLine();
				if(line.equals("CONNECT")) {
					out.println(game.getNumberOfPlayers());
				} else {
					for(int i=0;i<game.getPlayers().length;i++) {
						game.getPlayers()[i].out.println(line);
					}
				}
			}
		} catch (IOException e) {
			System.out.append("Nie mo¿na przeczytac lini");
			System.exit(0);
		}
	}
}
