package gamepackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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
	JPanel buttons,filler,filler2;
	JTextArea communicator;
	BoardImage boardImage;
	JCheckBox p2,p3,p4,p6;
	ButtonGroup players;
	JButton endTurn;
	
	public Client() {
		
		clientWindow = new JFrame("Chinese checkers");
		buttons = new JPanel();
		filler = new JPanel();
		filler2 = new JPanel();
		communicator = new JTextArea();
		boardImage  = new BoardImage();	
		
		endTurn = new JButton("END YOUR TURN");
		communicator.setFont(new Font("Segoe Script",Font.BOLD,15));
		communicator.setText("WELCOME!");
		communicator.setEditable(false);
		
		clientWindow.setLayout(new BorderLayout());
		clientWindow.setSize(800, 600);
		clientWindow.getContentPane().setBackground(new Color(255,153,51));		
		clientWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		buttons.setLayout(new GridLayout(0,1));
		buttons.setPreferredSize(new Dimension(150, 600));
		buttons.add(endTurn);
		buttons.add(communicator);
		buttons.add(filler);
		buttons.add(filler2);
	    
	    
	    clientWindow.add(buttons,BorderLayout.EAST);
	    clientWindow.add(boardImage,BorderLayout.CENTER);
	    clientWindow.setVisible(true);
	}
	
	private void setMyColor(Color color) {
		communicator.setBackground(color);
		filler.setBackground(color);
		filler2.setBackground(color);
	}
	
	public static void main(String[] args)  {

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
