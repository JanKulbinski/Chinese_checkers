package gamepackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import gameServer.Connector;

public class BoardImage extends JPanel {

	private Board board;
	private Connector connector;
	
	BoardImage() {
		super();
		setPreferredSize(new Dimension(400,400));
		setBackground(new Color(255,204,153));
	}
	
	public void setBoard(Board board) {
		this.board = board;
		addMouseListener(new MoveListener(this,board.getCircles()));
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void setConnector(Connector connector) {
		this.connector = connector;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		ArrayList<ColorCircle> circles = board.getCircles();
		for ( ColorCircle c : circles ) {
			g.setColor(c.getColor());
			g.fillOval((int)c.x,(int)c.y, (int)ColorCircle.width, (int)ColorCircle.height);
		}
	}
	
	public void move(int c1, int c2, Color color) {
		board.getCircles().get(c1).setColor(Color.WHITE);
		board.getCircles().get(c2).setColor(color);
		this.repaint();
		connector.out.println(c1+" "+c2+" "+color.getRed()+" "+color.getGreen()+" "+color.getBlue());
	}
	
	public void opponentMove(String move) {
		String[] words = move.split(" ");
		Color color = new Color(Integer.parseInt(words[2]),Integer.parseInt(words[3]),Integer.parseInt(words[4]));
		board.getCircles().get(Integer.parseInt(words[0])).setColor(Color.WHITE);
		board.getCircles().get(Integer.parseInt(words[1])).setColor(color);
		this.repaint();
	}
}
