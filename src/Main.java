/**
 * The main class established new human and computer player and 
 * a new game instance to start playing and access other class and
 * methods.
 * 
 * You may change this file as much as you like,
 *  
 * HOWEVER:
 *  - You may not move the file to a different package
 * 	- You may not change the name of the file or the class
 */
package edu.wm.cs.cs301.connectn;
//import java.util.Scanner;

import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException {
		HumanPlayer humanPlayer = new HumanPlayer('X', "Human Player");
		ComputerPlayer computerPlayer = new ComputerPlayer('O', "Computer Player");

		ConnectNGame cn = new ConnectNGame(humanPlayer, computerPlayer); // Initiate new game instance
		cn.playGame();
	}
}
