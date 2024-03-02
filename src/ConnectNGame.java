/**
 * This class is a platform that employs the functions in other 
 * classes to generate a complete game loop. It set up the game 
 * version(Small/Medium/Large), and stores the row and column size 
 * information. Leaderboard is also called in this method for update.
 * It contains all the methods for controlling a single game loop,
 * including switching player, checking the winner, and allowing user 
 * to start again when one game is over.
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
 */
package edu.wm.cs.cs301.connectn;
import java.io.IOException;
import java.util.Scanner;

public class ConnectNGame {
	static int row;
	static int col;
	static int winNum;
	static String size;
	static Leaderboard lb = new Leaderboard();
	
    private Player currentPlayer;
    private HumanPlayer humanPlayer;
    private ComputerPlayer computerPlayer;
    
	Scanner scanner = new Scanner(System.in);
	
	
	public ConnectNGame(HumanPlayer humanPlayer,ComputerPlayer computerPlayer) {
        this.currentPlayer = humanPlayer;
        this.humanPlayer = humanPlayer;
        this.computerPlayer = computerPlayer;	
	}
	
	//the game loop
	public void playGame() throws IOException {
		
		setGameVersion();
		
		lb.printFile();
		
		GameBoard gameBoard = new GameBoard();
		
		currentPlayer = humanPlayer;//set currentPlayer to human Player
		humanPlayer.setMoves(0);
		computerPlayer.setMoves(0);
		
		System.out.println("Turn: " + (currentPlayer.getMoves()+1));
		gameBoard.displayBoard();
		
		//the game loop
		while (true) {
			//start again when the game is a tie
			if (gameBoard.isFull()) {
				System.out.println("It's a draw!");
				startAgain();
				break;
			}
			currentPlayer.takeTurn(gameBoard);
			System.out.println("Turn: " + currentPlayer.getMoves());
			gameBoard.displayBoard();
			checkWinner(gameBoard,currentPlayer.getRLoc(),currentPlayer.getCLoc(),currentPlayer.getNewLoc());
			switchPlayer();
		}
		
	}
	
	public void setGameVersion(){
		System.out.println("Welcome to Connect N! To play the game, enter the number of column to drop a token to the slot. \n"
				+ "The tokens will stack up on top of each other. \n"
				+ "Line up the tokens to win! There are three versions of the board: \n"
				+ "Small(3 in a line to win), Medium(4 in a line to win), and Large(5 in a line to win). \n"
				+ "GOOD LUCK! \n");
		System.out.print("Enter the version you want to play (Small/Medium/Large): ");
		size = scanner.nextLine();
		if (size.equals("Small")) {
			row = 4;
			col = 5;
			winNum = 3;
		}
		else if (size.equals("Medium")) {
			row = 6;
			col = 7;
			winNum = 4;
		}
		else if (size.equals("Large")) {
			row = 8;
			col = 9;
			winNum = 5;
		}else {
			System.exit(0);
		}
	}
	
	public String getGameVersion(){
		return size;
	}
	
	public void switchPlayer() {
		currentPlayer = (currentPlayer == humanPlayer) ? computerPlayer : humanPlayer;
	}
	
	public void decideWinner(int count) {
		if(count==winNum) {
			if(currentPlayer.getName().equals("Human Player")) {
				System.out.println("Congratulations! You won in " + currentPlayer.getMoves() + " moves!");
				try {
					lb.updateFile(size, humanPlayer);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				System.out.println("Opps, the computer won...");
			}
			startAgain();
		}
	}
	
	public void checkWinner(GameBoard board, int rLoc, int cLoc, Location newLoc) {
		Location [][] currentBoard = board.getBoard();
		
		//check vertical
		int countV = 1;
		for (int j = 1; rLoc+j < row; j++) { 
			if(currentBoard[rLoc+j][cLoc].equals(newLoc)) {
				countV+=1;
			}else{
				break;
			}
			decideWinner(countV);
		}
		
		//check horizontal
		int countH = 1;
		boolean toLeft = true;
		boolean toRight = true;
		for (int k = 1; cLoc+k < col | cLoc-k >= 0; k++) { 
			if (cLoc-k >= 0 && toLeft) {
				if(currentBoard[rLoc][cLoc-k].equals(newLoc)) {
					countH+=1;
				}else {
					toLeft = false;
				}
			}
			if (cLoc+k < col && toRight) {
				if(currentBoard[rLoc][cLoc+k].equals(newLoc)) {
					countH+=1;
				}else {
					toRight = false;
				}
			}
			decideWinner(countH);
		}
		
		//check diagonal (\: left-up and right-down)
		int countDUD = 1;
		boolean toLeftUp = true;
		boolean toRightDown = true;
		for (int l = 1; (rLoc-l >= 0 && cLoc-l >= 0) | (rLoc+l < row && cLoc+l < col); l++) { 
			if (rLoc-l >= 0 && cLoc-l >= 0 && toLeftUp) {
				if(currentBoard[rLoc-l][cLoc-l].equals(newLoc)) {
					countDUD+=1;
				}else {
					toLeftUp = false;
				}
			}
			if (rLoc+l < row && cLoc+l < col && toRightDown) {
				if(currentBoard[rLoc+l][cLoc+l].equals(newLoc)) {
					countDUD+=1;
				}else {
					toRightDown = false;
				}
			}
			decideWinner(countDUD);
		}
		
		//check diagonal (/: left-down and right-up)
		int countDDU = 1;
		boolean toLeftDown = true;
		boolean toRightUp = true;
		for (int l = 1; (rLoc+l < row && cLoc-l >= 0) | (rLoc-l >= 0 && cLoc+l < col); l++) { 
			if (rLoc+l < row && cLoc-l >= 0 && toLeftDown) {
				if(currentBoard[rLoc+l][cLoc-l].equals(newLoc)) {
					countDDU+=1;
				}else {
					toLeftDown = false;
				}
			}
			if (rLoc-l >= 0 && cLoc+l < col && toRightUp) {
				if(currentBoard[rLoc-l][cLoc+l].equals(newLoc)) {
					countDDU+=1;
				}else {
					toRightUp = false;
				}
			}
			decideWinner(countDDU);
		}
		
	}
	
	public void startAgain() {
		System.out.print("Would you like to play again (Y/N)??");
		String again = scanner.nextLine();
		if (again.equals("Y")) {
			try {
				playGame();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.exit(0);
		}
	}
	//scanner.close();
}
