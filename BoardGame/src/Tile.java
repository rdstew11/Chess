
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
	
	/**
	 * Constructs a tile object which contains an empty piece
	 */
	public Tile()
	{
		piece = new Piece();
	}
}
