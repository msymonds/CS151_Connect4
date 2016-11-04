/*
 *  Connect 4 Program
 *  CS151, section 4
 *  
 *  Michael Symonds - 011078574
 *  Michael Chen - 007230223
 *  
 */

public class BoardKeeper {
	private static Board gameBoard;
	
	static void setBoard(Board board){
		gameBoard = board;
	}
	
	static int getNumWins(){
		return gameBoard.getNumToWin();
	}
	
	static int getDim(){
		return gameBoard.getBoardDim();
	}
	
	static int addMove(int column, int player){
		int[][] board = gameBoard.getBoard();
		
		for(int i = (gameBoard.getBoardDim() - 1); i >= 0; i--){
			if(board[i][column] == 0){
				board[i][column] = player;
				return i;
			}
		}
		return -1;
	}
	
	public static int isWinner(int currentPlayer){
		int winner  = 0;
		if(gameBoard.isFull())
			winner = 2;
		if(		BoardKeeper.checkVertical(currentPlayer) || 
				BoardKeeper.checkHorizontal(currentPlayer) || 
				BoardKeeper.checkDiagR(currentPlayer) || 
				BoardKeeper.checkDiagL(currentPlayer)
				) {
			winner = 1;
		}
		return winner;
	}
	
	private static boolean checkVertical(int player){
		int[][] board = gameBoard.getBoard();
		int dim = gameBoard.getBoardDim();
		int numWin = gameBoard.getNumToWin();
		for(int column = 0; column < dim; column++){
			for(int rowStart = 0; rowStart < ((dim-numWin)+1); rowStart++){
				int offset = 0;
				int winCount = 0;
				while(offset < numWin){
					if(board[rowStart + offset][column] == player)
						winCount++;
					offset++;
				}
				if(winCount == numWin)
					return true;
			}
		}
		return false;
	}
	
	private static boolean checkHorizontal(int player){
		int[][] board = gameBoard.getBoard();
		int dim = gameBoard.getBoardDim();
		int numWin = gameBoard.getNumToWin();
		for(int row = 0; row < dim; row++){
			for(int columnStart = 0; columnStart < ((dim-numWin)+1); columnStart++){
				int offset = 0;
				int winCount = 0;
				while(offset < numWin){
					if(board[row][columnStart + offset] == player)
						winCount++;
					offset++;
				}
				if(winCount == numWin)
					return true;
			}
		}
		return false;
	}
	
	private static boolean checkDiagR(int player){
		int[][] board = gameBoard.getBoard();
		int dim = gameBoard.getBoardDim();
		int numWin = gameBoard.getNumToWin();
		for(int row = 0; row < ((dim - numWin)+1); row++){
			for(int columnStart = 0; columnStart < ((dim-numWin)+1); columnStart++){
				int offset = 0;
				int winCount = 0;
				while(offset < numWin){
					if(board[row + offset][columnStart + offset] == player)
						winCount++;
					offset++;
				}
				if(winCount == numWin)
					return true;
			}
		}
		return false;
	}
	
	private static boolean checkDiagL(int player){
		int[][] board = gameBoard.getBoard();
		int dim = gameBoard.getBoardDim();
		int numWin = gameBoard.getNumToWin();
		for(int row = (numWin-1); row < dim; row++){
			for(int columnStart = 0; columnStart < ((dim-numWin)+1); columnStart++){
				int offset = 0;
				int winCount = 0;
				while(offset < numWin){
					if(board[row - offset][columnStart + offset] == player)
						winCount++;
					offset++;
				}
				if(winCount == numWin)
					return true;
			}
		}
		return false;
	}
	
	public static Board getBoard(){
		return gameBoard;
	}
	
	public static void resetBoard(){
		gameBoard.clearBoard();
	}
}
