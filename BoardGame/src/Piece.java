
public class Piece {
	private String type = "";
	private String[] moveList;
	private String team;
	
	/**
	 * Creates a basic piece object
	 * @param type what kind of piece it is (like a rook in chess).
	 * @param color the team the piece belongs to 
	 */
	public Piece(String type, String team)
	{
		this.type = type;
		this.team = team;
	}
	
	public Piece()
	{
		
	}
	
	public String getTeam()
	{
		return team;
	}
	
	public void setTeam(String team)
	{
		this.team = team;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String type) 
	{
		this.type = type;
	}
	
	public String[] getMoveList()
	{
		return moveList;
	}
	
	public void setMoveList(String[] moveList)
	{
		this.moveList = moveList;
	}
	
	
}
