package SourceFiles;

public class Board {

	int[] spaces;//All the holes in the board, including the home squares
	public static int stoneCount = 3;
	
	public boolean gameOver = false;//Whether or not the game has ended.
	
	public int maximumLegalDepth = -1;//How far through the move chain you can go without getting an error or ending your turn.
	//This is where the move should be incremented. If the current move at depth X is not legal but there is a legal move at depth X,
	//Then this value will be set to X. This defaults to -1 because it is increased in a do-while loop.
	
	public int nextValidPosition = -1;//The position of the next valid move. This is to allow proper and efficient move incrementation.
	
	
	
	public boolean playTurn( int player, int[] moves){//Returns whether or not that moveset was legal (attempting illegal moves
		//This function plays out all of a player's moves on a particular turn.
		
		int movePosition = -1;//Start this at -1 since you increment it each time.
		maximumLegalDepth = -1;
		
		//TODO somewhere in here alter the maximumLegalDepth and the nextValidPosition to allow for good incrementation.
		do{
			movePosition++;

			
			//A for loop here should decide where the next possible move should be set.
			for( int i = moves[movePosition] + 1; i != 6 && i != 13; i++){//If there is a valid move 
				if(spaces[i] != 0){
					nextValidPosition = i;
					maximumLegalDepth = movePosition;
				}
			}
			
			if(  canMakeMove(player, moves[movePosition]) == false ){//If the move is not a legal one
				
				
				
				return false;
				
			}
			
		}while( makeMove(player, moves[movePosition]) );//Keep going until you can't go again
		
		
		return true;//If you haven't made an illegal move, then return true;].
		
	}
	
	public boolean canMakeMove( int player, int space){
		if(space < 0 || space > 5){//Moves beyond the legal range
			return false;
		}
		
		if(player == 2){//Adjust the position for player 2
			space += 7;
		}
		
		if( spaces[space] == 0 ){
			return false;
		}
		
		return true;
	}
	
	public boolean makeMove( int player, int space){//returns whether or not that move allows you to go again.
		if( space < 0 || space > 5){//The move is out of the legal range
			System.out.println("ERROR: Move outside of legal range.");
			
		}
		
		int hand = 0;//The number of stones in your hand
		int currentLocation = space;//This is where your "hand" currently is.
		
		
		
		if(player==2){
			currentLocation += 7;
		}
		
		
		do{
			hand = spaces[currentLocation];//Copy the number of stones in that space into your hand
			spaces[space] = 0;//Remove the stones from that location
		
			while(hand > 0){
				currentLocation++;
				
				if( !(player == 1 && currentLocation == 13) && !(player == 2 && currentLocation == 6)){//If you aren't dropping into the opponent's space
					
					//drop
					hand -= 1;
					spaces[currentLocation] += 1;
					
				}
			}
		
		
		}while(spaces[currentLocation] != 1);//Keep moving until you place your last stone into an empty pit.
		
		if( player == 1 && currentLocation == 6 || player == 2 && currentLocation == 13){//If your last drop was into you home space
			return true;//You can go again
			
		}
		
		else{
			return false;//You can't go again
		}
		
	}
	
	public void dump(){//If either side of the board is empty, put all pieces into their respective goal squares and end the game.
		
		int count1 = 0;
		int count2 = 0;
		
		for(int i = 0; i<6; i++){
			count1 += spaces[i];
		}
		for(int i = 7; i<14; i++){
			count2 += spaces[i];
		}
		
		if(count1 == 0 || count2 == 0){//If this is true, the game is now over.
			
			gameOver = true;
			
			spaces[6] += count1;//Add the stones in each row to their respective goals.
			spaces[13] += count2;
			
			
			
		}
		
	}
	
	public int getPlayer1Score(){
		
		return spaces[6];
		
	}
	
	public int getPlayer2Score(){
		return spaces[13];
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public int getMaximumLegalDepth() {
		return maximumLegalDepth;
	}

	public int getNextValidPosition() {
		return nextValidPosition;
	}

	public Board(){//Main constructor, doesn't use any parameters AFAIK
		
		spaces = new int[14];
		
		for(int i = 0; i<spaces.length; i++){
			spaces[i] = stoneCount;
		}
		
		spaces[0] = 0;
		spaces[spaces.length] = 0;//Set the two goal squares to zero.
		
	}
	
	public Board(Board input){//Copy constructor
		System.arraycopy(input.spaces, 0, this.spaces, 0, input.spaces.length);//Copy the input array into my array
	}
	
	
	
}
