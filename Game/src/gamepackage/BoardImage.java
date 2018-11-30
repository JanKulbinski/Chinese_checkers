package gamepackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class BoardImage extends JPanel {

	private Board board;
	
	BoardImage() {
		super();
		setPreferredSize(new Dimension(400,400));
		setBackground(new Color(255,204,153));
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		ArrayList<ColorCircle> circles = board.getCircles();
		for ( ColorCircle c : circles ) {
			g.setColor(c.getColor());
			g.fillOval((int)c.x,(int)c.y, (int)ColorCircle.width, (int)ColorCircle.height);
		}
	}
	

	
}
