package SourceFiles;

public class Board {

	int[] spaces;//All the holes in the board, including the home squares
	public static int stoneCount = 3;
	
	
	
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
