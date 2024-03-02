/**
 * The player interface declares and shares methods to be used
 * and expanded in HumanPlayer class and ComputerPlayer class
 * respectively. There are methods to get stored information like
 * current (new) column and row locations, get & set moves and take 
 * turn that is needed in both of the player classes.
 * 
 * You may add parameters to the takeTurn method and/or
 * change the return type of the method. But all classes that
 * implement the interface will need to be consistent with any
 * changes.
 *  
 * HOWEVER:
 *  - You may not move the file to a different package
 * 	- You may not change the name of the file or the interface
 *  - You may not change the name of the takeTurn method
 */
package edu.wm.cs.cs301.connectn;

public interface Player {
	Location getNewLoc();
	int getRLoc();
	int getCLoc();
	int getMoves();
	void setMoves(int n);
	
	char getToken();        // Get the player's token (e.g., 'X' or 'O')
    String getName();       // Get the player's name
    void takeTurn(GameBoard gameBoard);
}
