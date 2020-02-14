import java.util.ArrayList;

public class Board {
	private ArrayList<Tile> board = new ArrayList<Tile>();
	private ArrayList<Piece> deadPieces = new ArrayList<>();
	private ArrayList<Piece> alivePieces = new ArrayList<>();
	private int col;
	private int row;

	
	/**
	 * Creates a square board
	 * @param col number of columns
	 * @param row number of rows
	 */
	public Board(int col, int row)
	{
		this.col = col; //also the number of elements in each row
		this.row = row; //the number of rows in board
		for(int i = 0; i < this.row; i++)
		{
			for(int j = 0; j < this.col; j++)
			{
				Tile t = new Tile();
				board.add(t);
			}
		}
	}
	
	public Tile getTile(int x, int y)
	{
		if(checkInbounds(x,y))
		{
			int index = y * x + x;
			return(board.get(index));
		}
		else
		{
			return(null);
		}
	}
	
	private ArrayList<Tile> getNE(int x, int y)
	{
		ArrayList<Tile> ne = new ArrayList<Tile>();
		while(checkInbounds(x,y))
		{
			ne.add(getTile(x,y));
			x++;
			y++;
		}
		return(ne);
	}
	
	private ArrayList<Tile> getNW(int x, int y)
	{
		ArrayList<Tile> nw = new ArrayList<Tile>();
		while(checkInbounds(x,y))
		{
			nw.add(getTile(x,y));
			x--;
			y++;
		}
		return nw;
	}
	
	private ArrayList<Tile> getSE(int x, int y)
	{
		ArrayList<Tile> se = new ArrayList<Tile>();
		while(checkInbounds(x,y))
		{
			se.add(getTile(x,y));
			x++;
			y--;
		}
		return se;
	}
	
	private ArrayList<Tile> getSW(int x, int y)
	{
		ArrayList<Tile> sw = new ArrayList<Tile>();
		while(checkInbounds(x,y))
		{
			sw.add(getTile(x,y));
			x--;
			y--;
		}
		return sw;
	}
	
	private ArrayList<Tile> getDiagonals(int x, int y)
	{
		ArrayList<Tile> diagonals = new ArrayList<Tile>();
		diagonals.addAll(getNE(x,y));
		diagonals.addAll(getNW(x,y));
		diagonals.addAll(getSE(x,y));
		diagonals.addAll(getSW(x,y));
		return diagonals;
	}
	
	private ArrayList<Tile> getN(int x, int y)
	{
		ArrayList<Tile> n = new ArrayList<>();
		while(checkInbounds(x,y))
		{
			n.add(getTile(x,y));
			y++;
		}
		return n;
	}
	
	private ArrayList<Tile> getS(int x, int y)
	{
		ArrayList<Tile> s = new ArrayList<>();
		while(checkInbounds(x,y))
		{
			s.add(getTile(x,y));
			y--;
		}
		return s;
	}
	
	private ArrayList<Tile> getE(int x, int y)
	{
		ArrayList<Tile> e = new ArrayList<>();
		while(checkInbounds(x,y))
		{
			e.add(getTile(x,y));
			x++;
		}
		return e;
	}
	
	private ArrayList<Tile> getW(int x, int y)
	{
		ArrayList<Tile> w = new ArrayList<>();
		while(checkInbounds(x,y))
		{
			w.add(getTile(x,y));
			x--;
		}
		return w;
	}

	public ArrayList<Tile> getDirection(String dir, int x, int y)
	{
		ArrayList<Tile> direction = new ArrayList<>();
		dir = dir.toLowerCase();
		
		if(dir.equals("n")) 
		{
			direction.addAll(getN(x,y));
		}
		else if(dir.equals("s"))
		{
			direction.addAll(getS(x,y));
		}
		else if(dir.equals("e"))
		{
			direction.addAll(getE(x,y));
		}
		else if(dir.equals("w"))
		{
			direction.addAll(getW(x,y));
		}
		else if(dir.equals("ne"))
		{
			direction.addAll(getNE(x,y));
		}
		else if(dir.equals("nw"))
		{
			direction.addAll(getNW(x,y));
		}
		else if(dir.equals("se"))
		{
			direction.addAll(getSE(x,y));
		}
		else if(dir.equals("sw"))
		{
			direction.addAll(getSW(x,y));
		}
		else if(dir.equals("d"))
		{
			direction.addAll(getDiagonals(x,y));
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
	
	public ArrayList<Tile> getBoard()
	{
		return board;
	}
	
	public ArrayList<Piece> getDeadPieces()
	{
		return deadPieces;
	}
	
	public String toString()
	{
		String out = "";
		for(int i = row - 1; i >= 0; i--)
		{
			for(int j = 0; j < col; j++)
			{
				out = out + getTile(j,i);
			}
			out = out + "\n";
		}
		return out;
	}
}
