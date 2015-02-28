package SourceFiles;

public class MoveSet {
	
	int defaultValue = 0;//The default value for a move, this could be coded as a 0 or as a -1
	
	int player;//1 or 2 depending on the player
	
	int[] moves;//The current set of moves with which this array is working
	int[] bestMoves;//The best possible moves that can be done in this MoveSet
	
	int score;//The score generated by the last attempt (can be better or worse than 
	int bestScore;//The best score that can be attained by this MoveSet.
	
	private boolean exhausted = false;//Whether or not there are any more possible moves to try, or if they have all been exhausted.
	//This value is probably not necessary due to the recursive nature of the program.
	
	Board myBoard;
	Board givenBoard;//This one is not altered, this is how the board was presented by the parent.
	
	MoveSet child = null;//The moveSet that comes after this one in the game. This value is kept null if this is the last move in the game.
	
	public void findBestMove(){//This is the heart and soul of the program
		
		while( exhausted == false ){//While there are still moves to try
			myBoard = new Board(givenBoard);//Reset the board.
			if(myBoard.playTurn(player, moves) == true){//If that move was a valid move
				
				if(myBoard.isGameOver() == true){//If this move ends the game
					score = (player==1 ? myBoard.getPlayer1Score() : myBoard.getPlayer2Score());
				}
				
				else{
					child = new MoveSet(myBoard, (player==1 ? 1 : 2));//Create the next move in the chain
					child.findBestMove();
					score = Board.stoneCount * 6 - child.getBestScore();//Your score is the points the child didn't earn.
				}
				
				//regardless of whether you got a recursive score or an "end-game" score:
				bestTest();
				
			}
			
			
		}

		
		
		
	}
	
	public void bestTest(){//Tests whether the current moveset's score is better or worse than the best score, if it is better then it replaces bestMoves with Moves
		if(score > bestScore){
			bestScore = score;//Update bestScore
			System.out.println("NewBest");
			System.arraycopy(moves, 0, bestMoves, 0, moves.length);//Copy the moves array into bestMoves array
		}
	}
	
	public void setMove( int position, int value){//Sets one move to a certain value, then resets all of the ones after that to the default value.
		
		moves[position] = value;
		for(int i = position+1; i<moves.length; i++){
			moves[i] = defaultValue;
		}
		
	}
	
	public void resetMyBoard(){//Resets your board to the one given by the parent. Reset, move, reset, move, etc. 
		myBoard = new Board(givenBoard);
	}
	
	
	public MoveSet(Board inputBoard, int player){
		
		this.moves = new int[255];
		
		for(int i = 0; i < moves.length; i++){
			this.moves[i] = defaultValue;//TODO this default value should be either -1 or 0, I am not sure which as of now.
		}
		
		givenBoard = new Board(inputBoard);
		
		this.player = player;
		
		
		
	}


	public int getBestScore() {
		return bestScore;
	}
	
	
	

}
