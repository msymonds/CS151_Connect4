import java.util.HashSet;

public class PlayerBase extends HashSet<Player>{
	
	private static PlayerBase base;
	
	private PlayerBase(){};
	
	public static PlayerBase getPlayerBase(){
		if (base == null){
			base = new PlayerBase();
		}
		return base;
	}
	
	
}
