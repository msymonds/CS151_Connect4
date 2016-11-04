
/*
 *  Connect 4 Program
 *  CS151, section 4
 *  
 *  Michael Symonds - 011078574
 *  Michael Chen - 007230223
 *  
 */

import javafx.application.Application;
import javafx.stage.Stage;

public class Connect4 extends Application{
	
	public static void main(String [] args) {
		if(args.length < 2){
			System.out.println("App launch requires 2 integer arguments: <boardsize> <numToWin>\n"+
					"NOTE: \t1. <boardsize> Min = 4 Max = 30   \n\t2. <boardsize> >= <numToWin> ");
			System.exit(0);
		} else {
			String boardSize = args[0];
			String numToWin = args[1];
			int bSize = 0;
			int numWin = 0;
			
			try {
				bSize = Integer.parseInt(boardSize);
				numWin = Integer.parseInt(numToWin);
				if(numWin > 3 && bSize > 3 && bSize < 31 && bSize >= numWin){
					BoardKeeper.setBoard(new Board(bSize, numWin));
					launch(args);					
				} else {
					System.out.println("(INVALID ARGUMENTS) App launch requires 2 integer arguments: <boardsize> <numToWin>\n"+
							"NOTE: \t1. <boardsize> Min = 4 Max = 30   \n\t2. <boardsize> >= <numToWin> ");
					System.exit(0);
				}
				
			} catch(NumberFormatException e){
				System.out.println("(INVALID ARGUMENTS) App launch requires 2 integer arguments: <boardsize> <numToWin>\n"+
						"NOTE: \t1. <boardsize> Min = 4 Max = 30   \n\t2. <boardsize> >= <numToWin> ");
				System.exit(0);
			}
		}
        
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		C4GUI app = new C4GUI(new C4Controller());
		app.start(primaryStage);
		
	}
	

}
