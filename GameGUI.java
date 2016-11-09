package ConnectFourGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ConnectFourGame.RectangleSubclass;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.util.Duration;

public class GameGUI extends Application
{	
 public static void main(String[] args) {
		 int size = Integer.parseInt(args[0]);
		 int numberOfPieces = Integer.parseInt(args[1]);
		 Scanner in = new Scanner(System.in);
		 while(size <= 0){
			 System.out.println("Please enter a size greater than 0");
			 System.out.print("Board size: ");
			 size = in.nextInt();
		 }
		 
		 while(numberOfPieces <= 0){
			 System.out.println("Please enter number of pieces greater than 0");
			 System.out.print("Number of Pieces: ");
			 numberOfPieces = in.nextInt();
			 
		 }
		 
		 while(numberOfPieces > size){
			 System.out.println("Please enter number of pieces less than the size");
			 System.out.println("Board size: ");
			 size = in.nextInt();
			 System.out.println("Number of Pieces");
			 numberOfPieces = in.nextInt();
		 }
		 
		 Observer.o.setSize(size);
		 Observer.o.setPiecesNumber(numberOfPieces);
	     Application.launch(args);
	    }
	 
	 private Parent createContent()
	 {
		 Observer o = Observer.getObserver();
		 int size = o.getSize();
		 Pane origin = new Pane();
		 Pane root = new GridPane();						//Refers to the scene which uses grid
		 ScrollPane sp = new ScrollPane();
		sp.setContent(root);
 if(Observer.o.getSize()>7)
		 {
			 sp.setPrefSize(700, 700);			 			 
		 }
sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		 sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		 sp.setFitToHeight(true);
		 sp.setFitToWidth(true);
		 origin.getChildren().add(sp);
		 origin.getChildren().add(root);
		 Shape gridShape = makeGrid(size);
		 
		 Button b = new Button("Reset");
		 
		 BoardPiece[][] board = setUpPieces(size);
		 for (int i= 0; i < board.length; i++)
		 {
			 VBox v = new VBox(5);
			 v.setPadding(new Insets(20, 10, 10, 20 + (i*85)));
			 for (int j = 0; j < board[i].length; j++)
			 {
				 v.getChildren().add(board[i][j].getEllipse());
			 }
			 root.getChildren().add(v);
		 }
		 boolean GameOver = false;
		 TextField text = new TextField();
		 
		 b.setOnAction(new EventHandler<ActionEvent>(){
			 @Override
			 public void handle(ActionEvent event){
				 //board = setUpPieces(size);
				 //newBoard(board, v, root);
				 reset(board, size, text, GameOver);
				 //o.setRestarted(true);
				 //makeColumns(size, board, text);
			 }
		 });
		 text.setText("Welcome to Connect 4! Click on any column to begin!");
		 text.setFont(Font.font ("Verdana", 18));
		 text.setStyle("-fx-text-inner-color: black");
		 text.setEditable(false);
		 text.setTranslateY((size) * 42);
		 b.setTranslateY(size * 47);
		 b.setTranslateX(size * 42);
		 text.setAlignment(Pos.CENTER);
		 VBox v2 = new VBox(5);
		 v2.setPadding(new Insets(size * 46, 10, 10, 10));
		 v2.getChildren().add(text);
		 root.getChildren().addAll(gridShape,v2,text, b);
		 root.getChildren().addAll(makeColumns(size, board, text));		//Draws the data in the columns
		 origin.autosize();
		 return origin;	
	 }
	 
