/**
 * This class stores the information of token for a single
 * location. Together the location objects composed the entire 
 * game board. There is getter and setter method to return and update
 * the token in a specific location, the isEmpty method to determine
 * if the location is empty, and the equals method to determine if the 
 * token in the location is equal with another token.
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
 *  - You must overload the Object class's equals method
 *  - You must provide isEmpty and getToken methods
 *  - You must represent the symbol as a Character
 *  - the symbol can be one of 'X', 'O', or ' '
 */
package edu.wm.cs.cs301.connectn;

public class Location {
	private Character symbol;
	
	public Location() {
		this.symbol = ' ';
	}
	
	public boolean isEmpty() {
		return symbol.equals(' ');	//placeholder, replace with correct code
	}
	
	public Character getToken() {
		return symbol;
	}
	
	public void setToken(Character newSymbol) {
		this.symbol = newSymbol;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other.equals(symbol)) {
			return true;
		}else {
			return false;	//placeholder, replace with correct code
		}
	}
	
}
