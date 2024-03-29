/*
 *  Connect 4 Program
 *  CS151, section 4
 *  
 *  Michael Symonds - 011078574
 *  Michael Chen - 007230223
 *  
 */

import javafx.scene.paint.Color;

public class Player {
	private String name = "";
	private int score = 0;
	
	public Player(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public int getScore(){
		return score;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setScore(int i){
		if(i > 0)
			score = i;
	}
	
	public void incrementScore(){
		score++;
	}
	
	public void decrementScore(){
		if(score > 0)
			score--;
	}
        
	@Override
    public boolean equals(Object o) {
		if (this == o) return true;
        if (!(o instanceof Player)) return false;
        
        Player p = (Player)o;
        if(this.getName().toLowerCase() == p.getName().toLowerCase())
        	return true;

        return true;
    }
}
