
public class Board {
	private Tile[][] board;
	private int x;
	private int y;
	
	/**
	 * Creates a square board
	 * @param col number of columns
	 * @param row number of rows
	 */
	public Board(int col, int row)
	{
		x = col; //also the number of elements in each row
		y = row; //the number of rows in board
		board = new Tile[x][y];
		for(int i = 0; i < y; i++)
		{
			for(int j = 0; j < x; j++)
			{
				board[j][i] = new Tile();
			}
		}
	}
	
	public Tile[] getNE(int x, int y)
	{
		Tile[] ne;
		
		return ne;
	}
	
	public boolean checkInbounds(int x, int y)
	{
		if(x < this.x && x >= 0 && y < this.y && y >= 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public Tile[] getRow(int y)
	{
		Tile[] row = new Tile[x];
		for(int i = 0; i < x; i++)
		{
			row[i] = board[i][y];
		}
		
		return row;
	}
	
	public Tile[] getColumn(int x)
	{
		Tile[] column = new Tile[y];
		for(int i = 0; i < y; i++)
		{
			column[i] = board[x][i];
		}
		return column;
	}
	
	public Tile[][] getBoard()
	{
		return board;
	}
	
	public String toString()
	{
		String out = "";
		for(int i = y - 1; i >= 0; i--)
		{
			for(int j = 0; j < x; j++)
			{
				out = out + board[j][i];
			}
			out = out + "\n";
		}
		return out;
	}
}
