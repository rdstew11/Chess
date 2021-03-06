import java.util.ArrayList;

public class Board {
	private ArrayList<Tile> board = new ArrayList<Tile>();
	private int col;
	private int row;
	private int stride;

	
	/**
	 * Creates a square board
	 * @param col number of columns
	 * @param row number of rows
	 */
	
	public Board(int col, int row)
	{
		this.col = col; //also the number of elements in each row
		this.row = row; //the number of rows in board
		stride = col;
		freshBoard();
	}
	
	/**
	 * Creates a fresh board with empty tile pieces
	 * Automatically done when a new board is constructed
	 */
	public void freshBoard()
	{
		for(int i = 0; i < this.row; i++)
		{
			for(int j = 0; j < this.col; j++)
			{
				Tile t = new Tile(j,i);
				board.add(t);
			}
		}
	}
	
	public Tile getTile(int x, int y)
	{
		if(checkInbounds(x,y))
		{
			int index = y*stride + x;
			return(board.get(index));
		}
		else
		{
			return(null);
		}
	}
	
	public ArrayList<Tile> getNE(Tile tile)
	{
		int x = tile.getX();
		int y = tile.getY();
		ArrayList<Tile> ne = new ArrayList<Tile>();
		x++;
		y++;
		while(checkInbounds(x,y))
		{
			ne.add(getTile(x,y));
			x++;
			y++;
		}
		return(ne);
	}
	
	public ArrayList<Tile> getNW(Tile tile)
	{
		int x = tile.getX();
		int y = tile.getY();
		ArrayList<Tile> nw = new ArrayList<Tile>();
		x--;
		y++;
		while(checkInbounds(x,y))
		{
			nw.add(getTile(x,y));
			x--;
			y++;
		}
		return nw;
	}
	
	public ArrayList<Tile> getSE(Tile tile)
	{
		int x = tile.getX();
		int y = tile.getY();
		ArrayList<Tile> se = new ArrayList<Tile>();
		x++;
		y--;
		while(checkInbounds(x,y))
		{
			se.add(getTile(x,y));
			x++;
			y--;
		}
		return se;
	}
	
	public ArrayList<Tile> getSW(Tile tile)
	{
		int x = tile.getX();
		int y = tile.getY();
		ArrayList<Tile> sw = new ArrayList<Tile>();
		x--;
		y--;
		while(checkInbounds(x,y))
		{
			sw.add(getTile(x,y));
			x--;
			y--;
		}
		return sw;
	}
	
	public ArrayList<Tile> getDiagonals(Tile tile)
	{
		ArrayList<Tile> diagonals = new ArrayList<Tile>();
		diagonals.addAll(getNE(tile));
		diagonals.addAll(getNW(tile));
		diagonals.addAll(getSE(tile));
		diagonals.addAll(getSW(tile));
		return diagonals;
	}
	
	public ArrayList<Tile> getN(Tile tile)
	{
		int x = tile.getX();
		int y = tile.getY();
		ArrayList<Tile> n = new ArrayList<>();
		y++;
		while(checkInbounds(x,y))
		{
			n.add(getTile(x,y));
			y++;
		}
		return n;
	}
	
	public ArrayList<Tile> getS(Tile tile)
	{
		int x = tile.getX();
		int y = tile.getY();
		ArrayList<Tile> s = new ArrayList<>();
		y--;
		while(checkInbounds(x,y))
		{
			s.add(getTile(x,y));
			y--;
		}
		return s;
	}
	
	public ArrayList<Tile> getE(Tile tile)
	{
		int x = tile.getX();
		int y = tile.getY();
		ArrayList<Tile> e = new ArrayList<>();
		x++;
		while(checkInbounds(x,y))
		{
			e.add(getTile(x,y));
			x++;
		}
		return e;
	}
	
	public ArrayList<Tile> getW(Tile tile)
	{
		int x = tile.getX();
		int y = tile.getY();
		ArrayList<Tile> w = new ArrayList<>();
		x--;
		while(checkInbounds(x,y))
		{
			w.add(getTile(x,y));
			x--;
		}
		return w;
	}
	
