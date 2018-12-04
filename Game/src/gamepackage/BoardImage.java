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
	
	public BoardImage() {
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
		super.paintComponent(g);
		ArrayList<ColorCircle> circles = board.getCircles();
		for ( ColorCircle c : circles ) {
			g.setColor(c.getColor());
			g.fillOval((int)c.x,(int)c.y, (int)ColorCircle.width, (int)ColorCircle.height);
		}
	}
	
}
