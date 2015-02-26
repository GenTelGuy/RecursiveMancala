package SourceFiles;

public class MoveSet {
	
	int player;//1 or 2 depending on the player
	
	int[] moves;//The current set of moves with which this array is working
	int[] bestMoves;//The best possible moves that can be done in this MoveSet
	
	int bestScore;//The best score that can be attained by this MoveSet.
	
	Board myBoard;
	Board givenBoard;//This one is not altered, this is how the board was presented by the parent.
	
	MoveSet child = null;//The moveSet that comes after this one in the game. This value is kept null if this is the last move in the game.
	
	public void resetMyBoard(){//Resets your board to the one given by the parent. Reset, move, reset, move, etc. 
		myBoard = new Board(givenBoard);
	}
	
	
	public MoveSet(Board inputBoard, int player){
		
		this.moves = new int[255];
		
		for(int i = 0; i < moves.length; i++){
			this.moves[i] = -1;//TODO this default value should be either -1 or 0, I am not sure which as of now.
		}
		
		givenBoard = new Board(inputBoard);
		
		this.player = player;
		
		
		
	}
	

}
