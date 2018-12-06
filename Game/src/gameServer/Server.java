package gameServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import gamepackage.Client;

public class Server 
{
	public static void main(String[] args) throws InterruptedException
	{
		
		int numberOfPlayers = 0;
		ServerSocket server;
		Scanner in = new Scanner(System.in);
		System.out.println("Podaj liczbe graczy:");
		while(true)
		{
			numberOfPlayers = 6;
			break;
			//zakomentowane tylko do testowania
			/*String choice = in.nextLine();
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
				System.out.println("Bledny wybor");
			}	*/
		}		
		Game game = new Game(numberOfPlayers);
		Player[] players = new Player[numberOfPlayers];
		try {
			server=new ServerSocket(6666);
			System.out.println("Serwer utworzony");
			for(int i=0;i<numberOfPlayers;i++) {
				players[i] = new Player(server.accept(),game,i);
				players[i].start();
			}			
		} catch (IOException e) {
			System.out.println("Nie mozna utworzyc serwera");
			System.exit(0);
		}
		game.setPlayers(players);
		TimeUnit.SECONDS.sleep(1);
		game.whoBeginsGame();
		in.close();
	}
}
