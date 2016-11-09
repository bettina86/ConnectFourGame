package ConnectFourGame;


import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;


public class BoardPiece {

	int row;
	int column;
	Ellipse ellipse;
	BoardPiece N;
	BoardPiece NE;
	BoardPiece E;
	BoardPiece SE;
	BoardPiece S;
	BoardPiece SW;
	BoardPiece W;
	BoardPiece NW;
	boolean GameOver;

	public BoardPiece(int row, int column) {
		this.row = row;
		this.column = column;
		GameOver = false;
		ellipse = new Ellipse(row, column, 40, 40);
		//ellipse.setCenterX(row);
		//ellipse.setCenterY(column);
		ellipse.setRadiusX(40);
		ellipse.setRadiusY(40);
		ellipse.setFill(Color.WHITE);

	}

	public void setPlayable(boolean b)
	{
		GameOver = b;
	}
	public boolean getPlayable()
	{
		return GameOver;
	}
	public Ellipse getEllipse() {
		return ellipse;
	}
	
	public Paint getColor(){
		return ellipse.getFill();
	}
	
	public void setColor(Paint color){
		ellipse.setFill(color);
	}
	public int getX()
	{
		return row;
	}
	public int getY()
	{
		return column;
	}
}