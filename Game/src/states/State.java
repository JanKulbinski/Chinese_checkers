package states;

import gamepackage.Board;

public interface State {
	int[] player = {0,1,2,3,4,5,6,7,8,9};
	int[] player1 = {10,11,12,13,23,24,25,35,36,46};
	int[] player2 = {19,20,21,22,32,33,34,44,45,55};
	int[] player3 = {65,75,76,86,87,88,98,99,100,101};
	int[] player4 = {74,84,85,95,96,97,107,108,109,110};
	int[] player5 = {111,112,113,114,115,116,117,118,119,120};
	
	void setAreasForPlayers(Board board);
	
	int[] setDestinationForPlayer(int playerId);
}
