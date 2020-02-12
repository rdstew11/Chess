
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
	
	public Tile()
	{
		
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
		
		return("[ ]");
	}
}
