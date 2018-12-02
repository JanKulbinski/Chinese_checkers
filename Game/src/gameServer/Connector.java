package gameServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import gamepackage.BoardImage;

public class Connector 
{
	public BufferedReader in;
	public PrintWriter out;
	public Socket s;
	private BoardImage boardImage;
	public Connector() {
		try {
			s=new Socket("localhost",6666);
			out=new PrintWriter(s.getOutputStream(),true);
			in=new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setBoardImage(BoardImage boardImage) {
		this.boardImage = boardImage;
	}
	public void play() {
		String line;
		try {
			while(true) {
				line=in.readLine();
				boardImage.opponentMove(line);
			}
			
		} catch (IOException e) {
			System.out.println("Nie mo¿na przeczytac lini");
			System.exit(0);
		}
	}
	
}
