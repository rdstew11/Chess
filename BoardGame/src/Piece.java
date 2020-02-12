
public class Piece {
	String type = "";
	
	/**
	 * Creates a basic piece object
	 * @param type what kind of piece it is (like a rook in chess).
	 */
	public Piece(String type)
	{
		this.type = type;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String type) 
	{
		this.type = type;
	}
}
