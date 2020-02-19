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
				Tile t = new Tile();
				t.setX(j);
				t.setY(i);
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
		while(checkInbounds(x,y))
		{
			x++;
			y++;
			ne.add(getTile(x,y));
		}
		return(ne);
	}
	
	public ArrayList<Tile> getNW(Tile tile)
	{
		int x = tile.getX();
		int y = tile.getY();
		ArrayList<Tile> nw = new ArrayList<Tile>();
		while(checkInbounds(x,y))
		{
			x--;
			y++;
			nw.add(getTile(x,y));
		}
		return nw;
	}
	
	public ArrayList<Tile> getSE(Tile tile)
	{
		int x = tile.getX();
		int y = tile.getY();
		ArrayList<Tile> se = new ArrayList<Tile>();
		while(checkInbounds(x,y))
		{
			x++;
			y--;
			se.add(getTile(x,y));
		}
		return se;
	}
	
	public ArrayList<Tile> getSW(Tile tile)
	{
		int x = tile.getX();
		int y = tile.getY();
		ArrayList<Tile> sw = new ArrayList<Tile>();
		while(checkInbounds(x,y))
		{
			x--;
			y--;
			sw.add(getTile(x,y));
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
		while(checkInbounds(x,y))
		{
			y++;
			n.add(getTile(x,y));
		}
		return n;
	}
	
	public ArrayList<Tile> getS(Tile tile)
	{
		int x = tile.getX();
		int y = tile.getY();
		ArrayList<Tile> s = new ArrayList<>();
		while(checkInbounds(x,y))
		{
			y--;
			s.add(getTile(x,y));
		}
		return s;
	}
	
	public ArrayList<Tile> getE(Tile tile)
	{
		int x = tile.getX();
		int y = tile.getY();
		ArrayList<Tile> e = new ArrayList<>();
		while(checkInbounds(x,y))
		{
			x++;
			e.add(getTile(x,y));
		}
		return e;
	}
	
	public ArrayList<Tile> getW(Tile tile)
	{
		int x = tile.getX();
		int y = tile.getY();
		ArrayList<Tile> w = new ArrayList<>();
		while(checkInbounds(x,y))
		{
			x--;
			w.add(getTile(x,y));
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
		int x = tile.getX();
		int y = tile.getY();
		dir = dir.toLowerCase();
		
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
					direction.add(getN(tile).get(0));
				}
				else if(tile.getPiece().getTeam().getForward() < 0)
				{
					direction.add(getS(tile).get(0));
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
	
	public boolean checkInbounds(Tile tile)
	{
		return checkInbounds(tile.getX(), tile.getY());
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
