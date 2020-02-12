public class Board {
	private Tile[][] board;
	int stride; //the amount of steps to reach the next row, should = x
	int x;
	int y;
	
	/**
	 * Creates a square board
	 * @param x
	 * @param y
	 */
	public Board(int y, int x)
	{
		this.x = x;
		this.y = y;
		stride = x;
		board = new Tile[y][x];
		for(int i = 0; i < y; i++)
		{
			System.out.println("adding a row");
			for(int j = 0; j < x; j++)
			{
				System.out.println("add a tile");
				board[i][j] = new Tile();
			}
		}
	}
	
	public Tile[][] getBoard()
	{
		return board;
	}
	
	public String toString()
	{
		String out = "";
		
		for(int i = 0; i < y; i++)
		{
			for(int j = 0; j < x; j++)
			{
				out = out + board[i][j];
			}
			out = out + "\n";
		}
		return out;
	}
}
