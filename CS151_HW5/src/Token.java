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
