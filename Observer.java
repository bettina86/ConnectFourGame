package ConnectFourGame;

import java.awt.Color;


/**
 * used to connect the classes together.
 * @author Saajan Bariana
 *
 */
public class Observer {
	GameGUI game = new GameGUI();
	Player player1;
	Player player2;
	static Observer o = new Observer();
	private int size;
	private int numberOfPieces;
	private int boardPieceSize = 80;
	private boolean GameOver;
	private String currentPlayerName;
	private String currentPlayerColor;
	boolean restarted;
	/**
	 * initializes the Observer class to set up two players and their colors. 
	 */
	private Observer() 
	{
		
		player1 = new Player("Player 2", Color.RED, true);
		player2 = new Player("Player 1", Color.BLUE, false);
		GameOver = false;
		currentPlayerColor = "";
		currentPlayerName = "";
		restarted = false;
	}
	
	public boolean getRestarted()
	{
		return restarted;
	}
	public void setRestarted(boolean b)
	{
		restarted = b;
	}
	public void setSize(int size)
	{
		this.size = size;
	}
	
	public void setPiecesNumber(int pieces)
	{
		numberOfPieces = pieces;
	}
	
	public int getSize()
	{
		return size;
	}
	
	public int getPieceCount()
	{
		return numberOfPieces;
	}
	
	public int getPieceSize()
	{
		return boardPieceSize;
	}
	public boolean getGameOver()
	{
		return GameOver;
	}
	public String getCurrentPlayerName()
	{
		return currentPlayerName;
	}
	public String getCurrentPlayerColor()
	{
		return currentPlayerColor;
	}
	/**
	 * used to send information about the current player to the GUI to choose the correct color and name
	 * @return returns an array. Index 0 is the player's name. Index 1 is the player's color
	 */
	public String[] sendPlayerInfo()
	{
		String[] info = new String[2];
		if (player1.getTurn())
		{
			currentPlayerName = player1.getName();
			currentPlayerColor = player1.getColor();
			info[0] = player1.getName();
			info[1] = player1.getColor();
			player1.setTurn(false);
		}
		else
		{
			currentPlayerName = player2.getName();
			currentPlayerColor = player2.getColor();
			info[0] = player2.getName();
			info[1] = player2.getColor();
			player1.setTurn(true);
		}
		return info;
	}
	
	public static Observer getObserver()
	{
		return o;
	}
	public void setGameOver(boolean b)
	{
		GameOver = b;
	}
	/**
	 * used the get the board pieces and will send them to the GUI to create the game board
	 * @return
	 */
	public BoardPiece getPieces(int x, int y)
	{
		return new BoardPiece(x, y);
		//currently a shell method
	}

}
