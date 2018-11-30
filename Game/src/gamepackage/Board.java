package gamepackage;

import java.awt.Color;
import java.util.ArrayList;

public class Board {
	
	public static int numberOfCircles = 121;
	
	private int numberOfPlayers;
	private ArrayList<ColorCircle> circles;
	
	Board( int numberOfPlayers ) {
		
		this.numberOfPlayers = numberOfPlayers;
		this.circles = new ArrayList<ColorCircle> (numberOfCircles);
		
		createBoard();
		
		if(numberOfPlayers == 3) {
			
			int[] players = {10,11,12,13,23,24,25,35,36,46};
			int[] players2 = {19,20,21,22,32,33,34,44,45,55};
			int[] players3 = {0,1,2,3,4,5,6,7,8,9};
			createPlayer(players,Color.BLUE);
			createPlayer(players2,Color.RED);
			createPlayer(players3,Color.WHITE);
		}
		
		if(numberOfPlayers == 4) { // to dorobie potem
			int[] players = {2,3};
		}
		
		if(numberOfPlayers == 6) { // to tez
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
	
	public ArrayList<ColorCircle> getCircles() {
		return circles;
	}
}
