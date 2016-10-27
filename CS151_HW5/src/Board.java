
public class Board {
	private int dim = 0;
	private int numToWin = 0;
	private int[][] board;
	
	public Board(int dim, int numWin){
		this.dim = dim;
		numToWin = numWin;
		board = new int[dim][];
		for(int i = 0; i < dim; i++){
			board[i] = new int[dim];
		}
		clearBoard();
	}
	
	public boolean isFull(){
		boolean noZero = true;
		for(int i = 0; i < dim; i++){
			for(int j = 0; j < dim; j++){
				if(board[i][j] == 0)
					noZero = false;
			}
		}
		return noZero;
	}
	
	public int[][] getBoard(){
		return board;
	}
	
	public int getNumToWin(){
		return numToWin;
	}
	
	public int getBoardDim(){
		return dim;
	}
	
	public void clearBoard(){
		for(int i = 0; i < dim; i++){
			for(int j = 0; j < dim; j++){
				board[i][j] = 0;
			}
		}
	}
	
	public String toString(){
		String s = "";
		for(int row = 0; row < dim; row++){
			for(int column = 0; column < dim; column++){
				s += "" + board[row][column] + " "; 
			}
			s += "\n";
		}
		return s;
	}

}
