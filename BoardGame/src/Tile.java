
public class Tile {
	Piece piece;
	
	/**
	 * Constructs a tile object which contains the given piece
	 * @param piece the piece in which the tile should contain
	 */
	public Tile(Piece piece)
	{
		this.piece = piece;
	}
	
	public Tile(String n)
	{
		piece = new Piece(n);
	}
	
	public Tile()
	{
		piece = new Piece();
	}
	
	public Piece getPiece()
	{
		return piece;
	}
	
	public void setPiece(Piece piece)
	{
		this.piece = piece;
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
