package ConnectFourGame;
import java.awt.Color;

/**
 * Used to represent each player in the game
 * @author Saajan Bariana
 *
 */
public class Player {
	String name;
	Color color;
	boolean turn;
	/**
	 * initializes the player
	 * @param n the name of the player
	 * @param c the color of the player
	 * @param t if it is currently the player's turn
	 */
	public Player(String n, Color c, boolean t) {
		name = n;
		color = c;
		turn = t;
	}
	
	/**
	 * returns the name of the player
	 * @return
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * changes the status of the player on if it is their turn or not
	 * @param t true if it will be there turn next
	 */
	public void setTurn(boolean t)
	{
		turn = t;
	}
	
	/**
	 * checks if it is currently the player's turn
	 * @return true if it is the player's turn
	 */
	public boolean getTurn()
	{
		return turn;
	}
	
	/**
	 * gets the color of the player
	 * @return red if the color is red and blue if it is blue
	 */
	public String getColor()
	{
		if (color == Color.RED)
		{
			return "red";
		}
		else
		{
			return "blue";
		}
		
	}

}
