/**
 * This class implemented GameBoard object that stores a 
 * 2-dimensional array of locations and allows the program 
 * to print the physical form of game board in the console 
 * for display.
 * 
 * You may change this file as much as you like, including:
 * 	- adding constructor parameters
 *  - overloading the constructor
 *  - adding instance variables
 *  - adding methods (public or private)
 *  
 * HOWEVER:
 *  - You may not move the file to a different package
 * 	- You may not change the name of the file or the class
 *  - you must use the provided board instance variable to 
 *    store the state of your board.
 *  - you must implement the displayBoard method 
 */
package edu.wm.cs.cs301.connectn;

public class GameBoard {
	private Location[][] board;			//do not change!
	// a ?*? matrix of Location objects
	
	public GameBoard() {
		board = new Location[ConnectNGame.row][ConnectNGame.col];
		for (int row = 0; row < ConnectNGame.row; row++){
			for (int col = 0; col < ConnectNGame.col; col++){
				board[row][col] = new Location(); //set up a row x col-size board with every location set to empty
			}
		}
	}
	
	public Location[][] getBoard(){
		return board;
	}
	
	public boolean isFull() {
		for (int i = 0; i < ConnectNGame.col; i++) {
			if (board[0][i].isEmpty()) {
				return false;
			}
		}
		return true;
	}
	
	public void displayBoard() {
		for (int i = 0; i < ConnectNGame.col; i++) {
			System.out.print("   "+(i+1));
		}
		System.out.println();
		
		System.out.print(" ");
		for (int i = 0; i < ConnectNGame.col; i++) {
			System.out.print("====");
		}
		System.out.println("=");
		
		for (int row = 0; row < ConnectNGame.row; row++){
			System.out.print("||");
			for (int col = 0; col < ConnectNGame.col; col++){
				System.out.print(" ");
				System.out.print(board[row][col].getToken());//get token in the location
				System.out.print(" |");
			}
			System.out.println("|"); 
			if (row!=ConnectNGame.row-1){
				for (int i = 0; i < ConnectNGame.col; i++) {
					System.out.print("----");
				}
				System.out.print("---");
				System.out.println();
			}
		}
		
		System.out.print(" ");
		for (int i = 0; i < ConnectNGame.col; i++) {
			System.out.print("====");
		}
		System.out.println("=");
	}
	
}
