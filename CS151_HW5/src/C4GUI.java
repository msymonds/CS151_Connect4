
/*
 *  Connect 4 Program
 *  CS151, section 4
 *  
 *  Michael Symonds - 011078574
 *  Michael Chen - 007230223
 *  
 */

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class C4GUI extends Application{
	
	// access and data assets
	private C4Controller controller;
	private Color[] colorPicker = new Color[] {null, Color.RED, Color.YELLOW};
    private String[] ColorName = new String[] {null, "RED", "YELLOW"};
	private Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
	private double gameScreenHeight = primaryScreenBounds.getHeight()-50;
	private double gameScreenWidth = ((gameScreenHeight/5) * 4);
	
	// Main Panel Assets
	private BorderPane mainPanel = new BorderPane();
    
	// GameBoard Assets
	private GridPane grid = new GridPane();
    
    // Establish Player Assets
    private VBox setupPane = new VBox();
    private Label intro = new Label("");
    private TextField nameEntryTF = new TextField("");
    private Button playerNameConfirmB = new Button("");
    private Label badNameL = new Label("");
    
    // Establish Turn order Assets
    private VBox turnOrderPane = new VBox();
    
    // status pane Assets
    private VBox statusPane = new VBox();
    private HBox playerCardPane = new HBox();
    private VBox status = new VBox();
    private Label nameL = new Label("");
    private Label turnL = new Label("Turn!!");
    
    public C4GUI(C4Controller controller) {
        this.controller = controller;
        controller.attachView(this);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Welcome to Connect " + controller.getNumWins() + "!!");
		
		// Title Panel
		HBox titlePane = new HBox();
		titlePane.setAlignment(Pos.CENTER);
		titlePane.setPrefHeight((gameScreenHeight - gameScreenWidth) / 5);
		Label titleL = new Label("Let's Play Connect " + controller.getNumWins() + "!!!");
		titleL.setAlignment(Pos.CENTER);
		titlePane.getChildren().add(titleL);
		
		// game grid
		buildGameGrid();
		
        // Establish Player Panel
        intro.setText("Player 1\nPlease enter your name below.");
        intro.setAlignment(Pos.CENTER);
        intro.setTextAlignment(TextAlignment.CENTER);
        nameEntryTF.setText("Player 1");
        nameEntryTF.setAlignment(Pos.CENTER);
        nameEntryTF.setMaxWidth(150);
        playerNameConfirmB.setText("Confirm");
        playerNameConfirmB.setAlignment(Pos.CENTER);
        playerNameConfirmB.setOnMouseReleased(e -> {
        	String player = nameEntryTF.getText();
        	if(player.equals("")){
        		notifyBadName("You need to choose a name!");
        		nameEntryTF.requestFocus();
        		nameEntryTF.setText("Player " + controller.getCurrentPlayer());
        		nameEntryTF.selectAll();
        	} else {
        	controller.establishPlayer(player);
        	}
        	});
        badNameL.setTextFill(Color.RED);
        badNameL.setAlignment(Pos.CENTER);
        setupPane.setAlignment(Pos.CENTER);
        setupPane.getChildren().addAll(intro, nameEntryTF, playerNameConfirmB, badNameL);
        
        // build Status Panel
        status.getChildren().addAll(nameL, turnL);
        status.setAlignment(Pos.CENTER);
        status.setVisible(false);
        playerCardPane.setAlignment(Pos.CENTER);
        playerCardPane.setSpacing(50);
        statusPane.setAlignment(Pos.CENTER);
        statusPane.setPrefHeight(((gameScreenHeight - gameScreenWidth) / 5)*4);
        statusPane.getChildren().addAll(playerCardPane, status);
        
        // build main panel
        mainPanel.setTop(titlePane);
        mainPanel.setCenter(setupPane);
        mainPanel.setBottom(statusPane); 
        Scene scene = new Scene(mainPanel, 500, 600);
        scene.getStylesheets().add(Connect4.class.getResource("resources/game.css").toExternalForm());
        primaryStage.setHeight(gameScreenHeight);
        primaryStage.setWidth(gameScreenWidth);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
 		
	} // start
	
	
	public void buildGameGrid(){
		int dim = controller.getDim();
		grid.setPrefSize(gameScreenWidth - 50, gameScreenWidth - 50);
		grid.setAlignment(Pos.CENTER);
        grid.getStyleClass().add("game-grid");
        double squareSize = (grid.getPrefWidth()/dim)-1;
        for(int i = 0; i < dim; i++) {
            RowConstraints row = new RowConstraints(squareSize > 0 ? squareSize : 1);
            grid.getRowConstraints().add(row);
        }
        
        for(int i = 0; i < controller.getDim(); i++) {
            ColumnConstraints column = new ColumnConstraints(squareSize > 0 ? squareSize : 1);
            grid.getColumnConstraints().add(column);
        }

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                myPane pane = new myPane(i, j);
                pane.setOnMouseReleased(e -> {
                	int column = pane.getColumn();
                    controller.processMove(column);
                });
                pane.getStyleClass().add("game-grid-cell");
                if (i == 0) {
                    pane.getStyleClass().add("first-column");
                }
                if (j == 0) {
                    pane.getStyleClass().add("first-row");
                }
                grid.add(pane, i, j);
                GridPane.setHalignment(pane, HPos.CENTER);
                GridPane.setValignment(pane, VPos.CENTER);
            }
        }
        
	}
	
	public void getNextplayer(int currentPlayer){
		badNameL.setText("");
		intro.setText("Player " + currentPlayer + "\nPlease enter your name below.\n" +
		"NOTE: Name must be unique from other players");
		nameEntryTF.requestFocus();
		nameEntryTF.clear();
        nameEntryTF.setText("Player " + currentPlayer);
        nameEntryTF.selectAll();
        
	}
	
	public void notifyBadName(String s){
		badNameL.setText(s);
	}
	
	public void getTurnOrder(Player[] players){
		HBox turnOrder = new HBox();
		turnOrder.setAlignment(Pos.CENTER);
		Label nextStepL = new Label("Who wants to go first??");
		nextStepL.setAlignment(Pos.CENTER);
		nextStepL.setTextAlignment(TextAlignment.CENTER);
		for(int i = 1; i < players.length; i++){
			if(i == (((players.length-1)/2)+1)){
				Button random = new Button("RANDOM!!");
				random.setAlignment(Pos.CENTER);
				random.setOnMouseReleased(e -> {
		        	controller.randomizeTurnOrder();
		        	});
				turnOrder.getChildren().add(random);
			}
			Button b = new Button(players[i].getName());
			b.setAlignment(Pos.CENTER);
			b.setOnMouseReleased(e -> {
	        	controller.setTurnOrder(b.getText());
	        	});
			turnOrder.getChildren().add(b);
		}
		turnOrderPane.setAlignment(Pos.CENTER);
		turnOrderPane.getChildren().addAll(nextStepL, turnOrder);
		mainPanel.setCenter(turnOrderPane);
	}
	
	public void updateTurnName(String name){
		nameL.setText(name + "'s");
	}
	
	public void addPlayerCard(Player player, int playerNumber){
        VBox playerCard = new VBox();
        playerCard.setAlignment(Pos.CENTER);
        Label lPlayerL = new Label("Player: " + ColorName[playerNumber]);
	    lPlayerL.setAlignment(Pos.CENTER);
	    Label lNameL = new Label(player.getName());
	    lNameL.setAlignment(Pos.CENTER);
	    Label lScoreL = new Label();
	    lScoreL.setText("" + player.getScore());
	    lScoreL.setAlignment(Pos.CENTER);
	    playerCard.setPrefWidth(Math.max((lNameL.getText().length()*5), (lPlayerL.getText().length()*5)) + 20);
	    playerCard.getChildren().addAll(lPlayerL, lNameL, lScoreL);
	    playerCardPane.getChildren().add(playerCard);	
	}
	
	public void prepForStart(){
		mainPanel.setCenter(grid);
		status.setVisible(true);
	}
	
	public void addToken(int column, int row, int player){
		Token token = new Token(column, row);
		token.setRadius((((grid.getPrefWidth()/controller.getDim())-(60/controller.getDim()))/2));
		token.setFill(colorPicker[player]);
		token.setOnMouseReleased(e -> {
        	int myColumn = token.getColumn();
            controller.processMove(myColumn);
        });
		grid.add(token, column, row);
		GridPane.setHalignment(token, HPos.CENTER);
        GridPane.setValignment(token, VPos.CENTER);
	}
	
	public void stopGame(){
		grid.setDisable(true);
	}
	
	
	public void notifyWinner(){
		nameL.setText(nameL.getText().substring(0,(nameL.getText().length()-2) ));
		turnL.setText("WINS!!");
	}
	
	public void updateScore(int winner){
		VBox playerCard = (VBox)playerCardPane.getChildren().get(winner-1);
		Label score = (Label)playerCard.getChildren().get(2);
		String scoreS = score.getText();
		int s = Integer.parseInt(scoreS);
		s++;
		score.setText("" + s);
	}
	
	public void notifyCatGame(){
		nameL.setText("");
		turnL.setText("Cat's Game\nGame Over!");
	}

} // GUI



class myPane extends Pane{
	int row = 0;
	int column = 0;
	
	public myPane(int column, int row){
		this.row = row;
		this.column = column;
		
	}
	
	public int getColumn(){
		return column;
	}
	
	public int getRow(){
		return row;
	}
}
