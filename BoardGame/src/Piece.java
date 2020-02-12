
public class Piece {
	String type;
	
	/**
	 * Creates a basic piece object
	 * @param type what kind of piece it is (like a rook in chess). Will always be stored as a String.toUpperCase()
	 */
	public Piece(String type)
	{
		this.type = type.toUpperCase(); //upper case so it won't conflict with an empty piece
	}
	
	/**
	 * Constructs an empty piece object
	 * type = "empty" - all lower case to not interfere with a nonempty type
	 */
	public Piece()
	{
		type = "empty"; //lower case so it won't conflict with a real piece
	}
}
