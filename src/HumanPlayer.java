/**
 * This class established human player object that is capable of giving 
 * instructions and receiving input from user during the game for different 
 * operational options including making a move to a specific column and 
 * quitting during the game.
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
 *  - You must implement the Player interface
 */
package edu.wm.cs.cs301.connectn;

import java.util.Scanner;

public class HumanPlayer implements Player {
	Scanner scanner = new Scanner(System.in);
	
    private char token;
    private String name;
    
    static Location newLoc;
    static int rLoc;
    static int cLoc;
    static int moves;
    
    public HumanPlayer(char token, String name) {
        this.token = token;
        this.name = name;
    }

    @Override
    public char getToken() {
        return token;
    }

    @Override
    public String getName() {
        return name;
    }
    
    @Override
	public Location getNewLoc() {
		return newLoc;
	}
    
    @Override
    public int getRLoc() {
        return rLoc;
    }
    
    @Override
    public int getCLoc() {
        return cLoc;
    }
    
    @Override
    public int getMoves() {
        return moves;
    }
    
    @Override
    public void setMoves(int n) {
        moves = n;
    }
    

    @Override
    public void takeTurn(GameBoard gameBoard) {
		System.out.print("Choose a slot (1 - "+ ConnectNGame.col +") to place your piece or QUIT to quit the game: ");
		String enterValue = scanner.nextLine();
		
		if (enterValue.equals("QUIT")){
			gameBoard.displayBoard();
			System.out.println("User quits.");
			
			//start again after user quits.
			HumanPlayer humanPlayer = new HumanPlayer('X', "Human Player");
			ComputerPlayer computerPlayer = new ComputerPlayer('O', "Computer Player");
			ConnectNGame newGame = new ConnectNGame(humanPlayer, computerPlayer);
			newGame.startAgain();
			System.exit(0);
		}
		
		int enterCol = Integer.valueOf(enterValue);
		
		makeMove(gameBoard, enterCol); //use input to make a move in the game
        
    }
    
	public void makeMove(GameBoard board, int enterCol){
		int i = 0;
		Location [][] currentBoard = board.getBoard();
		
		if (!currentBoard[0][enterCol-1].isEmpty()) {
			System.out.print("Looks like this slot is full. Choose another: ");
			enterCol = scanner.nextInt();
			scanner.nextLine();
		}
		
		while (!currentBoard[ConnectNGame.row-1-i][enterCol-1].isEmpty()) {
			i++;
		}
		currentBoard[ConnectNGame.row-1-i][enterCol-1].setToken(token);
		
		HumanPlayer.newLoc = currentBoard[ConnectNGame.row-1-i][enterCol-1];
		HumanPlayer.rLoc = ConnectNGame.row-1-i;
		HumanPlayer.cLoc = enterCol-1;
		HumanPlayer.moves += 1;
		
	}
	
}