	public void reset(BoardPiece[][] board, int size, TextField text, boolean go)
	{
		go = false;
		Observer o = Observer.getObserver();
		String name = o.getCurrentPlayerName();
		o.setGameOver(false);
		if (name.equals("Player 2"))
		{
			o.sendPlayerInfo();
		}
		for (int i = 0; i < size; i++)
		 {
			 for(int j = 0; j < size; j++)
			 {
				 BoardPiece p = board[i][j];
				 p.setColor(Color.WHITE);
				 p.setPlayable(true);
				 board[i][j] = p;
			 }
		 }
		text.setStyle("-fx-text-inner-color: red");
		text.setText("Game reset. Player 1 start");
		
	}
	 /*public void newBoard(BoardPiece[][] board, VBox v, Pane root)
	 {
		 for (int i= 0; i < board.length; i++)
		 {
			
			 v.setPadding(new Insets(20, 10, 10, 20 + (i*85)));
			 for (int j = 0; j < board[i].length; j++)
			 {
				 v.getChildren().add(board[i][j].getEllipse());
			 }
			 root.getChildren().add(v);
		 }
	 }*/
	 private Shape makeGrid(int size){
		 // makes the grid
			 Shape shape = new Rectangle((size+1)*80,(size+1)*80 + 50);	//Creates the screen of the game
			 
			 for(int i = 0; i< size; i++)							//For each row
			 {
				 for(int j = 0; j<size;j++)						//For each column
				 {
					 //Creates the circle
					 Circle circle = new Circle(Observer.o.getPieceSize()/2, Observer.o.getPieceSize()/2, Observer.o.getPieceSize()/2);
					 
					 circle.setTranslateX(j*(Observer.o.getPieceSize()+5)+Observer.o.getPieceSize()/4);
					 
					 circle.setTranslateY(i*(Observer.o.getPieceSize()+5)+Observer.o.getPieceSize()/4);

					 //Cuts out a circle from the present shape
					 shape = shape.subtract(shape, circle);
				 }
			 }
			 shape.setFill(Color.BLACK);							//Changes color of shape 
			 return shape;
	 }
	 
	 public BoardPiece[][] setUpPieces(int size)
	 {
		 Observer o = Observer.getObserver();
		 BoardPiece[][] board = new BoardPiece[size][size];
		 for (int i = 0; i < size; i++)
		 {
			 for(int j = 0; j < size; j++)
			 {
				 BoardPiece p = o.getPieces( 25 + (j* 45), 25 + (i*45));
				 p.setPlayable(true);
				 board[i][j] = p;
			 }
		 }
		 addNeighbors(board);
		 return board;
	 }
	 
