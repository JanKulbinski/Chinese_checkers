package gameServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class Server 
{
	public static void main(String[] args)
	{
		int numberOfPlayers = 0;
		ServerSocket server;
		Scanner in = new Scanner(System.in);
		System.out.println("Podaj liczbê graczy:");
		while(true)
		{
			String choice = in.nextLine();
			if(choice.equals("2")) {
				numberOfPlayers = 2;
				break;
			} else if(choice.equals("3")) {
				numberOfPlayers = 3;
				break;
			} else if(choice.equals("4")) {
				numberOfPlayers = 4;
				break;
			} else if(choice.equals("6")) {
				numberOfPlayers = 6;
				break;
			} else {
				System.out.println("B³êdny wybór");
			}	
		}		
		Game g=new Game(numberOfPlayers);
		Player[] players = new Player[numberOfPlayers];
		try {
			server=new ServerSocket(6666);
			System.out.println("Serwer utworzony");
			for(int i=0;i<numberOfPlayers;i++) {
				players[i] = new Player(server.accept(),g);
				players[i].start();
			}
		} catch (IOException e) {
			System.out.println("Nie mo¿na utworzyc serwera");
			System.exit(0);
		}
		g.setPlayers(players);
		in.close();
	}
}
