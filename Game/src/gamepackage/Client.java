package gamepackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import gameServer.Connector;


public class Client {
	
	JFrame clientWindow;
	JPanel buttons;
	JTextArea communicator;
	BoardImage boardImage;
	JCheckBox p2,p3,p4,p6;
	ButtonGroup players;

	
	public Client() {
		
		clientWindow = new JFrame("Chinese checkers");
		buttons = new JPanel();
		communicator = new JTextArea();
		boardImage  = new BoardImage();
		
		communicator.setEditable(false);	// bedzie wyswietlal komunikaty o zlym ruchu itp.
		communicator.setFont(new Font("Segoe Script",Font.BOLD,15));
		clientWindow.setLayout(new BorderLayout());
		clientWindow.setSize(800, 600);
		clientWindow.getContentPane().setBackground(new Color(255,153,51));		
		clientWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		clientWindow.add(buttons,BorderLayout.EAST);
		clientWindow.add(boardImage,BorderLayout.CENTER);
		
		buttons.setLayout(new BoxLayout(buttons,BoxLayout.Y_AXIS));	// beda tu rozne przyciski jak connect itp
		buttons.setBounds(600, 0, 150, 665);
		buttons.add(new JButton("END YOUR TURN"));

	    buttons.add(communicator);
	    clientWindow.setVisible(true);
	}
	
	private void setMyColor(Color color) {
		communicator.setBackground(color);
	}
	public static void main(String[] args) throws InterruptedException {

				int numberOfPlayers = 0;
			    Color color = Color.WHITE;
				Connector connector = new Connector();
				
				connector.out.println("CONNECT");
				
				try {
					numberOfPlayers = Integer.parseInt(connector.in.readLine());
					color = new Color(Integer.parseInt(connector.in.readLine()));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				Client client = new Client(); 
				Board board = new Board(numberOfPlayers,client.boardImage);
				
				board.setConnector(connector);
				connector.setBoardImage(board,client.communicator);
				client.boardImage.setBoard(board);
				client.setMyColor(color);
				client.boardImage.setConnector(connector);			
				connector.play();

		}
		
	}
