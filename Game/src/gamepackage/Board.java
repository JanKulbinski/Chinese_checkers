package gamepackage;

import java.awt.Color;
import java.util.ArrayList;

import gameServer.Connector;

public class Board {
	
	public static int numberOfCircles = 121;
	private int numberOfPlayers;
	private ArrayList<ColorCircle> circles;
	private Connector connector;
	private BoardImage boardImage;
	
	public Board( int numberOfPlayers, BoardImage boardImage ) {
		this.numberOfPlayers = numberOfPlayers;
		this.circles = new ArrayList<ColorCircle> (numberOfCircles);
		this.boardImage = boardImage;
		
		createBoard();
		
		if(numberOfPlayers == 3) {
			
			int[] players = {10,11,12,13,23,24,25,35,36,46};
			int[] players2 = {19,20,21,22,32,33,34,44,45,55};
			int[] players3 = {0,1,2,3,4,5,6,7,8,9};
			createPlayer(players,Color.RED);
			createPlayer(players2,Color.BLUE);
			createPlayer(players3,Color.WHITE);
		}
		
		if(numberOfPlayers == 4) { // to dorobie potem 4.cyan 
			int[] players = {2,3};
		}
		
		if(numberOfPlayers == 6) { // to tez 5.pink 6.yellow
			int[] players = {2,3};
		}
		
	}
	
	/*
	 * create by default Board for 2 players
	 */
	private void createBoard() { 
		
		float start = ColorCircle.width*10;
		for( int i = 0; i < 4; i++) {
			createRaw(start-(ColorCircle.width/2)*i,i,i+1,Color.RED);
		}
		
		start = start - ColorCircle.width*8;
		for( int i = 4; i < 9 ; i++) {
			createRaw(start+(ColorCircle.width/2)*i,i,17-i,Color.WHITE);
		}
		
		start = (float) (start + 3.5*ColorCircle.width);
		for( int i = 9; i < 13 ; i++) {
			createRaw(start-((ColorCircle.width/2)*(i-9)),i,i+1,Color.WHITE);
		}
		
		start = (float) (start + 3*ColorCircle.width);
		for( int i = 13; i < 17 ; i++) {
			createRaw(start+((ColorCircle.width/2)*(i-13)),i,17-i,Color.GREEN);
		}
	}
	
	/*
	 * create 1 raw of circles
	 */
	private void createRaw(float startX,float row,int circlesNumber,Color color) {
		
		for( int i = 0; i < circlesNumber; i++ ) {
			circles.add(new ColorCircle(startX+ColorCircle.width*i,row*ColorCircle.height,color));
		}
	}
	
	private void createPlayer(int players[],Color color) {
		for( int i=0; i < players.length; i++ ) {
			circles.get(players[i]).setColor(color);
		}
			
	}
	
	public void move(int c1, int c2, Color color) {

		connector.out.println(c1+" "+c2+" "+color.getRed()+" "+color.getGreen()+" "+color.getBlue());
	}
	
	public void opponentMove(String move) {
		String[] words = move.split(" ");
		Color color = new Color(Integer.parseInt(words[2]),Integer.parseInt(words[3]),Integer.parseInt(words[4]));
		getCircles().get(Integer.parseInt(words[0])).setColor(Color.WHITE);
		getCircles().get(Integer.parseInt(words[1])).setColor(color);
		boardImage.repaint();
		
	}
	
	public void setConnector(Connector c) {
		this.connector = c;
	}
	public ArrayList<ColorCircle> getCircles() {
		return circles;
	}
}
