package gamepackage;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class Client {
	
	JFrame clientWindow;
	JPanel buttons;
	JTextArea communicator;
	BoardImage boardImage;
	JCheckBox p2,p3,p4,p6;
	ButtonGroup players;
	
	Client() {
		
		clientWindow = new JFrame("Chinese checkers");
		buttons = new JPanel();
		communicator = new JTextArea();
		boardImage  = new BoardImage();
		
		communicator.setEditable(false);	// bedzie wyswietlal komunikaty o zlym ruchu itp.
		communicator.setBackground(new Color(255,217,179));
		
		clientWindow.setLayout(new BorderLayout());
		clientWindow.setSize(800, 600);
		clientWindow.getContentPane().setBackground(new Color(255,153,51));		
		clientWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		clientWindow.add(buttons,BorderLayout.EAST);
		clientWindow.add(boardImage,BorderLayout.CENTER);
		
		buttons.setLayout(new BoxLayout(buttons,BoxLayout.Y_AXIS));	// beda tu rozne przyciski jak connect itp
		buttons.setBounds(600, 0, 150, 665);
		buttons.add(new JButton("Join game"));
		buttons.add(new JButton("Host game"));
		buttons.setBackground(new Color(255,217,179));
		
		p2 = new JCheckBox("2 Players");
	    p2.setSelected(true);
	    p3 = new JCheckBox("3 Players");
	    p3.setSelected(false);
	    p4 = new JCheckBox("4 Players");
	    p4.setSelected(false);
	    p6 = new JCheckBox("6 Players");
	    p6.setSelected(false);
	    players = new ButtonGroup();
	    players.add(p2);
	    players.add(p3);
	    players.add(p4);
	    players.add(p6);
	    buttons.add(p2);
	    buttons.add(p3);
	    buttons.add(p4);
	    buttons.add(p6);
	    buttons.add(communicator);
	    clientWindow.setVisible(true);
	}
	
	public static void main(String[] args) {

		Client client = new Client(); 

				Board board = new Board(2); 
				//Board board = new Board(3); //to tez dziala juz ;)
				client.boardImage.setBoard(board);
			}

		}

	