	 private void addNeighbors(BoardPiece[][] board) {
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					BoardPiece p = board[i][j];
					if (j - 1 >= 0)
						p.N = board[i][j - 1];
					if (j - 1 >= 0 && i + 1 < board.length)
						p.NE = board[i + 1][j - 1];
					if (i + 1 < board.length)
						p.E = board[i + 1][j];
					if (j + 1 < board.length && i + 1 < board.length)
						p.SE = board[i + 1][j + 1];
					if (j + 1 < board.length)
						p.S = board[i][j + 1];
					if (j + 1 < board.length && i - 1 >= 0)
						p.SW = board[i - 1][j + 1];
					if (i - 1 >= 0)
						p.W = board[i - 1][j];
					if (j - 1 >= 0 && i - 1 >= 0)
						p.NW = board[i - 1][j - 1];
				}
			}
		}

	 // makes the columns
	 private List<Rectangle> makeColumns(int size, BoardPiece[][] board, TextField text)
	 {
		 List<Rectangle> list = new ArrayList<>();
		 for(int i=0; i<size;i++)
		 {
			 RectangleSubclass rect = new RectangleSubclass(Observer.o.getPieceSize(),(size+1)*Observer.o.getPieceSize(), i);
			 rect.setTranslateX(i*(Observer.o.getPieceSize()+5)+ Observer.o.getPieceSize()/4);
			 rect.setFill(Color.TRANSPARENT);
			 rect.setOnMouseEntered(e -> rect.setFill(Color.rgb(200, 200, 50,0.3)));
			 rect.setOnMouseExited(e -> rect.setFill(Color.TRANSPARENT));
			 rect.setOnMouseClicked(e -> playPiece(rect, board, text));
			 list.add(rect);
		 }
		 return list;
	 }
	 
	 private void playPiece(RectangleSubclass rect, BoardPiece[][] board, TextField text)
	 {
		 Observer o = Observer.getObserver();
		 int col = rect.getColumn();
		 int i = 0;
		 boolean go = false;
		 BoardPiece previous = board[col][i];
		 String[] temp = new String[2];
		 
		 if(previous.getPlayable())
		 {
			 go = true;
			 
		 }
		if (go && !o.getGameOver())
		{
			 String[] playerinfo = o.sendPlayerInfo();
			 temp = playerinfo;
			 Color c;
			 String message = playerinfo[0];
			 if (playerinfo[1].equals("red"))
			 {
				 c = Color.RED;
				 text.setStyle("-fx-text-inner-color: blue");
			 }
			 else
			 {
				 c = Color.BLUE;
				 text.setStyle("-fx-text-inner-color: red");
			 }
			 text.setText(message + ", it is your turn to play your piece");
			 previous.setColor(c);
			 previous.setPlayable(false);
			 while (i < board.length-1)
			 {
				 BoardPiece current = board[col][i+1];
				 if(current.getColor().equals(Color.WHITE))
				 {
					 current.setColor(c); 
					 current.setPlayable(false);
				 }
				 else
				 {
					 break;
				 }
				 previous.setColor(Color.WHITE);
				 previous.setPlayable(true);
				 previous = current;
				 i++;
				TranslateTransition animation = new TranslateTransition(Duration.seconds(0.25), current.getEllipse());
				animation.setFromY(-(o.getPieceSize() * board.length) + o.getPieceSize() / 4);
				animation.setToY(0);
				animation.play();

			 }
			 go = previous.getPlayable();
		}
		if (checkTie(board))
		{
			text.setStyle("-fx-text-inner-color: black");
			text.setText("It is a TIE!! Click reset to start over");
		}
		if (o.getPieceCount() == 1 ||checkWinner(rect, board, i, temp)) {
			
			String[] player = Observer.o.sendPlayerInfo();
			String playername = player[0].substring(0, 8);
			if (player[1].equals("red"))
			{
				text.setStyle("-fx-text-inner-color: blue");
			}
			else
			{
				text.setStyle("-fx-text-inner-color: red");
			}
			o.setGameOver(true);
			//setGameAsOver(board);
			text.setText("Congratulations " + playername+ "!!! You Won!!!");
			
			
		}
	 }

	 public boolean checkTie(BoardPiece[][] board)
	 {
		 int size = Observer.getObserver().getSize();
		 for (int i = 0; i < size; i++)
		 {
			 BoardPiece p =  board[i][0];
			 if (p.getColor().equals(Color.WHITE))
			 {
				 return false;
			 }
		 }
		 return true;
	 }
	 
	 public void setGameAsOver(BoardPiece[][] board)
	 {
		 int size = Observer.getObserver().getSize();
		 for (int i = 0; i < size; i++)
		 {
			 for(int j = 0; j < size; j++)
			 {
				 BoardPiece p =  board[i][j];
				 p.setPlayable(false);
				 board[i][j] = p;
			 }
		 }
	 }
	 private boolean checkWinner(RectangleSubclass rect, BoardPiece[][] board, int row, String[] playerInfo) {
			boolean foundWinner = false;
			BoardPiece p = board[rect.getColumn()][row];
			Color c;
			BoardPiece savePiece;

			if (playerInfo[0] != null && playerInfo[1] != null) {
				if (playerInfo[1].equals("red")) {
					c = Color.RED;
				} else {
					c = Color.BLUE;
				}

				if (!foundWinner && p.N != null && p.N.getColor().equals(c)) {
					savePiece = theEndPiece(p, 1, c);
					foundWinner = findLengthOfPieces(savePiece, 1, c);

				}
				if (!foundWinner && p.NE != null && p.NE.getColor().equals(c)) {
					savePiece = theEndPiece(p, 2, c);
					foundWinner = findLengthOfPieces(savePiece, 2, c);
				}
				if (!foundWinner && p.E != null && p.E.getColor().equals(c)) {
					savePiece = theEndPiece(p, 3, c);
					foundWinner = findLengthOfPieces(savePiece, 3, c);
				}
				if (!foundWinner && p.SE != null && p.SE.getColor().equals(c)) {
					savePiece = theEndPiece(p, 4, c);
					foundWinner = findLengthOfPieces(savePiece, 4, c);
				}
				if (!foundWinner && p.S != null && p.S.getColor().equals(c)) {
					savePiece = theEndPiece(p, 5, c);
					foundWinner = findLengthOfPieces(savePiece, 5, c);
				}
				if (!foundWinner && p.SW != null && p.SW.getColor().equals(c)) {
					savePiece = theEndPiece(p, 6, c);
					foundWinner = findLengthOfPieces(savePiece, 6, c);
				}
				if (!foundWinner && p.W != null && p.W.getColor().equals(c)) {
					savePiece = theEndPiece(p, 7, c);
					foundWinner = findLengthOfPieces(savePiece, 7, c);
				}
				if (!foundWinner && p.NW != null && p.NW.getColor().equals(c)) {
					savePiece = theEndPiece(p, 8, c);
					foundWinner = findLengthOfPieces(savePiece, 8, c);
				}
			}

			return foundWinner;
		}

		private BoardPiece theEndPiece(BoardPiece piece, int direction, Color c) {
			BoardPiece p = piece;
			Boolean foundEndPiece = true;
			while (foundEndPiece) {
				foundEndPiece = false;
				if (direction == 1 && p.N != null && p.N.getColor().equals(c)) { // N
					foundEndPiece = true;
					p = p.N;
				}
				if (direction == 2 && p.NE != null && p.NE.getColor().equals(c)) { // NE
					foundEndPiece = true;
					p = p.NE;
				}
				if (direction == 3 && p.E != null && p.E.getColor().equals(c)) { // E
					foundEndPiece = true;
					p = p.E;
				}
				if (direction == 4 && p.SE != null && p.SE.getColor().equals(c)) { // SE
					foundEndPiece = true;
					p = p.SE;
				}
				if (direction == 5 && p.S != null && p.S.getColor().equals(c)) { // S
					foundEndPiece = true;
					p = p.S;
				}
				if (direction == 6 && p.SW != null && p.SW.getColor().equals(c)) { // SW
					foundEndPiece = true;
					p = p.SW;
				}
				if (direction == 7 && p.W != null && p.W.getColor().equals(c)) { // W
					foundEndPiece = true;
					p = p.W;
				}
				if (direction == 8 && p.NW != null && p.NW.getColor().equals(c)) { // NW
					foundEndPiece = true;
					p = p.NW;
				}
			}
			return p;
		}

		private boolean findLengthOfPieces(BoardPiece piece, int direction, Color c) {
			BoardPiece p = piece;
			Boolean isWinner = false;
			Boolean nextPiece = true;
			int counter = 1;
			while (nextPiece) {
				nextPiece = false;
				if (direction == 1 && p.S != null && p.S.getColor().equals(c)) { // S
					p = p.S;
					counter++;
					nextPiece = true;
				}
				if (direction == 2 && p.SW != null && p.SW.getColor().equals(c)) { // SW
					p = p.SW;
					counter++;
					nextPiece = true;
				}
				if (direction == 3 && p.W != null && p.W.getColor().equals(c)) { // W
					p = p.W;
					counter++;
					nextPiece = true;
				}
				if (direction == 4 && p.NW != null && p.NW.getColor().equals(c)) { // NW
					p = p.NW;
					counter++;
					nextPiece = true;
				}
				if (direction == 5 && p.N != null && p.N.getColor().equals(c)) { // N
					p = p.N;
					counter++;
					nextPiece = true;
				}
				if (direction == 6 && p.NE != null && p.NE.getColor().equals(c)) { // NE
					p = p.NE;
					counter++;
					nextPiece = true;
				}
				if (direction == 7 && p.E != null && p.E.getColor().equals(c)) { // E
					p = p.E;
					counter++;
					nextPiece = true;
				}
				if (direction == 8 && p.SE != null && p.SE.getColor().equals(c)) { // SE
					p = p.SE;
					counter++;
					nextPiece = true;
				}
			}

			if (counter >= Observer.getObserver().getPieceCount())
				isWinner = true;
			return isWinner;

		}
	
	//@Override
	 public void start(Stage stage) throws Exception  
	 {
		stage.setScene(new Scene(createContent()));
		stage.setFullScreen(false);
		stage.setTitle("CONNECT4");
		if(Observer.o.getSize()<8)
		{
			stage.sizeToScene();
		}
		stage.show();
	 }
}







