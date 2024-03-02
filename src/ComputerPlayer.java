/**
 * This class established the functions of computer player to make move when it is
 * the computer's turn.
 * This class implemented a Minimax algorithm to provide higher intelligence for the
 * computer player. The algorithm recursively evaluates each possible step and return
 * the best step.
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


public class ComputerPlayer implements Player {
    private char token;
    private String name;
    
    static Location newLoc;
    static int rLoc;
    static int cLoc;
    static int moves;

    public ComputerPlayer(char token, String name) {
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
    public void takeTurn(GameBoard gameBoard) {
        int[] move = minmax(gameBoard, 5, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
        int col = move[1];
        dropPiece(gameBoard, col);
    	//chooseMove(gameBoard);
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
    
    
    /*public void chooseMove(GameBoard board){
		int i = 0;
		boolean set = false;
		Location [][] currentBoard = board.getBoard();
		
		//least smart move
		for (int colCount = 0; colCount < ConnectNGame.col; colCount++) {
			int rowCount = ConnectNGame.row-1;
			int j = 0;
			while (rowCount-j>=0 && !currentBoard[rowCount-j][colCount].isEmpty()) {
				j++;
			}
			if (rowCount-j<0) {
				continue;
			}
			rowCount -= j;
			
			//check vertical
			int isTokenV = 1;
			int placeHolder = rowCount;
			while (placeHolder+1 < ConnectNGame.row) {
				if (currentBoard[placeHolder+1][colCount].equals(token)) {
					isTokenV += 1;
					placeHolder++;
				}else {
					break;
				}
				if (isTokenV >= ConnectNGame.winNum) {
					currentBoard[rowCount][colCount].setToken(token);
					ComputerPlayer.newLoc = currentBoard[rowCount][colCount]; //record inserted location
					ComputerPlayer.rLoc = rowCount;
					ComputerPlayer.cLoc = colCount;
					set = true;
					break;
				}
			}
			
			//check horizontal
			int x = 1;
			int isTokenH = 1;
			boolean right = true;
			boolean left = true;
			while (colCount+x < ConnectNGame.col | colCount-x >= 0) {
				if (colCount+x < ConnectNGame.col && right && currentBoard[rowCount][colCount+x].getToken().equals(token)) {
					isTokenH += 1;
				}else {
					right = false;
				}
				if (colCount-x >= 0 && left && currentBoard[rowCount][colCount-x].equals(token)) {
					isTokenH += 1;
				}else {
					left = false;
				}
				if (right== false && left == false) {
					break;
				}
				x++;
				if (isTokenH >= ConnectNGame.winNum) {
					currentBoard[rowCount][colCount].setToken(token);
					ComputerPlayer.newLoc = currentBoard[rowCount][colCount]; //record inserted location
					ComputerPlayer.rLoc = rowCount;
					ComputerPlayer.cLoc = colCount;
					set = true;
					break;
				}
			}
			
			//check diagonal(\)
			int y = 1;
			int isTokenD = 1;
			boolean leftUp = true;
			boolean rightDown = true;
			while ((rowCount-y >=0 && colCount-y >= 0) | (rowCount+y < ConnectNGame.row && colCount+y < ConnectNGame.col)) {
				if ((colCount-y >=0 && rowCount-y >= 0) && leftUp && currentBoard[rowCount-y][colCount-y].equals(token)) {
					isTokenD += 1;
				}else {
					leftUp = false;
				}
				if ((rowCount+y < ConnectNGame.row && colCount+y < ConnectNGame.col) && rightDown && currentBoard[rowCount+y][colCount+y].equals(token)) {
					isTokenD += 1;
				}else {
					rightDown = false;
				}
				if (leftUp== false && rightDown == false) {
					break;
				}
				y++;
				if (isTokenD >= ConnectNGame.winNum) {
					currentBoard[rowCount][colCount].setToken(token);
					ComputerPlayer.newLoc = currentBoard[rowCount][colCount]; //record inserted location
					ComputerPlayer.rLoc = rowCount;
					ComputerPlayer.cLoc = colCount;
					set = true;
					break;
				}
			}
			
			//check diagonal(/)
			int z = 1;
			int isTokenD2 = 1;
			boolean leftDown = true;
			boolean rightUp = true;
			while ((rowCount+z < ConnectNGame.row && colCount-z >= 0) | (rowCount-z >= 0 && colCount+z < ConnectNGame.col)) {
				if ((rowCount+z < ConnectNGame.row && colCount-z >= 0) && leftDown && currentBoard[rowCount+z][colCount-z].equals(token)) {
					isTokenD2 += 1;
				}else {
					leftDown = false;
				}
				if ((rowCount-z >= 0 && colCount+z < ConnectNGame.col) && rightUp && currentBoard[rowCount-z][colCount+z].equals(token)) {
					isTokenD2 += 1;
				}else {
					rightUp = false;
				}
				if (leftDown== false && rightUp == false) {
					break;
				}
				z++;
				if (isTokenD2 >= ConnectNGame.winNum) {
					currentBoard[rowCount][colCount].setToken(token);
					ComputerPlayer.newLoc = currentBoard[rowCount][colCount]; //record inserted location
					ComputerPlayer.rLoc = rowCount;
					ComputerPlayer.cLoc = colCount;
					set = true;
					break;
				}
			}
		}
		
		//random move
		if (set == false) {
			int column = (int) (Math.random() * ConnectNGame.col); //generate a random column number
			while (!currentBoard[0][column].isEmpty()) { //find a column
				column = (int) (Math.random() * ConnectNGame.col);
			}
			while (currentBoard[ConnectNGame.row-1-i][column].isEmpty()==false) { //find empty space
				i++;
			}
			currentBoard[ConnectNGame.row-1-i][column].setToken(token); //insert token into the empty location
			
			set = true;
			ComputerPlayer.newLoc = currentBoard[ConnectNGame.row-1-i][column]; //record inserted location
			ComputerPlayer.rLoc = ConnectNGame.row-1-i;
			ComputerPlayer.cLoc = column;
		}
		
		ComputerPlayer.moves += 1;
		
    }*/
    
    
    private static int[] minmax(GameBoard board, int depth, int alpha, int beta, boolean maximizingPlayer) {
    	//Location[][] currentBoard = board.getBoard();
        if (depth == 0 || board.isFull()) {
            int score = evaluate(board);
            return new int[]{score, -1};
        }

        if (maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            int bestMove = -1;

            for (int col = 0; col < ConnectNGame.col; col++) {
                if (board.getBoard()[0][col].isEmpty()) { // If the move is valid
                    dropPiece(board, col); // Make move
                    int eval = minmax(board, depth - 1, alpha, beta, false)[0];
                    withdrawPiece(board, col); // Undo the move

                    if (eval > maxEval) {
                        maxEval = eval;
                        bestMove = col;
                    }

                    alpha = Math.max(alpha, eval);
                    if (beta <= alpha) {
                        break; // Beta cut-off
                    }
                }
            }

            return new int[]{maxEval, bestMove};
        } else {
            int minEval = Integer.MAX_VALUE;
            int bestMove = -1;

            for (int col = 0; col < ConnectNGame.col; col++) {
                if (board.getBoard()[0][col].isEmpty()) { // If the move is valid
                    dropPiece(board, col);
                    int eval = minmax(board, depth - 1, alpha, beta, true)[0];
                    withdrawPiece(board, col); // Undo the move

                    if (eval < minEval) {
                        minEval = eval;
                        bestMove = col;
                    }

                    beta = Math.min(beta, eval);
                    if (beta <= alpha) {
                        break; // Alpha cut-off
                    }
                }
            }

            return new int[]{minEval, bestMove};
        }
    }
    
    private static void dropPiece(GameBoard board, int col) {
        Location[][] currentBoard = board.getBoard();
        int j = 0;
        int rowCount = ConnectNGame.row;
        
        // Find uppermost empty location in the column
        while (rowCount - j - 1 >= 0 && !currentBoard[rowCount - j - 1][col].isEmpty()) {
            j++;
        }
        
        // Check if the column is full
        if (rowCount - j - 1 < 0) {
            return;
        }
        
        rowCount -= (j + 1);
        
        currentBoard[rowCount][col].setToken('O'); // Insert token into the empty location
        
        ComputerPlayer.newLoc = currentBoard[rowCount][col]; // Record inserted location
        ComputerPlayer.rLoc = rowCount;
        ComputerPlayer.cLoc = col;
        ComputerPlayer.moves += 1;
    }
    
    private static void withdrawPiece(GameBoard board, int col) {
        Location[][] currentBoard = board.getBoard();
        int j = 0;
        int rowCount = ConnectNGame.row;
        
        // Find uppermost non-empty location in the column to erase it
        while (rowCount - j - 1 >= 0 && !currentBoard[rowCount - j - 1][col].isEmpty()) {
            j++;
        }
        
        rowCount -= j;
        
        currentBoard[rowCount][col].setToken(' '); // Insert empty token into the uppermost occupied location
        
        ComputerPlayer.newLoc = currentBoard[rowCount][col]; // Record inserted location
        ComputerPlayer.rLoc = rowCount;
        ComputerPlayer.cLoc = col;
        ComputerPlayer.moves -= 1; //withdraw move count
    }

	private static int evaluate(GameBoard board) {
        int score = 0;

        // Evaluate horizontal
        for (int row = 0; row < ConnectNGame.row; row++) {
            for (int col = 0; col < ConnectNGame.col - (ConnectNGame.winNum-1); col++) {
                score += evaluateWindow(board, row, col, 0, 1);
            }
        }

        // Evaluate vertical
        for (int row = 0; row < ConnectNGame.row - (ConnectNGame.winNum-1); row++) {
            for (int col = 0; col < ConnectNGame.col; col++) {
                score += evaluateWindow(board, row, col, 1, 0);
            }
        }

        // Evaluate diagonal
        for (int row = 0; row < ConnectNGame.row - (ConnectNGame.winNum-1); row++) {
            for (int col = 0; col < ConnectNGame.col - (ConnectNGame.winNum-1); col++) {
                score += evaluateWindow(board, row, col, 1, 1); // Diagonal \
                score += evaluateWindow(board, row, col + (ConnectNGame.winNum-1), 1, -1); // Diagonal /
            }
        }
        return score;
    }

    private static int evaluateWindow(GameBoard board, int row, int col, int rowDirection, int colDirection) {
        int score = 0;
        Location[][] currentBoard = board.getBoard();
        char player = currentBoard[row][col].getToken();

        for (int i = 0; i < ConnectNGame.winNum; i++) {
            int currentRow = row + i * rowDirection;
            int currentCol = col + i * colDirection;

            if (currentRow >= 0 && currentRow < ConnectNGame.row && currentCol >= 0 && currentCol < ConnectNGame.col) {
                if (currentBoard[currentRow][currentCol].getToken().equals(player)) {
                    score += (player == 'O') ? 1 : -1;
                }
            }
        }

        return score;
    }
    
}

