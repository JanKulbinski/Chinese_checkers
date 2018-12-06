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
	
		/*stare sprawdzanie ruchu do usuniecia ,jak Twoje dziala
		 * String[] words = line.split(" ");
		Color color = new Color(Integer.parseInt(words[2]),Integer.parseInt(words[3]),Integer.parseInt(words[4]));
		ArrayList<ColorCircle> circles = board.getCircles();
		

		
		float x = circleEnd.x-(2*ColorCircle.width);
		float y = circleEnd.y-(2*ColorCircle.height);
		Ellipse2D.Float bigCircle = new Ellipse2D.Float(x,y,2*ColorCircle.width,3*ColorCircle.height);
		float circleEndX = (float) circleEnd.getCenterX();
		float circleEndY = (float) circleEnd.getCenterY();
		*/
		String[] words = line.split(" ");
		Color color = new Color(Integer.parseInt(words[2]),Integer.parseInt(words[3]),Integer.parseInt(words[4]));
		ArrayList<ColorCircle> circles = board.getCircles();
		ColorCircle circleStart = circles.get(Integer.parseInt(words[0]));
		ColorCircle circleEnd = circles.get(Integer.parseInt(words[1]));
		if(!playerColor.equals(color)) {
			return false;
		}
		else {
			circleStart.setColor(Color.WHITE);
			circleEnd.setColor(color);
			return true;
		}
	}

	
}
