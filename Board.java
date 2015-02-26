package SourceFiles;

public class Board {

	int[] spaces;//All the holes in the board, including the home squares
	public static int stoneCount = 3;
	
	
	
	
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
			return true;
			
		}
		
		else{
			return false;
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
		
	}
	
	public Board(Board input){//Copy constructor
		System.arraycopy(input.spaces, 0, this.spaces, 0, input.spaces.length);//Copy the input array into my array
	}
	
	
	
}
