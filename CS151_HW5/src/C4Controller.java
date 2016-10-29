
import java.util.HashSet;

public class C4Controller {
	private final int MAX_NUM_PLAYERS = 2;
	private int numPlayers = MAX_NUM_PLAYERS;
	private C4GUI view;
	private int currentPlayer = 1;
	private Player[] turnOrder = new Player[(1+numPlayers)];	
	
	
	
	void attachView(C4GUI view) {
        this.view = view;
    }
	
	public int getCurrentPlayer(){
		return currentPlayer;
	}
	public void establishPlayer(String name){
		Player newPlayer = new Player(name);
		if(currentPlayer == 1){
			
			turnOrder[currentPlayer] = newPlayer;
			view.getNextplayer(++currentPlayer);
		} else {
			int samePlayer = 0;
			for(int j = 1; j < currentPlayer; j++){
				if(newPlayer.getName().toLowerCase().equals(turnOrder[j].getName().toLowerCase()))
					samePlayer = j;
			}
			if(samePlayer > 0){
				view.notifyBadName("Name already taken!! Please choose another.");
			} else {
				
				turnOrder[currentPlayer] = newPlayer;
				if(currentPlayer == numPlayers){
					currentPlayer = 1;
					view.getTurnOrder(turnOrder);
				} else {
					view.getNextplayer(++currentPlayer);
				}
			}
			
		}
		
	}
	
	public void randomizeTurnOrder(){
		int first = (int) (((Math.random()*numPlayers) + 1));
		setTurnOrder(turnOrder[first].getName());
	}
	
	public void setTurnOrder(String name){
		int i = findPlayerPosition(name);
		if(i > 0){
			makeFirstPlayer(i);
		}
		else{
			System.out.println("Error finding player when setting turn order");
		}
		for(int j = 1; j <= numPlayers; j++){
			view.addPlayerCard(turnOrder[j], j);
		}
		currentPlayer = 1;
		view.prepForStart();
		//readySetGo?
		view.updateTurnName(turnOrder[currentPlayer].getName());
	}
	
	private int findPlayerPosition(String name){
		for(int j = 1; j <= numPlayers; j++){
			if(turnOrder[j].getName().toLowerCase().equals(name.toLowerCase()))
				return j;
		}
		return 0;
	}
	
	private void makeFirstPlayer(int i){
		Player[] temp = new Player[numPlayers+1];
		
		if(i != 1){
			int counter = 1;
			for(int j = i; j <= numPlayers; j++){
				temp[counter] = turnOrder[j];
				counter++;
			}
			for(int k = 1; k < i; k++){
				temp[counter] = turnOrder[k];
				counter++;
			}
			for(int p = 1; p <= numPlayers; p++){
				turnOrder[p] = temp[p];
			}
			
		}
	}
	
	public void nextPlayer(){
		if(currentPlayer == numPlayers)
			currentPlayer = 1;
		else
			currentPlayer++;
		view.updateTurnName(turnOrder[currentPlayer].getName());
	}
	
	public int getNumWins(){
		return BoardKeeper.getNumWins();
	}
	
	public int getDim(){
		return BoardKeeper.getDim();
	}
	
	public void processMove(int column){
		int row = BoardKeeper.addMove(column, currentPlayer);
		if(row >= 0){
			view.addToken(column, row, currentPlayer);
			
			int isWinner = BoardKeeper.isWinner(currentPlayer);
			if(isWinner == 1 || isWinner == 2){
				view.stopGame();
				if(isWinner == 1){
					turnOrder[currentPlayer].incrementScore();
					view.notifyWinner();
					view.updateScore(currentPlayer);
				} else {
					view.notifyCatGame();
				} 
				// askForNewGame();
			} else {
				nextPlayer();
			}
			
		} else {
				/// no action on invalid move, play sound?
		} 
	
	}
	
	public String printBoard(){
		return BoardKeeper.getBoard().toString();
	}
	
	
	
	

}
