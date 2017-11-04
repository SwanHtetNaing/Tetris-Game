import java.awt.Color;

public class ShapeBank 
{
	Shape[] shape = new Shape[7];
	boolean[][] body1 = {
			{true,true},
			{true,true}
	};
	boolean[][] body2 = {
			{true,true,true,true}
	};
	boolean[][] body3 = {
			{true,true,false},
			{false,true,true}
	};
	boolean[][] body4 = {
			{false,true,true},
			{true,true,false}
	};
	boolean[][] body5 = {
			{true,false},
			{true,true},
			{true,false}
	};
	boolean[][] body6 = {
			{false,false,true},
			{true,true,true}
	};
	boolean[][] body7 = {
			{true,false,false},
			{true,true,true}
	};
	
	ShapeBank()
	{
		shape[0] = new Shape(body1,Color.LIGHT_GRAY);
		shape[1] = new Shape(body2,Color.RED);
		shape[2] = new Shape(body3,Color.YELLOW);
		shape[3] = new Shape(body4,Color.YELLOW);
		shape[4] = new Shape(body5,Color.MAGENTA);
		shape[5] = new Shape(body6,Color.GREEN);
		shape[6] = new Shape(body7,Color.GREEN);
	}
	
	public Shape getBody()
	{
		int random = (int) (Math.random() * 7);
		return shape[random];
	}
}