package gameServer;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import gamepackage.Board;
import gamepackage.ColorCircle;

class Game
{	
	private Player[] players;
	private int numberOfPlayers;
	private Board board;
	private boolean endOfMoves;
	private ColorCircle jumpingCircle;
	
	public Game(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
		board = new Board(numberOfPlayers,null);
	}
	public void setPlayers(Player[] players) {
		this.players = players;
	}
	public Player[] getPlayers() {
		return players;
	}
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	
	public void whoBeginsGame() {
		Random rand = new Random();
		int n = rand.nextInt(numberOfPlayers);
		players[n].turn(n);
	}
	
	public boolean checkMoveProperiety(String line, Color playerColor) {
	
		String[] words = line.split(" ");
		Color color = new Color(Integer.parseInt(words[2]),Integer.parseInt(words[3]),Integer.parseInt(words[4]));
		ArrayList<ColorCircle> circles = board.getCircles();
		ColorCircle circleStart = circles.get(Integer.parseInt(words[0]));
		ColorCircle circleEnd = circles.get(Integer.parseInt(words[1]));
		if(!playerColor.equals(color) || endOfMoves) {
			return false;
		}
		else {
			double distance = Math.sqrt((Math.pow(circleStart.getCenterX()-circleEnd.getCenterX(), 2)) + Math.pow(circleStart.getCenterY()-circleEnd.getCenterY(), 2));
			if(distance < 45) {
				if(jumpingCircle != null) {
					return false;
				}
				endOfMoves = true;
				circleStart.setColor(Color.WHITE);
				circleEnd.setColor(color);
				return true;
			} else if ( distance > 75) {
				return false;
			} else {
				if(jumpingCircle != null && jumpingCircle != circleStart) {
					return false;
				}
				double x = (circleStart.getCenterX()+circleEnd.getCenterX())/2;
				double y = (circleStart.getCenterY()+circleEnd.getCenterY())/2;
				for(int i=0;i<circles.size();i++) {
					if(circles.get(i).getCenterX() == x && circles.get(i).getCenterY() == y && circles.get(i).getColor() != Color.WHITE) {
						circleStart.setColor(Color.WHITE);
						circleEnd.setColor(color);	
						jumpingCircle = circleEnd;
						return true;
					}
				}
				return false;
			}
		}
	}
	
	public void reset() {
		endOfMoves = false;
		jumpingCircle = null;
	}

	
}
