import javafx.scene.paint.Color;

public class C4Player {
	private Player myPlayer = null;
	private Color myColor = null;
	private int turnOrder = -1;
	
	public C4Player(Player player){
		myPlayer = player;
	}
	
	public Player getPlayer(){
		return myPlayer;
	}
	
	public Color getColor(){
		return myColor;
	}
	
	public int getTurnOrder(){
		return turnOrder;
	}
	
	public void setColor(Color color){
		myColor = color;
	}
	
	public void setTurnOrder(int i){
		turnOrder = i;
	}
}
