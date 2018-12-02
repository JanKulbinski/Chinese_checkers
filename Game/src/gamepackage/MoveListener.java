package gamepackage;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import gameServer.Connector;

public class MoveListener implements MouseListener {

	private ArrayList<ColorCircle> circles;
	private BoardImage boardImage;
	private int clickedCircle;
	
	public MoveListener(BoardImage boardImage, ArrayList<ColorCircle> circles) {
		this.circles = circles;
		this.boardImage = boardImage;
		this.clickedCircle = -1;
	}

	public void mouseClicked(MouseEvent arg0) {
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
		for(int i=0;i<circles.size();i++) {
			if(circles.get(i).contains(arg0.getX(), arg0.getY())) {
				clickedCircle = i;
				break;
			}
		}
	}

	public void mouseReleased(MouseEvent arg0) {
		for(int i=0;i<circles.size();i++) {
			if(circles.get(i).contains(arg0.getX(), arg0.getY())) {
				
				if(clickedCircle != -1 && clickedCircle != i && circles.get(i).getColor() == Color.WHITE) {
					boardImage.move(clickedCircle, i, circles.get(clickedCircle).getColor());
					break;
				}	
			}
		}
		clickedCircle = -1;
	}
}
