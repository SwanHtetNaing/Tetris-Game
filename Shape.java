import java.awt.Color;

public class Shape 
{
	boolean[][] body;
	Color color;
	Shape(boolean[][] body, Color color)
	{
		this.body = body;
		this.color = color;
	}
	public int getColumn()
	{
		return body[0].length;
	}
	public int getRow()
	{
		return body.length;
	}
	public boolean isBody(int row,int col)
	{
		return body[row][col]; 
	}
	public boolean isBottom(int row,int col)
	{
		if(row == getRow()-1 || body[row+1][col] == false)
			return true;
		return false;
	}
	public boolean isLeft(int row,int col)
	{
		if(col == 0 || body[row][col-1] == false)
			return true;
		return false;
	}
	public boolean isRight(int row, int col)
	{
		if(col == getColumn()-1 || body[row][col+1] == false)
			return true;
		return false;
	}
	public Shape rotate()
	{
		// covert each columns to rows
		boolean[][] temp = new boolean[getColumn()][getRow()];
		for(int row = getRow()-1, r = 0; row >= 0; row--, r++)
			for(int col = 0; col < getColumn(); col++)
			{
				temp[col][r] = body[row][col];
			}
		return new Shape(temp,color);
	}
}
