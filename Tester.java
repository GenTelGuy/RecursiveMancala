package SourceFiles;

public class Tester {
	
	public static void main(String[] args){
		int x = 1;
		
		Board testBoard = new Board();
		MoveSet testMoveSet = new MoveSet( testBoard, 1);
		testMoveSet.findBestMove();
		System.out.println(testMoveSet.getBestScore());
		
		
	}

}
