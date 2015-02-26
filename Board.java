package SourceFiles;

public class Board {

	int[] spaces;//All the holes in the board, including the home squares
	public static int stoneCount = 3;
	
	
	
	
	public boolean playTurn( int player, int[] moves){//Returns whether or not that moveset was legal (attempting illegal moves
		//This function plays out all of a player's moves on a particular turn.
		
		int movePosition = -1;//Start this at -1 since you increment it each time.
		
		do{
			movePosition++;
			
			if(  !(canMakeMove(player, moves[movePosition])) ){//If the move is not a legal one
				
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
	
	
	public int getPlayer1Score(){
		
		return spaces[6];
		
	}
	
	public int getPlayer2Score(){
		return spaces[13];
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
