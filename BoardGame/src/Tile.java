
public class Tile {
	private Piece piece;
	private boolean isEmpty;
	private int x;
	private int y;
	private String name;
	/**
	 * Constructs a tile object which contains the given piece
	 * @param piece the piece in which the tile should contain
	 */
	public Tile(Piece piece)
	{
		this.piece = piece;
		isEmpty = false;
	}
	
	public Tile()
	{
		piece = null;
		isEmpty = true;
	}
	
	public void removePiece()
	{
		piece = null;
		isEmpty = true;
	}
	
	public Piece getPiece()
	{
		return piece;
	}
	
	public boolean isEmpty() 
	{
		return isEmpty;
	}
	
	public void setPiece(Piece piece)
	{
		this.piece = piece;
		isEmpty = false;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public int getX()
	{
		return x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String toString()
	{
		if(piece == null)
		{
			return("[  ]");
		}
		else
		{
			return("[" + piece.getIdentifier() + "]");
		}
	}
}
