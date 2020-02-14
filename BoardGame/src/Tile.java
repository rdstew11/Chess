
public class Tile {
	private Piece piece;
	private boolean isEmpty;
	
	/**
	 * Constructs a tile object which contains the given piece
	 * @param piece the piece in which the tile should contain
	 */
	public Tile(Piece piece)
	{
		this.piece = piece;
		isEmpty = false;
	}
	
	public Tile(String n)
	{
		piece = new Piece(n);
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
	
	public boolean checkEmpty() 
	{
		return isEmpty;
	}
	
	public void setPiece(Piece piece)
	{
		this.piece = piece;
		isEmpty = false;
	}
	
	public String toString()
	{
		if(piece.getType().isEmpty())
		{
			return("[ ]");
		}
		else
		{
			return("[" + piece.getType().substring(0,1) + "]");
		}
	}
}
