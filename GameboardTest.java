
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class GameboardTest extends JFrame implements KeyListener,ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel gamePanel, infoPanel,btnPanel,titlePanel,statusPanel,infoTopPanel, infoMidPanel,infoBottomPanel, labelPanel,labelBasePanel, nextShapePanel;
	JButton btnRestart,btnPause,btnAbout;
	JLabel lblTitle, lblStatus, lblLine,lblTime, lblHighestLine;
	int x = 5, y = 0, beforeX = 5, beforeY = 0, line = 0;
	static int highestLine = 0;
	String baseAction = "nothing";
	final int endX = 11, endY = 24;
	boolean theEnd = false, isPause = false, restart = false;
	boolean moveDown = true, moveLeft = true, moveRight = true, canRotate = true;
	boolean[][] existBody = new boolean[25][12];
	JButton[][] nextShapeBtn = new JButton[4][4];
	JButton[][] btn = new JButton[25][12];
	ShapeBank shapeBank;
	Shape shapeBody, beforeShape, nextShape;
	
	GameboardTest()
	{
		
		shapeBank = new ShapeBank();
		shapeBody = shapeBank.getBody();
		nextShape = shapeBank.getBody();
		
		btnRestart = new JButton("Restart");
		btnRestart.addKeyListener(this);
		btnRestart.setBackground(Color.LIGHT_GRAY);
		btnRestart.setForeground(Color.blue.brighter());
		btnRestart.setPreferredSize(new Dimension(300,40));
		btnRestart.addActionListener(this);
		
		btnPause = new JButton("Pause");
		btnPause.addKeyListener(this);
		btnPause.setForeground(Color.blue.brighter());
		btnPause.setBackground(Color.lightGray);
		btnPause.setPreferredSize(new Dimension(300,40));
		btnPause.addActionListener(this);
		
		btnAbout = new JButton("About");
		btnAbout.addKeyListener(this);
		btnAbout.setForeground(Color.blue.brighter());
		btnAbout.setBackground(Color.lightGray);
		btnAbout.setPreferredSize(new Dimension(300,40));
		btnAbout.addActionListener(this);
		
		lblTitle = new JLabel("Tetris Game");
		lblTitle.setFont(new Font("Times New Roman",Font.PLAIN,28));
		lblTitle.setForeground(Color.WHITE);
		
		lblLine = new JLabel("Lines  :  0");
		lblLine.setPreferredSize(new Dimension(300,30));
		lblLine.setForeground(Color.white);
		
		lblHighestLine = new JLabel("Highest Line  :  " + highestLine);
		lblHighestLine.setPreferredSize(new Dimension(300,30));
		lblHighestLine.setForeground(Color.white);
		
		lblTime = new JLabel("Times   :  coming soon");
		lblTime.setPreferredSize(new Dimension(300,30));
		lblTime.setForeground(Color.white);
		
		lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblStatus.setForeground(Color.white);
		
		// title Panel
		titlePanel = new JPanel(new FlowLayout());
		titlePanel.setBackground(Color.gray.darker());
		titlePanel.setPreferredSize(new Dimension(300,100));
		titlePanel.add(lblTitle);
		
		// button panel
		btnPanel = new JPanel(new GridLayout(3,1));
		btnPanel.setBackground(Color.gray.darker());
		btnPanel.add(btnRestart);
		btnPanel.add(btnPause);
		btnPanel.add(btnAbout);
		
		// info top panel
		infoTopPanel =  new JPanel(new FlowLayout());
		infoTopPanel.setPreferredSize(new Dimension(360,300));
		infoTopPanel.setBackground(Color.gray.darker());
		infoTopPanel.add(titlePanel);
		infoTopPanel.add(btnPanel);
		
		// next shape panel
		nextShapePanel = new JPanel(new GridLayout(4,4));
		nextShapePanel.setBackground(Color.gray.darker());
		
		// next shape button
		for(int i = 0; i < nextShapeBtn.length; i++)
			for(int j = 0; j < nextShapeBtn[0].length; j++)
				{
					nextShapeBtn[i][j] = new JButton();
					nextShapeBtn[i][j].setPreferredSize(new Dimension(30,30));
					nextShapePanel.add(nextShapeBtn[i][j]);
					nextShapeBtn[i][j].setVisible(false);
				}
				
		// info mid Panel
		infoMidPanel = new JPanel(new FlowLayout());
		infoMidPanel.setBackground(Color.gray.darker());
		infoMidPanel.add(nextShapePanel);
		
		// status panel
		statusPanel = new JPanel(new FlowLayout());
		statusPanel.setBackground(Color.gray.darker());
		statusPanel.add(lblStatus);
		
		//label panel
		labelPanel = new JPanel(new GridLayout(3,1));
		labelPanel.setBackground(Color.gray.darker());
		labelPanel.add(lblLine);
		labelPanel.add(lblHighestLine);
		labelPanel.add(lblTime);

		//label base panel
		labelBasePanel = new JPanel(new FlowLayout());
		labelBasePanel.setBackground(Color.GRAY.darker());
		labelBasePanel.add(labelPanel);
		
		// info bottom panel
		infoBottomPanel = new JPanel(new BorderLayout());
		infoBottomPanel.setPreferredSize(new Dimension(360,200));
		infoBottomPanel.setBackground(Color.gray.darker());
		infoBottomPanel.add(statusPanel,BorderLayout.NORTH);
		infoBottomPanel.add(labelBasePanel,BorderLayout.CENTER);
		
		// info panel
		infoPanel = new JPanel(new BorderLayout());
		infoPanel.setPreferredSize(new Dimension(350,700));
		infoPanel.setBackground(Color.gray.darker());
		infoPanel.add(infoTopPanel,BorderLayout.NORTH);
		infoPanel.add(infoMidPanel,BorderLayout.CENTER);
		infoPanel.add(infoBottomPanel,BorderLayout.SOUTH);
		
		for(int i=0; i<btn.length; i++)
			for(int j = 0; j < btn[0].length; j++)
			{
				btn[i][j] = new JButton();
				existBody[i][j] = false;
			}
		
		gamePanel = new JPanel(new GridLayout(25,12));
		gamePanel.setBackground(Color.GRAY.darker());
		
		Container c=getContentPane();
		c.setLayout(new GridLayout(1,2));
		for(int i=0; i < btn.length; i++)
		{
			for(int j = 0; j < btn[0].length; j++)
			{
				gamePanel.add(btn[i][j]);
				btn[i][j].setPreferredSize(new Dimension(25,25));
				btn[i][j].setBackground(Color.gray);
				//btn[i][j].setBorder(BorderFactory.createMatteBorder(-1,1,1,-1, Color.BLUE)); // top, left ,bottom , right
				btn[i][j].addKeyListener(this);
			}
		}
		
		c.add(infoPanel);
		c.add(gamePanel);
		
		pack();
		setTitle("Tetris");
		setLocationRelativeTo(null); // to show frame in centre of screen
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		show();
		automove();
	}
	
	void automove()
	{
		while(true)
		{
			displayBody(shapeBody);
			showNextShape(nextShape);
			
			// game over ?
			for(int col = 0; col <= endX; col++)
				if(existBody[0][col] == true)
				{
					theEnd = true;
					break;
				}
			if(theEnd || restart)
			{
				int confirm;
				if(theEnd) // game over?
					confirm = JOptionPane.showConfirmDialog(this,"Try Again ?","Game over", JOptionPane.YES_NO_OPTION);
				else // uer click restart ?
					confirm = JOptionPane.YES_OPTION;
				
				if(confirm == JOptionPane.YES_OPTION)
				{
					for(int i=0; i<btn.length; i++)
						for(int j = 0; j < btn[0].length; j++)
						{
							existBody[i][j] = false;
							btn[i][j].setBackground(Color.gray);
						}
					if(line > highestLine)
						highestLine = line;
					lblHighestLine.setText("Highest Line  :  " + highestLine);
					line = 0;
					lblLine.setText("Lines  :  " + line);
					theEnd = false;
					restart = false;
					moveDown = true;
					moveLeft = true; 
					moveRight = true;
					y = 0;
					x = 5;
					shapeBody = shapeBank.getBody();
					closeNextShape(nextShape);
					nextShape = shapeBank.getBody();
					continue;
				}
				else
				{
					while(true)
					{
						try
						{
							Thread.sleep(10);
						} catch (InterruptedException e)
						{
							e.printStackTrace();
						}
						if(restart)
							break;
					}
					for(int i=0; i<btn.length; i++)
						for(int j = 0; j < btn[0].length; j++)
						{
							existBody[i][j] = false;
							btn[i][j].setBackground(Color.gray);
						}
					theEnd = false;
					restart = false;
					moveDown = true;
					moveLeft = true; 
					moveRight = true;
					
					if(line > highestLine)
						highestLine = line;
					lblHighestLine.setText("Highest Line  :  " + highestLine);
					line = 0;
					lblLine.setText("Lines  :  " + line);
					y = 0;
					x = 5;
					shapeBody = shapeBank.getBody();
					closeNextShape(nextShape);
					nextShape = shapeBank.getBody();
					continue;
				}
			}
			pause();
			
			while(true)
			{
				if(isPause)
				{
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				else
					break;
			}
			
			if(y == endY+1-shapeBody.getRow()) // stop  at the base
			{
				moveDown = false;
			}
			if(moveDown)
			{
				closeBody(shapeBody);
				++y;
				baseAction = "nothing";
			}
			else
			{
				baseAction = "nothing";
				shapeBody = nextShape;
				closeNextShape(nextShape);
				nextShape = shapeBank.getBody();
				y = 0;
				x = 5;
				// delete line when all columns are red
				deleteFullLine();
			}
		}
		
	}
	
	void displayBody(Shape sB)
	{
		moveDown = true;
		moveLeft = true;
		moveRight = true;
		canRotate = true;
		Color color = sB.color;
		
		int row = sB.getRow();
		int col = sB.getColumn();

		moveDown = isBodyDown(sB);
		
		for(int i = 0, locY = y; i < row; i++, locY++)
		{
			for(int j = 0, locX = x; j < col; j++, locX++)
			{
				if(sB.isBody(i, j))
				{
					moveLeft = isBodyLeft(sB, i, j, locX, locY);
					moveRight = isBodyRight(sB, i, j, locX, locY);
					
					btn[locY][locX].setBackground(color);
					//when arrived base
					if(y == endY+1-shapeBody.getRow() || moveDown == false)
						existBody[locY][locX] = true;
				}
			}
		}
	}
	
	void closeBody(Shape sB)
	{
		moveDown = true;
		
		int row = sB.getRow();
		int col = sB.getColumn();

		moveDown = isBodyDown(sB);
		for(int i = 0, locY = y; i < row; i++, locY++)
		{
			for(int j = 0, locX = x; j < col; j++, locX++)
			{
				if(sB.isBody(i, j))
				{
					btn[locY][locX].setBackground(Color.gray);
					//when arrived base
					if(y == endY+1-shapeBody.getRow() || moveDown == false)
						existBody[locY][locX] = false;
				}
			}
		}
	}
	
	void pause()
	{
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// show next shape
	void showNextShape(Shape next)
	{
		Color color = next.color;
		int row = next.getRow();
		int col = next.getColumn();
		for(int i = 0; i< row; i++)
			for(int j = 0; j < col; j++)
				if(next.isBody(i, j))
				{
					nextShapeBtn[i][j].setBackground(color);
					nextShapeBtn[i][j].setVisible(true);
				}
	}
	
	// close next shape
	void closeNextShape(Shape next)
	{
		int row = next.getRow();
		int col = next.getColumn();
		for(int i = 0; i< row; i++)
			for(int j = 0; j < col; j++)
				if(next.isBody(i, j))
					nextShapeBtn[i][j].setVisible(false);
	}
	
	// delete line which columns are all red
	void deleteFullLine()
	{
		for(int row = endY; row >= 0; row--)
		{
			if(isAllRed(row))
			{
				deleteAnimation(row); // show deleteAnimation
				movingRowsDown(row);
				++row;
			}
		}
	}
	
	// check line which columns are all red or not
	boolean isAllRed(int rowIndex)
	{
		for(int col = 0; col <= endX; col++)
			if( existBody[rowIndex][col] == false)
				return false;
		return true;
	}
	
	// delete animation
	void deleteAnimation(int rowIndex)
	{
		++line;
		lblLine.setText("Lines     :  " + line);
		for(int col = 0; col <= endX; col++)
		{
			existBody[rowIndex][col] = false;
			btn[rowIndex][col].setBackground(Color.GREEN.darker());
			try
			{
				Thread.sleep(30);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		for(int col = endX; col >= 0; col--)
		{
			btn[rowIndex][col].setBackground(Color.GRAY);
			try
			{
				Thread.sleep(30);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	// moving down upper rows
	void movingRowsDown(int rowIndex)
	{
		for(int row = rowIndex-1; row >= 0; row--)
		{
			int colCount = -1;
			for(int col = 0; col <= endX; col++)
			{
				if(existBody[row][col])
				{
					existBody[row][col] = false;
					existBody[row+1][col] = true;
					btn[row+1][col].setBackground(btn[row][col].getBackground());
					btn[row][col].setBackground(Color.gray);
				}
				else
					++colCount;
			}
			if(colCount == endX)
				break;
		}
	}
	
	public void keyPressed(KeyEvent e) 
	{
		int key = e.getKeyCode();
		
		if(!isPause)
		{
			if(key == 37) // left arrow
			{
				if(x != 0 && moveLeft)
				{
					closeBody(shapeBody);
					--x;
					/*when arrived base
					if(y == endY+1-shapeBody.getRow() || moveDown == false)
						baseAction = "left";*/
					displayBody(shapeBody);
				}
			}
			if(key == 38) // up arrow
			{
				if(canRotate)
				{
					closeBody(shapeBody);
					
					shapeBody = shapeBody.rotate();
					int sizeY = y + shapeBody.getRow() - 1;
					int sizeX = x + shapeBody.getColumn() -1;
					
					// not to array index out of bound
					if(sizeX > endX)
						x = x - (sizeX-endX);
					
					if(sizeY > endY)
						y = y - (sizeY-endY);
					
					displayBody(shapeBody);
				}
			}
			if(key == 39) // right arrow
			{
				if(x != endX+1-shapeBody.getColumn() && moveRight)
				{
					closeBody(shapeBody);
					++x;
					/*when arrived base
					if(y == endY+1-shapeBody.getRow() || moveDown == false)
						baseAction = "right";*/
					displayBody(shapeBody);
				}
			}
			if(key == 40) // down arrow
			{
				if(y != endY+1-shapeBody.getRow() && moveDown)
				{	
					closeBody(shapeBody);
					++y;
					displayBody(shapeBody);
				}
			}
		}
	}
	public void keyReleased(KeyEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {}
	
	public boolean isBodyDown(Shape sB)
	{
		int row = sB.getRow();
		int col = sB.getColumn();
		
		for(int i = 0, locY = y; i < row; i++, locY++)
			for(int j = 0, locX = x; j < col; j++, locX++)
				if(sB.isBody(i, j) && sB.isBottom(i, j))
						if(locY != endY)
							if(existBody[locY+1][locX])
								moveDown = false;
		
		return moveDown;
	}
	public boolean isBodyLeft(Shape sB, int i, int j,int locX, int locY)
	{
		if(sB.isLeft(i, j))
		{
			if(locX == 0 || existBody[locY][locX-1] == true)
				moveLeft = false;
		}
		return moveLeft;
	}
	public boolean isBodyRight(Shape sB, int i, int j, int locX, int locY)
	{
		if(sB.isRight(i, j))
			if(locX == endX || existBody[locY][locX+1] == true)
				moveRight = false;
		return moveRight;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		// button Restart
		if(e.getSource() == btnRestart)
		{
			restart = true;
			if(isPause)
			{
				isPause = false;
				btnPause.setBackground(Color.lightGray);
			}
		}
		
		// button Pause
		if(e.getSource() == btnPause)
		{
			if(isPause)
			{
				isPause = false;
				btnPause.setBackground(Color.lightGray);
			}
			else
			{
				isPause = true;
				btnPause.setBackground(Color.cyan);
			}
		}
		
		// button about
		if(e.getSource() == btnAbout)
		{
			if(!isPause)
				isPause = true;
			String strAbout= "                                            Tetris Game\n\n" +
							 "This game is developed by using jframe of java.\n" +
							 "and it's a simple game that we play in childhood.\n" +
							 "i will be so happy if my game can give memory of your childhood back.\n\n" +
					         "Code by       :  NightHero\n" +
					         "Reference  :  sir Kyaw Myo Naing's tetris code\n" +
					         "Finish Date :  30/10/2017, 11:41 PM";
			JOptionPane.showMessageDialog(this,strAbout,"About",JOptionPane.PLAIN_MESSAGE);
			
			if(btnPause.getBackground() == Color.cyan)
				isPause = true;
			else
				isPause = false;
		}
	}
	
	public static void main(String[] args)
	{
		new GameboardTest();
	}

	
}
