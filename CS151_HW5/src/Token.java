/*
 *  Connect 4 Program
 *  CS151, section 4
 *  
 *  Michael Symonds - 011078574
 *  Michael Chen - 007230223
 *  
 */

import javafx.scene.shape.Circle;

public class Token extends Circle{
	
	private int column;
	private int row;
	
	public Token(int column, int row){
		this.column = column;
		this.row = row;
	}
	
	public int getColumn(){
		return column;
	}
	
}
