package gameServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

import gamepackage.Board;
import gamepackage.BoardImage;

public class Connector 
{
	JTextArea communicator;
	public BufferedReader in;
	public PrintWriter out;
	public Socket s;
	private Board board;
	
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
	public void setBoardImage(Board board, JTextArea communicator) {
		this.board = board;
		this.communicator = communicator;
	}
	public void play() {
		String line;
		try {
			while(true) {
				line=in.readLine();
				
				if(line.equals("INCORRECT !")) {
					communicator.setText(line);
				
				} else if(line.equals("TURN") ) {
						int id = Integer.parseInt(in.readLine());
						
						if(id == board.myId()) {
							communicator.setText("It's your turn");
							board.myTurn();
						}
						
				} else {
					board.opponentMove(line);
					communicator.setText("");
				}
			}
			
		} catch (IOException e) {
			System.out.println("Nie mozna przeczytac lini");
			System.exit(0);
		}
	}
	
	public void sendMessageToServer(String text) {
		out.println(text);
	}
	public void setTextOnCommunicator(String text) {
		communicator.setText(text);
	}
	
}