	public ArrayList<Tile> getDirection(String dir, int x, int y)
	{
		Tile tile = getTile(x,y);
		return getDirection(dir, tile);
	}

	public ArrayList<Tile> getDirection(String dir, Tile tile)
	{
		ArrayList<Tile> direction = new ArrayList<>();
		dir = dir.toLowerCase();
		int x = tile.getX();
		int y = tile.getY();
		
		if(dir.equals("n")) 
		{
			direction.addAll(getN(tile));
		}
		else if(dir.equals("s"))
		{
			direction.addAll(getS(tile));
		}
		else if(dir.equals("e"))
		{
			direction.addAll(getE(tile));
		}
		else if(dir.equals("w"))
		{
			direction.addAll(getW(tile));
		}
		else if(dir.equals("ne"))
		{
			direction.addAll(getNE(tile));
		}
		else if(dir.equals("nw"))
		{
			direction.addAll(getNW(tile));
		}
		else if(dir.equals("se"))
		{
			direction.addAll(getSE(tile));
		}
		else if(dir.equals("sw"))
		{
			direction.addAll(getSW(tile));
		}
		else if(dir.equals("d"))
		{
			direction.addAll(getDiagonals(tile));
		}
		else if(dir.equals("f"))
		{
			if(!tile.isEmpty()) 
			{
				if(tile.getPiece().getTeam().getForward() > 0)
				{
					direction.add(getTile(x,y+1));
				}
				else if(tile.getPiece().getTeam().getForward() < 0)
				{
					direction.add(getTile(x,y-1));
				}
			}
		}
		else if (dir.equals("a"))
		{	
			/* 		-1  0 +1
			 * X	-1  P +1
			 *		-1  0 +1
			 *		
			 *		+1 +1 +1
			 *Y		 0  P  0
			 *		-1 -1 -1
			 */		 
			
			//starts at (-1,0)
			int[] xIncrement = new int[] {-1,0,1,1,1,0,-1,-1};
			int[] yIncrement = new int[] {0,1,1,1,0,-1,-1,-1};
			
			for(int i = 0; i < 8; i++)
			{
				int newX = x + xIncrement[i];
				int newY = y + yIncrement[i];
				if(checkInbounds(newX,newY))
				{
					Tile temp = getTile(newX,newY);
					direction.add(temp);
				}
			}
		}
		
		return direction;
	}
	
	public boolean checkInbounds(int x, int y)
	{
		if(x < this.col && x >= 0 && y < this.row && y >= 0)
		{
			return(true);
		}
		else
		{
			return(false);
		}
	}
	
	
	public ArrayList<Tile> getRow(Tile tile)
	{
		return getRow(tile.getY());
	}
	public ArrayList<Tile> getRow(int y)
	{
		ArrayList<Tile> row = new ArrayList<Tile>();
		for(int i = 0; i < col; i++)
		{
			row.add(getTile(i,y));
		}
		return row;
	}
	
	public ArrayList<Tile> getColumn(int x)
	{
		ArrayList<Tile> column = new ArrayList<Tile>();
		for(int i = 0; i < row; i++)
		{
			column.add(getTile(x,i));
		}
		return column;
	}
	
	public ArrayList<Tile> getColumn(Tile tile)
	{
		return getColumn(tile.getY());
	}
	
	public ArrayList<Tile> getBoard()
	{
		return board;
	}
	
	public int getX()
	{
		return col;
	}
	
	public int getY()
	{
		return row;
	}
	
	public int getStride()
	{
		return stride;
	}
	
	public String rowToString(int y)
	{
		String output = "";
		for(int i = 0; i < col; i++)
		{
			output = output + getTile(i,y);
		}
		
		return output;
	}
	public String toString()
	{
		String out = "";
		for(int i = row - 1; i >= 0; i--)
		{
			out = out + rowToString(i) + "\n";
		}
		return out;
	}
}
